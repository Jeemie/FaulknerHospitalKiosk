import Map.Building;
import Map.Destination;
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
 * Created by maryannoconnell on 4/17/16.
 */
public class AstarTest {

    private Building mTestBuilding, mTestSecondBuilding;
    private LocationNode startNode;
    private LocationNode destinationNode;
    private LocationNode connectionNode1, connectionNode2, connectionNode3, connectionNode4, connectionNode5;
    private LocationNode junctionNode1, junctionNode2, junctionNode3;
    private ArrayList<LocationNode> actualPath = new ArrayList<>();
    private ArrayList<LocationNode> expectedPath = new ArrayList<>();

    @Before
    public void setUp() {

        mTestBuilding = new Building();
        mTestSecondBuilding = new Building();
    }

    /**
     * Get path between two nodes connected by exactly one edge (the nodes are adjacent to one another)
     */
    @Test
    public void testSingleEdgePath() throws FloorDoesNotExistException, NoPathException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.addNode(1, new Location(0, 0));
        mTestBuilding.addNode(1, new Location(0, 1));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);
        destinationNode =  mTestBuilding.getFloor(1).getFloorNodes().get(1);

        startNode.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get path between nodes connected through two edges
     */
    @Test
    public void testSinglePath() throws FloorDoesNotExistException, NoPathException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");

        mTestBuilding.addNode(1, new Location(0, 0));
        mTestBuilding.addNode(1, new Location(0, 1));
        mTestBuilding.addNode(1, new Location(0, 2));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);
        connectionNode1 = mTestBuilding.getFloor(1).getFloorNodes().get(1);
        destinationNode =  mTestBuilding.getFloor(1).getFloorNodes().get(2);

        // Edges
        startNode.addAdjacentNode(connectionNode1);
        connectionNode1.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get shortest path between two nodes connected through two paths
     */
    @Test
    public void testTwoPaths() throws FloorDoesNotExistException, NoPathException {

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

        // Path one edge to destination
        connectionNode2.addAdjacentNode(destinationNode);

        // Path two edges to destination
        connectionNode3.addAdjacentNode(connectionNode4);
        connectionNode4.addAdjacentNode(connectionNode5);
        connectionNode5.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path (path 1) from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(connectionNode2);
        expectedPath.add(connectionNode1);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get shortest path between two nodes connected through two paths
     * Test that result is not impacted by order in which adjacent nodes are added
     */
    @Test
    public void testOrder() throws FloorDoesNotExistException, NoPathException {

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

        // Path two edges to destination
        connectionNode3.addAdjacentNode(connectionNode4);
        connectionNode4.addAdjacentNode(connectionNode5);
        connectionNode5.addAdjacentNode(destinationNode);

        // Path one edge to destination
        connectionNode2.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path (path 1) from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(connectionNode2);
        expectedPath.add(connectionNode1);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get shortest path between two nodes connected through three paths
     */
    @Test
    public void testThreePaths() throws FloorDoesNotExistException, NoPathException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");

        // Start node - index 0
        mTestBuilding.addNode(1, new Location(0, 0));

        // Junction between all paths - index 1
        mTestBuilding.addNode(1, new Location(0, 1));

        // First junction between path two and three - index 2
        mTestBuilding.addNode(1, new Location(1, 1));

        // Path one connection node - index 3
        mTestBuilding.addNode(1, new Location(0, 2));

        // Path two connection node - index 4
        mTestBuilding.addNode(1, new Location(1, 2));

        // Second junction between path two and three - index 5
        mTestBuilding.addNode(1, new Location(1, 3));

        // Path three connection nodes - indices 6-8
        mTestBuilding.addNode(1, new Location(2, 1));
        mTestBuilding.addNode(1, new Location(2, 2));
        mTestBuilding.addNode(1, new Location(2, 3));

        // Destination node - index 9
        mTestBuilding.addNode(1, new Location(0, 3));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);

        // Junction between all paths
        junctionNode1 = mTestBuilding.getFloor(1).getFloorNodes().get(1);

        // Junction between path two and three (first junction)
        junctionNode2 = mTestBuilding.getFloor(1).getFloorNodes().get(2);

        // Path one connection node
        connectionNode1 = mTestBuilding.getFloor(1).getFloorNodes().get(3);

        // Path two connection node
        connectionNode2 = mTestBuilding.getFloor(1).getFloorNodes().get(4);

        // Junction between path two and three (second junction)
        junctionNode3 = mTestBuilding.getFloor(1).getFloorNodes().get(5);

        // Path three connection nodes
        connectionNode3 =  mTestBuilding.getFloor(1).getFloorNodes().get(6);
        connectionNode4 =  mTestBuilding.getFloor(1).getFloorNodes().get(7);
        connectionNode5 =  mTestBuilding.getFloor(1).getFloorNodes().get(8);

        destinationNode =  mTestBuilding.getFloor(1).getFloorNodes().get(9);

        // Start to first junction
        startNode.addAdjacentNode(junctionNode1);

        // First junction edges
        junctionNode1.addAdjacentNode(junctionNode2);
        junctionNode1.addAdjacentNode(connectionNode1);

        // Path one edge to destination
        connectionNode1.addAdjacentNode(destinationNode);

        // Path two edges to third junction in path two
        junctionNode2.addAdjacentNode(connectionNode2);
        connectionNode2.addAdjacentNode(junctionNode3);

        // Path three edges to third junction in path
        junctionNode2.addAdjacentNode(connectionNode3);
        connectionNode3.addAdjacentNode(connectionNode4);
        connectionNode4.addAdjacentNode(connectionNode5);
        connectionNode5.addAdjacentNode(junctionNode3);

        // Path two and three junction to destination
        junctionNode3.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path (path 1) from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(junctionNode1);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get path between nodes on different floors
     */
    @Test
    public void testSinglePathTwoFloors() throws FloorDoesNotExistException, NoPathException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.addFloor(2, "Floor2_Final.png");

        mTestBuilding.addNode(1, new Location(0, 0));
        mTestBuilding.addNode(1, new Location(0, 1));
        mTestBuilding.addNode(2, new Location(0, 1));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);
        connectionNode1 = mTestBuilding.getFloor(1).getFloorNodes().get(1);
        destinationNode =  mTestBuilding.getFloor(2).getFloorNodes().get(0);

        // Edges
        startNode.addAdjacentNode(connectionNode1);
        connectionNode1.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get path between nodes in different buildings
     */
    @Test
    public void testSinglePathTwoBuildings() throws FloorDoesNotExistException, NoPathException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestSecondBuilding.addFloor(2, "Floor2_Final.png");

        mTestBuilding.addNode(1, new Location(0, 0));
        mTestBuilding.addNode(1, new Location(0, 1));
        mTestSecondBuilding.addNode(2, new Location(1, 1));

        startNode = mTestBuilding.getFloor(1).getFloorNodes().get(0);
        connectionNode1 = mTestBuilding.getFloor(1).getFloorNodes().get(1);
        destinationNode =  mTestSecondBuilding.getFloor(2).getFloorNodes().get(0);

        // Edges
        startNode.addAdjacentNode(connectionNode1);
        connectionNode1.addAdjacentNode(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from destination to start
        expectedPath.add(destinationNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(startNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

}
