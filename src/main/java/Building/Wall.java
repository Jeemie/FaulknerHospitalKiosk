package Building;

/**
 * Created by matthewlemay on 3/21/16.
 */
public class Wall extends Position implements ILocation {

    public Wall(int x, int y) {
        super(x, y);
    }

    public boolean canMoveTo() {
        return false;
    }

    public String message() {
        return null;
    }

}
