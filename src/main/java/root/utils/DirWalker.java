package root.utils;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import root.entity.TempDirData;
import root.repository.TempDirDataRepository;
import stdlib.enums.HashAlgorithm;
import stdlib.utils.HashGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirWalker implements FileVisitor<Path> {

    private static final Logger logger = LoggerFactory.getLogger(DirWalker.class);

    private ArrayList<TempDirData> tempDirDataList;
    private final TempDirDataRepository tempDirDataRepository;


    public DirWalker(TempDirDataRepository tempDirDataRepository) {
        this.tempDirDataRepository = tempDirDataRepository;
        tempDirDataList = new ArrayList<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dirPath, BasicFileAttributes attrs) {

        File dir = dirPath.toFile();

        try {

            tempDirDataList.add(new TempDirData(
                    HashGenerator.generateStringHash(dir.getCanonicalPath(), HashAlgorithm.SHA256.getName()),
                    dir.getCanonicalPath(),
                    dir.lastModified()
            ));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //  Save tempDirDataList to A_TEMP_DIRS_DATA Table
        if (tempDirDataList.size() > 1000) saveList(tempDirDataList);


        return FileVisitResult.CONTINUE;
    }

    public void saveList(List<TempDirData> list) {

        if (list == null || list.isEmpty()) {
            logger.error("L0G: saveList(): Failed to Save tempDirDataList.");
            logger.error("L0G: saveList(): List is either null or Empty.");
            return;
        }

//        list.stream().map(TempDirData::toString).forEach(logger::info);


        //  Save TempDirDataList to A_TEMP_DIR_DATA Table
        tempDirDataRepository.saveAll(list);

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {

        try {
            System.out.println("visitFileFailed on File  : " + file.toFile().getCanonicalPath() +
                    "\nException : " + exc.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    public void saveTempDirPojoListToJsonFile() {

        String jsonDirName = "TempDirPojoList_" + System.nanoTime() + ".json";
        String strJsonFilePath = "./temp/" + "TempDirectoryData/" + jsonDirName;

        JsonController.createNewJsonFile(
                new Gson().toJson(tempDirDataList),
                new File(strJsonFilePath)
        );

        // Clear tempDirDataList after saved in DB i.e. empty the list
        tempDirDataList.clear();

    }

    public ArrayList<TempDirData> getTempDirDataList() {
        return tempDirDataList;
    }

    public void setTempDirDataList(ArrayList<TempDirData> tempDirDataList) {
        this.tempDirDataList = tempDirDataList;
    }
}
