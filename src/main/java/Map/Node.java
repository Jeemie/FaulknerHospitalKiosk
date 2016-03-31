package Map;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.UUID;

public class Node {

    private double heuristicCost;
    private final UUID uniqueID;
    private Location location;
    private ArrayList<Node> adjacentNodes;
    private EnumMap<Destination, ArrayList<String>> destinations;
    private Floor currentFloor;

    public Node(double heuristicCost, Location location, Floor currentFloor) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = UUID.randomUUID();
        this.location = location;
        this.adjacentNodes = new ArrayList<>();
        this.destinations = new EnumMap<Destination, ArrayList<String>>(Destination.class);
        this.currentFloor = currentFloor;


    }

    public Node(double heuristicCost, UUID uniqueID, Location location, Floor currentFloor, EnumMap<Destination, ArrayList<String>> destinations) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = uniqueID;
        this.location = location;
        this.adjacentNodes = new ArrayList<>();
        this.destinations = destinations;
        this.currentFloor = currentFloor;

    }

    public void addDestination(Destination destination) {
    }

    public void removeDestination(Destination destination) {
    }

    public ArrayList<Destination> getDestinations(Destination destinationType) {
        return null;
    }

    public ArrayList<Destination> getDestinations() {
        return null;
    }

    public void addAdjacentNode(Node adjacentNode) {
    }

    public double getDistanceBetweenNodes(Node destinationNode) {
        return 0;
    }

    public double getHueristicCost() {
        return 0;
    }

    public Location getLocation() {
        return null;
    }

    public void removeAdjacentNode(Node adjacentNode) {
    }

    public void setHeuristicCost(double heuristicCost) {
    }

}
