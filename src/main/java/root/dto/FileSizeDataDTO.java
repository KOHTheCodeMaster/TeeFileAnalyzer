package root.dto;


public class FileSizeDataDTO {

    private Long id;                    //  [0 - Long.MAX_VALUE]
    private String sizeUnit;            //  B, KB, MB, GB, TB, ...
    private double minimalSize;         //  1023.99 KB
    private long sizeInBytes;           //  [0 - Long.MAX_VALUE]

    @Override
    public String toString() {
        return "FileSizeDataDTO{" +
                "id=" + id +
                ", sizeUnit='" + sizeUnit + '\'' +
                ", minimalSize=" + minimalSize +
                ", sizeInBytes=" + sizeInBytes +
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

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public double getMinimalSize() {
        return minimalSize;
    }

    public void setMinimalSize(double minimalSize) {
        this.minimalSize = minimalSize;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }
}
