package Map.Exceptions;

import Map.MapNode;

/**
 * Created by matt on 4/5/16.
 */
public class NoPathException extends Exception {

    private final MapNode starNode;
    private final MapNode destinatonNode;

    public NoPathException(MapNode starNode, MapNode destinatonNode) {

        this.starNode = starNode;
        this.destinatonNode = destinatonNode;

    }

}
