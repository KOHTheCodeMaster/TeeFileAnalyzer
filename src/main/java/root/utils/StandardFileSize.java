package root.utils;

public enum StandardFileSize {

    BYTE("B", 1),
    KILO_BYTE("KB", 1024),
    MEGA_BYTE("MB", 1024 * KILO_BYTE.sizeInBytes),
    GIGA_BYTE("GB", 1024 * MEGA_BYTE.sizeInBytes),
    TERA_BYTE("TB", 1024 * GIGA_BYTE.sizeInBytes),
    PETA_BYTE("PB", 1024 * TERA_BYTE.sizeInBytes),
    EXA_BYTE("EB", 1024 * PETA_BYTE.sizeInBytes),
    ZETA_BYTE("ZB", 1024 * EXA_BYTE.sizeInBytes),
    YOTTA_BYTE("YB", 1024 * ZETA_BYTE.sizeInBytes);

    private final String unit;
    private final long sizeInBytes;

    StandardFileSize(String unit, long sizeInBytes) {
        this.unit = unit;
        this.sizeInBytes = sizeInBytes;
    }

    public String getUnit() {
        return unit;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    @Override
    public String toString() {
        return getUnit();
    }
}
