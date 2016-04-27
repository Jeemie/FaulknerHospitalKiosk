package Map;

import Map.Enums.MapState;
import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Enums.UpdateType;
import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NoPathException;
import Map.Exceptions.NodeDoesNotExistException;
import Map.Memento.*;
import Map.SearchAlgorithms.AStar;
import Map.SearchAlgorithms.Dijkstras;
import Map.SearchAlgorithms.ISearchAlgorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;


public class Map implements Observer {

    private String name;

    // Unique ID for this Map
    private UUID uniqueID;

    private LocationNode startLocationNode;

    private ArrayList<Building> mapBuildings;

    // Building UUIDs for serialization
    private ArrayList<UUID> buildingIdList;

    private ISearchAlgorithm searchAlgorithm;

    private MapState currentMapState;

    // TODO DELETE EVENTUALLY:
    // I'm not sure who wrote this, why are we deleting it?  - Binam
    // To do was added on commit f32ef4d, "Merged with little success"
    private ObservableList<Destination> directoryList;

    private Path currentPath;


    //||\\ Current Destination //||\\

    private Destination currentDestination;


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
    private ObservableList<LocationNode> currentFloorLocationNodes;


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


    /*
    TODO create exception to throw when adding something to the map when something of the sametype already has that name
    */
    public Map(String name) {

        this.name = name;
        this.uniqueID = UUID.randomUUID();
        this.startLocationNode = null;
        this.mapBuildings = new ArrayList<>();
        this.buildingIdList = new ArrayList<>();
        this.searchAlgorithm = new AStar();
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
        this.buildingIdList.add(newBuilding.getUniqueID());

    }


    public void addFloor(String name, String resourceFileName) {

        if (this.currentBuilding == null) {

            LOGGER.debug("Floor could not be added because the currentBuilding was null");

            return;
        }

        this.setCurrentFloor(this.currentBuilding.addFloor(name, resourceFileName));

    }

    public void addLocationNode(String name, Location location, ImageType imageType) {

        if (this.currentFloor == null) {

            LOGGER.debug("LocationNode could not be added because the currentFloor was null");

            return;
        }

        this.setCurrentLocationNode(this.currentFloor.addLocationNode(name, location, imageType));

        this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
        this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

        this.currentFloorLocationNodes.add(this.currentLocationNode);

    }

    public void addLocationNodeEdge(LocationNode locationNode) throws NodeDoesNotExistException {

        if(this.currentLocationNode == null) {

            LOGGER.debug("Edge could not be added because the currentLocationNdoe was null");

        }

        this.currentLocationNode.addEdge(locationNode);

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


    //No currentLocationNodeEdge
//    public void removeLocationNodeEdge() {
//        //TODO create debug message
//
//        if (this.currentFloor == null) {
//
//            // TODO create debug message
//
//            return;
//        }
//
//        if (this.currentLocationNodeEdge == null) {
//
//            // TODO create debug message
//
//            return;
//        }
//
//        return;
//    }

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

        this.currentFloorLocationNodePane.getChildren().clear();
        this.currentFloorEdgePane.getChildren().clear();

    }

