package Building;

/**
 * Created by matthewlemay on 3/21/16.
 */
public class Room extends Position implements ILocation {

    public Room(int x, int y) {
        super(x, y);
    }

    public boolean canMoveTo() {
        return true;
    }

    public String message() {
        return null;
    }
}
