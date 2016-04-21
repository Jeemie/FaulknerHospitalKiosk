package Map.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TODO
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ImageType {

    POINT(""),
    BATHROOM(""),
    STAIRS(""),
    ELEVATOR(""),
    WAITINGROOM(""),
    SERVICE(""),
    KIOSK("");

    private String resourceFileName;

    ImageType(String resourceFileName) {

        this.resourceFileName = resourceFileName;

    }

    public String getResourceFileName() {

        return resourceFileName;
    }
}
