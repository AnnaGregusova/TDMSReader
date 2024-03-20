import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TDMSFile extends TDMSObject {
    private static RandomAccessFile file;
    private TDMSFile(RandomAccessFile file) throws FileNotFoundException {
        this.file = file;
    }

    public static TDMSFile read(String filePath) throws IOException {

        try {
            int segmentOffset = 0;
            file = new RandomAccessFile(filePath, "r");
            TDMSSegment tdmsSegment = readSegments(segmentOffset);
            if (tdmsSegment != null) {
                TDMSObject.segments.add(tdmsSegment);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new TDMSFile(file);
    }

    private static TDMSSegment readSegments(int segmentOffset) throws IOException {
        LeadInDataReader leadInDataReader = new LeadInDataReader(file, segmentOffset);
        int leadInDataByteCount = 28;
        MetaDataReader metaDataReader = new MetaDataReader(file, segmentOffset + leadInDataByteCount);
        //leadInDataReader.readBytes(0, 100);

        if (leadInDataReader.isValidTag()) {
            LeadInData leadInData = leadInDataReader.createLeadInData();
            MetaData metaData = metaDataReader.createMetaData();
            return new TDMSSegment(leadInData, metaData);
        } else {
            System.out.println("Invalid TDMS file tag. The file might not be a valid TDMS file.");
            return null;
        }
    }

    public void printMetaData() {
        for (TDMSSegment segment : segments) {
            MetaData metaData = segment.getMetaData();
            System.out.println("Segment Meta Data:");
            System.out.println("Number of objects: " + metaData.getNumberOfObjects());
        }
    }

    public void printLeadInData() {
        for (TDMSSegment segment : segments) {
            LeadInData leadInData = segment.getLeadInData();
            System.out.println("Segment LeadIn Data:");
            System.out.println("Tag: " + leadInData.getTag());
            System.out.println("Version Number: " + leadInData.getVersion());
            System.out.println("Mask: " + leadInData.getMask());
            System.out.println("Next Segment Offset: " + leadInData.getSegmentOffset());
            System.out.println("Raw Data Offset: " + leadInData.getRawDataOffset());
            System.out.println("--------------------------------");
        }
    }
}
