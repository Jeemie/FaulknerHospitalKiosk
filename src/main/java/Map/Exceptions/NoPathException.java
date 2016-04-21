package Map.Exceptions;


import Map.LocationNode;

/**
 * Created by matt on 4/5/16.
 */
public class NoPathException extends Exception {

    private final LocationNode startLocationNode;
    private final LocationNode destinatonLocationNode;

    public NoPathException(LocationNode startLocationNode, LocationNode destinatonLocationNode) {

        this.startLocationNode = startLocationNode;
        this.destinatonLocationNode = destinatonLocationNode;

    }

}
