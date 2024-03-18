import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MetaDataReader extends DataReader {
    private long metaDataOffset;
    private final int numberOfObjectsOffset = 28;
    private final int lengthOfFirstObjectOffset = 32;
    private final int groupNameOffset = 36;

    public MetaDataReader(RandomAccessFile file, long metaDataOffset) {
        super(file);
        this.metaDataOffset = metaDataOffset;

    }
    public boolean hasObjects() throws IOException {
        if (getNumberOfObjects() == 0){
            return false;
        }
        return true;
    }
    public String getName() throws IOException{
        return readString(groupNameOffset, getLengthOfObject()/2);
    }
    public int getNumberOfObjects() throws IOException {
        return readInt32(numberOfObjectsOffset);
    }
    public List<TDMSGroup> getGroups() throws IOException{
        return null;
    }
    public List<TDMSChannel> getChannels() throws IOException{
        return null;
    }
    public MetaData createMetaData() throws IOException {
        return new MetaData(getNumberOfObjects(), getGroups(), getChannels());
    }
    public int getLengthOfObject() throws IOException{
        return readInt32(lengthOfFirstObjectOffset);
    }
}
class TDMSGroup extends TDMSObject {
    private List<Property> properties;
    private String name;
    private List<TDMSChannel> channels = new ArrayList<>();
    private TDMSGroup(String name){
        this.name = name;
        this.channels = channels;
    }
    public String getName() {return name;}
}

class TDMSChannel extends TDMSObject {}

//MetaData by mela vracet pocet objektu, groupy, channely,
class MetaData extends TDMSObject{
    private int numberOfObjects;
    private List<TDMSGroup> groups;
    private List<TDMSChannel> channels;

    public int getNumberOfObjects() { return numberOfObjects; }
    public List<TDMSGroup> getGroups() {return groups;}
    public List<TDMSChannel> getChannels(){return channels;}
    public MetaData(int numberOfObjects, List<TDMSGroup> groups, List<TDMSChannel> channels){
        this.numberOfObjects = numberOfObjects;
        this.groups = groups;
        this.channels = channels;
    }

}
class Property<tdsDataType>{
    private String name;

}
