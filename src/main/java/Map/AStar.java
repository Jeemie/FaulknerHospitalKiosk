package Map;

import Map.Exceptions.NoPathException;
import org.slf4j.LoggerFactory;

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


    //

    /**
     * Determines the shortest path between a start node and destination node.
     * fScore the total cost of getting from the start node to the goal
     * gScore is the cost of getting from the start node to that node
     * Heuristic is determined by the straight line distance (computed with getDistanceBetweenNodes())
     *  @param startNode Start destination in path
     * @param destinationNode End destination in path
     */
    public static ArrayList<LocationNode> aStar(LocationNode startNode, LocationNode destinationNode) throws NoPathException {

        LocationNode currentNode;
        Double tentative_gScore;

        // The set of nodes already evaluated
        ArrayList<LocationNode> closedNodes = new ArrayList<>();

        // The set of currently discovered nodes still to be evaluated
        // Node with lowest f(n) value has highest priority in the queue
        PriorityQueue<LocationNode> openNodes = new PriorityQueue<>(fComparator);

        // Initially, only the start node is known
        openNodes.add(startNode);

        // The cost of going from start to start is zero
        startNode.setGscore(0.0);

        // For the first node, the total cost of getting from the start node to the goal
        // by passing by that node is completely heuristic.
        startNode.setFscore(startNode.getDistanceBetweenNodes(destinationNode));

        while(!openNodes.isEmpty()) {

            // the node in openSet having the lowest fScore value
            currentNode = openNodes.peek();

            if (currentNode.equals(destinationNode)) {

                return reconstructPath(currentNode);

            }

            openNodes.remove(currentNode);
            closedNodes.add(currentNode);

            for (LocationNode neighbor : currentNode.getAdjacentLocationNodes()) {

                // Check if neighbor is not traversable and/or not contained in closed set.
                // Else skip to next neighbor.
                if(neighbor != null && !closedNodes.contains(neighbor)) {


                    // The distance from start to a neighbor
                    tentative_gScore = currentNode.getGscore() + currentNode.getDistanceBetweenNodes(neighbor);

                    // If new path to neighbor is shorter or neighbor is not in open set
                    if (!openNodes.contains(neighbor)) {

                        // Discover a new node
                        // Set current node as previous for this neighbor
                        neighbor.setCameFrom(currentNode);

                        // Set g cost of neighbor (cost from start node to this node)
                        neighbor.setGscore(currentNode.getFscore() + currentNode.getDistanceBetweenNodes(neighbor));

                        // Add to open set
                        openNodes.add(neighbor);

                    }  else if (tentative_gScore < neighbor.getGscore()) {

                        // This is a better path. Record it.
                        neighbor.setCameFrom(currentNode);
                        neighbor.setGscore(tentative_gScore);
                        neighbor.setFscore(neighbor.getGscore() + neighbor.getDistanceBetweenNodes(destinationNode));

                    }
                }
            }
        }

        // Reconstructed path was not returned. No path exists
        throw new NoPathException(startNode, destinationNode);

    }


    //TODO reset all set costs



    //Comparator anonymous class implementation
    public static Comparator<LocationNode> fComparator = new Comparator<LocationNode>(){

        @Override
        public int compare(LocationNode n1, LocationNode n2) {
            return (int) (n1.getFscore() - n2.getFscore());
        }
    };



    public static ArrayList<LocationNode> reconstructPath(LocationNode currentNode) {
        ArrayList<LocationNode> total_path = new ArrayList<>();

        while (currentNode != null) {

            total_path.add(currentNode);
            currentNode = currentNode.getCameFrom();

        }

        // Path from destination to start
        return total_path;
    }

}
