package MapTest;

import Map.Building;
import Map.*;
import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NoPathException;
import Map.Exceptions.NodeDoesNotExistException;
import Map.Location;
import Map.LocationNode;
import Map.SearchAlgorithms.AStar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


/**
 * Created by maryannoconnell on 4/17/16.
 */
public class AStarTest {

    private AStar mAstar;
    private Map mTestMap;
    private Building mTestBuilding, mTestSecondBuilding;
    private Floor mFloor1, mFloor2, mFloorA;
    private LocationNode startNode;
    private LocationNode destinationNode;
    private LocationNode connectionNode1, connectionNode2, connectionNode3, connectionNode4, connectionNode5;
    private LocationNode junctionNode1, junctionNode2, junctionNode3;
    private ArrayList<LocationNode> actualPath = new ArrayList<>();
    private ArrayList<LocationNode> expectedPath = new ArrayList<>();

    @Before
    public void setUp() {

        // Create new AStar object - default constructor is empty
        mAstar = new AStar();

        // Create new map
        mTestMap = new Map("test map");

        // Add buildings to map
        mTestMap.addBuilding("test building1");
        mTestMap.addBuilding("test building2");

        // Get buildings from map and assign values to building test variables
        ArrayList<Building> mBuildings = mTestMap.getMapBuildings();
        mTestBuilding = mBuildings.get(0);
        mTestSecondBuilding = mBuildings.get(1);

        // Add floor method in Map class adds floor to the current building
        // Set first test building as current building then add floors
        mTestMap.setCurrentBuilding(mTestBuilding);
        mTestMap.addFloor("floor1", "floor1.png");
        mTestMap.addFloor("floor2", "floor2.png");

        // Get building floors and assign values to floor test variables
        ArrayList<Floor> mFloors = mTestMap.getCurrentBuilding().getFloors();
        mFloor1 = mFloors.get(0);
        mFloor2 = mFloors.get(1);

        // Set second test building as current building then add a floor
        // Using arbitrary floor image for testing purposes
        mTestMap.setCurrentBuilding(mTestSecondBuilding);
        mTestMap.addFloor("floorA", "floor4.png");

        // Get second building floor and assign value to floor test variable
        mFloorA = mTestMap.getCurrentBuilding().getFloors().get(0);

    }

    /**
     * Get path between two nodes connected by exactly one edge (the nodes are adjacent to one another)
     */
    @Test
    public void testSingleEdgePath() throws FloorDoesNotExistException, NoPathException, NodeDoesNotExistException {

        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mTestMap.addLocationNode("node2", new Location(0, 1), ImageType.POINT);

        // Get list of location nodes from floor 1 of the current building and assign to test variables
        startNode = mTestMap.getCurrentFloor().getLocationNodes().get(0);
        destinationNode = mTestMap.getCurrentFloor().getLocationNodes().get(1);

        startNode.addEdge(destinationNode);

        actualPath = mAstar.getPath(startNode, destinationNode);

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


        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mTestMap.addLocationNode("node2", new Location(0, 1), ImageType.POINT);
        mTestMap.addLocationNode("node3", new Location(0, 1), ImageType.POINT);

        // Get list of location nodes from floor 1 of the current building and assign to test variables
        startNode = mTestMap.getCurrentFloor().getLocationNodes().get(0);
        connectionNode1 = mTestMap.getCurrentFloor().getLocationNodes().get(1);
        destinationNode = mTestMap.getCurrentFloor().getLocationNodes().get(2);

        // Edges
        startNode.addEdge(connectionNode1);
        connectionNode1.addEdge(destinationNode);

        actualPath = mAstar.getPath(startNode, destinationNode);

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

        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor
        // Start node - index 0
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);

        // Junction between two paths - index 1
        mTestMap.addLocationNode("node1", new Location(0, 1), ImageType.POINT);

        // Path one connection node - index 2
        mTestMap.addLocationNode("node2", new Location(0, 2), ImageType.POINT);

        // Path two connection nodes - indices 3-5
        mTestMap.addLocationNode("node4", new Location(1, 1), ImageType.POINT);
        mTestMap.addLocationNode("node5", new Location(1, 2), ImageType.POINT);
        mTestMap.addLocationNode("node6", new Location(1, 3), ImageType.POINT);

        // Destination node - index 6
        mTestMap.addLocationNode("node1", new Location(0, 3), ImageType.POINT);

        // Get the current floor's location, then assign values to test location node variables
        ArrayList<LocationNode> mNodes = mTestMap.getCurrentFloor().getLocationNodes();

        startNode = mNodes.get(0);

        // Junction
        connectionNode1 = mNodes.get(1);

        // Path one connection node
        connectionNode2 = mNodes.get(2);

        // Path two connection nodes
        connectionNode3 = mNodes.get(3);
        connectionNode4 = mNodes.get(4);
        connectionNode5 = mNodes.get(5);

        destinationNode = mNodes.get(6);

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

