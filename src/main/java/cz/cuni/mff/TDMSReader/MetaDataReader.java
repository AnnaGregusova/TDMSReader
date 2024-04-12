import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MetaDataReader extends DataReader {
    private long metaDataOffset;
    private final int numberOfObjectsOffset = 28;
    private int lengthOfObjectOffset = 32;
    private int groupNameOffset = 36;
    private boolean isGroup;
    private int currentOffset = ;
    private int lengthOfObjectpath = getLengthOfObject();
    ArrayList<TDMSGroup> groups = new ArrayList<>();

    int numberOfPropertiesOffset = 0;


    public MetaDataReader(RandomAccessFile file, long metaDataOffset) throws IOException {
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
    public ArrayList<TDMSGroup> getGroups() throws IOException{
        
        
        int numberOfObjects = getNumberOfObjects();

        System.out.println("Length of object: " + lengthOfObjectpath);
        System.out.println("Number of objects: " + numberOfObjects);
        
        if (lengthOfObjectpath != 0 && numberOfObjects != 0){
            String name = readString(groupNameOffset, lengthOfObjectpath);
            ArrayList<Property> properties = getProperties();
            groups.add(new TDMSGroup(name, properties));
        }
            
        if (!isGroup){
            groupNameOffset = currentOffset;

            getGroups();
        }
        return groups;
    }
    public List<TDMSChannel> getChannels() throws IOException{
        return null;
    }
    public MetaData createMetaData() throws IOException {
        return new MetaData(getNumberOfObjects(), getGroups());//, getGroups(), getChannels());
    }

    public int getLengthOfObject() throws IOException{
        return readInt32(lengthOfObjectOffset);
    }
    public int getNumberOfProperties() throws IOException{
        numberOfPropertiesOffset = getLengthOfObject() + groupNameOffset + 4;
        return readInt32(numberOfPropertiesOffset);
    }
    @SuppressWarnings("unchecked")
    public ArrayList<Property> getProperties() throws IOException {

        ArrayList properties = new ArrayList<Property>();
        int numberOfProperties = getNumberOfProperties();
        System.out.println("Number of properties: " + numberOfProperties);
        currentOffset = numberOfPropertiesOffset + 4;
        for (int i = 0; i < numberOfProperties; i++){

            int lengthOfPropertyName = readInt32(currentOffset);
            currentOffset += 4;
            System.out.println("length of Property name: " + lengthOfPropertyName);
            String propertyName = readString(currentOffset, lengthOfPropertyName);
            currentOffset += lengthOfPropertyName;
            System.out.println("Property name (property order): " + propertyName + " " + i);
            int propertyDataType =  readInt32(currentOffset);
            currentOffset += 4;
            System.out.println("Property Datatype: " + propertyDataType);
            Object propertyValue;

            if(propertyDataType == 32){
                int lengthOfPropertyValue = readInt32(currentOffset);
                currentOffset += 4;
                propertyValue = readString(currentOffset, lengthOfPropertyValue);
                currentOffset += lengthOfPropertyValue;
                System.out.println("Property value: " + propertyValue);
            }
            else{

                propertyValue = readInt32(numberOfPropertiesOffset + 16 + lengthOfPropertyName);
                currentOffset += 4;

            }
            properties.add(new Property(propertyName, propertyValue));
        }
        System.out.println(currentOffset);
        readBytes(currentOffset, 20);
        lengthOfObjectpath = readInt32(currentOffset);
        isGroup = isGroup(currentOffset);

        int pathLength = readInt32(currentOffset);
        System.out.println("Path length: " + pathLength);
        return properties;
    }
    private boolean isGroup(int currentOffset){

        //check if the next object is Group or channel
        return false;
    }
}
class TDMSGroup{
    private ArrayList<Property> properties;
    private String name;
    private List<TDMSChannel> channels;

    public TDMSGroup(String name, ArrayList properties){
        this.name = name;
        this.properties = properties;
        this.channels = channels;
    }

    public String getName() {return name;}
    public ArrayList<Property> getProperties(){

        return properties;
    }
}

class TDMSChannel{}