package root.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "A_TEMP_DIR_DATA")
public class TempDirData {

    @Id
    @Column (name = "PATH_HASH_SHA_256")
    private String pathHashSHA256;
    private String canonicalPath;
    private long lastModTimeInMicros;
    private boolean shouldScan;

    public TempDirData() {
    }

    public TempDirData(String pathHashSHA256, String canonicalPath, long lastModTimeInMicros) {
        this.pathHashSHA256 = pathHashSHA256;
        this.canonicalPath = canonicalPath;
        this.lastModTimeInMicros = lastModTimeInMicros;
        this.shouldScan = false;
    }

    @Override
    public String toString() {
        return "TempDirData{" +
                "pathHashSHA256='" + pathHashSHA256 + '\'' +
                ", canonicalPath='" + canonicalPath + '\'' +
                ", lastModTimeInMicros=" + lastModTimeInMicros +
                ", shouldScan=" + shouldScan +
                '}';
    }

    public String getPathHashSHA256() {
        return pathHashSHA256;
    }

    public void setPathHashSHA256(String pathHashSHA256) {
        this.pathHashSHA256 = pathHashSHA256;
    }

    public String getCanonicalPath() {
        return canonicalPath;
    }

    public void setCanonicalPath(String canonicalPath) {
        this.canonicalPath = canonicalPath;
    }

    public long getLastModTimeInMicros() {
        return lastModTimeInMicros;
    }

    public void setLastModTimeInMicros(long lastModTimeInMicros) {
        this.lastModTimeInMicros = lastModTimeInMicros;
    }

    public boolean isShouldScan() {
        return shouldScan;
    }

    public void setShouldScan(boolean shouldScan) {
        this.shouldScan = shouldScan;
    }
}
