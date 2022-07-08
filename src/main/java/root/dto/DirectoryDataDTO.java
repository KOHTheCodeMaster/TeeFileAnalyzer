package root.dto;


public class DirectoryDataDTO {

    private String pathHashSHA256;
    private String name;
    private String canonicalPath;
    private String dataHashSHA256;
    private DirectoryMetaDataDTO directoryMetaDataDTO;
    private boolean shouldScanDir;
    private boolean toBeDeleted;

    @Override
    public String toString() {
        return "DirectoryDataDTO{" +
                "pathHashSHA256='" + pathHashSHA256 + '\'' +
                ", name='" + name + '\'' +
                ", canonicalPath='" + canonicalPath + '\'' +
                ", dataHashSHA256='" + dataHashSHA256 + '\'' +
                ", directoryMetaDataDTO=" + directoryMetaDataDTO +
                ", shouldScanDir=" + shouldScanDir +
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

    public DirectoryMetaDataDTO getDirectoryMetaData() {
        return directoryMetaDataDTO;
    }

    public void setDirectoryMetaData(DirectoryMetaDataDTO directoryMetaDataDTO) {
        this.directoryMetaDataDTO = directoryMetaDataDTO;
    }

    public boolean isShouldScanDir() {
        return shouldScanDir;
    }

    public void setShouldScanDir(boolean shouldScanDir) {
        this.shouldScanDir = shouldScanDir;
    }

    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }
}
