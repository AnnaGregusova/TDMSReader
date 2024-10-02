package cz.cuni.mff.TDMSReader;

import java.io.IOException;
import java.io.RandomAccessFile;

public class LeadInDataReader extends DataReader {
    private long currentSegmentOffset;
    private final String expectedTag = "TDSm";
    private final int tagOffset = 0;
    private final int maskOffset = 4;
    private final int versionOffset = 8;
    private final int nextSegmentOffset = 12;
    private static final int RawDataOffset = 20;

    public LeadInDataReader(RandomAccessFile file, long segmentOffset) {
        super(file);
        this.currentSegmentOffset = segmentOffset;
    }

    public boolean isValidTag() throws IOException, FileFormatException {
        String tag = getTag();
        if (!tag.equals(expectedTag)) {
            throw new FileFormatException("Invalid file tag: expected " + expectedTag + ", but found " + tag);
        }
        return true;
    }

    public String getTag() throws IOException {
        return readString(tagOffset, 4);
    }

    public long getNextSegment() throws IOException, DataReadException {
        try {
            return readInt64(nextSegmentOffset);
        } catch (IOException e) {
            throw new DataReadException("Failed to read next segment offset: " + e.getMessage());
        }
    }

    public int getVersion() throws IOException {
        return readInt32(versionOffset);
    }

    public int getMask() throws IOException {
        return readInt32(maskOffset);
    }

    public long getRawData() throws IOException {
        return readInt64(RawDataOffset);
    }

    public LeadInData createLeadInData() throws IOException {
        return new LeadInData(getTag(), getMask(), getVersion(), getNextSegment(), getRawData());
    }
    public class FileFormatException extends IOException {
        public FileFormatException(String message) {
            super(message);
        }
    }

    public class DataReadException extends IOException {
        public DataReadException(String message) {
            super(message);
        }
    }
}
