package Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

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

    public static void constructPath(LocationNode node) {
        node.minDistance = 0.;
        PriorityQueue<LocationNode> nodeQueue = new PriorityQueue<>();
        nodeQueue.add(node);

        while (!nodeQueue.isEmpty()) {
            LocationNode u = nodeQueue.poll();

            for (Neighbors e : u.getNeighbors()) {
                LocationNode goal = e.getTempGoal();
                double cost = e.getCost();
                double distanceThroughNeighbor = u.minDistance + cost + u.getDistanceBetweenNodes(u,e);
                if (distanceThroughNeighbor < goal.minDistance) {
                    nodeQueue.remove(goal);
                    goal.minDistance = distanceThroughNeighbor;
                    goal.previous = u;
                    nodeQueue.add(goal);
                }
            }

        }
    }

    public static List<LocationNode> getShortestPathTo(LocationNode target)
    {
        List<LocationNode> path = new ArrayList<LocationNode>();
        for (LocationNode node = target; node != null; node =node.previous)
            path.add(node);

        Collections.reverse(path);
        return path;
    }
    }



    /**
     * TODO
     *
     * @param startNode
     * @param destinationNode
     * @return
     */
//    public ArrayList<LocationNode> getPath(LocationNode startNode, LocationNode destinationNode) throws NoPathException {
//        return null;
//    }


