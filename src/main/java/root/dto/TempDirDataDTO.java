package root.dto;


import java.util.Map;

public class TempDirDataDTO {

    private final String pathHashSHA256;
    private final String canonicalPath;
    private final long lastModTimeInMicros;

    public TempDirDataDTO(String pathHashSHA256, String canonicalPath, long lastModTimeInMicros) {
        this.pathHashSHA256 = pathHashSHA256;
        this.canonicalPath = canonicalPath;
        this.lastModTimeInMicros = lastModTimeInMicros;
    }

    @Override
    public String toString() {
        return "pathHashSHA256 - " + pathHashSHA256 + '\n' +
                "canonicalPath - " + canonicalPath + '\n' +
                "lastModTimeInMicros=" + lastModTimeInMicros +
                '}';
    }

    public static TempDirDataDTO parseFromMap(Map<String, String> map) {

        if (map == null) {
            System.out.println("Map is Null.\nFailed To Parse Map into FilePOJO");
            return null;
        }

/*
        return new TempDirDataDTO(
                map.get(KeysOfTempDirsMap.PathHashSHA256.getKey()),
                map.get(KeysOfTempDirsMap.CanonicalPath.getKey()),
                Long.parseLong(map.get(KeysOfTempDirsMap.LastModTimeInMicros.getKey())),
                map.get(KeysOfTempDirsMap.RelativeModTimeHashSHA256.getKey())
        );
*/
        return null;
    }

    public String getPathHashSHA256() {
        return pathHashSHA256;
    }

    public String getCanonicalPath() {
        return canonicalPath;
    }

    public long getLastModTimeInMicros() {
        return lastModTimeInMicros;
    }

}
