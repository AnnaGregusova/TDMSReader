package cz.cuni.mff.TDMSReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Class for reading metadata from a file.
 */
public class MetaDataReader extends DataReader {
    DataTypeReader dataTypeReader = new DataTypeReader();
    private long metaDataOffset;
    int numberOfObjects = 0;
    private boolean isFirstCallToGetGroups = true;
    private int currentOffset = 28;
    ArrayList<TDMSGroup> groups = new ArrayList<>();
    ArrayList<TDMSProperty> tdmsFileProperties = new ArrayList<>();
    boolean FirstCall = true;
    int intRawDataIndex;

    /**
     * Constructs a MetaDataReader with the given RandomAccessFile and metadata offset.
     *
     * @param file          The RandomAccessFile to read from.
     * @param metaDataOffset The offset of the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public MetaDataReader(RandomAccessFile file, long metaDataOffset) throws IOException {
        super(file);
        this.metaDataOffset = metaDataOffset;
    }

    /**
     * Checks if the metadata has objects.
     *
     * @return true if the metadata has objects, otherwise false.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public boolean hasObjects() throws IOException {
        return getNumberOfObjects() != 0;
    }

    /**
     * Gets the name of the group.
     *
     * @return The name of the group.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public String getName() throws IOException {
        int groupNameOffset = 36;
        return readString(groupNameOffset, getLengthOfObject() / 2);
    }

    /**
     * Gets the number of objects in the metadata.
     *
     * @return The number of objects in the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int getNumberOfObjects() throws IOException {
        int numberOfObjects = readInt32(currentOffset);
        currentOffset += 4;
        return numberOfObjects;
    }

    /**
     * Gets the groups from the metadata.
     *
     * @return The groups from the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<TDMSGroup> getGroups() throws IOException {
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        ArrayList<TDMSProperty> properties = new ArrayList<>();

        if (isFirstCallToGetGroups) {
            numberOfObjects = getNumberOfObjects();
            isFirstCallToGetGroups = false;
        }

        numberOfObjects--;

        int lengthOfObjectPath = getLengthOfObject();
        String name = readString(currentOffset, lengthOfObjectPath);
        currentOffset += lengthOfObjectPath;
        if (hasRawData() != -1) {
            return null;
        }

        properties = getProperties();

        processObjects(channels);

        if (name.equals("/")) {
            TDMSGroup tdmsFileGroup = new TDMSGroup(name, properties, channels);
            tdmsFileProperties = tdmsFileGroup.getProperties();
        } else {
            groups.add(new TDMSGroup(name, properties, channels));
        }

        return groups;
    }

    /**
     * Processes objects in the metadata.
     *
     * @param channels The list of TDMS channels.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private void processObjects(ArrayList<TDMSChannel> channels) throws IOException {
        if (numberOfObjects != 0) {
            if (isGroup(currentOffset)) {
                getGroups();
            } else {
                TDMSChannel tdmsChannel = getChannel();
                channels.add(tdmsChannel);
            }
            processObjects(channels); // Recursive call
        }
    }

    /**
     * Creates a MetaData object from the metadata.
     *
     * @return The MetaData object created from the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public MetaData createMetaData() throws IOException {
        return new MetaData(getGroups(), getTdmsFileProperties());
    }

    /**
     * Gets the TDMS file information properties.
     *
     * @return The list of TDMS file information properties.
     */
    private ArrayList<TDMSProperty> getTdmsFileProperties() {
        return tdmsFileProperties;
    }

