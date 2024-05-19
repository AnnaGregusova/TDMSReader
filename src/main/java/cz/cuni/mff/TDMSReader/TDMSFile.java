package cz.cuni.mff.TDMSReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Represents a TDMS (Technical Data Management Streaming) file and provides methods to read its contents.
 */
public class TDMSFile {
    private static RandomAccessFile file;
    private static ArrayList<TDMSSegment> segments = new ArrayList<TDMSSegment>();
    private static ArrayList<TDMSGroup> groups = new ArrayList<TDMSGroup>();

    private static ArrayList<TDMSProperty> tdmsFileProperties = new ArrayList<>();

    /**
     * Constructs a TDMSFile object with the provided RandomAccessFile.
     *
     * @param file The RandomAccessFile representing the TDMS file.
     * @throws FileNotFoundException if the file cannot be found.
     */
    private TDMSFile(RandomAccessFile file) throws FileNotFoundException {
        this.file = file;
    }

    /**
     * Reads a TDMS file from the specified file path.
     *
     * @param filePath The path to the TDMS file.
     * @return A TDMSFile object representing the read file.
     * @throws IOException if an I/O error occurs while reading the file.
     */
    public static TDMSFile read(String filePath) throws IOException {

        try {
            int segmentOffset = 0;
            file = new RandomAccessFile(filePath, "r");
            long fileSize = file.length();
            TDMSFile tdmsFile = new TDMSFile(file);
            tdmsFile.readSegments(segmentOffset, fileSize);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        }
        finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new TDMSFile(file);
    }

    /**
     * Reads segments from the TDMS file recursively.
     *
     * @param segmentOffset The offset of the segment in the file.
     * @param fileSize      The size of the TDMS file.
     * @throws IOException if an I/O error occurs while reading the segments.
     */
    private void readSegments(long segmentOffset, long fileSize) throws IOException {
        int leadInDataSizeInBytes = 28;
        if (segmentOffset + leadInDataSizeInBytes < fileSize) {
            TDMSSegment segment = readSegment(segmentOffset);
            segmentOffset += segment.getLeadInData().getSegmentOffset();
            if (segment != null) {
                segments.add(segment);
                readSegments(segmentOffset, fileSize);
            }
        }
    }

    /**
     * Reads a segment from the TDMS file.
     *
     * @param segmentOffset The offset of the segment in the file.
     * @return A TDMSSegment object representing the read segment.
     * @throws IOException if an I/O error occurs while reading the segment.
     */
    private TDMSSegment readSegment(long segmentOffset) throws IOException {
        LeadInDataReader leadInDataReader = new LeadInDataReader(file, segmentOffset);
        int leadInDataByteCount = 28;
        MetaDataReader metaDataReader = new MetaDataReader(file, segmentOffset + leadInDataByteCount);

        if (leadInDataReader.isValidTag()) {
            LeadInData leadInData = leadInDataReader.createLeadInData();
            MetaData metaData = metaDataReader.createMetaData();
            return new TDMSSegment(leadInData, metaData);

        }
        else {
            System.out.println("Invalid TDMS file tag. The file might not be a valid TDMS file.");
            return null;
        }
    }
    /**
     * Retrieves the properties of the TDMS file.
     *
     * @return An ArrayList of TDMSProperty objects representing the properties of the file.
     */
    public ArrayList<TDMSProperty> getProperties(){

        for (TDMSSegment segment : segments){
            MetaData metaData = segment.getMetaData();
            tdmsFileProperties = metaData.getTDMSFileProperties();
        }
        return tdmsFileProperties;
    }

    /**
     * Retrieves a group from the TDMS file by its name.
     *
     * @param name The name of the group to retrieve.
     * @return The TDMSGroup object representing the retrieved group, or null if not found.
     */
    public TDMSGroup getGroup(String name) {
        try {
            for (TDMSSegment segment : segments) {
                for (int i = 0; i < segments.size(); i++) {
                    for (int j = 0; j < groups.size(); j++) {
                        TDMSGroup group = groups.get(j);
                        if (group.getName().equals(name)) {
                            return group;
                        }
                    }
                }
            }
            throw new GroupNotFoundException("Group with name '" + name + "' not found.");
        } catch (GroupNotFoundException ex) {
            // Handle exception or rethrow if necessary
            System.err.println(ex.getMessage());
            return null; // Or throw another exception or handle it according to your needs
        }
    }


    /**
     * Retrieves all groups from the TDMS file.
     *
     * @return An ArrayList of TDMSGroup objects representing all groups in the file.
     */
    public ArrayList<TDMSGroup> getGroups(){
        for (TDMSSegment segment : segments){
            MetaData metaData = segment.getMetaData();
            groups = metaData.getGroups();
        }
        return groups;
    }

    /**
    * Prints the metadata of the TDMS file segments.
    */
    public void printMetaData() {
        for (TDMSSegment segment : segments) {
            MetaData metaData = segment.getMetaData();
            System.out.println("Segment Meta Data:");
            System.out.println("Number of objects: " + metaData.getNumberOfObjects());
        }
    }

    /**
     * Prints the lead-in data of the TDMS file segments.
     */
    public void printLeadInData() {
        for (TDMSSegment segment : segments) {
            LeadInData leadInData = segment.getLeadInData();
            System.out.println("Segment LeadIn Data:");
            System.out.println("Tag: " + leadInData.getTag());
            System.out.println("Version Number: " + leadInData.getVersion());
            System.out.println("Mask: " + leadInData.getMask());
            System.out.println("Next Segment Offset: " + leadInData.getSegmentOffset());
            System.out.println("Raw Data Offset: " + leadInData.getRawDataOffset());
            System.out.println("--------------------------------");
        }
    }
    public class GroupNotFoundException extends Exception {
        public GroupNotFoundException(String message) {
            super(message);
        }
    }

}
