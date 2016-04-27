package Map.Memento;

import Map.Enums.DestinationType;
import Map.LocationNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
@JsonSerialize
public class DestinationMemento {


    private String name;

    // Unique ID for this edge
    private UUID uniqueID;

    private String destinationTypeString;

    // LocationNode where this destination is located
    private UUID currentLocationNodeID;

    public DestinationMemento() {

        super();

    }

    public DestinationMemento(String name, UUID uniqueID, DestinationType destinationType, LocationNode currentLocationNode) {

        this.name = name;
        this.uniqueID = uniqueID;
        this.destinationTypeString = destinationType.toString();
        this.currentLocationNodeID = currentLocationNode.getUniqueID();

    }

    public UUID getUniqueID() {

        return uniqueID;
    }

    public String getName() {

        return name;
    }

    public String getDestinationTypeString() {

        return destinationTypeString;
    }

    public UUID getCurrentLocationNodeID() {

        return currentLocationNodeID;
    }
}
