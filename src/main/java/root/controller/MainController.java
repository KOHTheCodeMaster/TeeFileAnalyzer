package root.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import stdlib.utils.KOHFilesUtil;
import stdlib.utils.MyTimer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class MainController {

    //    Environment environment;
    TempDirDataController tempDirDataController;
    DirectoryDataController directoryDataController;
    private MyTimer myTimer;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    public static final String PATH_TEMP = "./temp";
    private static final String PATH_DIRECTORY_DATA = PATH_TEMP + "/DirectoryData";
    private static final String PATH_FILE_DATA = PATH_TEMP + "/FileData";
    private static final String PATH_TEMP_DIRECTORY_DATA = PATH_TEMP + "/TempDirectoryData";
    private static final String PATH_TEMP_FILE_DATA = PATH_TEMP + "/TempFileData";

    private static final String srcPath = "./Z/";
//    private boolean updateSrcDirFiles = false;
    private static final boolean updateSrcDirFiles = true;
    private static final boolean updateSrcDirOperations = true;
    private static final boolean shouldInitSrcDir = true;
//    private boolean shouldInitSrcDir = true;

    @Autowired
    public MainController(TempDirDataController tempDirDataController, DirectoryDataController directoryDataController) {
        this.tempDirDataController = tempDirDataController;
        this.directoryDataController = directoryDataController;
        myTimer = new MyTimer();
    }


    public void major() {

        myTimer.startTimer();
        logger.info("\n\nL0G: --------------------------------------------------" +
                " -------------------------------------------------- \n");
        logger.info("L0G: major(): Begin.");


        init();
        analyzeQuickScanResults();

        logger.info("\n\nL0G: -------------------------------------------------- " +
                " -------------------------------------------------- \n");
        logger.info("L0G: major(): End.");
        myTimer.stopTimer(true);

    }

    private void analyzeQuickScanResults() {

        tempDirDataController.updateShouldScan();

        tempDirDataController.processUpdatedTempDirList();

//        f1();
//        prepareTempMap();

    }

    private void init() {

        initializeTempDir();    //  Clean ./temp dir.
        initializeSrcDir();     //  Update ./z/ dir. for file updates before Quick Scan

        /*
            1. Quick Scan for only Dirs. in srcPath (Multi-threaded)
            2. Save quick scan results into A_TEMP_DIRS_DATA
            3. Analyze A_TEMP_DIRS_DATA table and update flags accordingly for starting the Main File Scan
               to sync all the changes and update A_FILE_DATA & A_DIRECTORY_DATA Tables.
         */

        tempDirDataController.quickScan(srcPath);

        //  Copy Quick Scan results from A_TEMP_DIR_DATA Table to A_DIRECTORY_DATA Table if EMPTY. (1st Scan)
        directoryDataController.saveIfEmpty();

    }

    /**
     * Initialize srcDir (./Z) by copying Z Backup dir if ./Z doesn't already exists.
     */
    private void initializeSrcDir() {

        File zDir = new File(srcPath);
        File zBackupDir = new File("./Z Backup/");

        if (!shouldInitSrcDir) return;

        if (zDir.isDirectory()) {
            logger.info("L0G: initializeSrcDir(): Z Dir. already Exists.\nDeleting ./Z");
        }

        try {

            //  Delete already stored files within the temp dirs.
            FileUtils.deleteDirectory(zDir);
            logger.info("L0G: initializeSrcDir(): ./Z Dir deleted successfully.");

            //  Copy "./Z Backup/" to "./Z" dir.
            FileUtils.copyDirectory(zBackupDir, zDir);
            logger.info("L0G: initializeSrcDir(): ./Z Dir Created.");

            if (updateSrcDirFiles) updateSrcDirFiles();
            if (updateSrcDirOperations) updateSrcDirs();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Perform Directory Operations - Add, Delete, Rename & Move Dirs.
     *
     * Operations on Directory:
     *
     * 1.  New Directory Added
     * 2.  Existing Directory Deleted.
     * 3.  Rename Existing Directory.
     * 4.  Move Existing Directory To Different Directory.
     * 5.  Modify Content of Existing Directory. [Irrelevant]
     *
     * Scenarios:                                Changed
     * 1.  /Z/C/C1/C13 - NEW/       Added   -       C1, C13 - NEW
     * 2.  /Z/C/C2/C22/             Deleted -       C2, C22
     * 3.  /Z/C/C3/C31/             Renamed -       C3, C31, C31 - Renamed
     * 4.  /Z/D/D1/D11/         Moved to B2 -       D1, D2, D11
     * 5.  /Z/D/D1/D12/     Moved & Renamed -       D1, D2, D12 - Moved & Renamed
     */
    private void updateSrcDirs() {

        try {

            File dir1 = new File(srcPath + "C/C1/C13 - Added/");
            Files.createDirectory(dir1.toPath());

            File dir2 = new File(srcPath + "C/C2/C22/");
            FileUtils.deleteDirectory(dir2);

            //  Rename C/C3/C31/ -> C/C3/C31 - Renamed/
            File dir3 = new File(srcPath + "C/C3/C31/");
            dir3.renameTo(new File(srcPath + "C/C3/C31 - Renamed/"));

            //  Move D/D1/D11/ -> D/D2/D11/
            FileUtils.moveDirectory(new File(srcPath + "D/D1/D11/"), new File(srcPath + "D/D2/D11/"));

            //  Move D/D1/D12/ -> D/D2/D22/     |   Move & Rename both operations
            FileUtils.moveDirectory(new File(srcPath + "D/D1/D12/"),
                    new File(srcPath + "D/D2/D12 - Moved & Renamed/"));

            logger.info("L0G: updateSrcDirs(): Dirs. updated.");

        } catch (IOException e) {
            logger.error("Failed to update Dirs. in ./Z dir.");
            e.printStackTrace();
        }

    }

    /**
     *
     * Operations on Item - Both File & Directory:
     *
     * 1.  New Item Added -> Addition of new file 4.txt in Z/A/A1/4.txt
     * 2.  Existing Item Deleted.
     * 3.  Rename Existing Item.
     * 4.  Move Existing Item To Different Directory.
     * 5.  Modify Content of Existing Item. [Irrelevant]
     *
     * Scenarios:                            Changed
     * 1.  /Z/A/A1/3.txt Added          -       A1
     * 2.  /Z/A/A2/2.txt Deleted        -       A2
     * 3.  /Z/A/A3/1.txt Renamed        -       A3
     * 4.  /Z/B/B1/1.txt Moved to B2    -       B1, B2
     * 5.  /Z/B/B3/3.txt Updated time   -       B3
     */
    private void updateSrcDirFiles() {

        try {

            File file1 = new File(srcPath + "A/A1/3 - Added.txt");
            boolean isFileCreated = file1.createNewFile();

            File file2 = new File(srcPath + "A/A2/2.txt");
            boolean isFileDeleted = file2.delete();

            //  Rename B/B1/1.txt -> B/B1/1 - Renamed.txt
            File file3 = new File(srcPath + "A/A3/1.txt");
            boolean isFileRenamed = file3.renameTo(new File(srcPath + "A/A3/1 - Renamed.txt"));

            //  Move B/B2/1.txt -> B/B1/1.txt
            Files.move(new File(srcPath + "B/B1/1.txt").toPath(),
                    new File(srcPath + "B/B2/1.txt").toPath());

            //  Updating File content doesn't change parent dir last mod. time
/*
            //  Update File Last Mod Time B/B3/1.txt
            File file4 = new File(srcPath + "B/B3/1.txt");
            String str = LocalDate.now().toString();
            Files.write(file4.toPath(), str.getBytes(), StandardOpenOption.APPEND);
//            boolean isFileUpdated = file4.setLastModified(System.currentTimeMillis());
*/

            if (isFileCreated && isFileDeleted && isFileRenamed) logger.info("Files updated.");
            else logger.info("Failed to update Files in ./Z dir.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initializeTempDir() {

        ArrayList<File> tempDirList = new ArrayList<>();

        tempDirList.add(new File(PATH_DIRECTORY_DATA));
        tempDirList.add(new File(PATH_FILE_DATA));
        tempDirList.add(new File(PATH_TEMP_DIRECTORY_DATA));
        tempDirList.add(new File(PATH_TEMP_FILE_DATA));

        //  Delete already stored files within the temp dirs.
        tempDirList.forEach(dir -> {
            File[] files = dir.listFiles();
            if (files != null) Arrays.stream(files).forEach(KOHFilesUtil::deleteFileNow);
        });

    }


    private void prepareTempMap() {


    }

}
