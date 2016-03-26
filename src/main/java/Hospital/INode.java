package Hospital;

import java.util.ArrayList;

/**
 * Created by matthewlemay on 3/25/16.
 */
public interface INode {

    int getFloor() throws MultipleFloorException;

    boolean isType(Object type);

    ArrayList<INode> getNeighbors();

    ArrayList<String> getDepartments();

    ArrayList<String> getDoctors();

    NodeLocation getLocation();
}
