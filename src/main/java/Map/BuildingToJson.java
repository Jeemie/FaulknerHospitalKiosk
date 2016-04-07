package Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by maryannoconnell on 4/7/16.
 */

public class BuildingToJson {

    public static void saveToFile(File file, Building building) throws IOException, URISyntaxException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(file.toString());
        objectMapper.writeValue(file, building);
    }
}
