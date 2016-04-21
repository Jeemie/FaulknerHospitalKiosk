package MapTest;

import Map.Building;
import Map.*;
import Map.Destination;
import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NoPathException;
import Map.Exceptions.NodeDoesNotExistException;
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
public class AStarTest {

    private Building mTestBuilding, mTestSecondBuilding;
    private LocationNode startNode;
    private LocationNode destinationNode;
    private LocationNode connectionNode1, connectionNode2, connectionNode3, connectionNode4, connectionNode5;
    private LocationNode junctionNode1, junctionNode2, junctionNode3;
    private ArrayList<LocationNode> actualPath = new ArrayList<>();
    private ArrayList<LocationNode> expectedPath = new ArrayList<>();

    @Before
    public void setUp() {

        mTestBuilding = new Building("test building1", new Map("test map1"));
        mTestSecondBuilding = new Building("test building2", new Map("test map2"));
    }

    /**
     * Get path between two nodes connected by exactly one edge (the nodes are adjacent to one another)
     */
    @Test
    public void testSingleEdgePath() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor = mTestBuilding.addFloor("floor1", ImageType.FLOOR);
        mFloor.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mFloor.addLocationNode("node2", new Location(0, 1), ImageType.POINT);

        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);
        destinationNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);

        startNode.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from start to destination
        expectedPath.add(startNode);
        expectedPath.add(destinationNode);


        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get path between nodes connected through two edges
     */
    @Test
    public void testSinglePath() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor = mTestBuilding.addFloor("floor1", ImageType.FLOOR);

        mFloor.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mFloor.addLocationNode("node2", new Location(0, 1), ImageType.POINT);
        mFloor.addLocationNode("node3", new Location(0, 2), ImageType.POINT);

        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);
        connectionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);
        destinationNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(2);

        // Edges
        startNode.addEdge(connectionNode1);
        connectionNode1.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from start to destination
        expectedPath.add(startNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(destinationNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get shortest path between two nodes connected through two paths
     */
    @Test
    public void testTwoPaths() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor = mTestBuilding.addFloor("floor1", ImageType.FLOOR);
        // Start node - index 0
        mFloor.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        // Junction between two paths - index 1
        mFloor.addLocationNode("node2", new Location(0, 1), ImageType.POINT);
        // Path one connection node - index 2
        mFloor.addLocationNode("node3", new Location(0, 2), ImageType.POINT);
        // Path two connection nodes - indices 3-5
        mFloor.addLocationNode("node4", new Location(1, 1), ImageType.POINT);
        mFloor.addLocationNode("node5", new Location(1, 2), ImageType.POINT);
        mFloor.addLocationNode("node6", new Location(1, 3), ImageType.POINT);
        // Destination node - index 6
        mFloor.addLocationNode("node7", new Location(0, 3), ImageType.POINT);

        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);

        // Junction
        connectionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);

        // Path one connection node
        connectionNode2 = mTestBuilding.getFloors().get(0).getLocationNodes().get(2);

        // Path two connection nodes
        connectionNode3 = mTestBuilding.getFloors().get(0).getLocationNodes().get(3);
        connectionNode4 = mTestBuilding.getFloors().get(0).getLocationNodes().get(4);
        connectionNode5 = mTestBuilding.getFloors().get(0).getLocationNodes().get(5);

        destinationNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(6);

        // Edges
        startNode.addEdge(connectionNode1);

        // Junction edges
        connectionNode1.addEdge(connectionNode2);
        connectionNode1.addEdge(connectionNode3);

        // Path one edge to destination
        connectionNode2.addEdge(destinationNode);

        // Path two edges to destination
        connectionNode3.addEdge(connectionNode4);
        connectionNode4.addEdge(connectionNode5);
        connectionNode5.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path (path 1) from start to destination

        expectedPath.add(startNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(connectionNode2);
        expectedPath.add(destinationNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get shortest path between two nodes connected through two paths
     * Test that result is not impacted by order in which adjacent nodes are added
     */
    @Test
    public void testOrder() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor = mTestBuilding.addFloor("floor1", ImageType.FLOOR);

        // Start node - index 0
        mFloor.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        // Junction between two paths - index 1
        mFloor.addLocationNode("node2", new Location(0, 1), ImageType.POINT);
        // Path one connection node - index 2
        mFloor.addLocationNode("node3", new Location(0, 2), ImageType.POINT);
        // Path two connection nodes - indices 3-5
        mFloor.addLocationNode("node4", new Location(1, 1), ImageType.POINT);
        mFloor.addLocationNode("node5", new Location(1, 2), ImageType.POINT);
        mFloor.addLocationNode("node6", new Location(1, 3), ImageType.POINT);
        // Destination node - index 6
        mFloor.addLocationNode("node7", new Location(0, 3), ImageType.POINT);

        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);

        // Junction
        connectionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);

        // Path one connection node
        connectionNode2 = mTestBuilding.getFloors().get(0).getLocationNodes().get(2);

        // Path two connection nodes
        connectionNode3 = mTestBuilding.getFloors().get(0).getLocationNodes().get(3);
        connectionNode4 = mTestBuilding.getFloors().get(0).getLocationNodes().get(4);
        connectionNode5 = mTestBuilding.getFloors().get(0).getLocationNodes().get(5);

        destinationNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(6);

        // Edges
        startNode.addEdge(connectionNode1);

        // Swap order that edges are added to show list order does not impact pathfinding
        // Junction edges
        connectionNode1.addEdge(connectionNode3);
        connectionNode1.addEdge(connectionNode2);

        // Path two edges to destination
        connectionNode3.addEdge(connectionNode4);
        connectionNode4.addEdge(connectionNode5);
        connectionNode5.addEdge(destinationNode);

        // Path one edge to destination
        connectionNode2.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path (path 1) from start to destination

        expectedPath.add(startNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(connectionNode2);
        expectedPath.add(destinationNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get shortest path between two nodes connected through three paths
     */
    @Test
    public void testThreePaths() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor = mTestBuilding.addFloor("floor1", ImageType.FLOOR);

        // Start node - index 0
        mFloor.addLocationNode("node1", new Location(0, 0), ImageType.POINT);

        // Junction between all paths - index 1
        mFloor.addLocationNode("node2", new Location(0, 1), ImageType.POINT);

        // First junction between path two and three - index 2
        mFloor.addLocationNode("node3", new Location(1, 1), ImageType.POINT);

        // Path one connection node - index 3
        mFloor.addLocationNode("node4", new Location(0, 2), ImageType.POINT);

        // Path two connection node - index 4
        mFloor.addLocationNode("node5", new Location(1, 2), ImageType.POINT);

        // Second junction between path two and three - index 5
        mFloor.addLocationNode("node6", new Location(1, 3), ImageType.POINT);

        // Path three connection nodes - indices 6-8
        mFloor.addLocationNode("node7", new Location(2, 1), ImageType.POINT);
        mFloor.addLocationNode("node8", new Location(2, 2), ImageType.POINT);
        mFloor.addLocationNode("node9", new Location(2, 3), ImageType.POINT);

        // Destination node - index 9
        mFloor.addLocationNode("node10", new Location(0, 3), ImageType.POINT);



        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);

        // Junction between all paths
        junctionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);

        // Junction between path two and three (first junction)
        junctionNode2 = mTestBuilding.getFloors().get(0).getLocationNodes().get(2);

        // Path one connection node
        connectionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(3);

        // Path two connection node
        connectionNode2 = mTestBuilding.getFloors().get(0).getLocationNodes().get(4);

        // Junction between path two and three (second junction)
        junctionNode3 = mTestBuilding.getFloors().get(0).getLocationNodes().get(5);

        // Path three connection nodes
        connectionNode3 =  mTestBuilding.getFloors().get(0).getLocationNodes().get(6);
        connectionNode4 =  mTestBuilding.getFloors().get(0).getLocationNodes().get(7);
        connectionNode5 =  mTestBuilding.getFloors().get(0).getLocationNodes().get(8);

        destinationNode =  mTestBuilding.getFloors().get(0).getLocationNodes().get(9);

        // Start to first junction
        startNode.addEdge(junctionNode1);

        // First junction edges
        junctionNode1.addEdge(junctionNode2);
        junctionNode1.addEdge(connectionNode1);

        // Path one edge to destination
        connectionNode1.addEdge(destinationNode);

        // Path two edges to third junction in path two
        junctionNode2.addEdge(connectionNode2);
        connectionNode2.addEdge(junctionNode3);

        // Path three edges to third junction in path
        junctionNode2.addEdge(connectionNode3);
        connectionNode3.addEdge(connectionNode4);
        connectionNode4.addEdge(connectionNode5);
        connectionNode5.addEdge(junctionNode3);

        // Path two and three junction to destination
        junctionNode3.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path (path 1) from start to destination
        expectedPath.add(startNode);
        expectedPath.add(junctionNode1);
        expectedPath.add(connectionNode1);
        expectedPath.add(destinationNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get path between nodes on different floors
     */
    @Test
    public void testSinglePathTwoFloors() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor1 = mTestBuilding.addFloor("floor1", ImageType.FLOOR);
        Floor mFloor2 = mTestBuilding.addFloor("floor2", ImageType.FLOOR);

        mFloor1.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mFloor1.addLocationNode("node2", new Location(0, 1), ImageType.POINT);
        mFloor2.addLocationNode("node3", new Location(0, 1), ImageType.POINT);

        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);
        connectionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);
        destinationNode =  mTestBuilding.getFloors().get(1).getLocationNodes().get(0);

        // Edges
        startNode.addEdge(connectionNode1);
        connectionNode1.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from start to destination
        expectedPath.add(startNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(destinationNode);


        Assert.assertEquals(expectedPath, actualPath);
    }

    /**
     * Get path between nodes in different buildings
     */
    @Test
    public void testSinglePathTwoBuildings() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        Floor mFloor1 = mTestBuilding.addFloor("floor1", ImageType.FLOOR);
        Floor mFloor2 = mTestSecondBuilding.addFloor("floor2", ImageType.FLOOR);

        mFloor1.addLocationNode("building1 node1", new Location(0, 0), ImageType.POINT);
        mFloor1.addLocationNode("building1 node2", new Location(0, 1), ImageType.POINT);
        mFloor2.addLocationNode("building2 node1", new Location(10, 10), ImageType.POINT);



        startNode = mTestBuilding.getFloors().get(0).getLocationNodes().get(0);
        connectionNode1 = mTestBuilding.getFloors().get(0).getLocationNodes().get(1);
        destinationNode =  mTestSecondBuilding.getFloors().get(0).getLocationNodes().get(0);

        // Edges
        startNode.addEdge(connectionNode1);
        connectionNode1.addEdge(destinationNode);

        actualPath = aStar(startNode, destinationNode);

        // Expect path from start to destination
        expectedPath.add(startNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(destinationNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

}
