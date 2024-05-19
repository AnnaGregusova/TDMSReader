package cz.cuni.mff.TDMSReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the main entry point of the application which reads
 * TDMS files based on user input and displays relevant data from these files.
 */
public class Main {

    /**
     * The main method which serves as the entry point for the program.
     * It handles user input to select different paths for reading TDMS files,
     * and provides options to exit the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6, path7, path8. Type 'exit' to quit.");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            try {
                switch (input) {
		    case "path1":
                        readPath1();
                        break;
                    case "path2":
                        readPath2();
                        break;
                    case "path3":
                        readPath3();
                        break;
                    case "path4":
                        readPath4();
                        break;
                    case "path5":
                        readPath5();
                        break;
                    case "path6":
                        readPath6();
                        break;
                    case "path7":
                        readPath7();
                        break;
                    case "path8":
                        readPath8();
                        break;
                    default:
                        System.out.println("Invalid path. Please choose one of the listed paths.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading the TDMS file: " + e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Reads and processes the TDMS file specified in the path1.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */

    private static void readPath1() throws IOException {
        String path = "tdms_files/Sample_2021_2496_20210916_123804.tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group properties: ");
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println(groupProperties);
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'SENT channels'/'Date time\n" +
                "S2021_2496_pressure'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_increment");
        System.out.println("Getting channel property Value: ");
        System.out.println(channelPropertyValue);
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");
    }

    /**
     * Reads and processes the TDMS file specified in the path2.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath2() throws IOException {

        String path = "tdms_files/coilResistanceTest_20210819_150423_0001.tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'AI channels'/'-AI-Temperature'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        ArrayList<Object> rawData = tdmsChannel.getRawData();
        System.out.println(rawData);
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        Object channelPropertyValue = tdmsChannel.getPropertyValue("wf_samples");
        System.out.println("Property value: " + channelPropertyValue);
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");
    }

    /**
     * Reads and processes the TDMS file specified in the path3.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath3() throws IOException {
        String path = "tdms_files/Test_OK_Sample_20210916_125133 (1).tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println("Group channels:" + channels);
        System.out.println("ALL RAW DATAS");
        int countOfRawDatasToPrint = 10;
        for (TDMSChannel tdmsChannel : channels) {
            ArrayList<Object> rawData = tdmsChannel.getRawData(10);
            System.out.println(tdmsChannel.getName());
            System.out.println(rawData);
        }
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");

    }

    /**
     * Reads and processes the TDMS file specified in the path4.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath4() throws IOException {

        String path = "tdms_files/sent_test_20200826_135215_0001.tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        ArrayList<TDMSChannel> channels = tdmsGroup.getChannels();
        System.out.println(channels);
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'SENT channels'/'Date time\n" +
                "Sample_01_temperature_status'");
        ArrayList<Object> rawData = tdmsChannel1.getRawData();
        System.out.println(rawData);
        ArrayList<TDMSProperty> groupProperties = tdmsGroup.getProperties();
        System.out.println("Printing group properties: ");
        System.out.println(groupProperties);
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'SENT channels'/'Sample_01_temperature'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel2.getProperties());
        Object channelPropertyValue = tdmsChannel2.getPropertyValue("wf_start_time");
        System.out.println("Property value: " + channelPropertyValue);
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");
    }

    /**
     * Reads and processes the TDMS file specified in the path5.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath5() throws IOException {
        String path = "tdms_files/NH3_concentration_1a_0002.tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel = tdmsGroup.getChannel("/'AI channels'/'Date time\n" +
                "-AI-NH3_concentration'");
        System.out.println("Channel name: " + tdmsChannel.getName());
        System.out.println("Printing channel properties");
        System.out.println(tdmsChannel.getProperties());
        int numberOfRawDataToPrint = 20;
        for (TDMSGroup tdmsGroup1: groups){
            ArrayList<TDMSChannel> channels = tdmsGroup1.getChannels();
            for (TDMSChannel tdmsChannel1: channels) {
                ArrayList<Object> rawData = tdmsChannel.getRawData(numberOfRawDataToPrint);
                System.out.println(tdmsChannel1.getName());
                System.out.println(rawData);
            }
        }

        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");
    }

    /**
     * Reads and processes the TDMS file specified in the path6.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath6() throws IOException {

        String path = "tdms_files/test_TDMS_20240404_103108_0009.tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);
        System.out.println("File groups: "+ tdmsFile.getGroups());
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
        ArrayList<Object> rawData = tdmsChannel.getRawData();
        System.out.println(rawData);
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");
    }

    /**
     * Reads and processes the TDMS file specified in the path7.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath7() throws IOException {
        String path = "tdms_files/09_Point_T60C_V16000mV_20240315_102139.tdms";

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        System.out.println("Group name: " + tdmsGroup.getName());
        System.out.println("ALL RAW DATAS");

        for (TDMSGroup group : groups) {
            ArrayList<TDMSChannel> channels = group.getChannels();
            for (TDMSChannel tdmsChannel : channels) {
                ArrayList<Object> rawData = tdmsChannel.getRawData(1);
                System.out.println(tdmsChannel.getName());
                System.out.println(rawData);
            }

        }
        System.out.println("Choose which path do you want to read: " +
                "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");
    }

    /**
     * Reads and processes the TDMS file specified in the path8.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath8() throws IOException {
        String path = "Wrong_path";
        TDMSFile tdmsFile = TDMSFile.read(path);

    }
}
