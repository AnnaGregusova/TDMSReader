package cz.cuni.mff.TDMSReader;

public class TDMSProperty {
    private String propertyName;
    private Object propertyValue;
    private Object propertyDataType;

    public TDMSProperty(String name, Object propertyValue, Object propertyDataType) {
        this.propertyName = name;
        this.propertyValue = propertyValue;
        this.propertyDataType = propertyDataType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getPropertyValue() { return propertyValue; }

    public Object getPropertyDataType() { return propertyDataType;
    }

    @Override
    public String toString() {
        return "Property { " +
                "name='" + propertyName + '\'' +
                ", value= " + propertyValue +
                '}';
    }
}
