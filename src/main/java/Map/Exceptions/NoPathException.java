package Map.Exceptions;


import Map.LocationNode;

/**
 * Created by matt on 4/5/16.
 */
public class NoPathException extends Exception {

    private final LocationNode startLocationNode;
    private final LocationNode destinationLocationNode;

    public NoPathException(LocationNode startLocationNode, LocationNode destinationLocationNode) {

        this.startLocationNode = startLocationNode;
        this.destinationLocationNode = destinationLocationNode;

        if (startLocationNode == null) {

            System.err.println("startLocationNode is null.");
        }

        if (destinationLocationNode == null) {

            System.err.println("destinationLocationNode is null.");
        }

    }

}
