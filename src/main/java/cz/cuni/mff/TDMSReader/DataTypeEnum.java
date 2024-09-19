package cz.cuni.mff.TDMSReader;

/**
 * Enum representing different data types with their corresponding values and sizes.
 */
public enum DataTypeEnum {
    DS_TYPE_VOID(0, 4),
    TDS_TYPE_I8(1, 0),
    TDS_TYPE_I16(2, 4),
    TDS_TYPE_I32(3, 4),
    TDS_TYPE_I64(4, 4),
    TDS_TYPE_U8(5, 4),
    TDS_TYPE_U16(6, 4),
    TDS_TYPE_U32(7, 4),
    TDS_TYPE_U64(8, 4),
    TDS_TYPE_SINGLE_FLOAT(9, 4),
    TDS_TYPE_DOUBLE_FLOAT(10, 8),
    TDS_TYPE_EXTENDED_FLOAT(11, 8),
    TDS_TYPE_SINGLE_FLOAT_WITH_UNIT(18, 9),    // 0x19 in decimal
    TDS_TYPE_DOUBLE_FLOAT_WITH_UNIT(26, 8),    // 0x1A in decimal
    TDS_TYPE_EXTENDED_FLOAT_WITH_UNIT(27, 8), // 0x1B in decimal
    TDS_TYPE_STRING(32, 4),                   // 0x20 in decimal
    TDS_TYPE_BOOLEAN(33, 4),                   // 0x21 in decimal
    TDS_TYPE_TIMESTAMP(68, 16),                // 0x44 in decimal
    TDS_TYPE_FIXED_POINT(79, 4),               // 0x4F in decimal
    TDS_TYPE_COMPLEX_SINGLE_FLOAT(0x08000C, 8),
    TDS_TYPE_COMPLEX_DOUBLE_FLOAT(0x10000D, 16),
    TDS_TYPE_DAQMX_RAW_DATA(0xFFFFFFFF, 4);

    private final int value;
    private final int size;

    /**
     * Constructs a DataTypeEnum with the given value and size.
     *
     * @param value The value representing the data type.
     * @param size  The size of the data type in bytes.
     */
    DataTypeEnum(int value, int size) {
        this.value = value;
        this.size = size;
    }

    /**
     * Returns the value representing the data type.
     *
     * @return The value of the data type.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the size of the data type in bytes.
     *
     * @return The size of the data type.
     */
    public int getSize() {
        return this.size;
    }
}
