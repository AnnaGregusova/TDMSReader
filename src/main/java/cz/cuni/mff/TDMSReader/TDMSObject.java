import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;


// tohle je abstract class
public class TDMSObject {
    public static List<TDMSSegment> segments = new ArrayList<>();
    //public static List<TDMSGroup> groups = new ArrayList<>();
    //public static List<TDMSChannel> channels = new ArrayList<>();

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





