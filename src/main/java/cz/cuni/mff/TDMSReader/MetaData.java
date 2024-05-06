import java.util.ArrayList;
import java.util.List;

public class MetaData{
    private int numberOfObjects;
    private ArrayList<TDMSGroup> groups;
    private ArrayList<Property> tdmsFileInfo;
   // private List<TDMSChannel> channels;

    public int getNumberOfObjects() { return numberOfObjects; }

    public ArrayList<Property> getTdmsFileInfo() {return tdmsFileInfo; }
    public ArrayList<TDMSGroup> getGroups() {return groups;}
    //public List<TDMSChannel> getChannels(){return channels;}
    public MetaData(ArrayList<TDMSGroup> groups, ArrayList<Property> tdmsFileInfo){ //, List<TDMSGroup> groups, List<TDMSChannel> channels){
        this.groups = groups;
        this.tdmsFileInfo = tdmsFileInfo;
        //this.channels = channels;
    }

}
class Property{
    private String name;
    private Object propertyValue;
    public Property(String name, Object propertyValue){
        this.name = name;
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "Property { " +
                "name='" + name + '\'' +
                ", value= " + propertyValue+
                '}';
    }

    public String getName() {
        return name;
    }

    public Object getPropertyValue() {
        return propertyValue;
    }
}



