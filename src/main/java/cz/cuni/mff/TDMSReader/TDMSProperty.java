/**
 * Represents a property within a TDMS (Technical Data Management Streaming) file.
 */
public class TDMSProperty {

    private String propertyName;
    private Object propertyValue;
    private Object propertyDataType;

    /**
     * Constructs a TDMSProperty object with the specified name, value, and data type.
     *
     * @param name             The name of the property.
     * @param propertyValue    The value of the property.
     * @param propertyDataType The data type of the property.
     */
    public TDMSProperty(String name, Object propertyValue, Object propertyDataType){
        this.propertyName = name;
        this.propertyValue = propertyValue;
        this.propertyDataType = propertyDataType;
    }

    /**
     * Retrieves the name of the property.
     *
     * @return The name of the property.
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Retrieves the value of the property.
     *
     * @return The value of the property.
     */
    public Object getPropertyValue() {
        return propertyValue;
    }

    /**
     * Retrieves the data type of the property.
     *
     * @return The data type of the property.
     */
    public Object getPropertyDataType(){
        return propertyDataType;
    }

    /**
     * Provides a string representation of the TDMSProperty object.
     *
     * @return A string representation of the TDMSProperty object.
     */
    @Override
    public String toString() {
        return "Property { " +
                "name='" + propertyName + '\'' +
                ", value= " + propertyValue+
                '}';
    }
}
