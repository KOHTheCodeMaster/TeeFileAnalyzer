package root.utils;

import stdlib.utils.DirFilesCounter;
import stdlib.utils.KOHFilesUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonController {


    public static void createNewJsonFile(String jsonPojoList, File destFile) {

        if (destFile.isFile()) {
            //  return if file already exists
            System.out.println("Json File Already Exists : " + destFile.getAbsolutePath());
            return;
        } else {
            //  Create the file if it doesn't already exists
            try {
                //  print the error statement if unable to create the file
                if (!destFile.createNewFile())
                    System.out.println("ERROR : Unable to create json file : " + destFile);
            } catch (IOException e) {
                System.out.println("Exception : Unable to create json file : " + destFile +
                        "Exception Message : " + e.getMessage());
                e.printStackTrace();
            }

        }

        //  write the jsonPojoList into the destFile
        KOHFilesUtil.writeStrToFile(jsonPojoList, destFile);

    }

    private static long findNumOfJsonFiles(Path src) {

        DirFilesCounter dirFilesCounter = new DirFilesCounter();

        try {
            Files.walkFileTree(src, dirFilesCounter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirFilesCounter.getFileCount();
    }

    public static void updateJsonArrayFile(String jsonFilesPojoList, File jsonFile) {

        if (jsonFile.length() == 0) {
            KOHFilesUtil.writeStrToFile(jsonFilesPojoList, jsonFile);
            System.out.println("Json File Created");
            return;
        }

        //  Ignore first '[' from jsonFilesPojoList
        jsonFilesPojoList = jsonFilesPojoList.substring(1);

        try (ReadableByteChannel rbc = Channels.newChannel(new ByteArrayInputStream(jsonFilesPojoList.getBytes()));
             ReadableByteChannel singleComma = Channels.newChannel(new ByteArrayInputStream(",".getBytes()));
             FileChannel wbc = (new FileOutputStream(jsonFile, true)).getChannel()) {

            /*
             *  Time Stamp : 14th January 2K20, 07:12 PM..!!
             *
             *  Ignore first '[' from jsonFilesPojoList
             *  Update Json File by replacing last ']' with ','
             *  and Finally append jsonFilesPojoList to the jsonFile
             */

            long bytesTransferred;
            bytesTransferred = wbc.transferFrom(singleComma, jsonFile.length() - 1, Long.MAX_VALUE);
            bytesTransferred = wbc.transferFrom(rbc, jsonFile.length(), Long.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
