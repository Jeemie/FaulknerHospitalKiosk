package Map;

import Map.Enums.MapState;
import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NoPathException;
import Map.SearchAlgorithms.AStar;
import Map.SearchAlgorithms.BreadthFirstSearch;
import Map.SearchAlgorithms.Dijkstras;
import Map.SearchAlgorithms.ISearchAlgorithm;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uniqueID", scope=Map.class)
public class Map implements Observer {

    private String name;

    // Unique ID for this Map
    private UUID uniqueID;

    private LocationNode startLocationNode;

    private ArrayList<Building> mapBuildings;

    private ISearchAlgorithm searchAlgorithm;

    private MapState currentMapState;

    // TODO DELETE EVENTUALLY
    @JsonIgnore
    private ObservableList<Destination> directoryList;

    private Path currentPath;


    //||\\ Current Destination //||\\

    private Destination currentDestination;


    //||\\ Current LocationNode //||\\

    //
    private LocationNode currentLocationNode;


    //
    @JsonIgnore
    private ObservableList<LocationNode> currentAdjacentLocationNodes;


    //
    @JsonIgnore
    private ObservableList<Destination> currentLocationNodeDestinations;


    //||\\ Current Floor //||\\

    //
    private Floor currentFloor;


    //
    @JsonIgnore
    private ObservableList<LocationNode> currentFloorLocationNodes;


    //
    @JsonIgnore
    private ObservableList<Destination> currentFloorDestinations;


    //
    @JsonIgnore
    private Pane currentFloorLocationNodePane;


    //
    @JsonIgnore
    private Pane currentFloorEdgePane;


    //
    @JsonIgnore
    private ImageView currentFloorImage;


    //||\\ Current Building //||\\

    //
    private Building currentBuilding;


    //
    @JsonIgnore
    private ObservableList<Floor> currentBuildingFloors;


    //
    @JsonIgnore
    private ObservableList<Destination> currentBuildingDestinations;


    // Logger for this class
    @JsonIgnore
    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class);

    /**
     * Jackson Constructor
     */
    public Map() {

        super();

    }

    /*
    TODO create exception to throw when adding something to the map when something of the sametype already has that name
    */

    public Map(String name) {

        this.name = name;
        this.uniqueID = UUID.randomUUID();
        this.startLocationNode = null;
        this.mapBuildings = new ArrayList<>();
        this.searchAlgorithm = new BreadthFirstSearch();
        this.currentMapState = MapState.NORMAL;
        this.directoryList = FXCollections.observableArrayList();
        this.currentLocationNode = null;
        this.currentAdjacentLocationNodes = FXCollections.observableArrayList();
        this.currentLocationNodeDestinations = FXCollections.observableArrayList();
        this.currentFloor = null;
        this.currentFloorLocationNodes = FXCollections.observableArrayList();
        this.currentFloorDestinations = FXCollections.observableArrayList();
        this.currentFloorLocationNodePane = new Pane();
        this.currentFloorEdgePane = new Pane();
        this.currentFloorImage = new ImageView();
        this.currentBuilding = null;
        this.currentBuildingFloors = FXCollections.observableArrayList();
        this.currentBuildingDestinations = FXCollections.observableArrayList();

    }



