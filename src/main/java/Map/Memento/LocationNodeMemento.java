package Map.Memento;

import Map.*;
import Map.Enums.ImageType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */

@JsonSerialize
public class LocationNodeMemento {

    // Name of this node
    private String name;

    // Unique ID for this node
    private UUID uniqueID;

    // Location of this node
    private Location location;

    // Floor this node is located on
    private UUID currentFloorID;

    // Type of image icon to display with this (Enum)
    private ImageType associatedImage;

    // Edges from this node to adjacent nodes
    private ArrayList<LocationNodeEdgeMemento> edgeMomentos;

    // Destinations at this node
    private ArrayList<DestinationMemento> destinationMementos;

    public LocationNodeMemento(String name, UUID uniqueID, Location location, Floor currentFloor, ImageType associatedImage, ArrayList<LocationNodeEdge> edges, ArrayList<Destination> destinations) {

        this.name = name;
        this.uniqueID = uniqueID;
        this.location = location;
        this.currentFloorID = currentFloor.getUniqueID();
        this.associatedImage = associatedImage;
        this.edgeMomentos = new ArrayList<LocationNodeEdgeMemento>();
        this.destinationMementos = new ArrayList<DestinationMemento>();

        for (LocationNodeEdge locationNodeEdge : edges) {

            edgeMomentos.add(new LocationNodeEdgeMemento(locationNodeEdge.getUniqueID(), locationNodeEdge.getLocationNode1(), locationNodeEdge.getLocationNode2()));

        }

        for (Destination destination: destinations) {

            destinationMementos.add(new DestinationMemento(destination.getUniqueID(), destination.getName(), destination.getDestinationType(), destination.getCurrentLocationNode()));

        }


    }

    public String getName() {

        return name;
    }

    public UUID getUniqueID() {

        return uniqueID;
    }

    public Location getLocation() {

        return location;
    }

    public UUID getCurrentFloorID() {

        return currentFloorID;
    }

    public ImageType getAssociatedImage() {

        return associatedImage;
    }

    public ArrayList<LocationNodeEdgeMemento> getEdgeMomentos() {

        return edgeMomentos;
    }

    public ArrayList<DestinationMemento> getDestinationMementos() {

        return destinationMementos;
    }
}
