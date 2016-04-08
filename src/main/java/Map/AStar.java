package Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
        //distance from start to start is 0
        node.minDistance = 0.;
        //create node set nodeQueue
        PriorityQueue<LocationNode> nodeQueue = new PriorityQueue<>();
        nodeQueue.add(node);
        //while nodeQueue is not empty
        while (!nodeQueue.isEmpty()) {
            //start node will be selected first
            LocationNode u = nodeQueue.poll();
            //for each neighbor e of u
            for (Neighbors e : u.neighbors) {
                LocationNode v = e.getTempGoal();
                double cost = e.getCost();
                //calculate the distance to that neighbor
                double distanceThroughNeighbor = u.minDistance + cost + u.getDistanceBetweenNodes(v);
                if (distanceThroughNeighbor < v.minDistance) {
                    //replace path with a shorter path and remove node u to the node set
                    nodeQueue.remove(u);
                    v.minDistance = distanceThroughNeighbor;
                    v.previous = u;
                    nodeQueue.add(v);
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


