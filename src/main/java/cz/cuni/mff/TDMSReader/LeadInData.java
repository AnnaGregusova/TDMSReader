package cz.cuni.mff.TDMSReader;

/**
 * Class representing lead-in data with tag, mask, version, segment offset, and raw data offset.
 */
public class LeadInData {
    public static long rawDataOffset = 1656;
    private final String tag;
    private final int mask;
    private final int version;
    private final long segmentOffset;

    /**
     * Constructs a LeadInData object with the given parameters.
     *
     * @param tag           The tag associated with the lead-in data.
     * @param mask          The mask value associated with the lead-in data.
     * @param version       The version of the lead-in data.
     * @param segmentOffset The offset of the segment.
     * @param rawDataOffset The offset of the raw data.
     */
    public LeadInData(String tag, int mask, int version, long segmentOffset, long rawDataOffset) {
        this.tag = tag;
        this.mask = mask;
        this.version = version;
        this.segmentOffset = segmentOffset;
        LeadInData.rawDataOffset = rawDataOffset;
    }

    /**
     * Gets the tag associated with the lead-in data.
     *
     * @return The tag of the lead-in data.
     */
    public String getTag() {
        return tag;
    }

    /**
     * Gets the mask value associated with the lead-in data.
     *
     * @return The mask value of the lead-in data.
     */
    public int getMask() {
        return mask;
    }

    /**
     * Gets the version of the lead-in data.
     *
     * @return The version of the lead-in data.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Gets the offset of the segment.
     *
     * @return The offset of the segment.
     */
    public long getSegmentOffset() {
        return segmentOffset;
    }

    /**
     * Gets the offset of the raw data.
     *
     * @return The offset of the raw data.
     */
    public long getRawDataOffset() {
        return rawDataOffset;
    }
}
