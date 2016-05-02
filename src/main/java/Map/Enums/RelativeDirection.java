package Map.Enums;

import Map.Map;

/**
 * Created by binam on 4/14/16.
 */
public enum RelativeDirection {

    STRAIGHT("/icons/left.png"),
    RIGHT("/icons/right.png"),
    BACK("/icons/backward.png"),
    LEFT("/icons/left.png"),
    ELEVATOR("/icons/elevator.png");

    private String resourceFileName;

    RelativeDirection(String resourceFileName) {

        this.resourceFileName = resourceFileName;

    }

    public String getResourceFileName() {

        return resourceFileName;
    }

}
