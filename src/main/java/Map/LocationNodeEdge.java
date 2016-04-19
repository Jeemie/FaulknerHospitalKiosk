package Map;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * Created by matt on 4/18/16.
 */
public class LocationNodeEdge {

    private double weight;
    // Nodes to connect with edge
    private LocationNode v;
    private LocationNode w;
    Line newLine;

    /**
     * Constructor to create edge and associated line between two nodes
     * @param v Node to connect
     * @param w Neighbor node to connect
     */
    public LocationNodeEdge(LocationNode v, LocationNode w) {

        this.v = v;
        this.w = w;
        this.weight = computeWeight();

    }

    /**
     *
     * @param pane
     */
    public Pane drawLine(Pane pane) {

        this.newLine = new Line(this.v.getLocation().getX(), this.v.getLocation().getY(),
                this.w.getLocation().getX(), this.v.getLocation().getY());

        pane.getChildren().add(this.newLine);

        return pane;

    }

    /**
     *
     * @param pane
     */
    public Pane removeLine(Pane pane) {

        pane.getChildren().remove(this.newLine);

        this.newLine = null;

        return pane;

    }

    /**
     * Update edge when node is moved.
     */
    public void updateEdge() {

        this.newLine = new Line(this.v.getLocation().getX(), this.v.getLocation().getY(),
                this.w.getLocation().getX(), this.v.getLocation().getY());

        this.weight = computeWeight();

    }

    /**
     * Check if edge already exists
     * @param currentNode LocationNode (this) from calling class
     * @param adjacentNode Adjacent node to check
     */
    public boolean edgeExists(LocationNode currentNode, LocationNode adjacentNode) {

        // Edge exists
        return this.v.equals(currentNode) && this.w.equals(adjacentNode)
                || this.w.equals(currentNode) && this.v.equals(adjacentNode);
    }

    /**
     * Check if specified edge is between two specified nodes
     * @param currentNode Node from currentNode's list of edges
     * @param adjacentNode Node to check
     */
    public boolean isEdgeBetweenNodes(LocationNode currentNode, LocationNode adjacentNode) {

        // This is the edge we are looking for
// This is a different edge
        return this.v.equals(currentNode) && this.w.equals(adjacentNode);
    }


    /** Edge weight is the distance between two adjacent nodes
     * @return Computed weight
     */
    public double computeWeight() {

        return this.v.getDistanceBetweenNodes(this.w);

    }

    public double getWeight() {
        return weight;
    }

    // Use when a node's location changes
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocationNode getV() {
        return v;
    }

    public void setV(LocationNode v) {
        this.v = v;
    }

    public LocationNode getW() {
        return w;
    }

    public void setW(LocationNode w) {
        this.w = w;
    }

    public Line getNewLine() {
        return newLine;
    }

    public void setNewLine(Line newLine) {
        this.newLine = newLine;
    }

}
