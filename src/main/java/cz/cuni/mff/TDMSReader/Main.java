import java.io.IOException;

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
            TDMSFile tdmsFile = TDMSFile.read(path7);
            tdmsFile.printLeadInData();
            tdmsFile.printMetaData();
            //TDMSGroup.getGroup();


        } catch (IOException e) {
            System.err.println("An error occurred while reading the TDMS file: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
