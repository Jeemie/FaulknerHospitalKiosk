package Map;

import Map.Enums.UpdateType;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;

/**
 * Created by matt on 4/18/16.
 */
public class LocationNodeEdge extends Observable {

    //
    private double weight;

    // Nodes to connect with edge
    private LocationNode locationNode1;

    //
    private LocationNode locationNode2;

    //
    private Line edgeLine;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeEdge.class);

    /**
     * Constructor to create edge and associated line between two nodes
     * @param locationNode1 Node to connect
     * @param locationNode2 Neighbor node to connect
     */
    public LocationNodeEdge(LocationNode locationNode1, LocationNode locationNode2) {

        this.locationNode1 = locationNode1;
        this.locationNode2 = locationNode2;
        this.weight = computeWeight();


        this.addObserver(this.locationNode1);
        this.addObserver(this.locationNode2);

    }

    /**
     *
     * @param pane
     */
    public void drawEdge(Pane pane) {

        if (!pane.getChildren().contains(this.edgeLine)) {

            this.edgeLine = new Line(this.locationNode1.getLocation().getX(), this.locationNode1.getLocation().getY(),
                    this.locationNode2.getLocation().getX(), this.locationNode2.getLocation().getY());

            pane.getChildren().add(this.edgeLine);

        }


        this.edgeLine.setStartX(this.locationNode1.getLocation().getX());
        this.edgeLine.setStartY(this.locationNode1.getLocation().getY());
        this.edgeLine.setEndX(this.locationNode2.getLocation().getX());
        this.edgeLine.setEndY(this.locationNode2.getLocation().getY());

        this.setWeight(computeWeight());

    }

    /**
     *
     * @param pane
     */
    public void undrawEdge(Pane pane) {

        pane.getChildren().remove(this.edgeLine);

        this.edgeLine = null;

    }

    /**
     * Check if edge already exists
     * @param currentNode LocationNode (this) from calling class
     * @param adjacentNode Adjacent node to check
     */
    public boolean edgeExists(LocationNode currentNode, LocationNode adjacentNode) {

        // Edge exists
        return this.locationNode1.equals(currentNode) && this.locationNode2.equals(adjacentNode)
                || this.locationNode2.equals(currentNode) && this.locationNode1.equals(adjacentNode);
    }

    /**
     * Check if specified edge is between two specified nodes
     * @param currentNode Node from currentNode's list of edges
     * @param adjacentNode Node to check
     */
    public boolean isEdgeBetweenNodes(LocationNode currentNode, LocationNode adjacentNode) {

        // This is the edge we are looking for
        // This is a different edge
        return this.locationNode1.equals(currentNode) && this.locationNode2.equals(adjacentNode);
    }


    /** Edge weight is the distance between two adjacent nodes
     * @return Computed weight
     */
    public double computeWeight() {

        return this.locationNode1.getDistanceBetweenNodes(this.locationNode2);

    }

    public double getWeight() {

        return weight;
    }

    // Use when a node's location changes
    public void setWeight(double weight) {

        this.weight = weight;

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEEDGE);

    }


    public LocationNode getOtherNode(LocationNode locationNode) {

        if (this.locationNode1.equals(locationNode)) {

            return this.locationNode1;
        } else {

            return this.locationNode2;
        }

    }

    public LocationNode getLocationNode1() {

        return locationNode1;
    }

    public LocationNode getLocationNode2() {

        return locationNode2;
    }

}
