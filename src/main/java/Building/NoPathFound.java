package Building;

import java.util.UUID;

/**
 * Created by matthewlemay on 3/21/16.
 */
public class NoPathFound extends Exception {

    UUID currentLocation;
    UUID destination;

    NoPathFound(UUID currentLocation, UUID destination) {
        this.currentLocation = currentLocation;
        this.destination = destination;
    }
}
