package Map;

/**
 * Created by Matt on 3/26/2016.
 */
public enum  Destination {
    PHYSICIAN(""),
    DEPARTMENT(""),
    KIOSK(""),
    BATHROOM(""),
    ELEVATOR(""),
    STAIRS("");

    private String name;

    Destination(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
