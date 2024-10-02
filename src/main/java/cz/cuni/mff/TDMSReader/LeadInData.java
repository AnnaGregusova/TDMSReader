package cz.cuni.mff.TDMSReader;

public class LeadInData {
    public static long rawDataOffset = 1656;
    private final String tag;
    private final int mask;
    private final int version;
    private final long segmentOffset;

    public LeadInData(String tag, int mask, int version, long segmentOffset, long rawDataOffset) {
        this.tag = tag;
        this.mask = mask;
        this.version = version;
        this.segmentOffset = segmentOffset;
        LeadInData.rawDataOffset = rawDataOffset;
    }

    public String getTag() {
        return tag;
    }

    public int getMask() {
        return mask;
    }

    public int getVersion() {
        return version;
    }

    public long getSegmentOffset() {
        return segmentOffset;
    }

    public long getRawDataOffset() {
        return rawDataOffset;
    }
}
