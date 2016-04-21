package Map;


import Map.Enums.CardinalDirection;
import Map.Enums.DestinationType;
import Map.Enums.UpdateType;
import Map.EventHandlers.LocationNodeClickedEventHandler;
import Map.EventHandlers.LocationNodeDraggedEventHandler;
import Map.Enums.ImageType;
import Map.Exceptions.NodeDoesNotExistException;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static Map.Enums.CardinalDirection.*;

public class LocationNode extends Observable implements Observer, Comparable<LocationNode> {

    //
    private double heuristicCost;

    // Name of this node
    private String name;

    // Unique ID for this node
    private UUID uniqueID;

    // Location of this node
    private Location location;

    // Floor this node is located on
    private Floor currentFloor;

    //
    private ImageType associatedImage;

    //
    private ArrayList<LocationNodeEdge> edges;

    //
    private ArrayList<Destination> destinations;

    // Variables for Dijkstra's and A* search:

    @JsonIgnore
    // the cost of getting from the start node to this node - changes during shortest path search
    private double gScore;

    @JsonIgnore
    // the total cost of getting from the start node to the goal - changes during shortest path search
    private double fScore;

    @JsonIgnore
    // the node this node came from - changes during shortest path search
    private LocationNode cameFrom;

    //
    @JsonIgnore
    private Image currentImage;

    @JsonIgnore
    private Label imageLabel;

