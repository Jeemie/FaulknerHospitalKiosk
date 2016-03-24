package Hospital;

import java.util.UUID;

/**
 * Created by matthewlemay on 3/24/16.
 */
public class NoPathFound extends Exception {

    private UUID startingPoint;
    private UUID destination;

    NoPathFound(UUID startingPoint, UUID destination) {

        this.startingPoint = startingPoint;
        this.destination = destination;

    }

}
