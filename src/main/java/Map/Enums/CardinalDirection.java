package Map.Enums;

/**
 * Enum to represent the CardinalDirection. Used for the method
 *   in the LocationNode class called getDirectionsTo
 */
public enum CardinalDirection {

    NORTH,
    SOUTH,
    EAST,
    WEST,
    ERR;

    //Get the opposites of the compas, used by the getTextualDirections function
    public CardinalDirection opposite() {
        switch(this) {
            case NORTH:
                return CardinalDirection.SOUTH;
            case EAST:
                return CardinalDirection.WEST;
            case SOUTH:
                return CardinalDirection.NORTH;
            case WEST:
                return CardinalDirection.EAST;
            default:
                return CardinalDirection.ERR;
        }
    }

    //Get the value to the right of the compass, used by getTextualDirections function
    public CardinalDirection right() {
        switch(this) {
            case NORTH:
                return CardinalDirection.EAST;
            case EAST:
                return CardinalDirection.SOUTH;
            case SOUTH:
                return CardinalDirection.WEST;
            case WEST:
                return CardinalDirection.NORTH;
            default:
                return CardinalDirection.ERR;
        }

    }

    //Get the value of the left of the compass, used by getTextualDirections function
    public CardinalDirection left() {
        switch(this) {
            case NORTH:
                return CardinalDirection.WEST;
            case EAST:
                return CardinalDirection.NORTH;
            case SOUTH:
                return CardinalDirection.EAST;
            case WEST:
                return CardinalDirection.SOUTH;
            default:
                return CardinalDirection.ERR;
        }
    }

}
