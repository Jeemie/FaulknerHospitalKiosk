package Map;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NoPathException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Observable;
import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A class the represents a building.
 */
public class Building extends Observable {

    private BuildingState state;
    private UUID uniqueID; // A randomly generated UUID associated with the current building
    private ArrayList<Floor> floors; // A list of all of the floors in the building
    private final AStar aStarSearch; // The AStar algorithm associated with the current building
    private static BuildingObserver observer = new BuildingObserver(); // Observer for all of the buildings
    private static final Logger LOGGER = LoggerFactory.getLogger(Building.class); // Logger for this class

    /**
     * Default constructor for the building class.
     */
    public Building() {

        this.state = BuildingState.NORMAL;
        this.uniqueID = UUID.randomUUID();
        this.floors = new ArrayList<>();
        this.aStarSearch = new AStar(this);

        LOGGER.info("Created new Building: " + this.toString());

        // start observing the building
        observer.observeBuilding(this);

    }

    /**
     * TODO
     *
     * @param filePath
     * @throws IOException
     */
    public void loadFromFile(URL filePath) throws IOException {

        LOGGER.info("Loading the building from the file: " + filePath.toString());

    }

    /**
     * TODO
     *
     * @param filePath
     * @throws IOException
     */
    public void saveToFile(URL filePath) throws IOException {
        new Thread() {
            @Override
            public void run() {

                try {
                    Writer writer = new FileWriter(filePath.toString());
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(floors, writer);
                    writer.close();
                } catch (IOException e) {
                    // exception handler code here
                    // ...
                }

                LOGGER.info("Saving the building to the file: " + filePath.toString());
            }
        }.start();
    }


    /**
     * TODO
     *
     * @param floor
     * @param location
     * @return
     */
    public Node addNode(int floor, Location location) throws FloorDoesNotExistException {

        // Attempts to add a node to the specified floor
        Floor currentFloor = getFloor(floor);
        Node newNode = currentFloor.addNode(location);

        // mark as value changed
        hasChanged();

        // trigger notification
        notifyObservers();

        return newNode;
    }

    /**
     * TODO
     *
     * @param destinationType
     * @return
     */
    public ArrayList<String> getBuildingDestinations(Destination destinationType) {

        //ArrayList to hold the entire list of destinations
        ArrayList<String> destinations = new ArrayList<>();

        // Iterate through each floor
        for (Floor currentFloor : floors) {

            // Add the destinations of the current type at the current floor to the list of destinations
            destinations.addAll(currentFloor.getFloorDestinations(destinationType));

        }

        // Return the entire list of destinations of the given type
        return destinations;
    }

    /**
     * TODO
     *
     * @return
     */
    public ArrayList<String> getBuildingDestinations() {

        //ArrayList to hold the entire list of destinations
        ArrayList<String> dests = new ArrayList<>();

        //temp variable that is used to hold return value of
        ArrayList<String> temp;

        //iterate through each floor
        for(int i = 0; i < floors.size(); i++){

            //get a list of destinations on current floor
            temp = floors.get(i).getFloorDestinations();

            //iterate through the list of destinations
            for (int j = 0; j < temp.size(); j++) {

                //add each destination string to the list of destinations
                dests.add(temp.get(j));
            }

        }

        //return list of destinations
        return dests;
    }

    /**
     * TODO
     *
     * @param floorNumber
     * @return
     */
    public Floor getFloor(int floorNumber) throws FloorDoesNotExistException {

        // iterate through array of floors and get each floorNumber from the array
        for (Floor currentFloor : floors) {

            //if a floorNumber exists with the specified floorNumber number, return that floorNumber
            if (currentFloor.getFloor() == floorNumber) {

                //mark as value changed
                setChanged();

                //return current floorNumber
                return currentFloor;
            }

        }

        // floor does not exist
        throw new FloorDoesNotExistException(floorNumber);
    }

    public void addFloor(int floorNumber) {


        for (Floor currentFloor : floors) {

            // if a floor exists with the specified floor number, don't add a new floor.
            if (currentFloor.getFloor() == floorNumber) {

                //return current floor
                return;
            }

        }

        Floor newFloor = new Floor(floorNumber, this);

        floors.add(newFloor);

        notifyObservers();

    }


    /**
     * TODO
     *
     * @param startNode
     * @param destinationNode
     * @return
     */
    public ArrayList<Node> getShortestPath(Node startNode, Node destinationNode) throws NoPathException {

        LOGGER.info("Getting the shortest path between " + startNode.toString() + " and " + destinationNode.toString());

        try {

            // run aStar algorithm
            return aStarSearch.getPath(startNode, destinationNode);

        } catch (NoPathException e) {

            LOGGER.error("NoPathException: ", e);

            throw e;

        }

    }

    /**
     * Getter for the building's observer.
     *
     * @return The current building's observer.
     */
    public BuildingObserver getBuildingObserver(){

        return this.observer;
    }

    /**
     * Getter for the building's current state.
     *
     * @return The state of the building.
     */
    public BuildingState getState() {

        return state;
    }

    /**
     * Sets the state of the building
     *
     * @param state The state you want to set the building to
     */
    public void setState(BuildingState state) {

        this.state = state;

    }

    @Override
    public String toString() {

        return this.uniqueID.toString();
    }

}