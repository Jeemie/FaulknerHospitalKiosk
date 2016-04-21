package MapTest;

import Map.Building;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NoPathException;
import Map.Location;
import Map.LocationNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static Map.AStar.aStar;

/**
 * Created by maryannoconnell on 4/18/16.
 */
public class NoPathExceptionTest {

    private Building mTestBuilding;
    private LocationNode startNode;
    private LocationNode destinationNode;
    private LocationNode connectionNode1, connectionNode2, connectionNode3, connectionNode4, connectionNode5;

    @Before
    public void setUp() {

        mTestBuilding = new Building();

    }

    /**
     * Path between two nodes has no edge
     */
    @Test(expected = NoPathException.class)
    public void testNoEdges() throws Exception {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.addNode(1, new Location(0, 0));
        mTestBuilding.addNode(1, new Location(0, 1));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);
        destinationNode =  mTestBuilding.getFloor(1).getFloorNodes().get(1);

        // Throws exception
        aStar(startNode, destinationNode);

    }

    /**
     * Two paths are  disconnected from the destination
     * @throws Exception
     */
    @Test(expected = NoPathException.class)
    public void testTwoPathsDisconnected() throws Exception {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        // Start node - index 0
        mTestBuilding.addNode(1, new Location(0, 0));
        // Junction between two paths - index 1
        mTestBuilding.addNode(1, new Location(0, 1));
        // Path one connection node - index 2
        mTestBuilding.addNode(1, new Location(0, 2));
        // Path two connection nodes - indices 3-5
        mTestBuilding.addNode(1, new Location(1, 1));
        mTestBuilding.addNode(1, new Location(1, 2));
        mTestBuilding.addNode(1, new Location(1, 3));
        // Destination node - index 6
        mTestBuilding.addNode(1, new Location(0, 3));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);

        // Junction
        connectionNode1 = mTestBuilding.getFloor(1).getFloorNodes().get(1);

        // Path one connection node
        connectionNode2 = mTestBuilding.getFloor(1).getFloorNodes().get(2);

        // Path two connection nodes
        connectionNode3 = mTestBuilding.getFloor(1).getFloorNodes().get(3);
        connectionNode4 = mTestBuilding.getFloor(1).getFloorNodes().get(4);
        connectionNode5 = mTestBuilding.getFloor(1).getFloorNodes().get(5);

        destinationNode =  mTestBuilding.getFloor(1).getFloorNodes().get(6);

        // Edges
        startNode.addAdjacentNode(connectionNode1);

        // Junction edges
        connectionNode1.addAdjacentNode(connectionNode2);
        connectionNode1.addAdjacentNode(connectionNode3);

        // Path one edge to destination removed

        // Path two edges
        connectionNode3.addAdjacentNode(connectionNode4);
        connectionNode4.addAdjacentNode(connectionNode5);

        // Path two edge to destination was removed

        // Throws exception
        aStar(startNode, destinationNode);

    }


}
