package cz.cuni.mff.TDMSReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class MetaDataReader extends DataReader {
    DataTypeReader dataTypeReader = new DataTypeReader();
    int numberOfObjects = 0;
    private boolean isFirstCallToGetGroups = true;
    private int currentOffset = 28;
    ArrayList<TDMSGroup> groups = new ArrayList<>();
    ArrayList<TDMSProperty> TDMSFileProperties = new ArrayList<>();
    boolean firstCall = true;
    int intRawDataIndex;
    private long metaDataOffset;

    public MetaDataReader(RandomAccessFile file, long metaDataOffset) throws IOException {
        super(file);
        this.metaDataOffset = metaDataOffset;
    }

    public boolean hasObjects() throws IOException {
        return getNumberOfObjects() != 0;
    }

    public String getName() throws IOException {
        int groupNameOffset = 36;
        return readString(groupNameOffset, getLengthOfObject() / 2);
    }

    public int getNumberOfObjects() throws IOException {
        int numberOfObjects = readInt32(currentOffset);
        currentOffset += 4;
        return numberOfObjects;
    }

    public ArrayList<TDMSGroup> getGroups() throws IOException, DataTypeNotFoundException {
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
            TDMSFileProperties = tdmsFileGroup.getProperties();
        } else {
            groups.add(new TDMSGroup(name, properties, channels));
        }
        return groups;
    }

    private void processObjects(ArrayList<TDMSChannel> channels) throws IOException, DataTypeNotFoundException {
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

    public MetaData createMetaData() throws IOException, DataTypeNotFoundException {
        return new MetaData(getGroups(), getTDMSFileProperties());
    }

    private ArrayList<TDMSProperty> getTDMSFileProperties() {
        return TDMSFileProperties;
    }

    public int getLengthOfObject() throws IOException {
        int lengthOfObjectPath = readInt32(currentOffset);
        currentOffset += 4;
        return lengthOfObjectPath;
    }

    public int getNumberOfProperties() throws IOException {
        int numberOfProperties = readInt32(currentOffset);
        currentOffset += 4;
        return numberOfProperties;
    }

    public int hasRawData() throws IOException {
        int hasRawData = readInt32(currentOffset);
        currentOffset += 4;
        return hasRawData;
    }

    public ArrayList<TDMSProperty> getProperties() throws IOException, DataTypeNotFoundException {
        int numberOfProperties = getNumberOfProperties();
        if (numberOfProperties < 0) {
            throw new WrongNumberOfProperties("Number of properties can not be negative." + numberOfProperties);
        }
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
                case TDS_TYPE_I8:
                case TDS_TYPE_U8:
                case TDS_TYPE_U16:
                case TDS_TYPE_U32:
                case TDS_TYPE_U64:
                case TDS_TYPE_SINGLE_FLOAT:
                case TDS_TYPE_EXTENDED_FLOAT:
                case TDS_TYPE_BOOLEAN:
                case TDS_TYPE_FIXED_POINT:
                case TDS_TYPE_EXTENDED_FLOAT_WITH_UNIT:
                    currentOffset += propertyDataType.getSize();
                    propertyValue = propertyDataType.name();
                    break;
                case TDS_TYPE_I16, TDS_TYPE_I32:
                    propertyValue = readInt32(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_I64:
                    propertyValue = readInt64(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_DOUBLE_FLOAT:
                    propertyValue = dataTypeReader.readDoubleFloat(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_TIMESTAMP:
                    propertyValue = dataTypeReader.readTimeStamp(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_SINGLE_FLOAT_WITH_UNIT:
                    propertyValue = propertyDataType.name();
                    currentOffset += propertyDataType.getSize();
                    break;
                case TDS_TYPE_STRING:
                    int lengthOfPropertyValue = readInt32(currentOffset);
                    currentOffset += propertyDataType.getSize();
                    propertyValue = readString(currentOffset, lengthOfPropertyValue);
                    currentOffset += lengthOfPropertyValue;
                    break;
                default:
                    propertyValue = readInt32(currentOffset);
                    currentOffset += 4;
                    break;
            }
            properties.add(new TDMSProperty(propertyName, propertyValue, propertyDataType));
        }
        return properties;
    }

    private DataTypeEnum findDataTypeByValue(int offset) throws IOException, DataTypeNotFoundException {
        int dataTypeValue = readInt32(offset);
        for (DataTypeEnum type : DataTypeEnum.values()) {
            if (type.getValue() == dataTypeValue) {
                return type;
            }
        }
        throw new DataTypeNotFoundException("Data type with value " + dataTypeValue + " not found.");
    }

    private boolean isGroup(int currentOffset) throws IOException {
        int nextObjectPathLength = readInt32(currentOffset);
        String nextObjectName = readString(currentOffset + 4, nextObjectPathLength);
        int slashCount = nextObjectName.length() - nextObjectName.replace("/", "").length();
        return slashCount < 2;
    }

    public TDMSChannel getChannel() throws IOException, DataTypeNotFoundException {
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

        long numberOfRawDataValues = readInt32(currentOffset);
        currentOffset += 8;

        int numberOfProperties = readInt32(currentOffset);
        RawDataReader rawDataReader = new RawDataReader(this.file);
        if (numberOfProperties != 0) {
            properties = getProperties();
        } else {
            properties = null;
            currentOffset += 4;
        }

        if (firstCall) {
            long rawDataOffset = LeadInData.rawDataOffset + 28;
            intRawDataIndex = (int) rawDataOffset;
            rawData = rawDataReader.getRawData(dataTypeOfRawData, numberOfRawDataValues, intRawDataIndex);
            long rawDataIndex = numberOfRawDataValues * sizeTypeOfRawData;
            intRawDataIndex += (int) rawDataIndex;
            firstCall = false;
        } else {
            rawData = rawDataReader.getRawData(dataTypeOfRawData, numberOfRawDataValues, intRawDataIndex);
            intRawDataIndex += (int) (numberOfRawDataValues * sizeTypeOfRawData);
        }

        TDMSChannel tdmsChannel = new TDMSChannel(channelName, properties, rawData);
        return tdmsChannel;
    }
    private class WrongNumberOfProperties extends IOException {
        public WrongNumberOfProperties(String message) {
            super(message);
        }
    }
    public class DataTypeNotFoundException extends Exception {
        public DataTypeNotFoundException(String message) {
            super(message);
        }
    }

}
