package Map;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Set;
import java.util.UUID;

/**
 * TODO
 */
public class Node {

    private double heuristicCost; // heuristic cost for AStar algorithm
    private final UUID uniqueID; // A randomly generated UUID associated with the current node
    private Location location; // TODO
    private ArrayList<Node> adjacentNodes; // TODO
    private EnumMap<Destination, ArrayList<String>> destinations; // TODO
    private Floor currentFloor; // TODO

    /**
     * TODO
     *
     * @param heuristicCost
     * @param location
     * @param currentFloor
     */
    public Node(double heuristicCost, Location location, Floor currentFloor) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = UUID.randomUUID();
        this.location = location;
        this.adjacentNodes = new ArrayList<>();
        this.destinations = new EnumMap<Destination, ArrayList<String>>(Destination.class);
        this.currentFloor = currentFloor;

    }

    /**
     * TODO
     *
     * @param heuristicCost
     * @param uniqueID
     * @param location
     * @param currentFloor
     * @param destinations
     */
    public Node(double heuristicCost, UUID uniqueID, Location location, Floor currentFloor, EnumMap<Destination, ArrayList<String>> destinations) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = uniqueID;
        this.location = location;
        this.adjacentNodes = new ArrayList<>();
        this.destinations = destinations;
        this.currentFloor = currentFloor;

    }

    /**
     * TODO
     *
     * @param destination
     * @param name
     */
    public void addDestination(Destination destination, String name) {

        ArrayList<String> temp;

        if(destinations.containsKey(destination)){

            temp = destinations.get(destination);
            temp.add(name);

        } else {

            temp = new ArrayList<String>();
            temp.add(name);

            destinations.put(destination,temp);

        }

    }

    /**
     * TODO
     *
     * @param destination
     */
    public void removeDestination(Destination destination, String name) {

        if (destinations.containsKey(destination)) {

            ArrayList<String> temp = destinations.get(destination);

            temp.remove(name);

        }

    }

    /**
     * TODO
     *
     * @param destinationType
     * @return
     */
    public ArrayList<String> getDestinations(Destination destinationType) {

        ArrayList<String> nodeDestinations = new ArrayList<>();

        if (destinations.containsKey(destinationType)) {

            nodeDestinations.addAll(destinations.get(destinationType));

        }

        return nodeDestinations;
    }

    /**
     * TODO
     *
     * @return
     */
    public ArrayList<String> getDestinations() {

        Set<Destination> entries = destinations.keySet();

        ArrayList<String> nodeDestinations = new ArrayList<>();

        for (Destination d : entries) {

            nodeDestinations.addAll(destinations.get(d));

        }

        return nodeDestinations;
    }

    /**
     * TODO
     *
     * @param adjacentNode
     */
    public void addAdjacentNode(Node adjacentNode) {

        if (this.equals(adjacentNode)) {

            return;
        }

        if (!adjacentNodes.contains(adjacentNode)) {

            adjacentNodes.add(adjacentNode);

            adjacentNode.addAdjacentNode(this);

        }

    }

    /**
     * TODO
     *
     * @param destinationNode
     * @return
     */
    public double getDistanceBetweenNodes(Node destinationNode) {

        Location destinationLocation = destinationNode.getLocation();

        double xDistance = Math.pow((this.location.getX() - destinationLocation.getX()), 2);
        double yDistance = Math.pow((this.location.getY() - destinationLocation.getY()), 2);

        return Math.sqrt(xDistance + yDistance);
    }

    /**
     * TODO
     *
     * @return
     */
    public double getHueristicCost() {

        return heuristicCost;
    }

    /**
     * TODO
     *
     * @return
     */
    public Location getLocation() {

        return location;
    }

    /**
     * TODO
     *
     * @param adjacentNode
     */
    public void removeAdjacentNode(Node adjacentNode) {

        if (adjacentNodes.contains(adjacentNode)) {

            adjacentNodes.remove(adjacentNode);
            adjacentNode.removeAdjacentNode(this);

        }

    }

    /**
     * TODO
     *
     * @param heuristicCost
     */
    public void setHeuristicCost(double heuristicCost) {

        this.heuristicCost = heuristicCost;
    }

}
