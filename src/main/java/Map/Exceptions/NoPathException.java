package Map.Exceptions;

import Map.LocationNode;

/**
 * Created by matt on 4/5/16.
 */
public class NoPathException extends Exception {

    private final LocationNode starLocationNode;
    private final LocationNode destinatonLocationNode;

    public NoPathException(LocationNode starLocationNode, LocationNode destinatonLocationNode) {

        this.starLocationNode = starLocationNode;
        this.destinatonLocationNode = destinatonLocationNode;

    }

}
