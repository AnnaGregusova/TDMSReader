import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String path1 = "TDMSFiles\\Sample_2021_2496_20210916_123804 (1).tdms"; 
        String path2 = "TDMSFiles\\coilResistanceTest_20210819_150423_0001 (1).tdms"; 
        String path3 = "TDMSFiles\\Test_OK_Sample_20210916_125133 (3).tdms"; 
        String path4 = "TDMSFiles\\sent_test_20200826_135215_0001 (1).tdms"; 
        String path5 = "TDMSFiles\\NH3_concentration_1a_0002 (1).tdms";     
        String path6 = "TDMSFiles\\test_TDMS_20240404_0001-0009_joined.tdms"; 
        String path7 = "TDMSFiles\\E2300282-07_ED2404273_0_2024-03-27_093509.tdms ";
        String path8 = "TDMSFiles\\test_TDMS_20240404_103108_0009.tdms"; 
        String path9 = "TDMSFiles\\09_Point_T60C_V16000mV_20240315_102139.tdms"; 
        String path10 = "TDMSFiles\\test_TDMS_20240404_093532_0001.tdms"; //9k
        String path11 = "TDMSFiles\\test_TDMS_20240404_103108_0009.tdms"; //vice segmentu 13
        String path12 = "example.tdms"; 
    
        try {
            //readPath1(path1);
            //readPath2(path2);
            readPath3(path3);
            //readPath4(path4);
            //readPath5(path5);
            //readPath6(path6); //joined files
            //readPath7(path7); // 119 objects
            //readPath8(path8); // mega moc segmentu
            //readPath9(path9); //two groups
           //readPath10(path10); // 9k
           //readPath11(path11);
      

            

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
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
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
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
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
        tdmsFile.printLeadInData();
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        //System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'Test_OK_Sample_pressure'");
        System.out.println(tdmsChannel.getProperties());
        //System.out.println(channels);
        //ArrayList<Property> groupProperties = tdmsGroup.getProperties();
        /*System.out.println("Printing group properties: ");
        System.out.println(groupProperties);*/

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
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
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
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'AI channels'/'-AI-NH3_concentration'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }
    private static void readPath6(String path) throws IOException {

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

    }
    private static void readPath7(String path) throws IOException {

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);


    }
    private static void readPath8(String path) throws IOException {

        
        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'AI channels'/'-AI-NH3_concentration'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);


    }

    private static void readPath9(String path) throws IOException{

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        for (TDMSGroup tdmsGroup : groups) {
            System.out.println("All tdmsChannels in file");
            System.out.println(tdmsGroup.getChannels());    
        }

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'Sample1_temperature'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }
    private static void readPath10(String path) throws IOException{

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'Sample1_temperature'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }
    private static void readPath11(String path) throws IOException{

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'AI channels'/'-AI-Presure_Inlet_02'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_start_time");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);

    }

}