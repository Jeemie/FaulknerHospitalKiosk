package Map;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Observable;
import java.util.UUID;

/**
 * A class that represents a floor in a building
 */
public class Floor extends Observable {

    private final int floor; // The level number associated with the floor
    private final UUID uniqueID; // A randomly generated UUID associated with the current floor
    private ArrayList<Node> nodes; // The nodes that are attached to the current floor
    private final Building currentBuilding; // The building that the floor is a part of
    private ImageView floorImage;
    private Pane nodePane;
    private static FloorObserver observer = new FloorObserver(); // the FloorObserver observing all Floor objects
    private static final Logger LOGGER = LoggerFactory.getLogger(Floor.class); // Logger for this class
    private Node otherNode;

    /**
     * Constructor for a new floor with a new randomly generated UUID and an empty list of nodes on the current floor.
     *
     * @param floor           The number that is associated with the current floor.
     * @param currentBuilding The building that the floor is located in.
     */
    public Floor(int floor, Building currentBuilding) {

        this.floor = floor;
        this.uniqueID = UUID.randomUUID();
        this.nodes = new ArrayList<>();
        this.currentBuilding = currentBuilding;
        this.floorImage = new ImageView();
        this.nodePane = new Pane();

        LOGGER.info("Created new Floor: " + this.toString());

        // adds an observer to this floor and add the floor to list of observed floors in the Observer object
        observer.observeFloor(this);

    }

    /**
     * Constructor for a new floor that has been created previously. By default the floor is created without any Nodes.
     *
     * @param floor The number that is associated with the current floor.
     * @param uniqueID An UUID that is associated with the floor.
     * @param currentBuilding The building that the floor is located in.
     */
    public Floor(int floor, UUID uniqueID, Building currentBuilding) {

        this.floor = floor;
        this.uniqueID = uniqueID;
        this.nodes = new ArrayList<>();
        this.currentBuilding = currentBuilding;

        LOGGER.info("Created new Floor: " + this.toString());

        //adds an observer to this floor and add the floor to list of observed floors in the Observer object
        observer.observeFloor(this);

    }

    /**
     * Getter for the floor number.
     *
     * @return The current floor's number
     */
    public int getFloor(){
        return this.floor;
    }

    /**
     * Add a new node to the current floor. By default the heuristic cost is set to 0.
     *
     * @param location The x and y coordinate in which the node was placed on the current floor.
     * @return The newly created node
     */
    public Node addNode(Location location) {

        // Create a new node
        Node newNode = new Node(0, location, this);

        // Add the node to the list of nodes on the current floor
        this.nodes.add(newNode);

        // mark the floor as changed
        setChanged();

        // trigger notification
        notifyObservers();

        // Return the new Node
        return newNode;
    }

    /**
     * Adds a previously generated node to the current floor.
     *
     * @param heuristicCost Cost of the current node.
     * @param uniqueID An UUID that is associated with the node.
     * @param location The x and y coordinate in which the node was placed on the current floor.
     * @param destinations The destinations that are associated with the current node.
     * @param observer The NodeObserver that watches this node
     * @return The newly created node
     */
    public Node addNode(double heuristicCost, UUID uniqueID, Location location,
                        EnumMap<Destination, ArrayList<String>> destinations, NodeObserver observer) { //added NodeObserver pa

        // Create a new node
        Node newNode = new Node(heuristicCost, uniqueID, location, this, destinations);

        // Add the node to the list of nodes on the current floor
        this.nodes.add(newNode);

        // mark floor as changed
        setChanged();

        // trigger notification
        notifyObservers();

        // Return the new Node
        return newNode;
    }

    /**
     * Gets a list of all of the destinations of the given type located on this floor.
     *
     * @param destinationType Type of destination that you want to get.
     * @return A list of all of the destinations of the given type on the current floor.
     */
    public ArrayList<String> getFloorDestinations(Destination destinationType) {

        // List of destinations of the given type on the current floor
        ArrayList<String> floorDestinations = new ArrayList<>();

        // Go through all of the nodes on the current floor and add destinations of the given type to the list
        for (Node n : nodes) {

            // add all of the destinations of the given type at the current node to the list
            floorDestinations.addAll(n.getDestinations(destinationType));

        }

        // Return all of the destinations of the given type on the current floor
        return floorDestinations;
    }

    /**
     * Gets a list of all of the destinations on the current floor.
     *
     * @return A List of all the destinations on the current floor.
     */
    public ArrayList<String> getFloorDestinations() {

        // List of all the destinations on the current floor
        ArrayList<String> floorDestinations = new ArrayList<>();

        for (Node n : nodes) {

            // add all of the destinations at the current node to the list
            floorDestinations.addAll(n.getDestinations());

        }

        // Return all of the destinations on the current floor
        return floorDestinations;
    }



    public void drawFloorAdmin(StackPane stackPane) {

        // clear the stackpane
        stackPane.getChildren().removeAll();

        this.nodePane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (getState() == BuildingState.ADDNODE) {

                    LOGGER.info("APPLESAUCE1");

                    Location clickLocation = new Location(event.getX(), event.getY());

                    Node newNode = addNode(clickLocation);

                    newNode.drawAdmin(nodePane);

                }

            }

        });

        // add the current floor's canvas and imageview to the stackpane
        stackPane.getChildren().addAll(this.floorImage, this.nodePane);

    }

    public void updateFloorAdmin() {

        for (Node node : this.nodes) {

            node.drawAdmin(this.nodePane);
            node.drawAdjacentNodes(this.nodePane);

        }

    }


    public void drawFloorNormal(StackPane stackPane) {


    }


    public void setFloorImage(URL imagePath) {

        Image image = new Image(imagePath.toString());

        this.floorImage.setImage(image);
        this.floorImage.setFitHeight(150);
        this.floorImage.setFitWidth(200);

        this.nodePane.setPrefHeight(150);
        this.nodePane.setPrefWidth(200);

    }

    /**
     * Get all of the nodes on the current floor.
     *
     * @return A list of all of the nodes on the current floor.
     */
    public ArrayList<Node> getFloorNodes() {

        return this.nodes;
    }

    /**
     * Getter for the building's current state.
     *
     * @return The state of the building.
     */
    public BuildingState getState() {

        return this.currentBuilding.getState();
    }

    /**
     * Sets the state of the building
     *
     * @param state The state you want to set the building to
     */
    public void setState(BuildingState state) {

        this.currentBuilding.setState(state);

    }


    /**
     * Return a FloorObserver associated with Floor
     *
     * @return the FloorObserver called observer
     */
    public FloorObserver getFloorObserver() {

        return this.observer;
    }

    @Override
    public String toString() {

        return uniqueID.toString();
    }

    public void setOtherNode(Node otherNode) {
        this.otherNode = otherNode;
    }

    public Node getOtherNode() {
        return otherNode;
    }

    public Pane getNodePane() {
        return nodePane;
    }
}