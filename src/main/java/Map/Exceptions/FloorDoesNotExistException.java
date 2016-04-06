package Map.Exceptions;

/**
 * TODO
 */
public class FloorDoesNotExistException extends Exception {

    private final int floorNumber;

    public FloorDoesNotExistException(int floorNumber) {

        this.floorNumber = floorNumber;

    }


}
