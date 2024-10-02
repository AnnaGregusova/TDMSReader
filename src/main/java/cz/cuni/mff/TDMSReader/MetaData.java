package cz.cuni.mff.TDMSReader;

import java.util.ArrayList;

public class MetaData {
    private int numberOfObjects;
    private ArrayList<TDMSGroup> groups;
    private ArrayList<TDMSProperty> TDMSFileProperties;

    public int getNumberOfObjects() {
        return numberOfObjects;
    }

    public ArrayList<TDMSProperty> getTDMSFileProperties() {
        return TDMSFileProperties;
    }

    public ArrayList<TDMSGroup> getGroups() {
        return groups;
    }

    public MetaData(ArrayList<TDMSGroup> groups, ArrayList<TDMSProperty> TDMSFileProperties) {
        this.groups = groups;
        this.TDMSFileProperties = TDMSFileProperties;
        this.numberOfObjects = groups.size() + TDMSFileProperties.size();
    }
}