//    private void setCurrentChangeListeners() {
//
//        this.currentBuilding.
//
//
//    }




    public void addBuilding(String name) {

        for (Building building : this.mapBuildings) {

            if (building.getName().equals(name)) {

                LOGGER.debug("There is already a building with the name: ", name);

                return;
            }

        }


        Building newBuilding = new Building(name, this);

        this.currentBuilding = newBuilding;
        this.mapBuildings.add(newBuilding);

    }


    public void addFloor(String name, String resourceFileName) {

        if (this.currentBuilding == null) {

            LOGGER.debug("Floor could not be added because the currentBuilding was null");

            return;
        }

        this.currentBuilding.addFloor(name, resourceFileName);

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

        this.currentLocationNode.addDestination(name, destinationType);

    }

    public void removeDestination() {

        // TODO fill in
        // TODO create debug message

    }

    public void removeLocationNode() {

        if (this.currentFloor == null) {

            // TODO create debug message

            return;
        }

        if (this.currentLocationNode == null) {

            // TODO create debug message

            return;
        }

        this.currentFloor.removeLocationNode(this.currentLocationNode);

    }

    public void removeFloor() {

        // TODO fill in
        // TODO create debug message

    }

    public void physicianDirectory() {

        this.directoryList.clear();

        for (Building building : this.mapBuildings) {

            this.directoryList.addAll(building.getBuildingDestinations(DestinationType.PHYSICIAN));

        }

    }

    public void departmentDirectory() {

        this.directoryList.clear();

        for (Building building : this.mapBuildings) {

            this.directoryList.addAll(building.getBuildingDestinations(DestinationType.DEPARTMENT));

        }

    }

    public void serviceDirectory() {

        this.directoryList.clear();

        for (Building building : this.mapBuildings) {

            this.directoryList.addAll(building.getBuildingDestinations());

        }

    }


    /**
     * TODO
     *
     * @param stackPane
     */
    public void setupAdminStackPane(StackPane stackPane) {


        this.currentMapState = MapState.ADMIN;

        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(this.currentFloorImage, this.currentFloorEdgePane, this.currentFloorLocationNodePane);


        this.currentFloorImage.setPreserveRatio(true);
        this.currentFloorImage.setSmooth(true);
        this.currentFloorImage.setCache(true);
        this.currentFloorImage.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {

            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {

                LOGGER.info("Image Bounds changed, updating pane bounds");
                LOGGER.info("Old Image Bounds: " + newValue.toString());
                LOGGER.info("New Image Bounds: " + newValue.toString());

                currentFloorLocationNodePane.setPrefWidth(newValue.getWidth());
                currentFloorLocationNodePane.setPrefHeight(newValue.getHeight());


                LOGGER.info("" + currentFloorEdgePane.getPrefWidth());

                currentFloorEdgePane.setPrefWidth(newValue.getWidth());
                currentFloorEdgePane.setPrefHeight(newValue.getHeight());

                LOGGER.info("" + currentFloorEdgePane.getPrefWidth());


            }

        });

        this.currentFloorLocationNodePane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (currentMapState == MapState.ADDNODE) {

                    LOGGER.info("Adding a node at x: " + event.getX() + " and y: " + event.getY());


                    Location newLocation = new Location(event.getX(), event.getY());

//                    addLocationNode();

                }


            }

        });

    }

    public void setupPathStackPane(StackPane stackPane) {

        ArrayList<LocationNode> path;

        try {

            //path = this.searchAlgorithm.getPath(this.startLocationNode, this.currentLocationNode);

            path = AStar.getPath(this.startLocationNode, this.currentLocationNode);

        } catch (NoPathException e) {

            LOGGER.error("Unable to get a path", e);

            return;
        }

        stackPane.getChildren().clear();
        stackPane.getChildren().addAll(this.currentFloorImage, this.currentFloorEdgePane, this.currentFloorLocationNodePane);

        this.currentPath = new Path(this.currentFloorImage, this.currentFloorLocationNodePane, this.currentFloorEdgePane,
                path);

        this.currentPath.setup();

    }


    public void setupNormalStackPane(StackPane stackPane) {




    }




    public ArrayList<LocationNode> getPathFromKiosk(LocationNode destination) throws NoPathException {

        return this.searchAlgorithm.getPath(this.startLocationNode, destination);
    }

   /*
    public void useAStar() {


        this.searchAlgorithm = new AStar();

    }
    */

    public void useDijkstras() {

        this.searchAlgorithm = new Dijkstras();

    }

    public void pathNextFloor() {

        this.currentPath.drawNextFloor();

    }

    public void pathPreviousFloor() {



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

                // TODO cleanup by only modifying one destination
                this.currentBuildingDestinations.clear();
                this.currentBuildingDestinations.addAll(this.currentBuilding.getBuildingDestinations());

//
//                // remove current location node destinations from current floor destinations and building destinations
//                this.currentFloorDestinations.removeAll(this.currentLocationNodeDestinations);
//                this.currentBuildingDestinations.removeAll(this.currentLocationNodeDestinations);
//
//                // Update currentLocationNodeDestinations by clearing the list, and replacing it with the getDestinations function
//                this.currentLocationNodeDestinations.clear();
//                this.currentLocationNodeDestinations.addAll(this.currentLocationNode.getDestinations());
//
//                // Add current location node destinations from current floor and building destinations
//                this.currentFloorDestinations.addAll(this.currentLocationNodeDestinations);
//                this.currentBuildingDestinations.addAll(this.currentLocationNodeDestinations);


                break;

            case LOCATIONNODEPOSITION:

                this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                break;

            case LOCATIONNODEADDED:

//                this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
//                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                // TODO decide whether or not we are going to redraw the entire floor
                break;

            case FLOORADDED:

                this.currentBuildingFloors.clear();
                this.currentBuildingFloors.addAll(this.currentBuilding.getFloors());

                break;


            case LOCATIONNODEREMOVED:

                this.currentLocationNode.undrawLocationNode(this.currentFloorLocationNodePane, this.currentFloorEdgePane);
                this.currentLocationNode = null;

                break;

            case BUILDINGADDED:

                this.currentBuildingDestinations.clear();
                this.currentBuildingDestinations.addAll(this.currentBuilding.getBuildingDestinations());

                break;

            case LOCATIONNODEEDGE:

                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);
                this.locationNodeUpdater(this.currentLocationNode);

                break;


            default:

                break;




        }

    }

    @Override
    public String toString() {

        return this.name;
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



    //||\\ Getters And Setters //||\\



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

    public ObservableList<LocationNode> getCurrentFloorLocationNodes() {

        return currentFloorLocationNodes;
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

    public void setStartLocationNode(LocationNode locationNode) {

        LOGGER.info(locationNode.toString());

        this.startLocationNode = locationNode;
    }

    public void setCurrentDestination(Destination destination) {

        // TODO possibly refresh the observable lists
        // TODO possible highlight the current LocationNode

        LOGGER.info("set current destination:" + destination.toString());

        this.currentDestination = destination;

        this.locationNodeUpdater(currentDestination.getCurrentLocationNode());
        this.currentLocationNode = currentDestination.getCurrentLocationNode();

        this.floorChangeUpdater(currentLocationNode.getCurrentFloor());
        this.currentFloor = currentLocationNode.getCurrentFloor();
        this.currentBuilding = currentFloor.getCurrentBuilding();

    }

//    private void destinationChangeUpdater(Destination newDestination) {
//
//        if ((this.currentDestination == null) || (!this.currentDestination.equals(newDestination))) {
//
//            this.currentFloorDestinations.clear();
//            this.currentFloorDestinations.addAll(newDestination.getFloorDestinations());
//
//            LOGGER.info("Updating current floor destinations: " + currentFloorDestinations.size());
//
//            this.currentFloorLocationNodes.clear();
//            this.currentFloorLocationNodes.addAll(newFloor.getLocationNodes());
//
//
//            LOGGER.info("Updating current floor location nodes: " +  currentFloorLocationNodes.size());
//
//        }
//
//    }

    public void setCurrentLocationNode(LocationNode locationNode) {

        // TODO possibly refresh the observable lists
        // TODO possible highlight the current LocationNode

        this.currentDestination = null;
        locationNodeUpdater(locationNode);
        this.currentLocationNode = locationNode;
        floorChangeUpdater(currentLocationNode.getCurrentFloor());
        this.currentFloor = currentLocationNode.getCurrentFloor();
        this.currentBuilding = currentFloor.getCurrentBuilding();

    }

    private void locationNodeUpdater(LocationNode newLocationNode) {

        if (currentMapState.equals(MapState.NORMAL)) {

            return;
        }

        if ((this.currentLocationNode == null) || (!this.currentLocationNode.equals(newLocationNode))) {

            LOGGER.info("Rebuilding current location node destinations and connected location nodes");

            this.currentLocationNodeDestinations.clear();
            this.currentLocationNodeDestinations.addAll(newLocationNode.getDestinations());

            this.currentAdjacentLocationNodes.clear();
            this.currentAdjacentLocationNodes.addAll(newLocationNode.getAdjacentLocationNodes());

        }

    }

    public void setCurrentFloor(Floor floor) {

        // TODO possibly refresh the observable lists
        // TODO possible highlight the current LocationNode

        this.floorChangeUpdater(floor);

        this.currentDestination = null;
        this.currentLocationNode = null;
        this.currentFloor = floor;
        this.currentBuilding = currentFloor.getCurrentBuilding();

    }

    private void floorChangeUpdater(Floor newFloor) {

        if (currentMapState.equals(MapState.NORMAL)) {

            return;
        }

        if ((this.currentFloor == null) || (!this.currentFloor.equals(newFloor))) {

            LOGGER.info("Rebuilding current floor destinations and location nodes");

            this.currentFloorDestinations.clear();
            this.currentFloorDestinations.addAll(newFloor.getFloorDestinations());

            this.currentFloorLocationNodes.clear();
            this.currentFloorLocationNodes.addAll(newFloor.getLocationNodes());

            newFloor.drawFloorAdmin(this.currentFloorImage, this.currentFloorLocationNodePane, this.currentFloorEdgePane);

        }

    }

    public void setCurrentBuilding(Building building) {

        // TODO possibly refresh the observable lists
        // TODO possible highlight the current LocationNode

        this.currentDestination = null;
        this.currentLocationNode = null;
        this.currentFloor = null;
        this.currentBuilding = building;

    }

    public void setCurrentMapState(MapState currentMapState) {

        this.currentMapState = currentMapState;

    }

    public MapState getCurrentMapState() {

        return currentMapState;
    }

    public ObservableList<Destination> getDirectoryList() {

        return directoryList;
    }

}