        actualPath = mAstar.getPath(startNode, destinationNode);

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

        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor
        // Start node - index 0
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);

        // Junction between two paths - index 1
        mTestMap.addLocationNode("node1", new Location(0, 1), ImageType.POINT);

        // Path one connection node - index 2
        mTestMap.addLocationNode("node2", new Location(0, 2), ImageType.POINT);

        // Path two connection nodes - indices 3-5
        mTestMap.addLocationNode("node4", new Location(1, 1), ImageType.POINT);
        mTestMap.addLocationNode("node5", new Location(1, 2), ImageType.POINT);
        mTestMap.addLocationNode("node6", new Location(1, 3), ImageType.POINT);

        // Destination node - index 6
        mTestMap.addLocationNode("node1", new Location(0, 3), ImageType.POINT);

        // Get the current floor's location, then assign values to test location node variables
        ArrayList<LocationNode> mNodes = mTestMap.getCurrentFloor().getLocationNodes();

        startNode = mNodes.get(0);

        // Junction
        connectionNode1 = mNodes.get(1);

        // Path one connection node
        connectionNode2 = mNodes.get(2);

        // Path two connection nodes
        connectionNode3 = mNodes.get(3);
        connectionNode4 = mNodes.get(4);
        connectionNode5 = mNodes.get(5);

        destinationNode = mNodes.get(6);

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

        actualPath = mAstar.getPath(startNode, destinationNode);

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

        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor
        // Start node - index 0
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);

        // Junction between all paths - index 1
        mTestMap.addLocationNode("node2", new Location(0, 1), ImageType.POINT);

        // First junction between path two and three - index 2
        mTestMap.addLocationNode("node3", new Location(1, 1), ImageType.POINT);

        // Path one connection node - index 3
        mTestMap.addLocationNode("node4", new Location(0, 2), ImageType.POINT);

        // Path two connection node - index 4
        mTestMap.addLocationNode("node5", new Location(1, 2), ImageType.POINT);

        // Second junction between path two and three - index 5
        mTestMap.addLocationNode("node6", new Location(1, 3), ImageType.POINT);

        // Path three connection nodes - indices 6-8
        mTestMap.addLocationNode("node6", new Location(2, 1), ImageType.POINT);
        mTestMap.addLocationNode("node7", new Location(2, 2), ImageType.POINT);
        mTestMap.addLocationNode("node8", new Location(2, 3), ImageType.POINT);

        // Destination node - index 9
        mTestMap.addLocationNode("node10", new Location(0, 3), ImageType.POINT);

        // Get the current floor's location, then assign values to test location node variables
        ArrayList<LocationNode> mNodes = mTestMap.getCurrentFloor().getLocationNodes();

        startNode = mNodes.get(0);

        // Junction between all paths
        junctionNode1 = mNodes.get(1);

        // Junction between path two and three (first junction)
        junctionNode2 = mNodes.get(2);

        // Path one connection node
        connectionNode1 = mNodes.get(3);

        // Path two connection node
        connectionNode2 = mNodes.get(4);

        // Junction between path two and three (second junction)
        junctionNode3 = mNodes.get(5);

        // Path three connection nodes
        connectionNode3 =  mNodes.get(6);
        connectionNode4 =  mNodes.get(7);
        connectionNode5 =  mNodes.get(8);

        destinationNode =  mNodes.get(9);

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

        actualPath = mAstar.getPath(startNode, destinationNode);

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

        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor (floor 1)
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mTestMap.addLocationNode("node2", new Location(0, 1), ImageType.POINT);

        startNode = mTestMap.getCurrentFloor().getFloorNodes().get(0);
        connectionNode1 =  mTestMap.getCurrentFloor().getFloorNodes().get(1);

        // Set current floor to floor 2
        mTestMap.setCurrentFloor(mFloor2);

        // Add location nodes to current floor (floor 2)
        mTestMap.addLocationNode("node3", new Location(0, 1), ImageType.POINT);

        destinationNode =  mTestMap.getCurrentFloor().getLocationNodes().get(0);

        // Edges
        startNode.addEdge(connectionNode1);
        connectionNode1.addEdge(destinationNode);

        actualPath = mAstar.getPath(startNode, destinationNode);

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

        // Set first test building as current building
        mTestMap.setCurrentBuilding(mTestBuilding);

        // Set current floor to floor 1
        mTestMap.setCurrentFloor(mFloor1);

        // Add location nodes to current floor (floor 1)
        mTestMap.addLocationNode("node1", new Location(0, 0), ImageType.POINT);
        mTestMap.addLocationNode("node2", new Location(0, 1), ImageType.POINT);

        startNode = mTestMap.getCurrentFloor().getFloorNodes().get(0);
        connectionNode1 =  mTestMap.getCurrentFloor().getFloorNodes().get(1);

        // Set second test building as current building
        mTestMap.setCurrentBuilding(mTestSecondBuilding);

        // Set current floor of second building to floor A
        mTestMap.setCurrentFloor(mFloorA);

        // Add location node to current floor (floor A) of second building
        mTestMap.addLocationNode("building2 node1", new Location(10, 10), ImageType.POINT);

        destinationNode =  mTestMap.getCurrentFloor().getLocationNodes().get(0);

        // Edges
        startNode.addEdge(connectionNode1);
        connectionNode1.addEdge(destinationNode);

        actualPath = mAstar.getPath(startNode, destinationNode);

        // Expect path from start to destination
        expectedPath.add(startNode);
        expectedPath.add(connectionNode1);
        expectedPath.add(destinationNode);

        Assert.assertEquals(expectedPath, actualPath);
    }

}
