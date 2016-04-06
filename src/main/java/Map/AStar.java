package Map;

import Map.Exceptions.NoPathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * TODO
 */
public class AStar {

    private final Building building; // The building that will be associated with the AStar search
    private static final Logger LOGGER = LoggerFactory.getLogger(AStar.class); // Logger for this class

    /**
     * TODO
     *
     * @param building
     */
    public AStar(Building building) {

        this.building = building;

    }

    /**
     * TODO
     *
     * @param startNode
     * @param destinationNode
     * @return
     */
    public ArrayList<Node> getPath(Node startNode, Node destinationNode) throws NoPathException {
        return null;
    }

}
