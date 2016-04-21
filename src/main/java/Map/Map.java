package Map;

import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.Exceptions.FloorDoesNotExistException;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class Map implements Observer {

    // Unique ID for this Map
    private UUID uniqueID;

    private String name;

    private ArrayList<Building> mapBuildings;


    //||\\ Current LocationNode //||\\

    //
    private LocationNode currentLocationNode;

    @JsonIgnore
    //
    private ObservableList<LocationNode> currentAdjacentLocationNodes;

    @JsonIgnore
    //
    private ObservableList<Destination> currentLocationNodeDestinations;


    //||\\ Current Floor //||\\

    //
    private Floor currentFloor;

    @JsonIgnore
    //
    private ObservableList<LocationNode> currentFloorLocatioNodes;

    @JsonIgnore
    //
    private ObservableList<Destination> currentFloorDestinations;

    @JsonIgnore
    //
    private Pane currentFloorLocationNodePane;

    @JsonIgnore
    //
    private Pane currentFloorEdgePane;

    @JsonIgnore
    //
    private ImageView currentFloorImage;


    //||\\ Current Building //||\\

    //
    private Building currentBuilding;

    @JsonIgnore
    //
    private ObservableList<Floor> currentBuildingFloors;

    @JsonIgnore
    //
    private ObservableList<Destination> currentBuildingDestinations;

    @JsonIgnore
    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class);

    /**
     * Jackson Constructor
     */
    public Map() {

        super();

    }

    public Map(String name) {

        this.uniqueID = UUID.randomUUID();
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

        Building newBuilding = new Building(name, this);





        this.currentBuilding = newBuilding;
        this.mapBuildings.add(newBuilding);

    }


    public void addFloor(String name, ImageType imageType) {

        if (this.currentBuilding == null) {

            LOGGER.debug("Floor could not be added because the currentBuilding was null");

            return;
        }

        this.currentBuilding.addFloor(name, imageType);

    }

    public void addLocationNode(String name, Location location, ImageType imageType) {

        if (this.currentFloor == null) {

            LOGGER.debug("LocationNode could not be added because the currentFloor was null");

            return;
        }

        this.currentFloor.addLocationNode(name, location, imageType);

    }

    public void addDestination(String name, DestinationType destinationType) {

        if (this.currentLocationNode == null) {

            LOGGER.debug("Destination could not be added because the currentLocationNode was null");

            return;
        }

        this.currentLocationNode.addDestination(destinationType, name);

    }

    public void removeLocationNode() {

        // TODO add null checks


        this.currentFloor.removeLocationNode(this.currentLocationNode);

    }





    public void setupAdminStackPane(StackPane stackPane) {

        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(this.currentFloorImage, this.currentFloorEdgePane, this.currentFloorLocationNodePane);

    }










    @Override
    public void update(Observable o, Object arg) {

        // Check to see if the argument is null
        if (arg == null) {

            LOGGER.debug("Observer was updated but the argument was null");

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


            case LOCATIONNODEREMOVED:

                this.currentLocationNode.undrawLocationNode(this.currentFloorLocationNodePane, this.currentFloorEdgePane);
                this.currentLocationNode = null;

                break;


            default:

                break;




        }

    }

    /**
     * Save this map to a JSON file
     * @param file The JSON file you want to save to
     */
    public void saveToFile(File file) throws IOException, URISyntaxException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, this);
        LOGGER.info("Saving the map to the file: " + file.toString());

    }

    /**
     * Load a map from a JSON file
     * @param specifiedFilePath The JSON file you want to load from
     */
    public static Map loadFromFile(URL specifiedFilePath) throws IOException, FloorDoesNotExistException, DefaultFileDoesNotExistException {

        Map mMap = new Map();
        URL defaultFilePath = null;

        // Set up an ObjectMapper for deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {

            defaultFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
            //specifiedFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        try {

            File specifiedFile = new File(specifiedFilePath.toURI());

            File defaultFile = new File(defaultFilePath.toURI());

            if (specifiedFile.exists() && specifiedFile.length() > 0) {

                // Load specified file

                mMap = objectMapper.readValue(specifiedFile, Map.class);
                LOGGER.info("Loaded map from file " + specifiedFile.toString());

            } else if (defaultFile.exists()) {

                // Load default file
                mMap = objectMapper.readValue(defaultFile, Map.class);
                LOGGER.info("Loaded map from file " + defaultFile.toString());

                if (defaultFile.length() <= 2) {

                    LOGGER.warn("Loaded file is empty.");
                    // TODO uncomment after startMap is refactored
                    // mMap = FaulknerHospitalData.starterMap();
                }
            } else {

                throw new DefaultFileDoesNotExistException();
            }
        } catch (IOException e) {

            e.printStackTrace();

        } catch (URISyntaxException e) {

            e.printStackTrace();
        }

        return mMap;
    }

    //TODO
    /**
     * Reinitialize null fields in Map object and subclass objects after loading from file
     */
    public Map initMapComponents() {

        return this;
    }

    @JsonGetter
    public UUID getUniqueID() {

        return uniqueID;
    }

    @JsonGetter
    public String getName() {

        return name;
    }

    @JsonGetter

    public ArrayList<Building> getMapBuildings() {
        return mapBuildings;
    }

    @JsonGetter
    public LocationNode getCurrentLocationNode() {

        return currentLocationNode;
    }

    @JsonGetter
    public Floor getCurrentFloor() {

        return currentFloor;
    }

    @JsonGetter
    public Building getCurrentBuilding() {

        return currentBuilding;
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