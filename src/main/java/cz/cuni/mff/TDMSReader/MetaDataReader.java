import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class MetaDataReader extends DataReader {
    private long metaDataOffset;
    private final int numberOfObjectsOffset = 28;
    private final int lengthOfObjectOffset = 32;
    private int groupNameOffset = 36;
    int numberOfObjects = 0;
    private boolean isFirstCallToGetGroups = true;
    private int currentOffset = 28;
    ArrayList<TDMSGroup> groups = new ArrayList<>();
    ArrayList<Property> tdmsFileInfo = new ArrayList<>();
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
        int numberOfObjects = readInt32(currentOffset); //zacinam na 28
        currentOffset += 4; //jsem na 32 a jde na delku
        return numberOfObjects;
    }

    public ArrayList<TDMSGroup> getGroups() throws IOException{

        ArrayList<TDMSChannel> channels = new ArrayList<>();
        ArrayList<Property> properties;

        if (isFirstCallToGetGroups) {
            numberOfObjects = getNumberOfObjects(); // This line runs only on the first call
            System.out.println("Number of objects: " + numberOfObjects);
            isFirstCallToGetGroups = false; // Set flag to false after first call
        }
        numberOfObjects --;

        for (int i = 0; i < numberOfObjects -1; i++){

            int lengthOfObjectPath = getLengthOfObject();
            String name = readString(currentOffset, lengthOfObjectPath);
            currentOffset += lengthOfObjectPath;
            //System.out.println("Group name: " + name);
            int hasRawData = readInt32(currentOffset);
            if (hasRawData != -1){
                return null;
            }
            //System.out.println("Has Raw data " + hasRawData);
            currentOffset += 4;

           properties = getProperties();
           processObjects(channels);

           if (name. equals("/")){

                TDMSGroup tdmsFileGroup = new TDMSGroup(name, properties, channels);
                tdmsFileInfo = tdmsFileGroup.getProperties();
           }
           else{
               groups.add(new TDMSGroup(name, properties, channels));
           }
        }
        return groups;
    }
    private void processObjects(ArrayList channels) throws IOException {
        if (numberOfObjects != 0) {
            if (isGroup(currentOffset)) {
                getGroups(); // Assume this decreases numberOfObjects appropriately
            } else {
                TDMSChannel tdmsChannel = getChannel(); // Assume this decreases numberOfObjects appropriately
                channels.add(tdmsChannel);
            }
            processObjects(channels); // Recursive call
        }
    }

    public MetaData createMetaData() throws IOException {
        return new MetaData(getGroups(), getTdmsFileInfo());//, getGroups(), getChannels());
    }

    private ArrayList<Property> getTdmsFileInfo() {
        return tdmsFileInfo;
    }

    public int getLengthOfObject() throws IOException{
        int lengthOfObjectPath = readInt32(currentOffset); //jsem na 32
        currentOffset += 4; //jsem na 36 a jdu na jmeno
        return  lengthOfObjectPath;
    }
    public int getNumberOfProperties() throws IOException{

        int numberOfProperties =  readInt32(currentOffset); // jsem na 36 + delka jmena groupy
        currentOffset += 4;
        return numberOfProperties;

    }

    public ArrayList<Property> getProperties() throws IOException {

        int numberOfProperties = getNumberOfProperties();
        ArrayList properties = new ArrayList<Property>();

        //System.out.println("Number of properties: " + numberOfProperties);

        for (int i = 0; i < numberOfProperties; i++){

            int lengthOfPropertyName = readInt32(currentOffset);
            currentOffset += 4;
            String propertyName = readString(currentOffset, lengthOfPropertyName);
            currentOffset += lengthOfPropertyName;
            int propertyDataType =  readInt32(currentOffset);
            currentOffset += 4;
            Object propertyValue;

            switch (propertyDataType) {
                case 32:
                    int lengthOfPropertyValue = readInt32(currentOffset);
                    currentOffset += 4;
                    propertyValue = readString(currentOffset, lengthOfPropertyValue);
                    currentOffset += lengthOfPropertyValue;
                    //System.out.println("Property value: " + propertyValue);
                    break;
                case 10:
                    propertyValue = 10;
                    currentOffset += 8;
                    //System.out.println("Property value: " + propertyValue);
                    break;
                case 68:
                    propertyValue = 68;
                    currentOffset += 16;
                    //System.out.println("Property value: " + propertyValue);
                    break;
                case 12:
                    propertyValue = 12;
                    currentOffset += 12;
                    //System.out.println("Property value: " + propertyValue);
                    break;
                default:
                    propertyValue = readInt32(numberOfPropertiesOffset + 16 + lengthOfPropertyName);
                    currentOffset += 4;
                    //System.out.println("Property value: " + propertyValue);
                    break;
            }

            properties.add(new Property(propertyName, propertyValue));
        }

        return properties;
    }
    private boolean isGroup(int currentOffset) throws IOException {
        int nextObjectPathLength = readInt32(currentOffset);
        String nextObjectName = readString(currentOffset + 4, nextObjectPathLength);
        int slashCount = nextObjectName.length() - nextObjectName.replace("/", "").length();
        return slashCount < 2; // If there are fewer than 2 slashes, it's a group
    }
    public TDMSChannel getChannel() throws IOException{
        numberOfObjects--;
        ArrayList<Property> properties;
        int nextObjectPathLength = readInt32(currentOffset);
        currentOffset+=4;
        String channelName = readString(currentOffset, nextObjectPathLength);
        currentOffset += nextObjectPathLength;
        int lengthOfIndexInformation = readInt32(currentOffset);
        currentOffset += 4;
        int dataTypeOfRawData = readInt32(currentOffset);
        currentOffset += 4;
        int dimension = readInt32(currentOffset);
        currentOffset += 4;
        long numberOfRawDataValues = readInt64(currentOffset);
        currentOffset += 8;

        int numberOfProperties = readInt32(currentOffset);
        /*System.out.println("CHANNEL NAME: " + channelName);
        System.out.println("lengthOfIndexInformation: " + lengthOfIndexInformation);
        System.out.println("dataTypeOfRawData: " + dataTypeOfRawData);
        System.out.println("dimension: " + dimension);
        System.out.println("numberOfRawDataValues: " + numberOfRawDataValues);
        System.out.println("numberOfProperties: " + numberOfProperties);
        */
        if(numberOfProperties != 0){

            properties = getProperties();
        }
        else{

            properties = null;
            currentOffset += 4;
        }
        TDMSChannel tdmsChannel = new TDMSChannel(channelName, properties);
        return  tdmsChannel;
    }

}
class TDMSGroup{
    private ArrayList<Property> properties;
    private String name;
    private ArrayList<TDMSChannel> channels;

