package Map.SearchAlgorithms;

/**
 * Created by maryannoconnell on 4/20/16.
 */

import Map.Exceptions.NoPathException;
import Map.LocationNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Breadth first search pathfinding
 */
public class BreadthFirstSearch implements ISearchAlgorithm {

    /**
     * Find a path from specified start node to specified destination node
     * Since the edges have associated weights, this algorithm does not guarantee that
     * the path returned is the fastest path
     * @param startNode
     * @param destinationNode
     * @return List of nodes from in path from startNode to destinationNode
     */
    public ArrayList<LocationNode> getPath(LocationNode startNode, LocationNode destinationNode) throws
            NoPathException {

        LocationNode currentNode;

        // Queue to store active nodes
        PriorityQueue<LocationNode> nodeQueue = new PriorityQueue<>();

        // List of visited nodes
        ArrayList<LocationNode> visited = new ArrayList<>();

        // List of nodes in path
        ArrayList<LocationNode> path = new ArrayList<>();

        // Add start node to queue
        nodeQueue.add(startNode);

        // Add startNode to the list of the visited nodes
        visited.add(startNode);

        while (!nodeQueue.isEmpty()) {

            currentNode = nodeQueue.remove();

            if(currentNode.equals(destinationNode)) {

                path.add(currentNode);
                return path;

            } else {

                // Add the current node's neighbors to queue if they have not been visited
                for (LocationNode neighbor : currentNode.getAdjacentLocationNodes()) {

                    if (!visited.contains(neighbor)) {

                        visited.contains(neighbor);
                        path.add(neighbor);
                        nodeQueue.add(neighbor);

                    }
                }
            }
        }
        // Reconstructed path was not returned. No path exists
        throw new NoPathException(startNode, destinationNode);
    }

}
