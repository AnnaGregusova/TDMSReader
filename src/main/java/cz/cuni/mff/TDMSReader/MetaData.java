import java.util.ArrayList;
import java.util.List;

public class MetaData{
    private int numberOfObjects;
    private ArrayList<TDMSGroup> groups;
   // private List<TDMSChannel> channels;

    public int getNumberOfObjects() { return numberOfObjects; }
    public ArrayList<TDMSGroup> getGroups() {return groups;}
    //public List<TDMSChannel> getChannels(){return channels;}
    public MetaData(int numberOfObjects, ArrayList<TDMSGroup> groups){ //, List<TDMSGroup> groups, List<TDMSChannel> channels){
        this.numberOfObjects = numberOfObjects;
        this.groups = groups;
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

}



