package Building;

/**
 * Created by matthewlemay on 3/21/16.
 */
public class Elevator extends Position implements ILocation {

    public Elevator(int x, int y) {
        super(x, y);
    }

    public boolean canMoveTo() {
        return true;
    }

    public String message() {
        return null;
    }
}
