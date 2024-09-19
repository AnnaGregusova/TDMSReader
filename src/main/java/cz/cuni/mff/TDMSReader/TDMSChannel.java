package cz.cuni.mff.TDMSReader;
import java.util.ArrayList;

/**
 * Class representing a TDMS channel.
 */
public class TDMSChannel {
    private String name;
    private ArrayList<TDMSProperty> properties;
    private ArrayList<Object> rawData;

    /**
     * Constructor for TDMSChannel.
     *
     * @param name The name of the channel.
     * @param properties The properties of the channel.
     * @param rawData The raw data of the channel.
     */
    public TDMSChannel(String name, ArrayList<TDMSProperty> properties, ArrayList<Object> rawData) {
        this.name = name;
        this.properties = properties;
        this.rawData = rawData;
    }

    /**
     * Gets the name of the channel.
     *
     * @return The name of the channel.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the properties of the channel.
     *
     * @return The properties of the channel.
     */
    public ArrayList<TDMSProperty> getProperties() {
        return properties;
    }

    /**
     * Gets the raw data of the channel.
     *
     * @return The raw data of the channel.
     */
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

    /**
     * Gets the value of a property by name.
     *
     * @param name The name of the property.
     * @return The value of the property.
     */
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

    /**
     * Returns a string representation of the TDMSChannel object.
     *
     * @return A string representation of the TDMSChannel object.
     */
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
