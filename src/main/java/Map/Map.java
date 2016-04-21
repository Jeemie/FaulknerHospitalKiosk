package Map;

import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Map implements Observer {


    //
    private String name;

    //
    private ArrayList<Building> mapBuildings;


    //||\\ Current LocationNode //||\\

    //
    private LocationNode currentLocationNode;

    //
    private ObservableList<LocationNode> currentAdjacentLocationNodes;

    //
    private ObservableList<Destination> currentLocationNodeDestinations;


    //||\\ Current Floor //||\\

    //
    private Floor currentFloor;

    //
    private ObservableList<LocationNode> currentFloorLocatioNodes;

    //
    private ObservableList<Destination> currentFloorDestinations;

    //
    private Pane currentFloorLocationNodePane;

    //
    private Pane currentFloorEdgePane;

    //
    private ImageView currentFloorImage;


    //||\\ Current Building //||\\

    //
    private Building currentBuilding;

    //
    private ObservableList<Floor> currentBuildingFloors;

    //
    private ObservableList<Destination> currentBuildingDestinations;



    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class);

    public Map(String name) {

        this.name = name;
        this.mapBuildings = new ArrayList<>();
        this.currentLocationNode = null;
        this.currentAdjacentLocationNodes = FXCollections.observableArrayList();
        this.currentLocationNodeDestinations = FXCollections.observableArrayList();
        this.currentFloor = null;
        this.currentFloorLocatioNodes = FXCollections.observableArrayList();
        this.currentFloorDestinations = FXCollections.observableArrayList();
        this.currentFloorLocationNodePane = new Pane();
        this.currentFloorEdgePane = new Pane();
        this.currentFloorImage = new ImageView();
        this.currentBuilding = null;
        this.currentBuildingFloors = FXCollections.observableArrayList();
        this.currentFloorDestinations = FXCollections.observableArrayList();

    }





    // TODO fill in
    public void addBuilding(String name) {

        Building newBuilding = new Building();





        this.currentBuilding = newBuilding;
        this.mapBuildings.add(newBuilding);

    }


    public void addFloor(String name, ImageType imageType) {
        

    }

    public void addLocatioNode(String name, Location location, ImageType imageType) {

        this.currentFloor.addLocationNode();

    }





    public void setupAdminStackPane(StackPane stackPane) {

        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(this.currentFloorImage, this.currentFloorEdgePane, this.currentFloorLocationNodePane);

    }










    @Override
    public void update(Observable o, Object arg) {

        // Check to see if the argument is null
        if (arg == null) {

            LOGGER.debug("Observer was updated but the argument was null: ", arg);

            return;
        }

        LOGGER.info("Updating the Map");

        UpdateType updateType = ((UpdateType) arg);

        switch (updateType) {

            case DESTINATIONCHANGE:


                // remove current location node destinations from current floor destinations and building destinations
                this.currentFloorDestinations.removeAll(this.currentLocationNodeDestinations);
                this.currentBuildingDestinations.removeAll(this.currentLocationNodeDestinations);

                // Update currentLocationNodeDestinations by clearing the list, and replacing it with the getDestinations function
                this.currentLocationNodeDestinations.clear();
                this.currentLocationNodeDestinations.addAll(this.currentLocationNode.getDestinations());

                // Add current location node destinations from current floor and building destinations
                this.currentFloorDestinations.addAll(this.currentLocationNodeDestinations);
                this.currentBuildingDestinations.addAll(this.currentLocationNodeDestinations);


                break;

            case LOCATIONNODEPOSITION:

                this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                break;

            case LOCATIONNODEADDED:

                this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                // TODO decide whether or not we are going to redraw the entire floor
                break;

            case FLOORADDED:

                // TODO
                break;


            default:

                break;




        }

    }













    public ObservableList<LocationNode> getCurrentAdjacentLocationNodes() {

        return currentAdjacentLocationNodes;
    }

    public ObservableList<Destination> getCurrentLocationNodeDestinations() {

        return currentLocationNodeDestinations;
    }

    public ObservableList<LocationNode> getCurrentFloorLocatioNodes() {

        return currentFloorLocatioNodes;
    }

    public ObservableList<Destination> getCurrentFloorDestinations() {

        return currentFloorDestinations;
    }

    public Pane getCurrentFloorLocationNodePane() {

        return currentFloorLocationNodePane;
    }

    public Pane getCurrentFloorEdgePane() {

        return currentFloorEdgePane;
    }

    public ImageView getCurrentFloorImage() {

        return currentFloorImage;
    }

    public ObservableList<Floor> getCurrentBuildingFloors() {

        return currentBuildingFloors;
    }

    public ObservableList<Destination> getCurrentBuildingDestinations() {

        return currentBuildingDestinations;
    }

}