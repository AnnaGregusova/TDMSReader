import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Class for reading lead-in data from a file.
 */
public class LeadInDataReader extends DataReader {
    private long currentSegmentOffset;
    private final String expectedTag = "TDSm";
    private final int tagOffset = 0;
    private final int maskOffset = 4;
    private final int versionOffset = 8;
    private final int nextSegmentOffset = 12;
    private static final int RawDataOffset = 20;

    /**
     * Constructs a LeadInDataReader with the given RandomAccessFile and segment offset.
     *
     * @param file           The RandomAccessFile to read from.
     * @param segmentOffset  The offset of the segment.
     */
    public LeadInDataReader(RandomAccessFile file, long segmentOffset) {
        super(file);
        this.currentSegmentOffset = segmentOffset;
    }

    /**
     * Checks if the tag read from the file is valid.
     *
     * @return true if the tag is valid, otherwise false.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public boolean isValidTag() throws IOException {
        return getTag().equals(expectedTag);
    }

    /**
     * Gets the tag read from the file.
     *
     * @return The tag read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public String getTag() throws IOException {
        return readString(tagOffset, 4);
    }

    /**
     * Gets the offset of the next segment.
     *
     * @return The offset of the next segment.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public long getNextSegment() throws IOException {
        return readInt64(nextSegmentOffset);
    }

    /**
     * Gets the version read from the file.
     *
     * @return The version read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int getVersion() throws IOException {
        return readInt32(versionOffset);
    }

    /**
     * Gets the mask read from the file.
     *
     * @return The mask read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int getMask() throws IOException {
        return readInt32(maskOffset);
    }

    /**
     * Gets the raw data offset read from the file.
     *
     * @return The raw data offset read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public long getRawData() throws IOException {
        return readInt64(RawDataOffset);
    }

    /**
     * Creates a LeadInData object from the data read from the file.
     *
     * @return The LeadInData object created from the data read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public LeadInData createLeadInData() throws IOException {
        return new LeadInData(getTag(), getMask(), getVersion(), getNextSegment(), getRawData());
    }
}
