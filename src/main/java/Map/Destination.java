package Map;

/**
 * Enum to represent the destinations in Faulkner Hospital.
 */
public enum  Destination {
    Physician(""),
    Department(""),
    Kiosk(""),
    Bathroom(""),
    Elevator(""),
    Stairs("");

    private String name; // Name of the destination

    /**
     * Constructor for a destination.
     *
     * @param name The name of the destination.
     */
    Destination(String name) {

        this.name = name;
    }

    /**
     * Getter for the name of the Destination.
     *
     * @return The name of the Destination.
     */
    public String getName() {

        return name;
    }

}
