package Map;

import Map.Exceptions.NoPathException;
import org.slf4j.LoggerFactory;

import javax.print.attribute.standard.PrinterResolution;
import java.util.*;

/**
 * TODO
 */
public class AStar {

    private final Building building; // The building that will be associated with the AStar search
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AStar.class); // Logger for this class

    /**
     * TODO
     *
     * @param building
     */
    public AStar(Building building) {

        this.building = building;

    }

    /**
     * Compute straight line distance between start and destination
     *
     * @param startNode
     * @param destinationNode
     * @return
     */
    public double computeHeuristicCost(LocationNode startNode, LocationNode destinationNode) {
        double cost = 0.0;

        // Check if start and destination nodes are on the same floor
        if (startNode.getCurrentFloor().getFloor() == destinationNode.getCurrentFloor().getFloor()) {
            cost = startNode.getDistanceBetweenNodes(destinationNode);
        } else {
            // TODO
            // Destination is on a different floor
            // Compute cost of startNode to nearest current floor elevator
            System.out.println("Multifloor path.");
            // Compute cost of SAME elevator of destination floor to destination node
        }
        return cost;
    }

    /**
     *
     * g(n) represents the exact cost of the path from the starting point to any vertex n,
     * and h(n) represents the heuristic estimated cost from vertex n to the goal.
     * In the above diagrams, the yellow (h) represents vertices far from the goal
     * and teal (g) represents vertices far from the starting point.
     * A* balances the two as it moves from the starting point to the goal.
     * Each time through the main loop, it examines the vertex n that has
     * the lowest f(n) = g(n) + h(n).
     *
     */

    /**
     *
     * @param startNode
     * @param destinationNode
     */
    public static void constructPath(LocationNode startNode, LocationNode destinationNode) {

        // Node with lowest f(n) value has highest priority in the queue

        // The set of nodes already evaluated
        PriorityQueue<LocationNode> visitedNodes = new PriorityQueue<>();

        // The set of currently discovered nodes still to be evaluated
        PriorityQueue<LocationNode> openNodes = new PriorityQueue<>();
        // Initially, only the start node is known
        openNodes.add(startNode);

        // For each node, which the destination can most efficiently be reached from


        // If a node can be reached from many nodes,
        // cameFrom will eventually contain the most efficient previous step

        // cameFrom :=

        // For each node, the cost of getting from the start node to that node

        // gScore := map with default value of Inifinity

        // The cost of going from start to start is zero

        // gScore[start] := 0

        // For each node, the total cost of getting from the start to the goal
        // by passing by that node. That value is partly known, partly heuristic.

        //fScore := map with default value of Infinity

        // For the first node, that value is completey heuristic
        //fScore[start] := heuristic_cost_estimate(start, goal)

        //while openSet is not empty

        // current := the node in openSet having the lowest fScore[] value

        //if current = goal
            // return reconstruct_path(cameFrom, current)

            // openSet.Remove(current)
            // closedSet.Add(Current)
            // for each neightbor of current
                // if neighbor in closedSet
                    // continue // Ignore the neighbor which is already evaluated
                    // The distance from start to a neighbor
                    // tenative_gScore := gScore[current] + dist_between(current, neighbor)
                // if neighbor not in openSet // Discover a new node
                    //oopenSet.Add(e







        // Add start node to the queue

// Q. where should Fscore/stored be computed?



        // Compute estimated cost
        // Compute exact cost
        //
        // ----- Main loop -----
        // Exame node in
    }


   /*
   public static void constructPath(LocationNode startNode, double cost) {

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
            for (LocationNode e : u.getAdjacentLocationNodes()) {
                //double cost = e.getHeuristicCost();
                double cost = e.getDistanceBetweenNodes()
                //calculate the distance to that neighbor
                double distanceThroughNeighbor = u.minDistance + cost + u.getDistanceBetweenNodes(e);
                if (distanceThroughNeighbor < e.minDistance) {
                    //replace path with a shorter path and remove node u to the node set
                    nodeQueue.remove(u);
                    e.minDistance = distanceThroughNeighbor;
                    e.previous = u;
                    nodeQueue.add(e);
                }
            }

        }
    }
    */

    public static List<LocationNode> getShortestPathTo(LocationNode target)
    {
        List<LocationNode> path = new ArrayList<LocationNode>();

        for (LocationNode node = target; node != null; node=node.previous) {
            path.add(node);
        }

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


