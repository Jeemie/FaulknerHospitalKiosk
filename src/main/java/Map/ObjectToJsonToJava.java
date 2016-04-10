package Map;

import Map.Exceptions.FloorDoesNotExistException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Created by maryannoconnell on 4/7/16.
 */

public class ObjectToJsonToJava {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectToJsonToJava.class); // Logger for this class

    public static void saveToFile(File file, Building building) throws IOException, URISyntaxException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, building);
    }

    /**
     * @param file
     * @throws IOException
     */
    public static Building loadFromFile(File file) throws IOException, FloorDoesNotExistException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        LOGGER.info("Loading the building from the file: " + file.toString());
        return objectMapper.readValue(file, Building.class);
    }
}

