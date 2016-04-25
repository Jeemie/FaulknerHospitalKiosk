package Map.Memento;

import Map.Enums.DestinationType;
import Map.LocationNode;

import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
public class DestinationMemento {

    // Unique ID for this edge
    private UUID uniqueID;

    private String name;

    private DestinationType destinationType;

    // LocationNode where this destination is located
    private UUID currentLocationNodeID;

    public DestinationMemento(UUID uniqueID, String name, DestinationType destinationType, LocationNode currentLocationNode) {

        this.uniqueID = uniqueID;
        this.name = name;
        this.destinationType = destinationType;
        this.currentLocationNodeID = currentLocationNode.getUniqueID();

    }


}
