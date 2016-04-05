package Map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;
import java.math.*;

/**
 * TODO
 */
public class Building {

    private UUID uniqueID; // A randomly generated UUID associated with the current building
    private ArrayList<Floor> floors; // TODO
    private final AStar aStarSearch; // TODO

    /**
     * TODO
     */
    public Building() {

        this.uniqueID = UUID.randomUUID();
        this.floors = new ArrayList<>();
        this.aStarSearch = new AStar(this);

    }

    /**
     * TODO
     *
     * @param filePath
     * @throws IOException
     */
    public void loadFromFile(URL filePath) throws IOException {

    }

    /**
     * TODO
     *
     * @param filePath
     * @throws IOException
     */
    public void saveToFile(URL filePath) throws IOException {

    }

    /**
     * TODO
     *
     * @param floor
     * @param location
     * @return
     */
        public Node addNode(int floor, Location location) {

            //adds node to the specified floor
            return getFloor(floor).addNode(location);
    }

    /**
     * TODO
     *
     * @param destinationType
     * @return
     */
    public ArrayList<String> getBuildingDestinations(Destination destinationType) {

        //ArrayList to hold the entire list of destinations
        ArrayList<String> dests = new ArrayList<>();

        //temp variable that is used to hold return value of
        ArrayList<String> temp;

        //iterate through each floor
        for(int i = 0; i < floors.size(); i++){

            //get a list of destinations on current floor
            temp = floors.get(i).getFloorDestinations(destinationType);

            //iterate through the list of destinations
            for (int j = 0; j < temp.size(); j++) {

                //add each destination string to the list of destinations
                dests.add(temp.get(j));
            }

        }

        //return the entire list of destinations
        return dests;
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
     * @param floor
     * @return
     */
    public Floor getFloor(int floor) {

        //if floor is valid
        if (floor < floors.size()) {

            return floors.get(floor);

        // floor is invalid
        } else { return null; }
    }



    /**
     * TODO
     *
     * @param startNode
     * @param destinationNode
     * @return
     */
    public ArrayList<Node> getShortestPath(Node startNode, Node destinationNode) {

        //run aStar algorithm
        return aStarSearch.getPath(startNode, destinationNode);
    }

}