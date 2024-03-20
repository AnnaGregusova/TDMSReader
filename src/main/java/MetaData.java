import java.util.List;

public class MetaData extends TDMSObject{
    private int numberOfObjects;
    //private List<TDMSGroup> groups;
   // private List<TDMSChannel> channels;

    public int getNumberOfObjects() { return numberOfObjects; }
    //public List<TDMSGroup> getGroups() {return groups;}
    //public List<TDMSChannel> getChannels(){return channels;}
    public MetaData(int numberOfObjects){ //, List<TDMSGroup> groups, List<TDMSChannel> channels){
        this.numberOfObjects = numberOfObjects;
        //this.groups = groups;
        //this.channels = channels;
    }

}
class Property<tdsDataType>{
    private String name;

}

