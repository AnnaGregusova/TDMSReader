import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

public class LeadInDataReader extends DataReader {
    private long currentSegmentOffset;
    private final String expectedTag = "TDSm";
    private final int tagOffset = 0;
    private final int maskOffset = 4;
    private final int versionOffset = 8;
    private final int nextSegmentOffset = 12;
    private final int nextRawDataOffset = 20;


    public LeadInDataReader(RandomAccessFile file, long segmentOffset){
        super(file); //calls DataReader constructor
        this.currentSegmentOffset = segmentOffset;

    }

    public boolean isValidTag() throws IOException {
        return Objects.equals(getTag(), expectedTag);
    }
    public String getTag() throws IOException{
        return readString(tagOffset, 4);
    }

    public long getNextSegment() throws IOException {
        return readInt64(nextSegmentOffset);
    }
    public int getVersion() throws IOException {
        return readInt32(versionOffset);
    }
    public int getMask() throws IOException {
        return readInt32(maskOffset);
    }

    public long getRawData() throws IOException {
        return readInt64(nextRawDataOffset);
    }
    public LeadInData createLeadInData() throws IOException {
        return new LeadInData(getTag(), getMask(), getVersion(), getNextSegment(), getRawData());
    }

}
class LeadInData{

    private String tag;
    private int mask;
    private int version;
    private long segmentOffset;
    private long rawDataOffset;

    public LeadInData(String tag, int mask, int version, long segmentOffset, long rawDataOffset){

        this.tag = tag;
        this.mask = mask;
        this.version = version;
        this.segmentOffset = segmentOffset;
        this.rawDataOffset = rawDataOffset;
    }

    public String getTag() { return tag; }
    public int getMask() { return mask; }
    public int getVersion() { return version; }
    public long getSegmentOffset() { return segmentOffset; }
    public long getRawDataOffset() { return rawDataOffset; }


}