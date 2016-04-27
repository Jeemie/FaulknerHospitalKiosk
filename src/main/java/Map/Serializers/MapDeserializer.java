package Map.Serializers;

import Map.Map;
import Map.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by maryannoconnell on 4/21/16.
 * Adapted from http://www.davismol.net/2015/05/20/jackson-create-a-custom-json-deserializer-with-stddeserializer-and-jsontoken-classes/
 */
public class MapDeserializer extends StdDeserializer<Map> {

    public MapDeserializer(Class<Map> t) {

        super(t);
    }

    @Override
    public Map deserialize(JsonParser jp, DeserializationContext dc) throws IOException {

        UUID uniqueID = null;
        String name = null;
        ArrayList<UUID> mapB = new ArrayList<>();
        LocationNode startLocationNode = null;
        Floor currentFloor = null;
        Building currentBuilding = null;

        JsonToken currentToken = null;

        while ((currentToken = jp.nextValue()) != null) {

            switch (currentToken) {

                case VALUE_STRING:

                    switch (jp.getCurrentName()) {

                        case "name":

                            name = jp.getText();
                            break;

                        case "uniqueID":

                            uniqueID = jp.getCodec().readValue(jp, UUID.class);
                            break;

                        case "currentBuilding":

                            System.out.println("String");
                            currentBuilding = jp.getCodec().readValue(jp, Building.class);
                            break;

                        case "currentFloor":

                            currentFloor = (Floor) jp.getCurrentValue();
                            break;

                        case "currentLocationNode":

                            startLocationNode = jp.getCodec().readValue(jp, LocationNode.class);
                            break;

                        default:
                            break;

                    }
                    break;

                case NOT_AVAILABLE:
                    break;
                case START_OBJECT:

                    switch (jp.getCurrentName()) {
                        case "currentBuilding":
                            System.out.println("Start building object!");
                            currentBuilding = jp.getCodec().readValue(jp, Building.class);
                            break;

                        case "currentFloor":
                            System.out.println("Start floor object!");
                            currentFloor = jp.getCodec().readValue(jp, Floor.class);
                            break;

                        case "startLocationNode":
                            System.out.println("Start start-location object!");
                            startLocationNode = jp.getCodec().readValue(jp, LocationNode.class);
                            break;


                        default:
                            break;
                    }
                    break;

                case END_OBJECT:
                    break;
                case START_ARRAY:

                    switch (jp.getCurrentName()) {
                        case "mapBuildings":
                            System.out.println("Start array flag");
                            //mapBuildings.add(jp.getCodec().readValue(jp, Building.class));
                            // List<MyClass> myObjects = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, MyClass.class));
                            break;

                        default:
                            break;
                    }


                    break;
                case END_ARRAY:
                    System.out.println("End array flag");
                    break;
                case FIELD_NAME:
                    System.out.println("field name flag");
                    break;
                case VALUE_EMBEDDED_OBJECT:
                    System.out.println("embedded obj flag");
                    break;
                case VALUE_NUMBER_INT:
                    break;
                case VALUE_NUMBER_FLOAT:
                    break;
                case VALUE_TRUE:
                    break;
                case VALUE_FALSE:
                    break;
                case VALUE_NULL:
                    System.out.println("null flag");
                    break;
                default:
                    break;
            }

        }

        //TODO figure out how to deserialize array of buildings, then delete the following few lines
        //uniqueID = UUID.randomUUID();
        //mapBuildings = new ArrayList<Building>();

        //return new Map(uniqueID, name, mapBuildings, startLocationNode, currentFloor, currentBuilding);
        return new Map("test");

    }

}