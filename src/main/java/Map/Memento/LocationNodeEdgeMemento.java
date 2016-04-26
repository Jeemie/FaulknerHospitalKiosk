package Map.Memento;

import Map.Location;
import Map.LocationNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
@JsonSerialize
public class LocationNodeEdgeMemento {

     // Unique ID for this edge
    private UUID uniqueID;

    // Weight is determined by straight line distance
    private double weight;

    // Node to connect with edge
    private UUID locationNode1ID;

    // Other node to connect with
    private UUID locationNode2ID;

    public LocationNodeEdgeMemento (UUID uniqueID, LocationNode locationNode1, LocationNode locationNode2) {

        this.uniqueID = uniqueID;
        this.locationNode1ID = locationNode1.getUniqueID();
        this.locationNode2ID = locationNode2.getUniqueID();

    }

    public UUID getUniqueID() {

        return uniqueID;
    }

    public double getWeight() {

        return weight;
    }

    public UUID getLocationNode1ID() {

        return locationNode1ID;
    }

    public UUID getLocationNode2ID() {

        return locationNode2ID;
    }
}
