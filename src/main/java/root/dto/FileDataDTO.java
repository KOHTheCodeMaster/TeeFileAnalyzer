package root.dto;


public class FileDataDTO {

    private String pathHashSHA256;
    private String name;
    private String canonicalPath;
    private String dataHashSHA256;
    private FileSizeDataDTO fileSizeDataDTO;
    private long lastModTimeInMicros;
    private boolean toBeDeleted;

    @Override
    public String toString() {
        return "FileDataDTO{" +
                "pathHashSHA256='" + pathHashSHA256 + '\'' +
                ", name='" + name + '\'' +
                ", canonicalPath='" + canonicalPath + '\'' +
                ", dataHashSHA256='" + dataHashSHA256 + '\'' +
                ", fileSizeDataDTO=" + fileSizeDataDTO +
                ", lastModTimeInMicros=" + lastModTimeInMicros +
                ", toBeDeleted=" + toBeDeleted +
                '}';
    }

    /*
        Getters & Setters
        --------------------------------------------------
     */

    public String getPathHashSHA256() {
        return pathHashSHA256;
    }

    public void setPathHashSHA256(String pathHashSHA256) {
        this.pathHashSHA256 = pathHashSHA256;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCanonicalPath() {
        return canonicalPath;
    }

    public void setCanonicalPath(String canonicalPath) {
        this.canonicalPath = canonicalPath;
    }

    public String getDataHashSHA256() {
        return dataHashSHA256;
    }

    public void setDataHashSHA256(String dataHashSHA256) {
        this.dataHashSHA256 = dataHashSHA256;
    }

    public FileSizeDataDTO getFileSizeData() {
        return fileSizeDataDTO;
    }

    public void setFileSizeData(FileSizeDataDTO fileSizeDataDTO) {
        this.fileSizeDataDTO = fileSizeDataDTO;
    }

    public long getLastModTimeInMicros() {
        return lastModTimeInMicros;
    }

    public void setLastModTimeInMicros(long lastModTimeInMicros) {
        this.lastModTimeInMicros = lastModTimeInMicros;
    }

    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }
}
