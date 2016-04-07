package Map;

import Kiosk.Controller.AdminController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * A class the represents node(point) on a floor
 */
public class LocationNode extends Observable {

    private double heuristicCost; // heuristic cost for AStar algorithm
    private final UUID uniqueID; // A randomly generated UUID associated with the current node
    private Location location; // The pixel location of the node on the map
    private ArrayList<LocationNode> adjacentLocationNodes; // A list of nodes that are connected to the current node
    private EnumMap<Destination, ArrayList<String>> destinations; // A map  of the destinations at the current node
    private Floor currentFloor; // The floor that the node is associated with
    private static NodeObserver observer = new NodeObserver(); // Observer Object watching all LocationNode objects
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNode.class); // Logger for this class
    private Circle nodeCircle;
    private ArrayList<Line> adjacentLines;


    /**
     * TODO
     *
     * @param heuristicCost
     * @param location
     * @param currentFloor
     */
    public LocationNode(double heuristicCost, Location location, Floor currentFloor) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = UUID.randomUUID();
        this.location = location;
        this.adjacentLocationNodes = new ArrayList<>();
        this.destinations = new EnumMap<Destination, ArrayList<String>>(Destination.class);
        this.currentFloor = currentFloor;
        this.adjacentLines = new ArrayList<>();
        this.nodeCircle = new Circle(this.location.getX(), this.location.getY(), 5.0);

        observer.observeNode(this);  //starts observing new LocationNode object

    }




    /**
     * TODO
     *  @param heuristicCost
     * @param uniqueID
     * @param location
     * @param currentFloor
     * @param destinations
     */
    public LocationNode(double heuristicCost, UUID uniqueID, Location location, Floor currentFloor, EnumMap<Destination, ArrayList<String>> destinations) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = uniqueID;
        this.location = location;
        this.adjacentLocationNodes = new ArrayList<>();
        this.destinations = destinations;
        this.currentFloor = currentFloor;
        this.adjacentLines = new ArrayList<>();
        this.nodeCircle = new Circle(this.location.getX(), this.location.getY(), 5.0);

        observer.observeNode(this); //starts observing new LocationNode object

    }

    /**
     * TODO
     *
     * @param destination
     * @param name
     */
    public void addDestination(Destination destination, String name) {

        ArrayList<String> temp;

        if(destinations.containsKey(destination)) {

            temp = destinations.get(destination);
            temp.add(name);

        } else {

            temp = new ArrayList<String>();
            temp.add(name);

            destinations.put(destination,temp);

        }

        setChanged();

        notifyObservers();


    }

    public NodeObserver getNodeObserver(){
        return this.observer;
    }

    /**
     * TODO
     *
     * @param destination
     */
    public void removeDestination(Destination destination, String name) {

        if (destinations.containsKey(destination)) {

            ArrayList<String> temp = destinations.get(destination);

            temp.remove(name);
            setChanged();

        }



        notifyObservers();

    }

    /**
     * TODO
     *
     * @param destinationType
     * @return
     */
    public ArrayList<String> getDestinations(Destination destinationType) {

        ArrayList<String> nodeDestinations = new ArrayList<>();

        if (destinations.containsKey(destinationType)) {

            nodeDestinations.addAll(destinations.get(destinationType));

        }

        return nodeDestinations;
    }

    /**
     * TODO
     *
     * @return
     */
    public ArrayList<String> getDestinations() {

        Set<Destination> entries = destinations.keySet();

        ArrayList<String> nodeDestinations = new ArrayList<>();

        for (Destination d : entries) {

            nodeDestinations.addAll(destinations.get(d));

        }

        return nodeDestinations;
    }

    /**
     * TODO
     *
     * @param adjacentLocationNode
     */
    public void addAdjacentNode(LocationNode adjacentLocationNode) {

        if (this.equals(adjacentLocationNode)) {

            return;
        }

        if (!adjacentLocationNodes.contains(adjacentLocationNode)) {

            adjacentLocationNodes.add(adjacentLocationNode);

            adjacentLocationNode.addAdjacentNode(this);

            setChanged();
            notifyObservers();



        }

    }

    /**
     * The straight line distance between two nodes, ignoring the floor they are on.
     *
     * @param destinationLocationNode The node you want to get the distance to.
     * @return The distance between two nodes.
     */
    public double getDistanceBetweenNodes(LocationNode destinationLocationNode) {

        // location of destination node
        Location destinationLocation = destinationLocationNode.getLocation();

        // return the distance between the nodes
        return this.location.getDistanceBetween(destinationLocation);
    }

    /**
     * TODO
     *
     * @return
     */
    public double getHueristicCost() {

        return heuristicCost;
    }

    /**
     * TODO
     *
     * @return
     */
    public Location getLocation() {

        return location;
    }

    /**
     * TODO
     *
     * @param adjacentLocationNode
     */
    public void removeAdjacentNode(LocationNode adjacentLocationNode) {

        //if the node exists
        if (adjacentLocationNodes.contains(adjacentLocationNode)) {

            //removes the node from list of adjacent Nodes
            adjacentLocationNodes.remove(adjacentLocationNode);

            //removes this node from the other node's list of adjacent nodes
            adjacentLocationNode.removeAdjacentNode(this);

            setChanged();

        }

        //call to observer to check if AdjacentNodes has changed
        notifyObservers();

    }

    /**
     * TODO
     *
     * @param cost
     */
    public void setHeuristicCost(double cost) {

        if (this.heuristicCost != cost) {

            this.heuristicCost = cost;
            setChanged();

        }

        notifyObservers();

    }

    public void drawAdmin(Pane pane) {

        if (pane.getChildren().contains(nodeCircle)) {
            return;
        }

        pane.getChildren().add(this.nodeCircle);


        nodeCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (getState() == BuildingState.ADDADJACENTNODE) {

                    if (currentFloor.getOtherLocationNode() == null) {

                        currentFloor.setOtherLocationNode(getCurrentNode());
                        LOGGER.info("NULLLSLDASDASDASD");

                    } else {

                        LOGGER.info("Adding adjacent node");

                        currentFloor.getOtherLocationNode().addAdjacentNode(getCurrentNode());

                        currentFloor.setOtherLocationNode(null);


                    }

                } else if (getState() == BuildingState.MODIFYDESTINATIONS) {

                    modifyNodeView();

                }

            }
        });

    }


    public void drawAdjacentNodes(Pane pane) {

        if (adjacentLines.size() != 0) {
            adjacentLines.clear();
        }

        for (LocationNode locationNode : this.adjacentLocationNodes) {

            Line newLine  = new Line(this.location.getX(), this.location.getY(),
                    locationNode.getLocation().getX(), locationNode.getLocation().getY());

            pane.getChildren().add(newLine);

            this.adjacentLines.add(newLine);


        }



    }




    public void drawNormal(GraphicsContext context) {

    }

    /**
     * Getter for the building's current state.
     *
     * @return The state of the building.
     */
    public BuildingState getState() {

        return this.currentFloor.getState();
    }

    /**
     * Sets the state of the building
     *
     * @param state The state you want to set the building to
     */
    public void setState(BuildingState state) {

        this.currentFloor.setState(state);

    }


    public void modifyNodeView() {

        try {

            Stage stage;
            stage = new Stage();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Kiosk/Design/Admin.fxml"));
            Parent root = (Parent)loader.load();
            AdminController controller = loader.<AdminController>getController();
            controller.setNode(this);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {

        }

    }

    @Override
    public String toString() {

        return uniqueID.toString();
    }

    public LocationNode getCurrentNode() {
        return this;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }
}

