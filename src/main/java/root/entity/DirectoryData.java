package root.entity;


import javax.persistence.*;

@Entity (name = "A_DIRECTORY_DATA")
public class DirectoryData {

    @Id
    @Column (name = "PATH_HASH_SHA_256")
    private String pathHashSHA256;
    private String name;
    private String canonicalPath;
    @Column (name = "DATA_HASH_SHA_256")
    private String dataHashSHA256;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIRECTORY_META_DATA_ID")
    private DirectoryMetaData directoryMetaData;
    private boolean shouldScanDir;
    private boolean toBeDeleted;

    @Override
    public String toString() {
        return "DirectoryData{" +
                "pathHashSHA256='" + pathHashSHA256 + '\'' +
                ", name='" + name + '\'' +
                ", canonicalPath='" + canonicalPath + '\'' +
                ", dataHashSHA256='" + dataHashSHA256 + '\'' +
                ", directoryMetaData=" + directoryMetaData +
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

    public DirectoryMetaData getDirectoryMetaData() {
        return directoryMetaData;
    }

    public void setDirectoryMetaData(DirectoryMetaData directoryMetaData) {
        this.directoryMetaData = directoryMetaData;
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
