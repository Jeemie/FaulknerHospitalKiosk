package Map.Memento;

import Map.Floor;
import Map.Map;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
public class BuildingMemento {

    // Building name
    private String name;

    // A randomly generated UUID associated with the current building
    private UUID uniqueID;

    // A list of all of the floor momentos in the building
    //   They have to be momentoes to avoid circular dependencies of the floor having
    //   current Building
    private ArrayList<FloorMemento> floorMomentos;

    // Map this building belongs to
    private UUID currentMapID;

    public BuildingMemento(String name, UUID uniqueID, ArrayList<Floor> floors, Map currentMap) {

        this.name = name;
        this.uniqueID = uniqueID;
        this.floorMomentos = new ArrayList<FloorMemento>();
        this.currentMapID = currentMap.getUniqueID();

        for (Floor floor : floors) {

            floorMomentos.add(new FloorMemento(floor.getFloorName(), floor.getUniqueID(), floor.getResourceFileName(), floor.getCurrentBuilding(), floor.getLocationNodes()));

        }

    }

}
