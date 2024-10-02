package cz.cuni.mff.TDMSReader;

import java.util.ArrayList;

public class TDMSGroup {
    private ArrayList<TDMSProperty> properties;
    private String name;
    private ArrayList<TDMSChannel> channels;

    public TDMSGroup(String name, ArrayList<TDMSProperty> properties, ArrayList<TDMSChannel> channels) {
        this.name = name;
        this.properties = properties;
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TDMSProperty> getProperties() { return properties; }

    public ArrayList<TDMSChannel> getChannels() {
        // Return the list of channels
        return this.channels;
    }

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
