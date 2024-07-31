//package cz.cuni.mff.TDMSReader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<String> readJSONFile(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(jsonFilePath));

        List<String> groupNames = new ArrayList<>();

        JsonNode groupNamesNode = rootNode.path("TDMSFile").path("Group_names");
        if (groupNamesNode.isArray()) {
            for (JsonNode groupNameNode : groupNamesNode) {
                groupNames.add(groupNameNode.asText());
            }
        }

        System.out.println(groupNames);
        return groupNames;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java cz.cuni.mff.TDMSReader.Reader <path-to-json-file>");
            System.exit(1);
        }

        String jsonFilePath = args[0];
        Reader reader = new Reader();
        try {
            reader.readJSONFile(jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
