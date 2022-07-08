package root.utils;

import stdlib.utils.KOHFilesUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DummyDataHelper {

    public static void seedDummyData() {

        String dummyParentDirPath = "./DummyData/";
        ArrayList<File> dummyDirList = new ArrayList<>();
        ArrayList<File> dummyFilesList = new ArrayList<>();

        //  Dummy Dirs.
        dummyDirList.add(new File(dummyParentDirPath + "Z/"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/A"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/A/A1"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/A/A2"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/B"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/B/B1"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/B/B2"));

        //  Dummy files
        dummyFilesList.add(new File(dummyParentDirPath + "Z/", "1.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/", "2.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A", "3.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A", "4.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A/A1", "5.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A/A1", "6.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A/A2", "7.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A/A2", "8.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/B", "9.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/B", "10.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/B/B1", "11.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/B/B1", "12.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/B/B2", "13.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/B/B2", "14.txt"));

        //  Clean Dummy Data
//        DirHelper.deleteDirectory(new File(dummyParentDirPath));

        //  Create Dummy Data
        dummyDirList.forEach(File::mkdirs);
        dummyFilesList.forEach(file -> {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create dummy file : " + file.getAbsolutePath());
                e.printStackTrace();
            }
        });

    }

    public static void updateDummyDataChanges() {

        File fileToBeModified = new File("./DummyData/Z/A/A1", "5.txt");
        File dirToBeDeleted = new File("./DummyData/Z/A/A2");

        addNewDummyFiles();

        modifyExistingDummyFile(fileToBeModified);

        //  Delete Dummy Dir.
//        DirHelper.deleteDirectory(dirToBeDeleted);

    }

    private static void deleteDummyFiles(File fileToBeDeleted) {
    }

    private static void modifyExistingDummyFile(File file) {

        /*
            Modify Dummy files
            First delete then create the same dummy file again
            This will update the last modified time for the same file.
        */

        try {

            KOHFilesUtil.deleteFileNow(file);
            if (file.createNewFile()) System.out.println("Failed to modify dummy file: " + file.getAbsolutePath());
//            file.setLastModified(System.currentTimeMillis());
        } catch (IOException e) {
            System.out.println("Failed to modify dummy file: " + file.getAbsolutePath());
            e.printStackTrace();
        }

    }

    public static void addNewDummyFiles() {

        String dummyParentDirPath = "./DummyData/";
        ArrayList<File> dummyDirList = new ArrayList<>();
        ArrayList<File> dummyFilesList = new ArrayList<>();

        //  Dummy Dirs.
        dummyDirList.add(new File(dummyParentDirPath + "Z/A/A3"));
        dummyDirList.add(new File(dummyParentDirPath + "Z/C"));

        //  Dummy files
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A/A3", "15.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/A/A3", "16.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/C", "17.txt"));
        dummyFilesList.add(new File(dummyParentDirPath + "Z/C", "18.txt"));

        //  Create Dummy Data
        dummyDirList.forEach(File::mkdirs);
        dummyFilesList.forEach(file -> {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create dummy file : " + file.getAbsolutePath());
                e.printStackTrace();
            }
        });


    }

}
