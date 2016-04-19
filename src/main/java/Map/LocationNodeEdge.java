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
        this.newLine = new Line(this.v.getLocation().getX(), this.v.getLocation().getY(),
                this.w.getLocation().getX(), this.v.getLocation().getY());
    }

    public void drawLine(Pane pane) {

        pane.getChildren().add(this.newLine);

    }

    public void removeLine(Pane pane) {

        pane.getChildren().remove(this.newLine);
        this.newLine = null;

    }

    /**
     * Update edge when node is moved
     */
    public void updateEdge() {

        this.newLine = new Line(this.v.getLocation().getX(), this.v.getLocation().getY(),
                this.w.getLocation().getX(), this.v.getLocation().getY());

        this.weight = computeWeight();

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
