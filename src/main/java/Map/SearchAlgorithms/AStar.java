package Map.SearchAlgorithms;

import Map.*;
import Map.Exceptions.NoPathException;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Collections.reverse;

/**
 * TODO
 */
public class AStar implements ISearchAlgorithm {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AStar.class); // Logger for this class

    public AStar() {

    }

    /**
     * Determines the shortest path between a start node and destination node.
     * fScore the total cost of getting from the start node to the goal
     * gScore is the cost of getting from the start node to that node
     * Heuristic is determined by the straight line distance (computed with getDistanceBetweenNodes())
     * @param startNode Start destination in path
     * @param destinationNode End destination in path
     */
    public ArrayList<LocationNode> getPath(LocationNode startNode, LocationNode destinationNode) throws
            NoPathException {

        if (startNode == null || destinationNode == null) {

            throw new NoPathException(startNode, destinationNode);

        }

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
        startNode.setgScore(0.0);

        // For the first node, the total cost of getting from the start node to the goal
        // by passing by that node is completely heuristic.
        startNode.setfScore(startNode.getDistanceBetweenNodes(destinationNode));

        while(!openNodes.isEmpty()) {

            // the node in openSet having the lowest fScore value
            currentNode = openNodes.peek();

            if (currentNode.equals(destinationNode)) {

                ArrayList<LocationNode> path = reconstructPath(currentNode);

                resetCosts(closedNodes, openNodes);

                return path;

            }

            openNodes.remove(currentNode);
            closedNodes.add(currentNode);

            for (LocationNode neighbor : currentNode.getAdjacentLocationNodes()) {

                // Check if neighbor is not traversable and/or not contained in closed set.
                // Else skip to next neighbor.
                if(neighbor != null && !closedNodes.contains(neighbor)) {

                    // The distance from start to a neighbor
                    tentative_gScore = currentNode.getgScore() + currentNode.getDistanceBetweenNodes(neighbor);

                    // If new path to neighbor is shorter or neighbor is not in open set
                    if (!openNodes.contains(neighbor)) {

                        // Discover a new node

                        // Set current node as previous for this neighbor
                        neighbor.setCameFrom(currentNode);

                        // Set g cost of neighbor (cost from start node to this node)
                        neighbor.setgScore(tentative_gScore);

                        neighbor.setfScore(neighbor.getgScore() + neighbor.getDistanceBetweenNodes(destinationNode));

                        // Add to open set
                        openNodes.add(neighbor);

                    }  else if (tentative_gScore < neighbor.getgScore()) {

                        // This is a better path. Record it.
                        neighbor.setCameFrom(currentNode);
                        neighbor.setgScore(tentative_gScore);
                        neighbor.setfScore(neighbor.getgScore() + neighbor.getDistanceBetweenNodes(destinationNode));

                    }
                }
            }
        }

        resetCosts(closedNodes, openNodes);
        // Reconstructed path was not returned. No path exists
        throw new NoPathException(startNode, destinationNode);

    }

    /**
     * Reset F-costs, G-costs, and cameFrom for all nodes in closed and open node list
     * @param closedSet  LocationNodes that have already been evaluated
     * @param openSet Final node when path was found, or remaining nodes if node was not found
     */
    public static void resetCosts (ArrayList<LocationNode> closedSet, PriorityQueue<LocationNode> openSet) {

        for (LocationNode node: closedSet) {

            node.setfScore(Double.POSITIVE_INFINITY);
            node.setgScore(Double.POSITIVE_INFINITY);
            node.setCameFrom(null);
        }
    }

    //Comparator anonymous class implementation
    public static Comparator<LocationNode> fComparator = new Comparator<LocationNode>(){

        @Override
        public int compare(LocationNode n1, LocationNode n2) {
            return (int) (n1.getfScore() - n2.getfScore());
        }
    };

    /**
     * Reconstruct the shortest path found by dijkstras()
     * @param currentNode Current node is the goal node after the path was discovered
     * @return List of location nodes in path
     */
    public static ArrayList<LocationNode> reconstructPath(LocationNode currentNode) {

        ArrayList<LocationNode> total_path = new ArrayList<>();

        while (currentNode != null) {

            total_path.add(currentNode);
            currentNode = currentNode.getCameFrom();

        }

        reverse(total_path);

        // Path from start to destination
        return total_path;
    }

}
