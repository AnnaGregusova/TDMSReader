package cz.cuni.mff.TDMSReader;
import java.util.ArrayList;

/**
 * Represents a group within a TDMS (Technical Data Management Streaming) file.
 */
public class TDMSGroup {
    private ArrayList<TDMSProperty> properties;
    private String name;
    private ArrayList<TDMSChannel> channels;

    /**
     * Constructs a TDMSGroup object with the specified name, properties, and channels.
     *
     * @param name       The name of the group.
     * @param properties The properties associated with the group.
     * @param channels   The channels belonging to the group.
     */
    public TDMSGroup(String name, ArrayList<TDMSProperty> properties, ArrayList<TDMSChannel> channels){
        this.name = name;
        this.properties = properties;
        this.channels = channels;
    }

    /**
     * Retrieves the name of the group.
     *
     * @return The name of the group.
     */
    public String getName() {return name;}

    /**
     * Retrieves the properties associated with the group.
     *
     * @return An ArrayList of TDMSProperty objects representing the properties of the group.
     */
    public ArrayList<TDMSProperty> getProperties(){

        return properties;
    }

    /**
     * Retrieves the channels belonging to the group.
     *
     * @return An ArrayList of TDMSChannel objects representing the channels in the group.
     */
    public ArrayList<TDMSChannel> getChannels() {
        // Return the list of channels
        return this.channels;
    }

    /**
     * Retrieves a channel from the group by its name.
     *
     * @param name The name of the channel to retrieve.
     * @return The TDMSChannel object representing the retrieved channel, or null if not found.
     */
    public TDMSChannel getChannel(String name) {
        try {
            for (TDMSChannel channel : channels) {
                if (channel.getName().equals(name)) {
                    return channel;
                }
            }
            throw new ChannelNotFoundException("Channel with name '" + name + "' not found.");
        } catch (ChannelNotFoundException ex) {
            // Handle exception or rethrow if necessary
            System.err.println(ex.getMessage());
            return null; // Or throw another exception or handle it according to your needs
        }
    }


    /**
     * Provides a string representation of the TDMSGroup object.
     *
     * @return A string representation of the TDMSGroup object.
     */
    @Override
    public String toString() {
        return "TDMSGroup { " +
                "name= " + name + ' ' +
                '}';
    }
    public class ChannelNotFoundException extends Exception {
        public ChannelNotFoundException(String message) {
            super(message);
        }
    }
}
