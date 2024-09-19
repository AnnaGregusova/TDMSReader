package cz.cuni.mff.TDMSReader;

/**
 * Represents a segment within a TDMS (Technical Data Management Streaming) file.
 */
class TDMSSegment {
    private LeadInData leadInData;
    private MetaData metaData;

    /**
     * Constructs a TDMSSegment object with the provided lead-in data and metadata.
     *
     * @param leadInData The lead-in data of the segment.
     * @param metaData   The metadata of the segment.
     */
    public TDMSSegment(LeadInData leadInData, MetaData metaData) {
        this.leadInData = leadInData;
        this.metaData = metaData;
    }

    /**
     * Retrieves the lead-in data of the segment.
     *
     * @return The lead-in data of the segment.
     */
    public LeadInData getLeadInData() {
        return leadInData;
    }

    /**
     * Retrieves the metadata of the segment.
     *
     * @return The metadata of the segment.
     */
    public MetaData getMetaData() { return metaData; }
}
