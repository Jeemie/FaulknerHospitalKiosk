package Map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

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

        //iterate through array of floors and get each floor from the array
        for(int i = 0; i < floors.size(); i++){

            Floor temp = floors.get(i);

            //if a floor exists with the specified floor number, return that floor
            if(temp.getFloor()==floor) {
                return temp;
            }
        }

        //if unable to find a floor with that floor number, create a new floor with specified number and return that floor
        return addFloorHelper(floor);
    }

    //used to add a floor
    private Floor addFloorHelper(int floor){

        //temp variable to hold Floor object with
        Floor temp = new Floor(floor, this);
        floors.add(temp);
        return temp;
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