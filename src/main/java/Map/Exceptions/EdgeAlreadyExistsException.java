package Map.Exceptions;

import Map.LocationNode;

/**
 * Created by maryannoconnell on 4/30/16.
 */
public class EdgeAlreadyExistsException extends Throwable {

    public EdgeAlreadyExistsException(LocationNode currentNode, LocationNode adjacentNode) {

        System.err.println("Cannot add new edge. Edge between " + currentNode + " and " + adjacentNode + " already exists.");

    }
}
