package Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that contains the x and y coordinates of a arbitrary point.
 */

public class Location {
    private int x;
    private int y;

    /**
     * Default constructor for Location Class
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Location(int x, int y) {

        this.x = x;
        this.y = y;

    }

    /**
     * Getter for the x-coordinate
     *
     * @return The location's x-coordinate
     */
    public int getX() {

        return x;
    }

    /**
     * Getter for the y-coordinate
     *
     * @return The location's y-coordinate
     */
    public int getY() {

        return y;
    }

    /**
     * Setter for the x-coordinate
     *
     * @param x The new x-coordinate
     */
    public void setX(int x) {

        this.x = x;

    }

    /**
     * Setter for the y-coordinate
     *
     * @param y The new y-coordinate
     */
    public void setY(int y) {

        this.y = y;

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
