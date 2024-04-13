import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MetaDataReader extends DataReader {
    private long metaDataOffset;
    private int numberOfObjectsOffset = 28;
    private int lengthOfObjectOffset = 32;
    private int groupNameOffset = 36;
    int numberOfObjects = 0;
    private boolean isFirstCallToGetGroups = true;
    //private int numberOfObjects;
    private boolean isGroup;
    private int currentOffset = 28;
    //private int lengthOfObjectpath = getLengthOfObject();
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
        System.out.println("Current ofset1: " + currentOffset);
        int numberOfObjects = readInt32(currentOffset); //zacinam na 28
        currentOffset += 4; //jsem na 32 a jde na delku
        System.out.println("Current ofset2: " + currentOffset);
        return numberOfObjects;
    }

    public ArrayList<TDMSGroup> getGroups() throws IOException{

        if (isFirstCallToGetGroups) {
            numberOfObjects = getNumberOfObjects(); // This line runs only on the first call
            System.out.println("Number of objects: " + numberOfObjects);
            isFirstCallToGetGroups = false; // Set flag to false after first call
        }

        int lengthOfObjectPath = getLengthOfObject();
        System.out.println("Length of object: " + lengthOfObjectPath);


        if (lengthOfObjectPath != 0 && numberOfObjects != 0){
            System.out.println("jsem tady");
            String name = readString(currentOffset, lengthOfObjectPath);
            System.out.println(name);
            currentOffset += lengthOfObjectPath + 4; // tady nevim jestli tu 4 mam pricist
            ArrayList<Property> properties = getProperties();
            groups.add(new TDMSGroup(name, properties));
        }

        if (isGroup){
            System.out.println("Next object is group");
            groupNameOffset = currentOffset;
            getGroups();
        }
        else{
            System.out.println("Next object is channel");
            getChannels();
        }
        return groups;
    }
    public List<TDMSChannel> getChannels() throws IOException{

        return null;
    }
    public MetaData createMetaData() throws IOException {
        return new MetaData(getGroups());//, getGroups(), getChannels());
    }

    public int getLengthOfObject() throws IOException{
        System.out.println("Current ofset: " + currentOffset);
        int lengthOfObjectPath = readInt32(currentOffset); //jsem na 32
        currentOffset += 4; //jsem na 36 a jdu na jmeno
        System.out.println("Current ofset: " + currentOffset);
        return  lengthOfObjectPath;
    }
    public int getNumberOfProperties() throws IOException{
        System.out.println("number of propereties offset: " + currentOffset );
        int numberOfProperties =  readInt32(currentOffset); // jsem na 36 + delka jmena groupy


        currentOffset += 4;
        return numberOfProperties;

    }

    public ArrayList<Property> getProperties() throws IOException {

        ArrayList properties = new ArrayList<Property>();
        int numberOfProperties = getNumberOfProperties();
        System.out.println("Number of properties: " + numberOfProperties);

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
        //readBytes(currentOffset, 20);
        isGroup = isGroup(currentOffset);

        int pathLength = readInt32(currentOffset);
        //System.out.println("Path length: " + pathLength);
        return properties;
    }
    private boolean isGroup(int currentOffset) throws IOException {
        System.out.println("Checking second object");
        System.out.println("----------------------");
        //check if the next object is Group or channel
        int nextObjectPathLength = readInt32(currentOffset);
        String nextObjectName = readString(currentOffset + 4, nextObjectPathLength);
        System.out.println(nextObjectPathLength);
        System.out.println(nextObjectName);
        int slashCount = nextObjectName.length() - nextObjectName.replace("/", "").length();
        return slashCount < 2; // If there are fewer than 2 slashes, it's a group
    }
    //public ArrayList<TDMSChannel> getChannels(){


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

class TDMSChannel{
    private ArrayList<Property> properties;
    private String name;

    public TDMSChannel(String name, ArrayList properties){
        this.name = name;
        this.properties = properties;
    }

    public String getName() {return name;}
    public ArrayList<Property> getProperties(){

        return properties;
    }
}