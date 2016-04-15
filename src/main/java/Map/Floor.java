package Map;

import com.fasterxml.jackson.annotation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Observable;
import java.util.UUID;

/**
 * A class that represents a floor in a building
 */

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uniqueID", scope=Floor.class)
public class Floor extends Observable{

    private int floor; // The level number associated with the floor
    private UUID uniqueID; // A randomly generated UUID associated with the current floor
    private Building currentBuilding;
    private ArrayList<LocationNode> locationNodes;
    private String relativePath;
    @JsonIgnore
    private URL imagePath;
    @JsonIgnore
    private ImageView floorImage;
    @JsonIgnore
    private Pane nodePane;
    @JsonIgnore
    private static FloorObserver observer = new FloorObserver(); // the FloorObserver observing all Floor objects
    @JsonIgnore
    private static final Logger LOGGER = LoggerFactory.getLogger(Floor.class); // Logger for this class
    private LocationNode startNode;
    @JsonIgnore
    private StackPane stackPane;


    /**
     *  Default constructor for a new floor
     *  Required by ObjectToJsonToJava.loadFromFile()
     */
    public Floor() {
        super();
    }

    /**
     * Constructor for a new floor with a new randomly generated UUID and an empty list of locationNodes on the current floor.
     *
     * @param floor           The number that is associated with the current floor.
     * @param currentBuilding The building that the floor is located in.
     * @param relativePath       The relative path of the floor image
     */
    public Floor(int floor, Building currentBuilding, String relativePath) {

        this.floor = floor;
        this.uniqueID = UUID.randomUUID();
        this.locationNodes = new ArrayList<>();
        this.currentBuilding = currentBuilding;
        this.relativePath = relativePath;
        this.floorImage = new ImageView();
        this.nodePane = new Pane();

        setImagePath(this.relativePath);


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
        this.locationNodes = new ArrayList<>();
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
    @JsonGetter
    public int getFloor(){
        return this.floor;
    }

    /**
     * Add a new node to the current floor. By default the heuristic cost is set to 0.
     *
     * @param location The x and y coordinate in which the node was placed on the current floor.
     * @return The newly created node
     */
    public LocationNode addNode(Location location) {

        // Create a new node
        LocationNode newLocationNode = new LocationNode(0, location, this);

        // Add the node to the list of locationNodes on the current floor
        this.locationNodes.add(newLocationNode);

        // mark the floor as changed
        setChanged();

        // trigger notification
        notifyObservers();

        // Return the new LocationNode
        return newLocationNode;
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

        // Go through all of the locationNodes on the current floor and add destinations of the given type to the list
        for (LocationNode n : locationNodes) {

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
    @JsonIgnore
    public ArrayList<String> getFloorDestinations() {

        // List of all the destinations on the current floor
        ArrayList<String> floorDestinations = new ArrayList<>();

        for (LocationNode n : locationNodes) {

            // add all of the destinations at the current node to the list
            floorDestinations.addAll(n.getBuildingDestinations());

        }

        // Return all of the destinations on the current floor
        return floorDestinations;
    }



    public void drawFloorAdmin(StackPane stackPane) {

        // clear the stackpane
        stackPane.getChildren().clear();

        this.nodePane.getChildren().clear();
        this.nodePane = new Pane();
        this.nodePane.setPrefHeight(floorImage.getX());
        this.nodePane.setPrefWidth(floorImage.getY());

        this.nodePane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (currentBuilding.getState() == BuildingState.ADDNODE) {

                    LOGGER.info("APPLESAUCE1");

                    Location clickLocation = new Location(event.getX(), event.getY());

                    LocationNode newLocationNode = addNode(clickLocation);

                    newLocationNode.drawAdminNode(nodePane);

                }

            }

        });

        // add the current floor's canvas and imageview to the stackpane
        stackPane.getChildren().addAll(this.floorImage, this.nodePane);

        this.stackPane = stackPane;

        updateFloorAdmin();

    }

    public void updateFloorAdmin() {

        for (LocationNode locationNode : this.locationNodes) {

            locationNode.drawAdminNode(this.nodePane);
            locationNode.drawAdjacentNodes(this.nodePane);
            locationNode.setAssociatedPane(this.nodePane);

        }

    }


    public void drawFloorNormal(StackPane stackPane) {

        // clear the stackpane
        stackPane.getChildren().clear();
        this.nodePane.getChildren().clear();
        this.nodePane = new Pane();
        this.nodePane.setPrefHeight(floorImage.getX());
        this.nodePane.setPrefWidth(floorImage.getY());
        stackPane.getChildren().addAll(this.floorImage, this.nodePane);

    }

    public void setFloorImage(URL imagePath) {

        if(this.floorImage == null) {
            this.floorImage = new ImageView();
        }
        if(this.nodePane == null) {
            this.nodePane = new Pane();
        }

        Image image = new Image(String.valueOf(imagePath));
        this.floorImage.setImage(image);
        this.nodePane.setPrefHeight(floorImage.getX());
        this.nodePane.setPrefWidth(floorImage.getY());

    }


    public void addLocationToListView(ListView listView) {

        if (this.locationNodes == null) {

            return;
        }

        ObservableList<LocationNode> ObservedLocation = FXCollections.observableArrayList();

        ObservedLocation.addAll(this.locationNodes);

        listView.setItems(ObservedLocation);
    }


    public void addDestinationsToListView(ListView listView) {

        if (this.getFloorDestinations() == null) {

            return;
        }

        ObservableList<String> ObservedLocation = FXCollections.observableArrayList();

        ObservedLocation.addAll(this.getFloorDestinations());

        listView.setItems(ObservedLocation);
    }

    /**
     * Get all of the locationNodes on the current floor.
     *
     * @return A list of all of the locationNodes on the current floor.
     */
    @JsonIgnore
    public ArrayList<LocationNode> getFloorNodes() {

        return this.locationNodes;
    }

    /**
     * Getter for the building's current state.
     *
     * @return The state of the building.
     */
    @JsonIgnore
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

    public void removeLocationNode(LocationNode node) {

        this.locationNodes.remove(node);

    }

    /**
     * Return a FloorObserver associated with Floor
     *
     * @return the FloorObserver called observer
     */
    @JsonIgnore
    public FloorObserver getFloorObserver() {

        return observer;
    }

    @Override
    public String toString() {

        return uniqueID.toString();
    }

    public Pane getNodePane() {
        return nodePane;
    }

    @JsonGetter
    public UUID getUniqueID() {
        return uniqueID;
    }

    @JsonGetter
    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public ArrayList<LocationNode> getLocationNodes(){
        return locationNodes;
    }

    public void setStartNode(LocationNode startNode) {
        this.startNode = startNode;
    }

    public LocationNode getStartNode() {
        return startNode;
    }

    public ImageView getFloorImage() {
        return floorImage;
    }

    @JsonGetter
    public String getRelativePath() {
        return relativePath;
    }

    @JsonIgnore
    public void setRelativePath(URL imagePath) {
        URI uri = null;
        try {
            uri = imagePath.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String path = uri.getPath();
        String idStr = path.substring(path.lastIndexOf('/') + 1);
        int id = Integer.parseInt(idStr);
        this.relativePath =  idStr;
    }

    @JsonIgnore
    public URL getImagePath() {

        return imagePath;
    }

    @JsonIgnore
    public void setImagePath(String relativePath) {
        try {

            /**
             *
             *This is for OSx use this while testing.
             *
             *this.imagePath = new URL("file://" + System.getProperty("user.dir") + "/resources/" + relativePath);
             */
        //Use this for windows

            this.imagePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + relativePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }
}