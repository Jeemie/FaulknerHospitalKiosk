package Map.Memento;

import Map.Building;
import Map.LocationNode;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
public class FloorMemento {

    // The name of the floor
    private String floorName;

    // The uniqueID identifing the floor
    private UUID uniqueID;

    // The string which stores
    private String resourceFileName;

    // The building that the floor is associated with
    private UUID currentBuildingID;

    // A list of location nodes which exists on teh floor
    private ArrayList<LocationNodeMemento> locationNodeMomentos;

    public FloorMemento(String floorName, UUID uniqueID, String resourceFileName, Building currentBuilding, ArrayList<LocationNode> locationNodes) {

        this.floorName = floorName;
        this.uniqueID = uniqueID;
        this.resourceFileName = resourceFileName;
        this.currentBuildingID = currentBuilding.getUniqueID();
        this.locationNodeMomentos = new ArrayList<LocationNodeMemento>();

        for (LocationNode locationNode : locationNodes) {

            locationNodeMomentos.add(new LocationNodeMemento(locationNode.getName(), locationNode.getUniqueID(), locationNode.getLocation(), locationNode.getCurrentFloor(), locationNode.getAssociatedImage(), locationNode.getEdges(), locationNode.getDestinations()));

        }

    }



}
