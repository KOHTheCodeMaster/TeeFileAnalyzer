package root.entity;


import javax.persistence.*;

@Entity(name = "A_FILE_DATA")
public class FileData {

    @Id
    @Column (name = "PATH_HASH_SHA_256")
    private String pathHashSHA256;
    private String name;
    private String canonicalPath;
    @Column (name = "DATA_HASH_SHA_256")
    private String dataHashSHA256;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_size_data_id", referencedColumnName = "id")
    private FileSizeData fileSizeData;
    private long lastModTimeInMicros;
    private boolean toBeDeleted;

    @Override
    public String toString() {
        return "FileData{" +
                "pathHashSHA256='" + pathHashSHA256 + '\'' +
                ", name='" + name + '\'' +
                ", canonicalPath='" + canonicalPath + '\'' +
                ", dataHashSHA256='" + dataHashSHA256 + '\'' +
                ", fileSizeData=" + fileSizeData +
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

    public FileSizeData getFileSizeData() {
        return fileSizeData;
    }

    public void setFileSizeData(FileSizeData fileSizeData) {
        this.fileSizeData = fileSizeData;
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
