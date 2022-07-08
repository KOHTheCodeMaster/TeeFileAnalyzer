package root.utils;

import stdlib.utils.KOHFilesUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DirHelper {

    public static void cleanTempDirs(ArrayList<File> tempDirList) {
        //  Delete already stored files within the temp dirs.
        tempDirList.forEach(dir -> {
            File[] files = dir.listFiles();
            if (files != null) Arrays.stream(files).forEach(KOHFilesUtil::deleteFileNow);
        });
    }


    public static ArrayList<File> getDirectChildFilesList(File dir) {

        File[] files = dir.listFiles();

        if (files == null || files.length <= 0) return null;

        return Arrays.stream(files).filter(File::isFile).collect(Collectors.toCollection(ArrayList::new));

    }
}
