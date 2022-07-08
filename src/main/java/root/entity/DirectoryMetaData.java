package root.entity;


import javax.persistence.*;

@Entity (name = "A_DIRECTORY_META_DATA")
public class DirectoryMetaData {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Column (name = "PATH_HASH_SHA_256")
    private String pathHashSHA256;
    @OneToOne
    @JoinColumn (name = "FILE_SIZE_DATA_ID", referencedColumnName = "id")
    private FileSizeData fileSizeData;
    private long filesCount;
    private long dirsCount;
    private long lastModTimeInMicros;
    @Column (name = "MOD_TIME_HASH_SHA_256")
    private String modTimeHashSHA256;

    @Override
    public String toString() {
        return "DirectoryMetaData{" +
                "id=" + id +
                ", pathHashSHA256=" + pathHashSHA256 +
                ", fileSizeData=" + fileSizeData +
                ", filesCount=" + filesCount +
                ", dirsCount=" + dirsCount +
                ", lastModTimeInMicros=" + lastModTimeInMicros +
                ", modTimeHashSHA256='" + modTimeHashSHA256 + '\'' +
                '}';
    }
    /*
        Getters & Setters
        --------------------------------------------------
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathHashSHA256() {
        return pathHashSHA256;
    }

    public void setPathHashSHA256(String pathHashSHA256) {
        this.pathHashSHA256 = pathHashSHA256;
    }

    public FileSizeData getFileSizeData() {
        return fileSizeData;
    }

    public void setFileSizeData(FileSizeData fileSizeData) {
        this.fileSizeData = fileSizeData;
    }

    public long getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(long filesCount) {
        this.filesCount = filesCount;
    }

    public long getDirsCount() {
        return dirsCount;
    }

    public void setDirsCount(long dirsCount) {
        this.dirsCount = dirsCount;
    }

    public long getLastModTimeInMicros() {
        return lastModTimeInMicros;
    }

    public void setLastModTimeInMicros(long lastModTimeInMicros) {
        this.lastModTimeInMicros = lastModTimeInMicros;
    }

    public String getModTimeHashSHA256() {
        return modTimeHashSHA256;
    }

    public void setModTimeHashSHA256(String modTimeHashSHA256) {
        this.modTimeHashSHA256 = modTimeHashSHA256;
    }
}
