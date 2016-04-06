package Map.Exceptions;

import Map.Node;

/**
 * Created by matt on 4/5/16.
 */
public class NoPathException extends Exception {

    private final Node starNode;
    private final Node destinatonNode;

    public NoPathException(Node starNode, Node destinatonNode) {

        this.starNode = starNode;
        this.destinatonNode = destinatonNode;

    }

}