    /**
     * Gets the length of the object.
     *
     * @return The length of the object.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int getLengthOfObject() throws IOException {
        int lengthOfObjectPath = readInt32(currentOffset);
        currentOffset += 4;
        return lengthOfObjectPath;
    }

    /**
     * Gets the number of properties in the metadata.
     *
     * @return The number of properties in the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int getNumberOfProperties() throws IOException {
        int numberOfProperties = readInt32(currentOffset);
        currentOffset += 4;
        return numberOfProperties;
    }

    /**
     * Checks if the metadata has raw data.
     *
     * @return The indicator for raw data in the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public int hasRawData() throws IOException {
        int hasRawData = readInt32(currentOffset);
        currentOffset += 4;
        return hasRawData;
    }

    /**
     * Gets the properties from the metadata.
     *
     * @return The properties from the metadata.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<TDMSProperty> getProperties() throws IOException {
        int numberOfProperties = getNumberOfProperties();
        ArrayList<TDMSProperty> properties = new ArrayList<TDMSProperty>();

        for (int i = 0; i < numberOfProperties; i++) {
            int lengthOfPropertyName = readInt32(currentOffset);
            currentOffset += 4;
            String propertyName = readString(currentOffset, lengthOfPropertyName);
            currentOffset += lengthOfPropertyName;
            DataTypeEnum propertyDataType = findDataTypeByValue(currentOffset);
            currentOffset += 4;

            Object propertyValue;

            switch (propertyDataType) {
                case TDS_TYPE_I16:
                case TDS_TYPE_I64:
                case TDS_TYPE_U8:
                case TDS_TYPE_U16:
                case TDS_TYPE_U32:
                case TDS_TYPE_U64:
                case TDS_TYPE_SINGLE_FLOAT:
                case TDS_TYPE_EXTENDED_FLOAT:
                case TDS_TYPE_BOOLEAN:
                case TDS_TYPE_FIXED_POINT:
                case TDS_TYPE_DOUBLE_FLOAT:
                case TDS_TYPE_EXTENDED_FLOAT_WITH_UNIT:
                    currentOffset += propertyDataType.getSize();
                    propertyValue = propertyDataType.name();
                    System.out.println(propertyDataType.name());
                    break;
                case TDS_TYPE_I8:
                    System.out.println(propertyDataType.getSize());
                    propertyValue = readInt32(currentOffset);
                    System.out.println(propertyValue);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_TIMESTAMP:
                    System.out.println(propertyDataType.name());
                    propertyValue = dataTypeReader.readTimeStamp(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_I32:
                    propertyValue = readInt32(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_SINGLE_FLOAT_WITH_UNIT:
                    System.out.println(propertyDataType.name());
                    currentOffset += 12;
                    propertyValue = propertyDataType.name();
                    break;
                case TDS_TYPE_STRING:
                    System.out.println(propertyDataType.name());
                    int lengthOfPropertyValue = readInt32(currentOffset);
                    currentOffset += 4;
                    propertyValue = readString(currentOffset, lengthOfPropertyValue);
                    currentOffset += lengthOfPropertyValue;
                    break;
                default:
                    System.out.println(propertyDataType.name());
                    propertyValue = readInt32(currentOffset);
                    currentOffset += 4;
                    break;
            }

            properties.add(new TDMSProperty(propertyName, propertyValue, propertyDataType));
        }

        return properties;
    }

    /**
     * Finds the data type by value.
     *
     * @param offset The offset in the file.
     * @return The data type found.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private DataTypeEnum findDataTypeByValue(int offset) throws IOException {
        int DataTypeValue = readInt32(offset);
        //System.out.println("Current offset for data tupes: " + offset);
        for (DataTypeEnum type : DataTypeEnum.values()) {
            if (type.getValue() == DataTypeValue) {
                return type;
            }
        }
        return DataTypeEnum.DS_TYPE_VOID;
    }

    /**
     * Checks if the object at the given offset is a group.
     *
     * @param currentOffset The current offset.
     * @return true if the object is a group, otherwise false.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private boolean isGroup(int currentOffset) throws IOException {
        int nextObjectPathLength = readInt32(currentOffset);
        String nextObjectName = readString(currentOffset + 4, nextObjectPathLength);
        int slashCount = nextObjectName.length() - nextObjectName.replace("/", "").length();
        return slashCount < 2;
    }

    /**
     * Gets the TDMS channel from the metadata.
     *
     * @return The TDMS channel.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public TDMSChannel getChannel() throws IOException {
        numberOfObjects--;
        ArrayList<TDMSProperty> properties;
        ArrayList<Object> rawData;
        int nextObjectPathLength = readInt32(currentOffset);
        currentOffset += 4;
        String channelName = readString(currentOffset, nextObjectPathLength);
        currentOffset += nextObjectPathLength;

        int lengthOfIndexInformation = readInt32(currentOffset);
        currentOffset += 4;
        DataTypeEnum dataTypeOfRawData = findDataTypeByValue(currentOffset);
        int valueTypeOfRawData = dataTypeOfRawData.getValue();
        int sizeTypeOfRawData = dataTypeOfRawData.getSize();

        currentOffset += 4;
        int dimension = readInt32(currentOffset);

        if (dimension != 1) {
            throw new IOException("TDMS library only supports data of dimension 1 and not " + dimension);
        }
        currentOffset += 4;
        //System.out.println("Current offset for number of faw data values: " + currentOffset);
        long numberOfRawDataValues = readInt64(currentOffset);
        currentOffset += 8;

        int numberOfProperties = readInt32(currentOffset);

        RawDataReader rawDataReader = new RawDataReader(this.file);
        if (numberOfProperties != 0) {
            properties = getProperties();
        } else {
            properties = null;
            currentOffset += 4;
        }

        if (FirstCall) {

            long rawDataOffset = LeadInData.rawDataOffset + 28;
            intRawDataIndex = (int) rawDataOffset;
            //System.out.println("Current offset " + currentOffset);
            rawData = rawDataReader.getRawData(dataTypeOfRawData, numberOfRawDataValues, intRawDataIndex);
            long rawDataIndex = numberOfRawDataValues * sizeTypeOfRawData;
            intRawDataIndex += (int) rawDataIndex;
            FirstCall = false;
        } else {
            rawData = rawDataReader.getRawData(dataTypeOfRawData, numberOfRawDataValues, intRawDataIndex);
            intRawDataIndex += (int) (numberOfRawDataValues * sizeTypeOfRawData);
        }

        TDMSChannel tdmsChannel = new TDMSChannel(channelName, properties, rawData);
        return tdmsChannel;
    }
}
