package Map;

import Map.Enums.RelativeDirection;

/**
 * Created by binam on 4/29/16.
 */
public class Direction {

    RelativeDirection relativeDirection;
    String directionString;
    int distance;

    public Direction(RelativeDirection relativeDirection, String directionString, int distance) {
        this.relativeDirection = relativeDirection;
        this.directionString = directionString;
        this.distance = distance;
    }

    public RelativeDirection getRelativeDirection() {
        return relativeDirection;
    }

    public String getDirectionString() {
        return directionString;
    }

    public int getDistance() {
        return distance;
    }
}
