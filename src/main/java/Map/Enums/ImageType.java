package Map.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TODO
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ImageType {

    POINT("/icons/gift.png"),
    BATHROOM("/icons/bathroom.png"),
    STAIRS("/icons/stair.png"),
    ELEVATOR("/icons/elevator.png"),
    WAITINGROOM("/icons/doctor.png"),
    SERVICE("/icons/miscellaneous.png"),
    STORE("/icons/gift.png"),
    STARTLOCATION("/icons/startlocation.png"),
    DESTINATION("/icons/destination.png"),
    KIOSK("/icons/kiosk.png"),
    HALLWAYS("/icons/hall.png");

    private String resourceFileName;

    ImageType(String resourceFileName) {

        this.resourceFileName = resourceFileName;

    }

    public String getResourceFileName() {

        return resourceFileName;
    }
}
