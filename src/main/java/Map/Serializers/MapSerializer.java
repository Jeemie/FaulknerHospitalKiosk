package Map.Serializers;

import Map.Map;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by maryannoconnell on 4/21/16.
 * Adapted from http://www.davismol.net/2015/05/20/jackson-create-a-custom-json-deserializer-with-stddeserializer-and-jsontoken-classes/
 */

public class MapSerializer extends StdSerializer<Map> {

    public MapSerializer() {
        super(Map.class);
    }

    public MapSerializer(Class<Map> t) {
        super(t);
    }

    @Override
    public void serialize(Map map, JsonGenerator jgen, SerializerProvider sp) throws IOException {

        jgen.writeStartObject();

        jgen.writeObjectField("uniqueID", map.getUniqueID());
        jgen.writeStringField("name", map.getName());
        jgen.writeObjectField("currentBuilding", map.getCurrentBuilding());
        jgen.writeObjectField("startLocationNode", map.getCurrentLocationNode()); // Start Node
        jgen.writeObjectField("currentFloor", map.getCurrentFloor());

        // Serialize array of buildings UUIDs
        // Commented because currently an array of buildings cannot be deserialized

        jgen.writeArrayFieldStart("buildingIdList");
        for (UUID buildingId : map.getBuildingIdList()) {
            jgen.writeObject(buildingId);
        }
        jgen.writeEndArray();


        jgen.writeEndObject();

    }
}