    @JsonIgnore
    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNode.class);


    /**
     * Jackson Constructor
     */
    public LocationNode() {

        super();

    }

    public LocationNode(String name, Location location, Floor currentFloor, ImageType associatedImage) {

        this.heuristicCost = 0;
        this.name = name;
        this.uniqueID = UUID.randomUUID();
        this.location = location;
        this.currentFloor = currentFloor;
        this.associatedImage = associatedImage;
        this.edges = new ArrayList<>();
        this.destinations = new ArrayList<>();
        this.fScore = Double.POSITIVE_INFINITY;
        this.gScore = Double.POSITIVE_INFINITY;

        this.addObserver(this.currentFloor);

    }

    /**
     * Add a destination to this node
     *
     * @param destination
     */
    public void addDestination(Destination destination) {

        this.destinations.add(destination);

        setChanged();
        notifyObservers(UpdateType.DESTINATIONCHANGE);

    }

    /**
     * Remove a destination from this node
     *
     * @param destination
     */
    public void removeDestination(Destination destination) {

        this.destinations.remove(destination);

        setChanged();
        notifyObservers(UpdateType.DESTINATIONCHANGE);

    }

    /**
     * Get all of the destinations of the specified destination type at this node
     *
     * @param destinationType
     * @return ArrayList of destinations of specified type
     */
    public ArrayList<Destination> getDestinations(DestinationType destinationType) {

        ArrayList<Destination> nodeDestinations = new ArrayList<>();

        for (Destination d : this.destinations) {

            if (d.isDestinationType(destinationType)) {

                nodeDestinations.add(d);

            }

        }

        return nodeDestinations;
    }

    /**
     * Get all destinations at the node
     *
     * @return ArrayList of destinations
     */
    public ArrayList<Destination> getDestinations() {

        return this.destinations;
    }

    /**
     * Compute the straight line distance between two nodes
     *
     * @param destinationLocationNode The destination to calculate the distance to from this node
     * @return Computed distance
     */
    public double getDistanceBetweenNodes(LocationNode destinationLocationNode) {

        // location of destination node
        Location destinationLocation = destinationLocationNode.getLocation();

        // return the distance between the nodes
        return this.location.getDistanceBetween(destinationLocation);
    }

    /**
     * Draw all of the nodes as they should appear for the Administrative Dashboard View
     *
     * @param pane
     */
    public void drawAdmin(Pane pane) {


        // TODO setup labels with images

        if (pane.getChildren().contains(this.imageLabel)) {

            return;
        }

        pane.getChildren().add(this.imageLabel);

        this.imageLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new LocationNodeClickedEventHandler(this));
        this.imageLabel.addEventHandler(MouseEvent.MOUSE_DRAGGED, new LocationNodeDraggedEventHandler(this));

    }

    /**
     * Draw all edges to neighbors of this node
     *
     * @param pane
     */
    public void drawEdgesAdmin(Pane pane) {

        LOGGER.info("Drawing edges for the Node: " + this.toString());

        // If any edges exist
        if (this.edges.size() != 0) {

            // Draw lines for edges
            for (LocationNodeEdge edge : this.edges) {

                edge.drawEdge(pane);

            }

        }
    }

    /**
     * Draw edges between nodes in path
     *
     * @param pane
     * @param path
     */
    public void drawEdgesNormal(Pane pane, ArrayList<LocationNode> path) {

        LocationNode current;
        LocationNode next;

        // For each node in the path
        // Number of edges = number of nodes in path - 1
        for (int i = 0; i < path.size() - 1; i++) {

            current = path.get(i);
            next = path.get(i+1);

            // Find edge between specified nodes
            for (LocationNodeEdge edge : current.getEdges()) {

                if(edge.isEdgeBetweenNodes(current, next)) {

                    // Found edge between two specified nodes
                    edge.drawEdge(pane);
                    break;

                }
            }
        }

    }

    /**
     * Notify observers to remove this node from the list
     */
    public void deleteNode() {

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEREMOVED);

        return;

    }

    /**
     * Add edge between this node and a neighboring node
     * @param adjacentNode
     */
    public void addEdge(LocationNode adjacentNode) throws NodeDoesNotExistException {

        if (adjacentNode == null) {

            // Adjacent Node does not exist
            throw new NodeDoesNotExistException("Specified adjacent node does not exist.");
        }

        // Check if edge between nodes already exists
        for(LocationNodeEdge edge : edges) {

            if (edge.edgeExists(this, adjacentNode)) {

                // Edge has already been added
                LOGGER.error("Cannot add new edge. Edge already exists.");

                return;
            }
        }

        // Create new edge
        LocationNodeEdge newEdge = new LocationNodeEdge(this, adjacentNode);

        // Add new edge between nodes - add edges reference to both node's list of edges
        edges.add(newEdge);
        adjacentNode.getEdges().add(newEdge);

        setChanged();
        notifyObservers();

    }

    public ArrayList<LocationNode> getAdjacentLocationNodes() {

        ArrayList<LocationNode> adjacentNodes = new ArrayList<>();

        for (LocationNodeEdge edge : this.edges) {

            adjacentNodes.add(edge.getOtherNode(this));

        }

        return adjacentNodes;
    }

    /**
     * This function returns a CardinalDirection which tells the relation of the location between
     *   this node and the nextNode. It does this using angles.
     *
     * @param nextNode The node to which the the CardinalDirection should be calculated
     * @return Enum CardinalDirection
     */
    public CardinalDirection getDirectionsTo(LocationNode nextNode) {

        //Enum CardinalDirection which currently has NORTH, EAST, SOUTH, WEST
        CardinalDirection cardinalDirection;

        //x1, y1 is the position of the this node
        double x1 = this.getLocation().getX();
        double y1 = this.getLocation().getY();

        //x2, y2 is the position of nextNode
        double x2 = nextNode.getLocation().getX();
        double y2 = nextNode.getLocation().getY();

        //Get the difference between the x and y
        double xDiff = x2-x1;
        double yDiff = y2-y1;

        //Calculate the angle between the nodes using atan() function
        double angle = Math.toDegrees( Math.atan(yDiff/xDiff) );

        //If nextNode is in the II quadrant (>= was set to deal with edge case)
        if (xDiff < 0 && yDiff >= 0) {

            angle = 180 + angle;

        }
        //If nextNode is in the III quadrant
        else if (xDiff < 0 && yDiff < 0) {

            angle = 180 + angle;

        }
        //If nextNode is in the IV quadrant
        else if (xDiff >= 0 && yDiff < 0) {

            angle  = 360 + angle;

        }
        //Angle is between 225-315, including 315
        if((225 < angle) && (angle <= 315)){
            cardinalDirection = NORTH;
        }
        //Angle is between 315-360, or 0-45, including 360, 0, and 45
        else if (((315 < angle) && (angle <= 360)) || ((0 <= angle) && (angle <=45))) {
            cardinalDirection = EAST;
        }
        //Angle is between 45-135, including 135
        else if ((45 < angle) && (angle <= 135)) {
            cardinalDirection = SOUTH;
        }
        //Angle is between 135-225, including 225
        else if ((135 < angle) && (angle <= 225)) {
            cardinalDirection = WEST;
        }
        //Angle is lower than 0 or higher than 360
        else {
            cardinalDirection = null;
        }

        return cardinalDirection;
    }

    public Location getLocation() {

        return location;
    }

    public ArrayList<LocationNodeEdge> getEdges() {

        return edges;
    }

    @Override
    public String toString() {

        return this.name;
    }

    @Override
    public int compareTo(LocationNode other) {

        // TODO
        return 0;
    }

    @Override
    public void update(Observable o, Object arg) {



        setChanged();
        notifyObservers(arg);
    }

    public double getfScore() {

        return fScore;

    }

    public void setfScore(double fScore) {

        this.fScore = fScore;

    }

    public double getgScore() {

        return gScore;

    }

    public void setgScore(double gScore) {

        this.gScore = gScore;

    }

    public LocationNode getCameFrom() {

        return cameFrom;

    }

    public void setCameFrom(LocationNode cameFrom) {

        this.cameFrom = cameFrom;

    }
}
