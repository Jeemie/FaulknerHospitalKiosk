package AStar;

/**
 * Created by Matt on 3/26/2016.
 */
public enum  Destination {
    Physician(""),
    Department(""),
    Kiosk(""),
    Bathroom(""),
    Elevator(""),
    Stairs("");

    private String name;

    Destination(String name) {
        this.name = name;
    }
}
