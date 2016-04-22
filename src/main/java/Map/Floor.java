package Map;

import Map.Enums.DestinationType;

import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import com.fasterxml.jackson.annotation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * A class that represents a floor in a building
 */

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uniqueID", scope=Floor.class)
public class Floor extends Observable implements Observer {

    // The name of the floor
    private String floorName;

    // The uniqueID identifing the floor
    private UUID uniqueID;

    //
    private String resourceFileName;

    // The building that the floor is associated with
    private Building currentBuilding;

    @JsonIgnore
    // The image of the floor
    private Image floorImage;

    // A list of location nodes which exists on teh floor
    private ArrayList<LocationNode> locationNodes;

    @JsonIgnore
    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Floor.class);


    /**
     * Constructor for Jackson
     */
    public Floor() {

        super();

    }

    /**
     * TODO
     *
     * @param floorName
     * @param resourceFileName
     * @param currentBuilding
     */
    public Floor(String floorName, String resourceFileName, Building currentBuilding) {

        this.floorName = floorName;
        this.uniqueID = UUID.randomUUID();
        this.resourceFileName = resourceFileName;
        this.currentBuilding = currentBuilding;
        //TODO correct image loading
        //this.floorImage = new Image(this.getClass().getResource(resourceFileName.getResourceFileName()).toString());

        // TODO delete - Temp fix for testing:
        this.floorImage = null;
        this.locationNodes = new ArrayList<>();

        this.addObserver(this.currentBuilding);

    }

    /**
     * Add a new node to the current floor. By default, heruistic cost is set to 0
     *
     * @param name
     * @param location
     * @param imageType
     */
    public void addLocationNode(String name, Location location, ImageType imageType) {

        // Create a new LocationNode
        LocationNode newLocationNode = new LocationNode(name, location, this, imageType);

        // Add the node to the list of locationNodes on the current floor
        this.locationNodes.add(newLocationNode);

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEADDED);

    }


    public LocationNode addNode(String nodeName, Location nodeLocation, ImageType nodeImageType) {

        // Create a new node
        LocationNode newLocationNode = new LocationNode (nodeName, nodeLocation, this, nodeImageType);

        // Add the node to the list of locationNodes on the current floor
        this.locationNodes.add(newLocationNode);

        // mark the floor as changed
        setChanged();

        // trigger notification
        notifyObservers(UpdateType.LOCATIONNODEADDED);

        // Return the new LocationNode
        return newLocationNode;
    }


    /**
     * Gets a list of all of the destinations on the current floor.
     *
     * @return A List of all the destinations on the current floor.
     */
    public ArrayList<Destination> getFloorDestinations() {

        ArrayList<Destination> floorDestinations = new ArrayList<>();

        for (LocationNode locationNode : this.locationNodes) {


            floorDestinations.addAll(locationNode.getDestinations());

        }


        return floorDestinations;
    }

    /**
     * Get's all the floor destinations
     * @param destinationType
     * @return an arraylist of Destination objects
     */
    public ArrayList<Destination> getFloorDestinations(DestinationType destinationType) {

        ArrayList<Destination> floorDestinations = new ArrayList<>();

        for (LocationNode locationNode : this.locationNodes) {

            floorDestinations.addAll(locationNode.getDestinations(destinationType));

        }


        return floorDestinations;
    }

    public void removeLocationNode(LocationNode locationNode) {

        this.locationNodes.remove(locationNode);
        locationNode.deleteLocationNodeEdgeConnections();

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEREMOVED);
    }

    /* STUFF I ADDED PLZ REVIEW */
    /**
     * Draws the floor on the admin screen by
     * @param imageView
     * @param LocationNodePane
     * @param EdgePane
     */
    public void drawFloorAdmin(ImageView imageView, Pane LocationNodePane, Pane EdgePane) {


        LOGGER.info(this.resourceFileName);
//        getClass().getResource(".../resources/" + this.resourceFileName).toString();

        try {

            this.floorImage = new Image(new URL("file:///" + System.getProperty("user.dir") + "/resources/" +
                    this.resourceFileName).toString());

        } catch (MalformedURLException e) {

            LOGGER.error("Unable to load the image file for the current floor: ", e);

        }

        imageView.setImage(this.floorImage);

        // Reset LocationNodePane and EdgePane
        LocationNodePane.getChildren().clear();

        EdgePane.getChildren().clear();

        // Loop through the location nodes, and then draw the locations and the edges
        //   in their respective panes
        for (LocationNode locationNode : locationNodes) {

            locationNode.drawAdmin(LocationNodePane);
//            locationNode.drawEdgesAdmin(EdgePane);

        }




    }

        // marked the Floor as changed
//        setChanged();

        // trigger notification
//        notifyObservers(UpdateType.FLOORADDED); //TODO is this update correct?

        //There was also an event handler which handled a mouse click event:
        /*
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

         */
//    }


    /**
     * TODO
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating LocationNode: " + o.toString());


        setChanged();
        notifyObservers(arg);

    }

    @Override
    public String toString() {

        return this.floorName;
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

        for (Destination d : this.getFloorDestinations()) {
            ObservedLocation.add(d.toString());
        }

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


    @JsonGetter
    public String getFloorName() {
        return floorName;
    }

    @JsonGetter
    public UUID getUniqueID() {
        return uniqueID;
    }

    @JsonGetter
    public String getResourceFileName() {
        return resourceFileName;
    }

    @JsonGetter
    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    @JsonGetter
    public ArrayList<LocationNode> getLocationNodes() {
        return locationNodes;
    }

/*
*
TODO: Figure out if we ned these
Methods that I left out from old class

updateFloorAdmin() //Covered by drawFloorAdmin I believe.
drawFloorNormal() //draws the floor using stackPane
setFloorImage() //How to do this without stackPane, not sure where to draw it on
BuildingState getState()
BuildignState setState()   //As I understand it state is not being set using the Building
get and set startnode functions
getFloorObserver we don't have observer object.
getRelativePath()
setRelativePath
getImagePath
setImagePath

 */
}
