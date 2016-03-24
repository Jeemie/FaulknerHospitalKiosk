package Hospital;

import java.util.UUID;

/**
 * Created by matthewlemay on 3/24/16.
 */
public class LocationDoesNotExist extends Exception {

    private UUID location;

    LocationDoesNotExist(UUID location) {

        this.location = location;

    }

}
