package Map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Building {

    private ArrayList<Floor> floors;
    private final AStar aStarSearch;

    public Building() {

        this.aStarSearch = new AStar(this);

    }

    public Node addNode(int floor, Location location) {
        return null;
    }

    public void saveToFile(URL filePath) throws IOException {
    }

    public ArrayList<Destination> getBuildingDestinations(Destination destinationType) {
        return null;
    }

    public ArrayList<Destination> getBuildingDestinations() {
        return null;
    }

    public void loadFromFile(URL filePath) throws IOException {
    }

    public Floor getFloor(int floor) {
        return null;
    }

    public ArrayList<Node> getShortestPath(Node startNode, Node destinationNode) {
        return null;
    }

}