package Map.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Enum to represent the destinations in Faulkner Hospital.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DestinationType {

    PHYSICIAN,
    DEPARTMENT,
    KIOSK,
    BATHROOM,
    ELEVATOR,
    STAIR,
    SERVICE

}
