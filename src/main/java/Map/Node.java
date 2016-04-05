package Map;

import java.util.*;

import static sun.misc.Version.print;
import static sun.misc.Version.println;

/**
 * TODO
 */
public class Node implements Observer{

    private double heuristicCost; // heuristic cost for AStar algorithm
    private final UUID uniqueID; // A randomly generated UUID associated with the current node
    private Location location; // TODO
    private ArrayList<Node> adjacentNodes; // TODO
    private EnumMap<Destination, ArrayList<String>> destinations; // TODO
    private Floor currentFloor; // TODO

    private NodeObserver observer;

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
        this.observer = new NodeObserver();

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

        // call to observer checks if destinations have changed
        observer.observeDestinations(getDestinations());

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

        //call to observer checks if destinations have changed
        observer.observeDestinations(getDestinations());

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
        observer.observeAdjacentNodes(adjacentNodes);

    }

    /**
     * TODO
     *
     * @param destinationNode
     * @return
     */
    public double getDistanceBetweenNodes(Node destinationNode) {

        //temp value - location of destination node
        Location destinationLocation = destinationNode.getLocation();

        //preform distance formula -> distance = sqrt((x1-x2)^2 + (y1 - y2)^2)
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

        //if the node exists
        if (adjacentNodes.contains(adjacentNode)) {

            //removes the node from list of adjacent Nodes
            adjacentNodes.remove(adjacentNode);

            //removes this node from the other node's list of adjacent nodes
            adjacentNode.removeAdjacentNode(this);

        }

        //call to observer to check if AdjacentNodes has changed
        observer.observeAdjacentNodes(adjacentNodes);

    }

    /**
     * TODO
     *
     * @param heuristicCost
     */
    public void setHeuristicCost(double heuristicCost) {

        //set heuristicCost
        this.heuristicCost = heuristicCost;

        //call to observer that checks if heuristic cost has changed
        this.observer.observeHeuristicCost(heuristicCost);

    }

    /**
     * TODO
     *
     */
    public NodeObserver getObserver(){
        return this.observer;
    }

    /**
     * TODO
     *
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}

