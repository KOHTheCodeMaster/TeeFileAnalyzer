package root.utils;

public class KeysManager {

    public static KeysOfFilePOJO keysOfFilePOJO = new KeysOfFilePOJO(
            "name",
            "canonicalPath",
            "pathHashSHA256",
            "dataHashSHA256",
            "sizeInBytes",
            "minimalSize",
            "sizeUnit",
            "lastModTimeInMicros"
    );

    public KeysOfFilePOJO getKeysOfFilePOJO() {
        return keysOfFilePOJO;
    }

    public static void main(String[] args) {
//        System.out.println(KeysOfFilePOJO.CANONICALPATH);
    }

    public static class KeysOfFilePOJO {

        private String name;
        private String canonicalPath;
        private String pathHashSHA256;
        private String dataHashSHA256;
        private String sizeInBytes;
        private String minimalSize;
        private String sizeUnit;
        private String lastModTimeInMicros;

        public KeysOfFilePOJO(String name, String canonicalPath, String pathHashSHA256, String dataHashSHA256, String sizeInBytes, String minimalSize, String sizeUnit, String lastModTimeInMicros) {
            this.name = name;
            this.canonicalPath = canonicalPath;
            this.pathHashSHA256 = pathHashSHA256;
            this.dataHashSHA256 = dataHashSHA256;
            this.sizeInBytes = sizeInBytes;
            this.minimalSize = minimalSize;
            this.sizeUnit = sizeUnit;
            this.lastModTimeInMicros = lastModTimeInMicros;
        }

        public String getName() {
            return name;
        }

        public String getCanonicalPath() {
            return canonicalPath;
        }

        public String getPathHashSHA256() {
            return pathHashSHA256;
        }

        public String getDataHashSHA256() {
            return dataHashSHA256;
        }

        public String getSizeInBytes() {
            return sizeInBytes;
        }

        public String getMinimalSize() {
            return minimalSize;
        }

        public String getSizeUnit() {
            return sizeUnit;
        }

        public String getLastModTimeInMicros() {
            return lastModTimeInMicros;
        }
    }

    /*private enum KeysOfDirPOJO {

        NAME,
        PATHHASHSHA256,
        CANONICALPATH,
        DATAHASHSHA256,
        SIZEINBYTES,
        MINIMALSIZE,
        SIZEUNIT,
        FILESCOUNT,
        DIRSCOUNT,
        LASTMODTIMEINMICROS

    }*/

}
