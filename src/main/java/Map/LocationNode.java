package Map;


import Map.Enums.CardinalDirection;
import Map.Enums.DestinationType;
import Map.Enums.UpdateType;
import Map.EventHandlers.LocationNodeClickedEventHandler;
import Map.EventHandlers.LocationNodeDraggedEventHandler;
import Map.Enums.ImageType;
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

    //
    private String name;

    //
    private UUID uniqueID;

    //
    private Location location;

    //
    private Floor currentFloor;

    //
    private ImageType associatedImage;

    //
    private ArrayList<LocationNodeEdge> edges;

    //
    private ArrayList<Destination> destinations;

    //
    @JsonIgnore
    private Image currentImage;

    @JsonIgnore
    private Label imageLabel;

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

        this.addObserver(this.currentFloor);

    }

    public void addDestination(Destination destination) {

        this.destinations.add(destination);

    }


    public void removeDestination(Destination destination) {

        this.destinations.remove(destination);

        setChanged();
        notifyObservers(UpdateType.DESTINATIONCHANGE);

    }

    public ArrayList<Destination> getDestinations(DestinationType destinationType) {

        ArrayList<Destination> nodeDestinations = new ArrayList<>();

        for (Destination d : this.destinations) {

            if (d.isDestinationType(destinationType)) {

                nodeDestinations.add(d);

            }

        }

        return nodeDestinations;
    }

    public ArrayList<Destination> getDestinations() {

        return this.destinations;
    }

    public double getDistanceBetweenNodes(LocationNode destinationLocationNode) {

        // location of destination node
        Location destinationLocation = destinationLocationNode.getLocation();

        // return the distance between the nodes
        return this.location.getDistanceBetween(destinationLocation);
    }

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
     * TODO Maryann
     */
    public void deleteNode() {



    }

    /**
     * Add edge between this node and a neighboring node
     * @param adjacentNode
     */
    public void addEdge(LocationNode adjacentNode) {

        if (adjacentNode == null) {
            // Adjacent Node does not exist
            // TODO add exception
        }

        // Check if edge between nodes already exists
        for(LocationNodeEdge edge : edges) {

            if (edge.edgeExists(this, adjacentNode)) {

                // Edge has already been added
                //TODO add exception and/or logger message
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

    // TODO MARYANN
    public ArrayList<LocationNode> getAdjacentLocationNodes() {

        return null;
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
            cardinalDirection = ERR;
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

}
