package Map;

import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * A class that represents a building.
 */
public class Building extends Observable implements Observer {

    // Building name
    private String name;

    // A randomly generated UUID associated with the current building
    private UUID uniqueID;

    // A list of all of the floors in the building
    private ArrayList<Floor> floors;

    // Map this building belongs to
    private Map currentMap;

    private static final Logger LOGGER = LoggerFactory.getLogger(Building.class); // Logger for this class

    /**
     * Building Constructor
     * @param name Name of the building
     */
    public Building(String name, Map currentMap) {

        this.name = name;
        this.uniqueID = UUID.randomUUID();
        this.floors = new ArrayList<Floor>();
        this.currentMap = currentMap;

        this.addObserver(this.currentMap);


        LOGGER.info("A new Building was created");

//        setChanged();
//        notifyObservers(UpdateType.BUILDINGADDED);

    }

    /**
     * Add a new floor to this building
     * Note : this method assumes each floor in a building has a unique name
     * @param floorName Name for new floor
     * @param resourceFileName Image you want to associate with the floor.
     * @return New floor added to this building
     */
    public Floor addFloor(String floorName, String resourceFileName) {

        for (Floor floor : floors) {

            // If a floor exists in this building with the same name, don't add a new floor.
            if (floor.getFloorName().equals(floorName)) {

                LOGGER.info("A floor with the name " + floorName + " already exists in this building.");

                // Return the existing floor
                return null;
            }

        }

        Floor newFloor = new Floor(floorName, resourceFileName, this);

        floors.add(newFloor);

        setChanged();
        notifyObservers(UpdateType.FLOORADDED);

        return newFloor;
    }

    public ArrayList<Destination> getBuildingDestinations() {

        ArrayList<Destination> buildingDestinations = new ArrayList<>();

        for (Floor floor : this.floors) {

            buildingDestinations.addAll(floor.getFloorDestinations());

        }

        return buildingDestinations;
    }

    public ArrayList<Destination> getBuildingDestinations(DestinationType destinationType) {

        ArrayList<Destination> buildingDestinations = new ArrayList<>();

        for (Floor floor : this.floors) {

            buildingDestinations.addAll(floor.getFloorDestinations(destinationType));

        }

        return buildingDestinations;
    }

    public ArrayList<LocationNode> getBuildingLocationNodes(ImageType associatedImage) {

        ArrayList<LocationNode> buildingLocationNodes = new ArrayList<>();

        for (Floor floor : this.floors) {

            buildingLocationNodes.addAll(floor.getFloorLocationNodes(associatedImage));

        }

        return buildingLocationNodes;
    }

    /**
     * Remove a floor from this building
     * @param oldFloor Floor to remove from building
     */
    public void removeFloor(Floor oldFloor) {

        floors.remove(oldFloor);

        setChanged();
        notifyObservers();

    }

    @Override
    public void update(Observable o, Object arg) {

        setChanged();
        notifyObservers(arg);
    }

    @Override
    public String toString() {

        return this.name;
    }

    public String getName() {

        return name;
    }

    public UUID getUniqueID() {

        return uniqueID;
    }

    public ArrayList<Floor> getFloors() {

        return floors;
    }

    public Map getCurrentMap() {

        return currentMap;
    }

}
