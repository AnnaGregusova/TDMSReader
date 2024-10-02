package cz.cuni.mff.TDMSReader;
import java.util.ArrayList;

public class TDMSChannel {
    private String name;
    private ArrayList<TDMSProperty> properties;
    private ArrayList<Object> rawData;

    public TDMSChannel(String name, ArrayList<TDMSProperty> properties, ArrayList<Object> rawData) {
        this.name = name;
        this.properties = properties;
        this.rawData = rawData;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TDMSProperty> getProperties() {
        return properties;
    }

    public ArrayList<Object> getRawData() {
        return rawData;
    }
    public ArrayList<Object> getRawData(int count){
        ArrayList<Object> tempRawData = new ArrayList<>();
        if (rawData.size() >= count){
            for (int i = 0; i< count; i++){
                tempRawData.add(rawData.get(i));
            }
            return tempRawData;
        }
        else {return rawData;}
    }

    public Object getPropertyValue(String name) {
        try {
            //if(properties == null) {return "No properties";}
            for (TDMSProperty property : properties) {
                if (property.getPropertyName().equals(name)) {
                    return property.getPropertyValue();
                }
            }
            throw new PropertyNotFoundException("Property with name '" + name + "' not found.");
        } catch (PropertyNotFoundException ex) {
            // Handle exception or rethrow if necessary
            System.err.println(ex.getMessage());
            return null; // Or throw another exception or handle it according to your needs
        }
    }

    @Override
    public String toString() {
        return "TDMSChannel { " + "name= " + name + ' ' + '}';
    }
    public class PropertyNotFoundException extends Exception {
        public PropertyNotFoundException(String message) {
            super(message);
        }
    }
}
