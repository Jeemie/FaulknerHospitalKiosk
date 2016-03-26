package Hospital;

import java.util.ArrayList;

/**
 * Created by matthewlemay on 3/25/16.
 */
public abstract class AbsNode {

    private NodeLocation location;
    private java.util.ArrayList<INode> neighbors;

    public NodeLocation getLocation() {
        return null;
    }

    public ArrayList<INode> getNeighbors() {
        return null;
    }

    public boolean isType(Object type) {
        return false;
    }
}
