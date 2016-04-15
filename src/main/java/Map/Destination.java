package Map;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum to represent the destinations in Faulkner Hospital.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  Destination {

    PHYSICIAN,
    DEPARTMENT,
    KIOSK,
    BATHROOM,
    ELEVATOR,
    SERVICE, STAIR

}
