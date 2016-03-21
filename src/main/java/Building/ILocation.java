package Building;

/**
 * Created by matthewlemay on 3/21/16.
 */
public interface ILocation {

    int getXCoordinate();

    int getYCoordinate();

    boolean canMoveTo();

    String message();
}
