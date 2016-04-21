package Map.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

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
    FLOOR(""),
    SERVICE("");

    private String resourceFileName;

    ImageType(String resourceFileName) {

        this.resourceFileName = resourceFileName;

    }

    public String getResourceFileName() {

        return resourceFileName;
    }
}
