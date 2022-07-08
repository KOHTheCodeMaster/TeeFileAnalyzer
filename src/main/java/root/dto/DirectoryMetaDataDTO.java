package root.dto;


import java.io.File;

public class DirectoryMetaDataDTO {

    private Long id;
    private String pathHashSHA256;
    private FileSizeDataDTO fileSizeDataDTO;
    private long filesCount;
    private long dirsCount;
    private long lastModTimeInMicros;
    private String modTimeHashSHA256;

    public DirectoryMetaDataDTO createDirectoryMetaDataDTO(File dir) {

        DirectoryMetaDataDTO directoryMetaDataDTO = new DirectoryMetaDataDTO();


        return directoryMetaDataDTO;

    }

    @Override
    public String toString() {
        return "DirectoryMetaDataDTO{" +
                "id=" + id +
                ", pathHashSHA256=" + pathHashSHA256 +
                ", fileSizeDataDTO=" + fileSizeDataDTO +
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

    public FileSizeDataDTO getFileSizeDataDTO() {
        return fileSizeDataDTO;
    }

    public void setFileSizeDataDTO(FileSizeDataDTO fileSizeDataDTO) {
        this.fileSizeDataDTO = fileSizeDataDTO;
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
