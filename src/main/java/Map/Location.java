package Map;

import Map.Enums.UpdateType;

import java.util.Observable;

/**
 * Class that contains the x and y coordinates of a arbitrary point.
 */
public class Location extends Observable {

    private double x;
    private double y;

    public Location() {
        super();
    }

    /**
     * Default constructor for Location Class
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Location(double x, double y) {

        this.x = x;
        this.y = y;

    }

    /**
     * Getter for the x-coordinate
     *
     * @return The location's x-coordinate
     */
    public double getX() {

        return x;
    }

    /**
     * Getter for the y-coordinate
     *
     * @return The location's y-coordinate
     */
    public double getY() {

        return y;
    }

    /**
     * Setter for the x-coordinate
     *
     * @param x The new x-coordinate
     */
    public void setX(double x) {

        this.x = x;

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEMOVED);

    }

    /**
     * Setter for the y-coordinate
     *
     * @param y The new y-coordinate
     */
    public void setY(double y) {

        this.y = y;

        setChanged();
        notifyObservers(UpdateType.LOCATIONNODEMOVED);

    }

    /**
     * Gets the straight line distance between two Locations using the Euclidean distance formula.
     *
     * @param secondLocation The location you want to get the distance to.
     * @return The Euclidean distance between two locations.
     */
    public double getDistanceBetween(Location secondLocation) {

        //perform distance formula -> distance = sqrt((x1-x2)^2 + (y1 - y2)^2)
        double xDistance = Math.pow((this.x - secondLocation.getX()), 2);
        double yDistance = Math.pow((this.y - secondLocation.getY()), 2);
        double result = Math.sqrt(xDistance + yDistance);

        // return the distance between two locations
        return result;
    }



}
