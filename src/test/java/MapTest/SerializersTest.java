package MapTest;

import Map.*;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Serializers.MapDeserializer;
import Map.Serializers.MapSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 * Created by maryannoconnell on 4/21/16.
 * Adapted from http://www.davismol.net/2015/05/20/jackson-create-a-custom-json-deserializer-with-stddeserializer-and-jsontoken-classes/
 */

public class SerializersTest {

    public static void main (String[] args) {

        Map mMap = new Map("Faulkner Hospital Map");
        mMap = FaulknerHospitalData.starterMap();


        ObjectMapper mapper = new ObjectMapper();

        SimpleModule mod = new SimpleModule("MyModule");
        mod.addSerializer(new MapSerializer(Map.class));
        mod.addDeserializer(Map.class, new MapDeserializer(Map.class));
        mapper.registerModule(mod);

        System.out.println("--- ORIGINAL OBJECT ---\n" + mMap);

        String s = null;

        try {
            s = mapper.writeValueAsString(mMap);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- JAVA to JSON (Custom) ---\n" + s);

        Map mapOut = null;
        try {
            mapOut = mapper.readValue(s, Map.class);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- JSON to JAVA ---\n" + mapOut);
    }

}
