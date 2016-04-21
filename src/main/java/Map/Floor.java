package Map;

import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import com.fasterxml.jackson.annotation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * A class that represents a floor in a building
 */

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uniqueID", scope=Floor.class)
public class Floor extends Observable implements Observer {


    private String floorName;

    private UUID uniqueID;

    //
    private ImageType imageType;

    //
    private Building currentBuilding;

    //
    private LocationNode currentlySelectedLocationNode;

    @JsonIgnore
    //
    private ObservableList<LocationNode> currentlySelectedAdjacentLocationNodes;

    @JsonIgnore
    //
    private ObservableList<String> currentlySelectedLocationNodeDestinatioms;

    @JsonIgnore
    //
    private Pane locationNodePane;

    @JsonIgnore
    //
    private Pane locationNodeEdgePane;

    @JsonIgnore
    //
    private Image floorImage;

    //
    private ArrayList<LocationNode> locationNodes;

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
     * @param imageType
     * @param currentBuilding
     */
    public Floor(String floorName, ImageType imageType, Building currentBuilding) {

        this.floorName = floorName;
        this.uniqueID = UUID.randomUUID();
        this.imageType = imageType;
        this.currentBuilding = currentBuilding;
        this.currentlySelectedLocationNode = null;
        this.currentlySelectedAdjacentLocationNodes = FXCollections.observableArrayList();
        this.currentlySelectedLocationNodeDestinatioms = FXCollections.observableArrayList();
        this.locationNodePane = new Pane();
        this.locationNodeEdgePane = new Pane();
        this.floorImage = new Image(this.getClass().getResource(imageType.getResourceFileName()).toString());
        this.locationNodes = new ArrayList<>();

        this.addObserver(this.currentBuilding);

    }


    public void addLocationNode(String name, Location location, ImageType imageType) {


        // Create a new LocationNode
        LocationNode newLocationNode = new LocationNode(name, location, this, imageType);

        this.locationNodes.add(newLocationNode);

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEADDED);

    }

    public ArrayList<Destination> getFloorDestinations() {

        ArrayList<Destination> floorDestinations = new ArrayList<>();

        for (LocationNode locationNode : this.locationNodes) {


            floorDestinations.addAll(locationNode.getDestinations());

        }


        return floorDestinations;
    }

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

    public void drawFloorAdmin(ImageView imageView, Pane LocationNodePane, Pane EdgePane) {


    }



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


    @JsonGetter
    public String getFloorName() {
        return floorName;
    }

    @JsonGetter
    public UUID getUniqueID() {
        return uniqueID;
    }

    @JsonGetter
    public ImageType getImageType() {
        return imageType;
    }

    @JsonGetter
    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    @JsonGetter
    public LocationNode getCurrentlySelectedLocationNode() {
        return currentlySelectedLocationNode;
    }

    @JsonGetter
    public ArrayList<LocationNode> getLocationNodes() {
        return locationNodes;
    }
}