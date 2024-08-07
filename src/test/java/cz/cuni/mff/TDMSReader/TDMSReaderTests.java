package cz.cuni.mff.TDMSReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TDMSReaderTests {
    private RandomAccessFile raFile;
    private LeadInDataReader leadInDataReader;
    ExpectedLeadInData.LeadInDataExpectations leadInDataExpectations;
    private Path resourceDirectory = Paths.get("src", "test", "resources", "tdms_files");
    private String tdmsFilePath = "/Users/annagregusova/TDMSReader/tdms_files/Sample_2021_2496_20210916_123804.tdms";
    private String jsonFilePath = "/Users/annagregusova/TDMSReader/json_files/Sample_2021_2496_20210916_123804.json";
    private String getPath(String filename) {
        return resourceDirectory.resolve(filename).toString();
    }

    String path1 = "tdms_files/Sample_2021_2496_20210916_123804.tdms";
    String path2 = "tdms_files/coilResistanceTest_20210819_150423_0001.tdms";
    String path3 = "tdms_files/Test_OK_Sample_20210916_125133 (1).tdms";
    String path4 = "tdms_files/sent_test_20200826_135215_0001.tdms";
    String path5 = "tdms_files/NH3_concentration_1a_0002.tdms";
    String path6 = "tdms_files/test_TDMS_20240404_103108_0009.tdms";
    String path7 = "tdms_files/09_Point_T60C_V16000mV_20240315_102139.tdms";

    private void initializeReaderForFile(String filePath) throws IOException {
        this.raFile = new RandomAccessFile(filePath, "r");
        this.leadInDataReader = new LeadInDataReader(raFile, 0);
    }

    @BeforeEach
    void setUp() throws IOException {
	String filePath = tdmsFilePath;
        initializeReaderForFile(filePath);
        leadInDataExpectations = ExpectedLeadInData.getExpectations(filePath);
    }

    @Test
    @DisplayName("Test Tag Validity")
    void testTagValidity() throws IOException {
        assertTrue(leadInDataReader.isValidTag(), "The tag should be valid.");
    }

    @Test
    @DisplayName("Test Version Number")
    void testVersionNumber() throws IOException {

        assertEquals(leadInDataExpectations.version, leadInDataReader.getVersion(), "Version number should match expected.");
    }

    @Test
    @DisplayName("Test Mask Value")
    void testMaskValue() throws IOException {

        assertEquals(leadInDataExpectations.mask, leadInDataReader.getMask(), "Mask value should match expected.");
    }

    @Test
    @DisplayName("Test Next Segment Offset")
    void testNextSegmentOffset() throws IOException {

        assertEquals(leadInDataExpectations.nextSegmentOffset, leadInDataReader.getNextSegment(), "Next segment offset should match expected.");
    }

    @Test
    @DisplayName("Test Raw Data Offset")
    void testRawDataOffset() throws IOException {

        assertEquals(leadInDataExpectations.rawDataOffset, leadInDataReader.getRawData(), "Raw data offset should match expected.");
    }


    @Test
    @DisplayName("Test Group Names")
    void testGroupNames() throws IOException, InterruptedException {
        List<String> expectedGroupNames = readJSONFile(jsonFilePath);
        List<String> actualGroupNames = getJavaGroupNames(tdmsFilePath);

        assertEquals(expectedGroupNames, actualGroupNames, "Group names should match expected.");
    }

    private List<String> readJSONFile(String jsonFilePath) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(this.jsonFilePath));

        List<String> groupNames = new ArrayList<>();

        JsonNode groupNamesNode = rootNode.path("TDMSFile").path("Group_names");
        if (groupNamesNode.isArray()) {
            for (JsonNode groupNameNode : groupNamesNode) {
                groupNames.add(groupNameNode.asText());
            }
        }

        return groupNames;
    }

    private List<String> getJavaGroupNames(String filePath) throws IOException {
        TDMSFile tdmsFile = TDMSFile.read(filePath);
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        List<String> group_names = new ArrayList<>();

        for (TDMSGroup group : groups) {
            group_names.add(group.getName());
        }

        return group_names;
    }
    @Test
    @DisplayName("Test Channel Names")
    void testChannelNames() throws IOException, InterruptedException {
        List<String> expectedGroupNames = getPythonChannelNames(jsonFilePath);
        List<String> actualGroupNames = getJavaChannelNames(tdmsFilePath);

        assertEquals(expectedGroupNames, actualGroupNames, "channels names should match expected.");
    }
    private List<String> getPythonChannelNames(String jsonFilePath) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(this.jsonFilePath));

        List<String> channelNames = new ArrayList<>();

        JsonNode groupNamesNode = rootNode.path("TDMSFile").path("Channel_names");
        if (groupNamesNode.isArray()) {
            for (JsonNode groupNameNode : groupNamesNode) {
                channelNames.add(groupNameNode.asText());
            }
        }

        return channelNames;
    }

    private List<String> getJavaChannelNames(String filePath) throws IOException {
        TDMSFile tdmsFile = TDMSFile.read(filePath);
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        List<String> channels_names = new ArrayList<>();
        for (TDMSGroup group : groups) {
            ArrayList<TDMSChannel> channels = group.getChannels();
            for (TDMSChannel tdmsChannel : channels) {
                channels_names.add(tdmsChannel.getName());
            }
        }
        return channels_names;
    }

    @Test
    @DisplayName("Test Channel Properties")
    void testChannelProperties() throws IOException, InterruptedException {
        List<String> expectedGroupNames = getPythonChannelNames(jsonFilePath);
        List<String> actualGroupNames = getJavaChannelNames(tdmsFilePath);

        assertEquals(expectedGroupNames, actualGroupNames, "channels names should match expected.");
    }
    private List<String> getPythonChannelProperties(String jsonFilePath) throws IOException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(this.jsonFilePath));

        List<String> channelNames = new ArrayList<>();

        JsonNode groupNamesNode = rootNode.path("TDMSFile").path("Channel_names");
        if (groupNamesNode.isArray()) {
            for (JsonNode groupNameNode : groupNamesNode) {
                channelNames.add(groupNameNode.asText());
            }
        }

        return channelNames;
    }

    private List<String> getJavaChannelProperties(String filePath) throws IOException {
        TDMSFile tdmsFile = TDMSFile.read(filePath);
        ArrayList<TDMSGroup> groups = tdmsFile.getGroups();
        List<String> channels_names = new ArrayList<>();
        for (TDMSGroup group : groups) {
            ArrayList<TDMSChannel> channels = group.getChannels();
            for (TDMSChannel tdmsChannel : channels) {
                channels_names.add(tdmsChannel.getName());
            }
        }
        return channels_names;
    }



    class ExpectedLeadInData {
        static TDMSReaderTests tdmsTests = new TDMSReaderTests();

        static final Map<String, LeadInDataExpectations> expectationsMap = new HashMap<>();

        static {
            expectationsMap.put(tdmsTests.path1, new LeadInDataExpectations(true, 4713, 14, 258094, 1582));
            expectationsMap.put(tdmsTests.path2, new LeadInDataExpectations(true, 4713, 14, 124402, 1282));
            expectationsMap.put(tdmsTests.path3, new LeadInDataExpectations(true, 4713, 14, 248828, 1628));
            expectationsMap.put(tdmsTests.path4, new LeadInDataExpectations(true, 4713, 14, 248828, 1628));
            expectationsMap.put(tdmsTests.path5, new LeadInDataExpectations(true, 4713, 14, 534942, 2718));
            expectationsMap.put(tdmsTests.path6, new LeadInDataExpectations(true, 4713, 14, 2074193, 593));

        }

        public static LeadInDataExpectations getExpectations(String filePath) {
            return expectationsMap.getOrDefault(filePath, new LeadInDataExpectations(true, 4713, 14, 258094, 1582));
        }

        public static class LeadInDataExpectations {
            boolean isValidTag;
            int version;
            int mask;
            long nextSegmentOffset;
            long rawDataOffset;

            public LeadInDataExpectations(boolean isValidTag, int version, int mask, long nextSegmentOffset, long rawDataOffset) {
                this.isValidTag = isValidTag;
                this.version = version;
                this.mask = mask;
                this.nextSegmentOffset = nextSegmentOffset;
                this.rawDataOffset = rawDataOffset;
            }
        }
    }
}


