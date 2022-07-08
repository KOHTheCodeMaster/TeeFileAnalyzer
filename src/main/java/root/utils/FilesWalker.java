package root.utils;

import com.google.gson.Gson;
import stdlib.pojos.FilePOJO;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FilesWalker implements FileVisitor<Path> {

    private ArrayList<File> filesList;
    private ArrayList<FilePOJO> filePojoList;

    public FilesWalker() {
        filesList = new ArrayList<>();
        filePojoList = new ArrayList<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

//        filesList.add(file.toFile());

        FilePOJO filePOJO = FilePOJO.acquireFilePojo(file.toFile(), false);
        filePojoList.add(filePOJO);

        //  Save tempDirPojoList to TempDirsMap Table
        if (filePojoList.size() > 1000) saveFilePojoListToJsonFile();

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

    public void saveFilePojoListToJsonFile() {

        String jsonFilePojoList = new Gson().toJson(filePojoList);
        String jsonFileName = System.nanoTime() + "_FilesMap.json";

//        JsonController.createNewJsonFile(
//                jsonFilePojoList,
//                new File(FileAnalyzer.JSON_DIR_PATH + FileAnalyzer.STR_FILES_MAP, jsonFileName)
//        );

        // Clear filePojoList i.e. empty the list
        filePojoList.clear();

    }

    public ArrayList<File> getFilesList() {
        return filesList.isEmpty() ? null : filesList;
    }

    public void setFilesList(ArrayList<File> filesList) {
        this.filesList = filesList;
    }
}
