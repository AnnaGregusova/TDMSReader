package cz.cuni.mff.TDMSReader;
import java.util.ArrayList;

/**
 * Class representing metadata with information about groups and file properties.
 */
public class MetaData {
    private int numberOfObjects;
    private ArrayList<TDMSGroup> groups;
    private ArrayList<TDMSProperty> TDMSFileProperties;

    /**
     * Gets the number of objects in the metadata.
     *
     * @return The number of objects in the metadata.
     */
    public int getNumberOfObjects() {
        return numberOfObjects;
    }

    /**
     * Gets the list of TDMS file properties.
     *
     * @return The list of TDMS file properties.
     */
    public ArrayList<TDMSProperty> getTDMSFileProperties() {
        return TDMSFileProperties;
    }

    /**
     * Gets the list of groups.
     *
     * @return The list of groups.
     */
    public ArrayList<TDMSGroup> getGroups() {
        return groups;
    }

    /**
     * Constructs a MetaData object with the given groups and TDMS file properties.
     *
     * @param groups             The list of groups.
     * @param TDMSFileProperties The list of TDMS file properties.
     */
    public MetaData(ArrayList<TDMSGroup> groups, ArrayList<TDMSProperty> TDMSFileProperties) {
        this.groups = groups;
        this.TDMSFileProperties = TDMSFileProperties;
        this.numberOfObjects = groups.size() + TDMSFileProperties.size();
    }
}
