package Map;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;

/**
 * Created by matt on 4/18/16.
 */
public class LocationNodeEdge extends Observable {

    // Unique ID for this edge
    private UUID uniqueID;

    // Weight is determined by straight line distance
    private double weight;

    // Node to connect with edge
    private LocationNode locationNode1;

    // Other node to connect with
    private LocationNode locationNode2;

    private Line edgeLine;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeEdge.class);

    /**
     * Constructor to create edge and associated line between two nodes
     * @param locationNode1 Node to connect
     * @param locationNode2 Neighbor node to connect
     */
    public LocationNodeEdge(LocationNode locationNode1, LocationNode locationNode2) {

        this.uniqueID = UUID.randomUUID();
        this.locationNode1 = locationNode1;
        this.locationNode2 = locationNode2;
        this.weight = computeWeight();


        this.addObserver(this.locationNode1);
        this.addObserver(this.locationNode2);

    }

    public void drawEdgeAdmin(Pane locationNodeEdgePane) {

        // TODO setup edge clicking

        if (!locationNode1.isSameFloor(locationNode2)) {

            return;
        }

        if (!locationNodeEdgePane.getChildren().contains(this.edgeLine)) {

            this.edgeLine = new Line(this.locationNode1.getLocation().getX(), this.locationNode1.getLocation().getY(),
                    this.locationNode2.getLocation().getX(), this.locationNode2.getLocation().getY());
            this.edgeLine.setStrokeWidth(3);

            locationNodeEdgePane.getChildren().add(this.edgeLine);

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
    public void drawEdge(Pane pane) {

        if (!locationNode1.isSameFloor(locationNode2)) {

            return;
        }

        if (!pane.getChildren().contains(this.edgeLine)) {

            this.edgeLine = new Line(this.locationNode1.getLocation().getX(), this.locationNode1.getLocation().getY(),
                    this.locationNode2.getLocation().getX(), this.locationNode2.getLocation().getY());

            this.edgeLine.setStrokeWidth(2);

            pane.getChildren().add(this.edgeLine);

        }

        this.edgeLine.setStartX(this.locationNode1.getLocation().getX());
        this.edgeLine.setStartY(this.locationNode1.getLocation().getY());
        this.edgeLine.setEndX(this.locationNode2.getLocation().getX());
        this.edgeLine.setEndY(this.locationNode2.getLocation().getY());

        this.setWeight(computeWeight());

    }


    /**
     * Get the edge between two specified nodes
     * @param node1
     * @param node2
     * @return
     */
    public static LocationNodeEdge getEdgeBetween(ArrayList<LocationNodeEdge> edges, LocationNode node1, LocationNode node2) {

        for (LocationNodeEdge edge : edges) {

            if (edge.isEdgeBetweenNodes(node1, node2)) {

                return edge;

            }
        }

        // No edge exists in specified list between the two specified nodes
        return null;
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
     * Check if edge already exists (prevents duplicate edges from being added)
     * (Order in which nodes are specified does not matter)
     *
     * @param currentNode LocationNode (this) from calling class
     * @param adjacentNode Adjacent node to check
     */
    public boolean edgeExists(LocationNode currentNode, LocationNode adjacentNode) {

        // Edge exists
        return this.locationNode1.equals(currentNode) && this.locationNode2.equals(adjacentNode)
                || this.locationNode2.equals(currentNode) && this.locationNode1.equals(adjacentNode);
    }

    /**
     * Check if specified edge is between two specified nodes (Order in which nodes are specified matters)
     *
     * @param currentNode Node from currentNode's list of edges
     * @param adjacentNode Node to check
     * @return True, if this is the edge we are looking for; False, if this is a different edge
     */
    public boolean isEdgeBetweenNodes(LocationNode currentNode, LocationNode adjacentNode) {

        return (this.locationNode1.equals(currentNode) && this.locationNode2.equals(adjacentNode)) ||
                (this.locationNode1.equals(adjacentNode) && this.locationNode2.equals(currentNode));
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

    public void setWeight(double weight) {

        this.weight = weight;
    }

    /**
     * When given one of this edge's nodes, get the other node associated with the edge
     * @param locationNode The given node
     * @return The other node connected by this edge
     */
    public LocationNode getOtherNode(LocationNode locationNode) {

        if (this.locationNode1.equals(locationNode)) {

            return this.locationNode2;
        } else {

            return this.locationNode1;
        }

    }

    public LocationNode getLocationNode1() {

        return locationNode1;
    }

    public LocationNode getLocationNode2() {

        return locationNode2;
    }

    public UUID getUniqueID() {

        return uniqueID;
    }
}
