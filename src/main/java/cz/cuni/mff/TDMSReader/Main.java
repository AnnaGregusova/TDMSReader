import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String path1 = "/Users/annagregusova/java/TDMS_library/TDMS_files/Sample_2021_2496_20210916_123804.tdms";
        String path2 = "/Users/annagregusova/java/TDMS_library/TDMS_files/coilResistanceTest_20210819_150423_0001.tdms";
        String path3 = "/Users/annagregusova/java/TDMS_library/TDMS_files/Test_OK_Sample_20210916_125133 (2).tdms";
        String path4 = "/Users/annagregusova/java/TDMS_library/TDMS_files/Test_OK_Sample_20210916_125133 (1).tdms";
        String path5 = "/Users/annagregusova/java/TDMS_library/TDMS_files/sent_test_20200826_135215_0001.tdms";
        String path6 = "/Users/annagregusova/java/TDMS_library/TDMS_files/NH3_concentration_1a_0002.tdms";
        String path7 = "example.tdms";



        try {
            //readPath1(path1);
            //readPath2(path2);
            //readPath3(path3);
            readPath4(path4);
            //readPath5(path5);

        }

        catch (IOException e) {
            System.err.println("An error occurred while reading the TDMS file: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private static void readPath1(String path) throws IOException{

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<Property> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);

        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'S2021_2496_pressureRaw'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_increment");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }
    private static void readPath2(String path) throws IOException{

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<Property> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'AI channels'/'-AI-Temperature'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_samples");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);


    }
    private static void readPath3(String path) throws IOException{

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<Property> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);

    }
    private static void readPath4(String path) throws IOException {

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<Property> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'Test_OK_Sample_invPressureMSN'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }
    private static void readPath5(String path) throws IOException {

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<Property> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'Sample_01_level_status'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }

}