    public TDMSGroup(String name, ArrayList<Property> properties, ArrayList<TDMSChannel> channels){
        this.name = name;
        this.properties = properties;
        this.channels = channels;
    }

    public String getName() {return name;}
    public ArrayList<Property> getProperties(){

        return properties;
    }
    public ArrayList<TDMSChannel> getChannels() {
        // Return the list of channels
        return this.channels;
    }

    public TDMSChannel getChannel(String name) {

        for (TDMSChannel channel : channels){ //var
            for (int i = 0; i < channels.size(); i++){
                    if ( channel.getName().equals(name)){
                        System.out.println(channel.getName());
                        //System.out.println(channel.getProperties());
                        return channel;
                    }
                }
            }
        return null;
        }
        @Override
        public String toString() {
            return "TDMSGroup { " +
                    "name= " + name + ' ' +
                    '}';
            }
        }


class TDMSChannel{
    //private ArrayList<RawData> rawData;
    private String name;
    private ArrayList<Property> properties;

    public TDMSChannel(String name, ArrayList<Property> properties){//, ArrayList<RawData> rawData){
        this.name = name;
        this.properties = properties;
        //this.rawData = rawData;
    }

    public String getName() {return name;}
    public ArrayList<Property> getProperties() {return properties;}

    public Object getPropertyValue(String name) {

        for (Property property : properties){ //var
            for (int i = 0; i < properties.size(); i++){
                if ( property.getName().equals(name)){
                    Object propertyValue = property.getPropertyValue();
                    //System.out.println(channel.getProperties());
                    return propertyValue;
                }
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return "TDMSChannel { " +
                "name= " + name + ' ' +
                '}';
    }
    /*public ArrayList<RawData> getRawData(){

        return rawData;
    }*/
}