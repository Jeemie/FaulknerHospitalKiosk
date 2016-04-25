package Map.Memento;

import Map.Location;
import Map.LocationNode;

import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
public class LocationNodeEdgeMemento {

     // Unique ID for this edge
    private UUID uniqueID;

    // Weight is determined by straight line distance
    private double weight;

    // Node to connect with edge
    private UUID locationNode1ID;

    // Other node to connect with
    private UUID locationNode2ID;

    public LocationNodeEdgeMemento (UUID uniqueID, double weight, LocationNode locationNode1, LocationNode locationNode2) {

        this.uniqueID = uniqueID;
        this.weight = weight;
        this.locationNode1ID = locationNode1.getUniqueID();
        this.locationNode2ID = locationNode2.getUniqueID();

    }

}
