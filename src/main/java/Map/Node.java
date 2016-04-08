package Map;

import com.fasterxml.jackson.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static sun.misc.Version.print;

/**
 * TODO
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uniqueID")
public class Node extends Observable {

    private double heuristicCost; // heuristic cost for AStar algorithm
    private UUID uniqueID; // A randomly generated UUID associated with the current node
    private Location location; // TODO
    private ArrayList<Node> adjacentNodes; // TODO
    private EnumMap<Destination, ArrayList<String>> destinations; // TODO
    private Floor currentFloor; // TODO
    @JsonIgnore
    private static NodeObserver observer = new NodeObserver(); // Observer Object watching all Node objects
    @JsonIgnore
    private static final Logger LOGGER = LoggerFactory.getLogger(Node.class); // Logger for this class


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

        observer.observeNode(this);  //starts observing new Node object

    }




    /**
     * TODO
     *  @param heuristicCost
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

        observer.observeNode(this); //starts observing new Node object

    }

    /**
     * TODO
     *
     * @param destination
     * @param name
     */
    public void addDestination(Destination destination, String name) {

        ArrayList<String> temp;

        if(destinations.containsKey(destination)) {

            temp = destinations.get(destination);
            temp.add(name);

        } else {

            temp = new ArrayList<String>();
            temp.add(name);

            destinations.put(destination,temp);



        }

        setChanged();

        notifyObservers();


    }

    @JsonIgnore
    public NodeObserver getNodeObserver(){
        return observer;
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
            setChanged();

        }



        notifyObservers();

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
    @JsonGetter
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

            setChanged();



        }
        notifyObservers();


    }

    /**
     * The straight line distance between two nodes, ignoring the floor they are on.
     *
     * @param destinationNode The node you want to get the distance to.
     * @return The distance between two nodes.
     */
    public double getDistanceBetweenNodes(Node destinationNode) {

        // location of destination node
        Location destinationLocation = destinationNode.getLocation();

        // return the distance between the nodes
        return this.location.getDistanceBetween(destinationLocation);
    }

    /**
     * TODO
     *
     * @return
     */
    @JsonIgnore
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

            setChanged();

        }

        //call to observer to check if AdjacentNodes has changed
        notifyObservers();

    }

    /**
     * TODO
     *
     * @param cost
     */
    public void setHeuristicCost(double cost) {

        if (this.heuristicCost != cost) {

            this.heuristicCost = cost;
            setChanged();

        }

        notifyObservers();

    }

    @Override
    public String toString() {

        return uniqueID.toString();
    }

    @JsonGetter
    public UUID getUniqueID() {
        return uniqueID;
    }

}

