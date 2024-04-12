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
        //String path4 = "C:\Users\GRN1BJ\3D Objects\Desktop\Anicka\java\TDMSReader\TDMSFiles\Test_OK_Sample_20210916_125133 (3).tdms";
        String path5 = "TDMSFiles\\sent_test_20200826_135215_0001 (1).tdms";
        String path6 = "TDMSFiles\\NH3_concentration_1a_0002 (1).tdms";
        String path7 = "example.tdms";
        String path8 = "TDMSFiles\\test_TDMS_20240404_0001-0009_joined.tdms";

        try {
            TDMSFile tdmsFile = TDMSFile.read(path6);
            tdmsFile.printLeadInData();
            tdmsFile.printMetaData();
            ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
            TDMSGroup tdmsGroup = tdmsFile.getGroup("/");
            ArrayList<Property> properties = tdmsGroup.getProperties();
            //properties.toString();
            System.out.println(groups);
            System.out.println(tdmsGroup);



            //TDMSGroup.getGroup();


        } catch (IOException e) {
            System.err.println("An error occurred while reading the TDMS file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}