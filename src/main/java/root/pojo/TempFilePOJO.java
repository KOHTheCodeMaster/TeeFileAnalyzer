package root.pojo;

import stdlib.enums.HashAlgorithm;
import stdlib.utils.HashGenerator;
import stdlib.utils.KOHStringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.concurrent.TimeUnit;

public class TempFilePOJO {

    private final String parentDirPath;
    private final String pathHashSHA256;
    private final String canonicalPath;
    private final long sizeInBytes;
    private final long lastModTimeInMicros;

    public TempFilePOJO(String parentDirPath, String pathHashSHA256, String canonicalPath, long sizeInBytes, long lastModTimeInMicros) {
        this.parentDirPath = parentDirPath;
        this.pathHashSHA256 = pathHashSHA256;
        this.canonicalPath = canonicalPath;
        this.sizeInBytes = sizeInBytes;
        this.lastModTimeInMicros = lastModTimeInMicros;
    }

    /**
     * Time Stamp: 30th May 2K21, 02:29 PM..!!
     * Desc: Convert the given File instance into TempFilePOJO
     *
     * @param file file that needs to be used for instantiating TempFilePOJO.
     * @return returns instance of FilePOJO initialized using the file.
     */
    public static TempFilePOJO convertToTempFilePojo(File file) {

        TempFilePOJO tempFilePOJO = null;

        try {

            String canonicalPath = KOHStringUtil.replaceBackSlashWithForwardSlash(file.getCanonicalPath());
            String parentDirPath = KOHStringUtil.replaceBackSlashWithForwardSlash(
                    file.getParentFile().getCanonicalPath()) + "/";
            String pathHashSHA256 = HashGenerator.generateStringHash(canonicalPath, HashAlgorithm.SHA256.getName());

            //  Instantiate FilePojo for file
            tempFilePOJO = new TempFilePOJO(parentDirPath,
                    pathHashSHA256,
                    canonicalPath,
                    file.length(),
                    Files.getLastModifiedTime(file.toPath(), LinkOption.NOFOLLOW_LINKS).to(TimeUnit.MICROSECONDS)
            );

        } catch (IOException e) {
            //  Due to canonical path of file and parent file
            System.out.println("Exception: Failed to acquire TempFilePojo for the File: " + file.getAbsolutePath());
            e.printStackTrace();
        }

        return tempFilePOJO;
    }

    @Override
    public String toString() {
        return "TempFilePOJO{" +
                "parentDirPath='" + parentDirPath + '\'' +
                ", pathHashSHA256='" + pathHashSHA256 + '\'' +
                ", canonicalPath='" + canonicalPath + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                ", lastModTimeInMicros=" + lastModTimeInMicros +
                '}';
    }

    public String getParentDirPath() {
        return parentDirPath;
    }

    public String getCanonicalPath() {
        return canonicalPath;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getPathHashSHA256() {
        return pathHashSHA256;
    }

    public long getLastModTimeInMicros() {
        return lastModTimeInMicros;
    }
}
