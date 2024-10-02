package cz.cuni.mff.TDMSReader;

class TDMSSegment {
    private LeadInData leadInData;
    private MetaData metaData;

    public TDMSSegment(LeadInData leadInData, MetaData metaData) {
        this.leadInData = leadInData;
        this.metaData = metaData;
    }

    public LeadInData getLeadInData() {
        return leadInData;
    }

    public MetaData getMetaData() { return metaData; }
}