    public void setupPathStackPane(StackPane stackPane) {

        ArrayList<LocationNode> path;

        try {

            //path = this.searchAlgorithm.getPath(this.startLocationNode, this.currentLocationNode);
            path = (new AStar()).getPath(this.startLocationNode, this.currentLocationNode);


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


    public void useAStar() {


        this.searchAlgorithm = new AStar();

    }


    public void useDijkstras() {

        this.searchAlgorithm = new Dijkstras();

    }

    public void pathNextFloor() {

        this.currentPath.drawNextFloor();

    }

    public void pathPreviousFloor() {

        this.currentPath.drawPreviousFloor();

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

                if (this.currentLocationNode != null) {

                    this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
                    this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                }

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


        MapMemento mapMemento = saveStateToMemento();

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, mapMemento);

        System.out.println(objectMapper.writeValueAsString(mapMemento));

        LOGGER.info("Saving the map to the file: " + file.toString());

    }


    /**
     * Load a map from a JSON file
     * @param specifiedFilePath The JSON file you want to load from
     */
    public static Map loadFromFile(URL specifiedFilePath) throws IOException, FloorDoesNotExistException, DefaultFileDoesNotExistException {

        MapMemento mapMemento = null; //TODO watch out for null pointer exception

        URL defaultFilePath = null;

        // Set up an ObjectMapper for deserialization
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {

            defaultFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
            specifiedFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        try {


            File specifiedFile = new File(specifiedFilePath.toURI());

            File defaultFile = new File(defaultFilePath.toURI());

            //TODO uncomment after deserializer is complete - load from starter map for now
            if (specifiedFile.exists() && specifiedFile.length() > 0) {
                // Load specified file

                mapMemento = objectMapper.readValue(specifiedFile, MapMemento.class);
                LOGGER.info("Loaded map from file " + specifiedFile.toString());

            } else {

                //This will be caught by starterMap and will cause the map to be loaded with hardcoded code
                throw new DefaultFileDoesNotExistException();

            }
        } catch (IOException e) {

            e.printStackTrace();

        } catch (URISyntaxException e) {

            e.printStackTrace();
        }

        return loadStateFromMemento(mapMemento);
    }

    public MapMemento saveStateToMemento() {

        return new MapMemento(this.name, this.uniqueID, this.startLocationNode, this.mapBuildings);

    }

    public static Map loadStateFromMemento(MapMemento mapMemento) {
        //TODO working on

        HashMap<UUID, LocationNode> locationNodeHashMap= new HashMap<UUID, LocationNode>();

        //TODO to map, add startLocatio object and the mapBuilding arraylist
        Map map = new Map(mapMemento.getName());

        // Loop through the building memento arraylist in the mapMemento
        for(BuildingMemento buildingMemento : mapMemento.getBuildingMementos()) {

            // Add a building to the map
            map.addBuilding(buildingMemento.getName());

            // Get the last element in the array (which will be the element just added)
            // Set the added Building as the current building
            map.currentBuilding = map.getMapBuildings().get(map.getMapBuildings().size() - 1);

            // Loop through the floorMementos
            for(FloorMemento floorMemento : buildingMemento.getFloorMomentos()) {

                map.addFloor(floorMemento.getFloorName(), floorMemento.getResourceFileName());

                map.currentFloor = map.currentBuilding.getFloors().get(map.currentBuilding.getFloors().size() - 1);

                for (LocationNodeMemento locationNodeMemento : floorMemento.getLocationNodeMomentos()) {


                    ImageType imageType = ImageType.valueOf(locationNodeMemento.getAssociatedImageString());

                    LocationNode locationNode = new LocationNode(locationNodeMemento.getName(), locationNodeMemento.getUniqueID(), locationNodeMemento.getLocation(), map.getCurrentFloor(), imageType);

                    for(DestinationMemento destinationMemento : locationNodeMemento.getDestinationMementos()) {

                        DestinationType destinationType = DestinationType.valueOf(destinationMemento.getDestinationTypeString());

                        locationNode.addDestination(destinationMemento.getName(), destinationType);

                    }


                    locationNodeHashMap.put(locationNode.getUniqueID(), locationNode);
                    locationNodeMemento.setAssociatedLocationNode(locationNode);

                    map.getCurrentFloor().addLocationNode(locationNode);

                }

            }

        }

        map.setStartLocationNode(locationNodeHashMap.get(mapMemento.getStartLocationNodeID()));

        // At this point all the location nodes have been added, so we can start adding the edges

        //Loop through the existing Building, floor, then locationNodes
        //For each floor,

        for(BuildingMemento buildingMemento : mapMemento.getBuildingMementos()) {

            map.currentBuilding = map.getMapBuildings().get(map.getMapBuildings().size() - 1);

            for(FloorMemento floorMemento : buildingMemento.getFloorMomentos()) {

                map.currentFloor = map.currentBuilding.getFloors().get(map.currentBuilding.getFloors().size() - 1);

                for(LocationNodeMemento locationNodeMemento : floorMemento.getLocationNodeMomentos()) {

                    LocationNode associatedLocationNode = locationNodeMemento.getAssociatedLocationNode();

                    for(LocationNodeEdgeMemento locationNodeEdgeMemento : locationNodeMemento.getEdgeMomentos()) {

                        LocationNode locationNode1 = locationNodeHashMap.get(locationNodeEdgeMemento.getLocationNode1ID());
                        LocationNode locationNode2 = locationNodeHashMap.get(locationNodeEdgeMemento.getLocationNode2ID());


                        try{

                            if(!associatedLocationNode.equals(locationNode1)) {

                                associatedLocationNode.addEdge(locationNode1);

                            } else if (!associatedLocationNode.equals(locationNode2)) {

                                associatedLocationNode.addEdge(locationNode2);

                            }

                        } catch (NodeDoesNotExistException e) {
                                e.printStackTrace();
                        }



                    }


//                    locationNodeMemento.getAssociatedLocationNode().addEdge();

                }
            }

        }

//        for (LocationNode locationNode : map.getCurrentFloorLocationNodes()) {
//
//            // Get the memento version of this locationNode
//            LocationNodeMemento locationNodeMemento = locationNodeMementoHashMap.get(locationNode.getUniqueID());
//
//            for (LocationNodeEdgeMemento locationNodeEdgeMemento : locationNodeMemento.getEdgeMomentos()) {
//
//                if(locationNodeEdgeMemento.getLocationNode1ID() == locationNode.getUniqueID()) {
////                    locationNode.addEdge() //Get the location from hashmap,
//                }
////                locationNode.addEdge();
//
//            }
//        }



            //TODO set the floors arraylist
//            Building building = new Building(buildingMemento.getName(), buildingMemento.getUniqueID(), map);

//            for(FloorMemento floorMemento : buildingMemento.getFloorMomentos()) {
//
//                //TODO set the locationNodes arraylist
//                Floor floor = new Floor(floorMemento.getFloorName(), floorMemento.getUniqueID(), floorMemento.getResourceFileName(), building);
//
//                for(LocationNodeMemento locationNodeMemento : floorMemento.getLocationNodeMomentos()) {
//
//                    //Gets the imageType enum from the string form the locationNodeMomento
//                    ImageType imageType = ImageType.valueOf(locationNodeMemento.getAssociatedImageString());
//
//                    //TODO set the edges
//                    LocationNode locationNode = new LocationNode(locationNodeMemento.getName(), locationNodeMemento.getUniqueID(),locationNodeMemento.getLocation(), floor, imageType);
//
//                    ArrayList<Destination> destinations = new ArrayList<Destination>();
//
//                    for(DestinationMemento destinationMemento : locationNodeMemento.getDestinationMementos()) {
//
//                        //Gets the destinationType enum from the string from the destinationMemento
//                        DestinationType destinationType = DestinationType.valueOf(destinationMemento.getDestinationTypeString());
//
//                        Destination destination = new Destination( destinationMemento.getName(), destinationMemento.getUniqueID(), destinationType, locationNode);
//
//                        locationNode.addDestination(destination.getName(), destinationType);
//
//                    }
//
//                    floor.addLocationNode(locationNode.getName(), locationNode.getLocation(), locationNode.getAssociatedImage());
//
//                }
//
//            }





        return map;

    }



    //||\\ Getters And Setters //||\\



    //TODO
    /**
     * Reinitialize null fields in Map object and subclass objects after loading from file
     */
    public Map initMapComponents() {

        return this;
    }

    public UUID getUniqueID() {

        return uniqueID;
    }

    public String getName() {

        return name;
    }

    public ArrayList<Building> getMapBuildings() {

        return mapBuildings;
    }

    public LocationNode getCurrentLocationNode() {

        return currentLocationNode;
    }

    public Floor getCurrentFloor() {

        return currentFloor;
    }

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

            newLocationNode.drawAdmin(this.currentFloorLocationNodePane);
            newLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

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

    public ArrayList<UUID> getBuildingIdList() {
        return buildingIdList;
    }

}