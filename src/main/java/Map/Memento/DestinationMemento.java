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

    // Unique ID for this edge
    private UUID uniqueID;

    private String name;

    private String destinationTypeString;

    // LocationNode where this destination is located
    private UUID currentLocationNodeID;

    public DestinationMemento() {

        super();

    }

    public DestinationMemento(UUID uniqueID, String name, DestinationType destinationType, LocationNode currentLocationNode) {

        this.uniqueID = uniqueID;
        this.name = name;
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
