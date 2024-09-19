package cz.cuni.mff.TDMSReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Class for reading raw data from a file.
 */
public class RawDataReader extends DataReader {
    DataTypeReader dataTypeReader = new DataTypeReader();
    int currentOffsetRD;
    boolean isFirstCall = true;
    ArrayList<Object> rawData;

    /**
     * Constructor for RawDataReader.
     *
     * @param file The RandomAccessFile to read from.
     */
    public RawDataReader(RandomAccessFile file) {
        super(file);
    }

    /**
     * Gets the raw data based on the data type.
     *
     * @param dataTypeOfRawData     The data type of the raw data.
     * @param numberOfRawDataValues The number of raw data values.
     * @param currentOffset         The current offset in the file.
     * @return The raw data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Object> getRawData(DataTypeEnum dataTypeOfRawData, long numberOfRawDataValues, int currentOffset) throws IOException {
        switch (dataTypeOfRawData) {
            case TDS_TYPE_TIMESTAMP:
                rawData = readTimeStamps(numberOfRawDataValues, currentOffset);
                break;
            case TDS_TYPE_DOUBLE_FLOAT:
                rawData = readDoubleFloat(numberOfRawDataValues, currentOffset);
                break;
            case TDS_TYPE_I8:
                rawData = readTypeI8(numberOfRawDataValues, currentOffset);
                break;
            case TDS_TYPE_I16:
                rawData = readTypeI16(numberOfRawDataValues, currentOffset);
                break;
            case TDS_TYPE_I32:
                rawData = readTypeI32(numberOfRawDataValues, currentOffset);
                break;
            case TDS_TYPE_I64:
                rawData = readTypeI64();
                break;
            case TDS_TYPE_U8:
                rawData = readTypeU8();
                break;
            case TDS_TYPE_U16:
                rawData = readTypeU16();
                break;
            case TDS_TYPE_U32:
                rawData = readTypeU32();
                break;
            case TDS_TYPE_U64:
                rawData = readTypeU64();
                break;
            case TDS_TYPE_SINGLE_FLOAT:
                rawData = readTypeSingleFloat();
                break;
            case TDS_TYPE_EXTENDED_FLOAT:
                rawData = readTypeExtendedFloat();
                break;
            case TDS_TYPE_SINGLE_FLOAT_WITH_UNIT:
                rawData = readTypeSingleFloatWithUnit();
                break;
            case TDS_TYPE_DOUBLE_FLOAT_WITH_UNIT:
                rawData = readTypeDoubleFloatWithUnit();
                break;
            case TDS_TYPE_EXTENDED_FLOAT_WITH_UNIT:
                rawData = readTypeExtendedFloatWithUnit();
                break;
            case TDS_TYPE_STRING:
                rawData = readTypeString();
                break;
            case TDS_TYPE_BOOLEAN:
                rawData = readTypeBoolean();
                break;
            case TDS_TYPE_FIXED_POINT:
                rawData = readTypeFixedPoint();
                break;
            case TDS_TYPE_COMPLEX_SINGLE_FLOAT:
                rawData = readTypeComplexSingleFloat();
                break;
            case TDS_TYPE_COMPLEX_DOUBLE_FLOAT:
                rawData = readTypeComplexDoubleFloat();
                break;
            case TDS_TYPE_DAQMX_RAW_DATA:
                rawData = readTypeDaqmxRawData();
                break;
            default:
                // Handle other enum values here
                break;
        }
        return rawData;
    }

    /**
     * Reads timestamps from the file.
     *
     * @param numberOFRawData The number of raw data values.
     * @param currentOffset   The current offset in the file.
     * @return The list of timestamps.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private ArrayList<Object> readTimeStamps(long numberOFRawData, int currentOffset) throws IOException {
        ArrayList<Object> timestamps = new ArrayList<>();
        for (int i = 0; i < numberOFRawData; i++) {
            timestamps.add(dataTypeReader.readTimeStamp(currentOffset));
            currentOffset += 16;
        }
        return timestamps;
    }

    private ArrayList<Object> readDoubleFloat(long numberOfRawData, int currentOffset) throws IOException {
        ArrayList<Object> doubles = new ArrayList<>();
        for (int i = 0; i < numberOfRawData; i++) {
            doubles.add(dataTypeReader.readDoubleFloat(currentOffset));
            currentOffset += 8;
        }
        return doubles;
    }

    private ArrayList<Object> readVoid() {
        ArrayList<Object> voids = new ArrayList<>();
        voids.add("Raw datas are " + DataTypeEnum.DS_TYPE_VOID.name());
        return voids;
    }

    private ArrayList<Object> readTypeI8(long numberOFRawData, int currentOffset) throws IOException {
        ArrayList<Object> typeI8 = new ArrayList<>();
        typeI8.add("Raw datas are " + DataTypeEnum.TDS_TYPE_I8.name());
        return typeI8;
    }

    private ArrayList<Object> readTypeI16(long numberOfRawData, int currentOffset) throws IOException {
        ArrayList<Object> typeI16 = new ArrayList<>();
        for (int i = 0; i < numberOfRawData; i++) {
            int int16 = readInt32(currentOffset);
            typeI16.add(int16);
        }
        return typeI16;
    }

    private ArrayList<Object> readTypeI32(long numberOfRawData, int currentOffset) throws IOException {
        ArrayList<Object> typeI32 = new ArrayList<>();
        for (int i = 0; i < numberOfRawData; i++) {
            int int32 = readInt32(currentOffset);
            typeI32.add(int32);
        }
        return typeI32;
    }

    private ArrayList<Object> readTypeI64() {
        ArrayList<Object> typeI64 = new ArrayList<>();
        typeI64.add("Raw datas are " + DataTypeEnum.TDS_TYPE_I64.name());
        return typeI64;
    }

    private ArrayList<Object> readTypeU8() {
        ArrayList<Object> typeU8 = new ArrayList<>();
        typeU8.add("Raw datas are " + DataTypeEnum.TDS_TYPE_U8.name());
        return typeU8;
    }

    private ArrayList<Object> readTypeU16() {
        ArrayList<Object> typeU16 = new ArrayList<>();
        typeU16.add("Raw datas are " + DataTypeEnum.TDS_TYPE_U16.name());
        return typeU16;
    }

    private ArrayList<Object> readTypeU32() {
        ArrayList<Object> typeU32 = new ArrayList<>();
        typeU32.add("Raw datas are " + DataTypeEnum.TDS_TYPE_U32.name());
        return typeU32;
    }

    private ArrayList<Object> readTypeU64() {
        ArrayList<Object> typeU64 = new ArrayList<>();
        typeU64.add("Raw datas are " + DataTypeEnum.TDS_TYPE_U64.name());
        return typeU64;
    }

    private ArrayList<Object> readTypeSingleFloat() {
        ArrayList<Object> typeSingleFloat = new ArrayList<>();
        typeSingleFloat.add("Raw datas are " + DataTypeEnum.TDS_TYPE_SINGLE_FLOAT.name());
        return typeSingleFloat;
    }

    private ArrayList<Object> readTypeDoubleFloat() {
        ArrayList<Object> typeDoubleFloat = new ArrayList<>();
        typeDoubleFloat.add("Raw datas are " + DataTypeEnum.TDS_TYPE_DOUBLE_FLOAT.name());
        return typeDoubleFloat;
    }

    private ArrayList<Object> readTypeExtendedFloat() {
        ArrayList<Object> typeExtendedFloat = new ArrayList<>();
        typeExtendedFloat.add("Raw datas are " + DataTypeEnum.TDS_TYPE_EXTENDED_FLOAT.name());
        return typeExtendedFloat;
    }

    private ArrayList<Object> readTypeSingleFloatWithUnit() {
        ArrayList<Object> typeSingleFloatWithUnit = new ArrayList<>();
        typeSingleFloatWithUnit.add("Raw datas are " + DataTypeEnum.TDS_TYPE_SINGLE_FLOAT_WITH_UNIT.name());
        return typeSingleFloatWithUnit;
    }

    private ArrayList<Object> readTypeDoubleFloatWithUnit() {
        ArrayList<Object> typeDoubleFloatWithUnit = new ArrayList<>();
        typeDoubleFloatWithUnit.add("Raw datas are " + DataTypeEnum.TDS_TYPE_DOUBLE_FLOAT_WITH_UNIT.name());
        return typeDoubleFloatWithUnit;
    }

    private ArrayList<Object> readTypeExtendedFloatWithUnit() {
        ArrayList<Object> typeExtendedFloatWithUnit = new ArrayList<>();
        typeExtendedFloatWithUnit.add("Raw datas are " + DataTypeEnum.TDS_TYPE_EXTENDED_FLOAT_WITH_UNIT.name());
        return typeExtendedFloatWithUnit;
    }

    private ArrayList<Object> readTypeString() {
        ArrayList<Object> typeString = new ArrayList<>();
        typeString.add("Raw datas are " + DataTypeEnum.TDS_TYPE_STRING.name());
        return typeString;
    }

    private ArrayList<Object> readTypeBoolean() {
        ArrayList<Object> typeBoolean = new ArrayList<>();
        typeBoolean.add("Raw datas are " + DataTypeEnum.TDS_TYPE_BOOLEAN.name());
        return typeBoolean;
    }

    private ArrayList<Object> readTypeTimestamp() {
        ArrayList<Object> typeTimestamp = new ArrayList<>();
        typeTimestamp.add("Raw datas are " + DataTypeEnum.TDS_TYPE_TIMESTAMP.name());
        return typeTimestamp;
    }

    private ArrayList<Object> readTypeFixedPoint() {
        ArrayList<Object> typeFixedPoint = new ArrayList<>();
        typeFixedPoint.add("Raw datas are " + DataTypeEnum.TDS_TYPE_FIXED_POINT.name());
        return typeFixedPoint;
    }

    private ArrayList<Object> readTypeComplexSingleFloat() {
        ArrayList<Object> typeComplexSingleFloat = new ArrayList<>();
        typeComplexSingleFloat.add("Raw datas are " + DataTypeEnum.TDS_TYPE_COMPLEX_SINGLE_FLOAT.name());
        return typeComplexSingleFloat;
    }

    private ArrayList<Object> readTypeComplexDoubleFloat() {
        ArrayList<Object> typeComplexDoubleFloat = new ArrayList<>();
        typeComplexDoubleFloat.add("Raw datas are " + DataTypeEnum.TDS_TYPE_COMPLEX_DOUBLE_FLOAT.name());
        return typeComplexDoubleFloat;
    }

    private ArrayList<Object> readTypeDaqmxRawData() {
        ArrayList<Object> typeDaqmxRawData = new ArrayList<>();
        typeDaqmxRawData.add("Raw datas are " + DataTypeEnum.TDS_TYPE_DAQMX_RAW_DATA.name());
        return typeDaqmxRawData;
    }
}