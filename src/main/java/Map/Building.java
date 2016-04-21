package Map;

import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * A class that represents a building.
 */

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uniqueID", scope=Building.class)
public class Building extends Observable implements Observer {

    // Building name
    private String name;

    // A randomly generated UUID associated with the current building
    private UUID uniqueID;

    // A list of all of the floors in the building
    private ArrayList<Floor> floors;

    // Map this building belongs to
    private Map currentMap;

    @JsonIgnore
    private static final Logger LOGGER = LoggerFactory.getLogger(Building.class); // Logger for this class

    /**
     * Jackson Constructor
     */
    public Building() {
        super();
    }

    /**
     * Building Constructor
     * @param name Name of the building
     */
    public Building(String name, Map currentMap) {

        this.name = name;
        this.uniqueID = UUID.randomUUID();
        this.floors = new ArrayList<Floor>();
        this.currentMap = currentMap;

    }

    /**
     * Add a new floor to this building
     * Note : this method assumes each floor in a building has a unique name
     * @param floorName Name for new floor
     * @param imageType Type of image to associate with new floor
     * @return New floor added to this building
     */
    public Floor addFloor(String floorName, ImageType imageType) {

        for (Floor floor : floors) {

            // If a floor exists in this building with the same name, don't add a new floor.
            if (floor.getFloorName().equals(floorName)) {

                LOGGER.info("A floor with the name " + floorName + " already exists in this building.");

                // Return the existing floor
                return floor;
            }

        }

        Floor newFloor = new Floor(floorName, imageType, this);

        floors.add(newFloor);

        setChanged();
        notifyObservers();

        return newFloor;

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

    @JsonGetter
    public String getName() {

        return name;
    }

    @JsonGetter
    public UUID getUniqueID() {

        return uniqueID;
    }

    @JsonGetter
    public ArrayList<Floor> getFloors() {

        return floors;
    }

    @JsonGetter
    public Map getCurrentMap() {

        return currentMap;
    }
}
