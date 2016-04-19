package Map;


import Map.EventHandlers.LocationNodeClickedEventHandler;
import Map.EventHandlers.LocationNodeDraggedEventHandler;
import Map.EventHandlers.LocationNodeRefactoredClickedEventHandler;
import Map.EventHandlers.LocationNodeRefactoredDraggedEventHandler;
import Map.Observers.FloorLocationNodeObserver;
import Utils.ImageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LocationNodeRefactored extends Observable implements Comparable<LocationNodeRefactored> {

    //
    private double heuristicCost;

    //
    private String name;

    //
    private UUID uniqueID;

    //
    private Location location;

    //
    private FloorLocationNodeObserver currentFloor;

    //
    private ImageType associatedImage;

    //
    private ArrayList<LocationNodeEdge> sharedEdges;

    // List of edges adjacent to this LocationNode
    private ArrayList<LocationNodeEdge> edges;

    //
    private EnumMap<Destination, ArrayList<String>> associatedDestinations;

    //
    @JsonIgnore
    private Image labelImage;

    //
    @JsonIgnore
    private Label imageLabel;


    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeRefactored.class);


    /**
     * Jackson Constructor
     */
    public LocationNodeRefactored() {

        super();

    }

    public LocationNodeRefactored(String name, Location location, FloorLocationNodeObserver currentFloor, ImageType associatedImage) {

        this.heuristicCost = 0;
        this.name = name;
        this.uniqueID = UUID.randomUUID();
        this.location = location;
        this.currentFloor = currentFloor;
        this.associatedImage = associatedImage;
        this.sharedEdges = new ArrayList<>();
        this.associatedDestinations = new EnumMap<Destination, ArrayList<String>>(Destination.class);

        this.addObserver(this.currentFloor);

    }

    public void addDestination(Destination destinationType, String name) {

        LOGGER.info("Adding the destination " + name + " of the type " + destinationType.toString() + " to the node " +
            this.toString());

        ArrayList<String> temporaryListOfNames;

        if (associatedDestinations.containsKey(destinationType)) {

            temporaryListOfNames = this.associatedDestinations.get(destinationType);
            temporaryListOfNames.add(name);

        } else {

            temporaryListOfNames = new ArrayList<String>();
            temporaryListOfNames.add(name);

            this.associatedDestinations.put(destinationType, temporaryListOfNames);

        }

        setChanged();
        notifyObservers();

    }


    public void removeDestination(Destination destination, String name) {

        if (this.associatedDestinations.containsKey(destination)) {

            ArrayList<String> temporaryListOfNames = this.associatedDestinations.get(destination);

            temporaryListOfNames.remove(name);

            setChanged();
            notifyObservers();

        }

    }

    public ArrayList<String> getDestinations(Destination destinationType) {

        ArrayList<String> nodeDestinations = new ArrayList<>();

        if (this.associatedDestinations.containsKey(destinationType)) {

            nodeDestinations.addAll(this.associatedDestinations.get(destinationType));

        }

        return nodeDestinations;
    }

    public ArrayList<String> getDestinations() {

        Set<Destination> entries = this.associatedDestinations.keySet();

        ArrayList<String> nodeDestinations = new ArrayList<>();

        for (Destination d : entries) {

            nodeDestinations.addAll(this.associatedDestinations.get(d));

        }

        return nodeDestinations;
    }

    public double getDistanceBetweenNodes(LocationNode destinationLocationNode) {

        // location of destination node
        Location destinationLocation = destinationLocationNode.getLocation();

        // return the distance between the nodes
        return this.location.getDistanceBetween(destinationLocation);
    }

    public void drawAdmin(Pane pane) {

        if (pane.getChildren().contains(this.imageLabel)) {

            return;
        }

        pane.getChildren().add(this.imageLabel);

        imageLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, new LocationNodeRefactoredClickedEventHandler(this));
        imageLabel.addEventHandler(MouseEvent.MOUSE_DRAGGED, new LocationNodeRefactoredDraggedEventHandler(this));

    }

    public void drawNormal(Pane pane) {

        if (pane.getChildren().contains(this.imageLabel)) {
            return;
        }

        pane.getChildren().add(this.imageLabel);

    }

    // TODO Maryann
    public void drawEdgesAdmin(){}
    public void drawEdgesNormal(){}
    public void deleteNode(){}
    public void addEdge(){}


    /**
     * Draw all neighboring edges of this node
     * @param pane
     */
    public void drawEdges(Pane pane) {

        LOGGER.info("Drawing edges for the Node: " + this.toString());

        // If any edges exist
        if (this.edges.size() != 0) {

            // Draw lines for edges
            for (LocationNodeEdge edge : this.edges) {

                edge.drawLine(pane);

            }

        }

        setAssociatedPane(pane);

    }


    @Override
    public String toString() {

        return name;
    }

    @Override
    public int compareTo(LocationNodeRefactored other) {

        // TODO
        return 0;
    }

    public Location getLocation() {

        return location;

    }
}
