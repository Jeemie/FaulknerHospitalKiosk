package Map.Enums;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TODO
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DirectionIcons {

    LEFT("/icons/left.png"),
    RIGH("/icons/right.png"),
    FORWARD("/icons/forward.png"),
    BACK("/icons/backward.png"),
    ELEVATOR("/icons/elevator.png");


    private String resourceFileName;

    DirectionIcons(String resourceFileName) {

        this.resourceFileName = resourceFileName;

    }

    public String getResourceFileName() {

        return resourceFileName;
    }
}
