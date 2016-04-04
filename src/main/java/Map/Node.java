package Map;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Set;
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

        ArrayList<String> temp;

        if(destinations.containsKey(destination)){

            temp = destinations.get(destination);
            temp.add(destination.getName());

        } else {

            temp = new ArrayList<String>();
            temp.add(destination.getName());

            destinations.put(destination,temp);

        }

    }

    public void removeDestination(Destination destination) {

        if (destinations.containsKey(destination)) {

            ArrayList<String> temp = destinations.get(destination);

            temp.remove(destination.getName());

        }

    }

    // TODO
    public ArrayList<Destination> getDestinations(Destination destinationType) {

        return null;
    }

    // TODO
    public ArrayList<Destination> getDestinations() {

//        Set<Destination> entries = destinations.keySet();
//
//        for (Destination d : entries) {
//
//        }
        return null;
    }

    public void addAdjacentNode(Node adjacentNode) {

        if (!adjacentNodes.contains(adjacentNode)) {

            adjacentNodes.add(adjacentNode);

            adjacentNode.addAdjacentNode(this);

        }

    }

    public double getDistanceBetweenNodes(Node destinationNode) {

        Location destinationLocation = destinationNode.getLocation();

        double xDistance = Math.pow((this.location.getX() - destinationLocation.getX()), 2);
        double yDistance = Math.pow((this.location.getY() - destinationLocation.getY()), 2);

        return Math.sqrt(xDistance + yDistance);
    }

    public double getHueristicCost() {
        return 0;
    }

    public Location getLocation() {

        return location;
    }

    public void removeAdjacentNode(Node adjacentNode) {

        if (adjacentNodes.contains(adjacentNode)) {

            adjacentNodes.remove(adjacentNode);
            adjacentNode.removeAdjacentNode(this);

        }

    }

    public void setHeuristicCost(double heuristicCost) {

        this.heuristicCost = heuristicCost;
    }

}
