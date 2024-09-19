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
        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");

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
                    case "path9":
                        readPath9();
                        break;
                    case "path10":
                        readPath10();
                        break;
                    case "path11":
                        readPath11();
                        break;
                    case "path12":
                        readPath12();
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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'SENT channels'/'Date time\n" + "S2021_2496_pressure'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'SENT channels'/'S2021_2496_pressure'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");
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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'AI channels'/'Date time\n" + "-AI-Resistance'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'AI channels'/'-AI-Resistance'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");


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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'SENT channels'/'Date time\n" + "Test_OK_Sample_pressure'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'SENT channels'/'Test_OK_Sample_pressure'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");

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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'SENT channels'/'Date time\n" + "Sample_01_temperature'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'SENT channels'/'Sample_01_temperature'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");
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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'AI channels'/'Date time\n" + "-AI-NH3_concentration'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'AI channels'/'-AI-NH3_concentration'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");
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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'AI channels'/'-AI-Presure_Inlet_02'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'AI channels'/'-AI-Presure_Inlet_03'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");
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
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'AI channels'/'-AI-CURRENT_Heater-01'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSGroup tdmsGroup2 = tdmsFile.getGroup("/'SENT channels'");
        TDMSChannel tdmsChannel2 = tdmsGroup2.getChannel("/'SENT channels'/'Sample1_level_status'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");
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

    /**
     * Reads and processes the TDMS file specified in the path9.
     * It displays the file, its properties, groups, and channel data.
     *
     * @throws IOException If there is an issue reading the TDMS file.
     */
    private static void readPath9() throws IOException {

        String path = "tdms_files/H2_test_20240229_113102_0001 (1).tdms";

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'AI channels'/'-AI-CURRENT_Heater-01'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSGroup tdmsGroup2 = tdmsFile.getGroup("/'SENT channels'");
        TDMSChannel tdmsChannel2 = tdmsGroup2.getChannel("/'SENT channels'/'Sample1_level_status'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");

    }

    private static void readPath10() throws IOException { //pada na Java heap space nejspis proto ze je tam hodne channelu

        String path = "tdms_files/sent_test_20200826_135215_0001.tdms";
        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'SENT channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'SENT channels'/'Date time\n" + "Sample_01_temperature'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'SENT channels'/'Sample_01_temperature'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");

    }

    private static void readPath11() throws IOException { //totez jak u 10
        String path = "tdms_files/E2300282-07_ED2404273_0_2024-03-27_093509.tdms";

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs file leadindata: ");
        tdmsFile.printLeadInData();
        System.out.println("TDMs file metadata: ");
        tdmsFile.printMetaData();


        System.out.println("TDMs File: " + tdmsFile);

        System.out.println("TDMs file properties: " + tdmsFile.getProperties());

        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("File groups: " + groups);

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6. Type 'exit' to quit.");

    }

    private static void readPath12() throws IOException { //tady je nejak mo cdivne segmentu

        String path = "tdms_files/test_TDMS_20240404_103108_0009.tdms";

        TDMSFile tdmsFile = TDMSFile.read(path);
        System.out.println("TDMs file: " + tdmsFile);
        System.out.println("TDMs file properties: " + tdmsFile.getProperties());
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        System.out.println("TDMS file groups: " + groups);
        ArrayList<TDMSChannel> channels = new ArrayList<>();
        for (TDMSGroup group : groups) {
            channels.addAll(group.getChannels());

        }
        System.out.println("TDMS file channels: " + channels);
        TDMSGroup tdmsGroup = tdmsFile.getGroup("/'AI channels'");
        TDMSChannel tdmsChannel1 = tdmsGroup.getChannel("/'AI channels'/'-AI-Presure_Inlet_01'");
        System.out.println("Channel name: " + tdmsChannel1.getName());
        System.out.println("Channel properties: " + tdmsChannel1.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel1.getRawData(3));
        TDMSChannel tdmsChannel2 = tdmsGroup.getChannel("/'AI channels'/'-AI-Presure_Inlet_02'");
        System.out.println("Channel name: " + tdmsChannel2.getName());
        System.out.println("Channel properties: " + tdmsChannel2.getProperties());
        System.out.println("Channel rawData: " + tdmsChannel2.getRawData(3));

        System.out.println("Choose which path do you want to read: " + "path1, path2, path3, path4, path5, path6, path7, path8, path9, path10, path11, path12. Type 'exit' to quit.");

    }

}
