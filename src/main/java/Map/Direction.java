package Map;

import Map.Enums.RelativeDirection;

/**
 * Created by binam on 4/29/16.
 */
public class Direction {

    RelativeDirection relativeDirection;
    String directionString;
    int distance;
    LocationNode turningPoint;

    public Direction(RelativeDirection relativeDirection, String directionString, int distance, LocationNode turningPoint) {
        this.relativeDirection = relativeDirection;
        this.directionString = directionString;
        this.distance = distance;
        this.turningPoint = turningPoint;
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

    public LocationNode getTurningPoint() {
        return turningPoint;
    }
}
