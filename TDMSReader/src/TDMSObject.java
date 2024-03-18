import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


// tohle je abstract class
public class TDMSObject {
    public static List<TDMSSegment> segments = new ArrayList<>();
    public static List<TDMSGroup> groups = new ArrayList<>();
    public static List<TDMSChannel> channels = new ArrayList<>();



}

class TDMSSegment extends TDMSObject{
    private LeadInData leadInData;
    private MetaData metaData;

    //private RawData rawData;

   public TDMSSegment (LeadInData leadInData, MetaData metaData){
       this.leadInData = leadInData;
       this.metaData = metaData;
   }
    public LeadInData getLeadInData() {
        return leadInData;
    }
    public MetaData getMetaData(){
       return metaData;
    }
}


class TDMSFile extends TDMSObject {
    private static RandomAccessFile file;
    private TDMSFile(String filePath) throws FileNotFoundException {
        this.file = new RandomAccessFile(filePath, "r");
    }

    public static void read(String filePath) throws IOException {

        try {
            file = new RandomAccessFile(filePath, "r");
            TDMSSegment tdmsSegment = readSegments();
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
    }

    private static TDMSSegment readSegments() throws IOException {
        LeadInDataReader leadInDataReader = new LeadInDataReader(file, 0);
        MetaDataReader metaDataReader = new MetaDataReader(file, 0);
        leadInDataReader.readBytes(0, 100);

        if (leadInDataReader.isValidTag()) {
            LeadInData leadInData = leadInDataReader.createLeadInData();
            MetaData metaData = metaDataReader.createMetaData();
            return new TDMSSegment(leadInData, metaData);
        } else {
            System.out.println("Invalid TDMS file tag. The file might not be a valid TDMS file.");
            return null;
        }
    }

    public static void printMetaData() {
        MetaData metaData = segments.getFirst().getMetaData();
        System.out.println("Segment Meta Data:");
        System.out.println("Number of objects: " + metaData.getNumberOfObjects());
    }

    public static void printLeadInData() {
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

