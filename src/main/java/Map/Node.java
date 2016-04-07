package Map;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * A class the represents node(point) on a floor
 */
public class Node extends Observable {

    private double heuristicCost; // heuristic cost for AStar algorithm
    private final UUID uniqueID; // A randomly generated UUID associated with the current node
    private Location location; // The pixel location of the node on the map
    private ArrayList<Node> adjacentNodes; // A list of nodes that are connected to the current node
    private EnumMap<Destination, ArrayList<String>> destinations; // A map  of the destinations at the current node
    private Floor currentFloor; // The floor that the node is associated with
    private static NodeObserver observer = new NodeObserver(); // Observer Object watching all Node objects
    private static final Logger LOGGER = LoggerFactory.getLogger(Node.class); // Logger for this class
    private Circle nodeCircle;
    private ArrayList<Line> adjacentLines;


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
        this.adjacentLines = new ArrayList<>();
        this.nodeCircle = new Circle(this.location.getX(), this.location.getY(), 5.0);

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
        this.adjacentLines = new ArrayList<>();
        this.nodeCircle = new Circle(this.location.getX(), this.location.getY(), 5.0);

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

    public NodeObserver getNodeObserver(){
        return this.observer;
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
            notifyObservers();



        }

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

    public void drawAdmin(Pane pane) {

        if (pane.getChildren().contains(nodeCircle)) {
            return;
        }

        pane.getChildren().add(this.nodeCircle);


        nodeCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (getState() == BuildingState.ADDADJACENTNODE) {

                    if (currentFloor.getOtherNode() == null) {

                        currentFloor.setOtherNode(getCurrentNode());
                        LOGGER.info("NULLLSLDASDASDASD");

                    } else {

                        LOGGER.info("Adding adjacent node");

                        currentFloor.getOtherNode().addAdjacentNode(getCurrentNode());

                        currentFloor.setOtherNode(null);


                    }

                }

            }
        });

    }


    public void drawAdjacentNodes(Pane pane) {

        if (adjacentLines.size() != 0) {
            adjacentLines.clear();
        }

        for (Node node : this.adjacentNodes) {

            Line newLine  = new Line(this.location.getX(), this.location.getY(),
                    node.getLocation().getX(), node.getLocation().getY());

            pane.getChildren().add(newLine);

            this.adjacentLines.add(newLine);



        }



    }




    public void drawNormal(GraphicsContext context) {

    }

    /**
     * Getter for the building's current state.
     *
     * @return The state of the building.
     */
    public BuildingState getState() {

        return this.currentFloor.getState();
    }

    /**
     * Sets the state of the building
     *
     * @param state The state you want to set the building to
     */
    public void setState(BuildingState state) {

        this.currentFloor.setState(state);

    }

    @Override
    public String toString() {

        return uniqueID.toString();
    }

    public Node getCurrentNode() {
        return this;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }
}

