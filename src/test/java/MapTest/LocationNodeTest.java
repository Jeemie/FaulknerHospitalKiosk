//package MapTest;
//
//
//import Map.Map;
//import Map.LocationNode;
//import Map.Location;
//import Map.Destination;
//import Map.LocationNodeEdge;
//import Map.Enums.DestinationType;
//import Map.Enums.ImageType;
//import Map.Exceptions.NodeDoesNotExistException;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
///**
// * Created by maryannoconnell on 4/3/16.
// */
//public class LocationNodeTest {
//
//    /**
//     * Map for testing
//     */
//    Map mTestMap;
//
//    /**
//     * LocationNodes for testing
//     */
//    LocationNode mNode3A, mNode3B, mNode3C, mNode3D;
//
//    @Before
//    public void setUp() throws Exception {
//
//        mTestMap = new Map("test map");
//
//        mTestMap.addBuilding("test building");
//        mTestMap.addFloor("Floor 3", "floor1.png");
//
//
//        // Set current floor before adding nodes
//        mTestMap.setCurrentFloor(mTestMap.getMapBuildings().get(0).getFloors().get(0));
//
//        // 3A - index 0
//        mTestMap.addLocationNode("3A", new Location(100,100), ImageType.WAITINGROOM);
//        // 3B - index 1
//        mTestMap.addLocationNode("3B", new Location(100,150), ImageType.WAITINGROOM);
//        // 3C - index 2
//        mTestMap.addLocationNode("3C", new Location(100,300), ImageType.WAITINGROOM);
//        // 3D - index 3
//        mTestMap.addLocationNode("3D", new Location(150,100), ImageType.WAITINGROOM);
//
//        mNode3A = mTestMap.getMapBuildings().get(0).getFloors().get(0).getFloorNodes().get(0);
//        mNode3B = mTestMap.getMapBuildings().get(0).getFloors().get(0).getFloorNodes().get(1);
//        mNode3C = mTestMap.getMapBuildings().get(0).getFloors().get(0).getFloorNodes().get(2);
//        mNode3D = mTestMap.getMapBuildings().get(0).getFloors().get(0).getFloorNodes().get(3);
//    }
//
//    /**
//     * Add new department destination to specified location
//     */
//
//    @Test
//    public void testAddDestinationDepartment() {
//
//
//        mTestMap.addDestination("Optometry", DestinationType.DEPARTMENT);
//        ArrayList<Destination> destinations = mTestMap.getCurrentLocationNode().getDestinations(DestinationType.DEPARTMENT);
//
//        Assert.assertEquals(destinations.get(0).toString(), "Optometry");
//    }
//
//    /**
//     * Add new kiosk destination to specified location
//     */
//    @Test
//    public void testAddDestinationKiosk()  {
//
//        // Set current node before adding destination
//        mTestMap.setCurrentLocationNode(mNode3D);
//        mTestMap.addDestination("Kiosk", DestinationType.KIOSK);
//        ArrayList<Destination> destinations = mTestMap.getCurrentLocationNode().getDestinations(DestinationType.KIOSK);
//
//        Assert.assertEquals(destinations.get(0).toString(), "Kiosk");
//    }
//
//    /**
//     * Add new destination then remove that destination
//     */
//    @Test
//    public void testAddRemoveDestinationKiosk()  throws NodeDoesNotExistException  {
//
//        // Set current node before adding destination
//        mTestMap.setCurrentLocationNode(mNode3D);
//        mTestMap.addDestination("Kiosk", DestinationType.KIOSK);
//        ArrayList<Destination> destinations = mTestMap.getCurrentLocationNode().getDestinations(DestinationType.KIOSK);
//
//        Assert.assertEquals(destinations.get(0).toString(), "Kiosk");
//
//        mTestMap.getCurrentLocationNode().removeDestination(destinations.get(0));
//        destinations = mTestMap.getCurrentLocationNode().getDestinations(DestinationType.KIOSK);
//        Assert.assertTrue(destinations.isEmpty());
//    }
//
//    /**
//     * Add edges using method in Map class and get adjacent nodes
//     */
//    @Test
//    public void testGetAdjacentNodes() throws NodeDoesNotExistException {
//
//        // Set current location node
//        mTestMap.setCurrentLocationNode(mNode3A);
//
//        // Add 3 edges to current location node using method from Map class
//        mTestMap.addLocationNodeEdge(mNode3B);
//        mTestMap.addLocationNodeEdge(mNode3C);
//        mTestMap.addLocationNodeEdge(mNode3D);
//
//        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();
//        ArrayList<LocationNode> expectedVals = new ArrayList<>();
//
//        expectedVals.add(mNode3B);
//        expectedVals.add(mNode3C);
//        expectedVals.add(mNode3D);
//
//        Assert.assertEquals(expectedVals, adjEyeCareNodes);
//    }
//
//    /**
//     * Remove an edge
//     */
//    @Test
//    public void testRemoveEdge() throws NodeDoesNotExistException {
//
//        // Set current location node
//        mTestMap.setCurrentLocationNode(mNode3A);
//
//        // Add 3 edges to current location node using method from Map class
//        mTestMap.addLocationNodeEdge(mNode3B);
//        mTestMap.addLocationNodeEdge(mNode3C);
//        mTestMap.addLocationNodeEdge(mNode3D);
//
//        Assert.assertEquals(mNode3A.getEdges().size(), 3);
//
//        mNode3A.removeEdgeConnection(mNode3A.getEdges().get(0));
//
//        Assert.assertEquals(mNode3A.getEdges().size(), 2);
//    }
//
//    /**
//     * Remove a node connected to two with edges to two other nodes
//     * Note: removeLocationNode() is implemented in Map class
//     */
//    @Test
//    public void testRemoveNode() throws NodeDoesNotExistException {
//
//        // Set current location node to mNode3B
//        mTestMap.setCurrentLocationNode(mNode3B);
//
//        // Add edge
//        mTestMap.addLocationNodeEdge(mNode3C);
//
//        // Set current location node to mNode3A
//        mTestMap.setCurrentLocationNode(mNode3A);
//
//        // Add 3 edges to current location node using method from Map class
//        mTestMap.addLocationNodeEdge(mNode3B);
//        mTestMap.addLocationNodeEdge(mNode3C);
//        mTestMap.addLocationNodeEdge(mNode3D);
//
//        // Remove 3A
//        mTestMap.removeLocationNode();
//
//        // mNode3A (the current location node) is null
//        Assert.assertEquals(null, mTestMap.getCurrentLocationNode());
//
//        Assert.assertEquals(1, mNode3B.getEdges().size());
//
//    }
//
//    /**
//     * Get distance between two nodes
//     */
//    @Test
//    public void testGetDistanceBetween() {
//
//        double distance = mNode3A.getDistanceBetweenNodes(mNode3B);
//        Assert.assertEquals(distance, 50.0, 0.001);
//
//    }
//
//    /**
//     * Delete all edges connected to a node
//     */
//    @Test
//    public void testDeleteLocationNodeEdgeConnections() throws NodeDoesNotExistException {
//
//        LocationNode mNode5;
//        mTestMap.addLocationNode("node5", new Location(200, 200), ImageType.POINT);
//        mNode5 = mTestMap.getCurrentFloor().getLocationNodes().get(4);
//
//        // Set current location node
//        mTestMap.setCurrentLocationNode(mNode3A);
//
//        // Add 3 edges to current location node using method from Map class
//        mTestMap.addLocationNodeEdge(mNode3B);
//        mTestMap.addLocationNodeEdge(mNode3C);
//        mTestMap.addLocationNodeEdge(mNode3D);
//        mTestMap.addLocationNodeEdge(mNode5);
//
//        Assert.assertEquals(mNode3A.getEdges().size(), 4);
//
//        mNode3A.deleteLocationNodeEdgeConnections();
//
//        // Get the updated list edges of mNode3A (expected to be empty)
//        ArrayList<LocationNodeEdge> edges = mNode3A.getEdges();
//
//        Assert.assertTrue(edges.isEmpty());
//    }
//}
