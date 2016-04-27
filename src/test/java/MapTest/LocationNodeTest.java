package MapTest;

import Map.Building;
import Map.Floor;
import Map.LocationNode;
import Map.LocationNodeEdge;
import Map.Location;
import Map.Destination;
import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Exceptions.NodeDoesNotExistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by maryannoconnell on 4/3/16.
 */
public class LocationNodeTest {

    /**
     * LocationNodes for testing
     */
    private LocationNode mNode3A, mNode3B, mNode3C, mNode3D;

//    @Before
//    public void setUp() throws Exception {
//
//        Building mMainHospital = new Building();
//        Floor mFloor3 = new Floor("Floor 3", "Floor1_Final.png", mMainHospital);
//        Location mLocation3B = new Location(10, 10);
//
//        mNode3A = new LocationNode("3A", mLocation3B, mFloor3, ImageType.POINT);
//        mNode3B = new LocationNode("3B", new Location (10, 15), mFloor3, ImageType.POINT);
//        mNode3C = new LocationNode("3C", new Location(10, 30), mFloor3, ImageType.POINT);
//        mNode3D = new LocationNode("3D", new Location(10, 40), mFloor3, ImageType.POINT);
//    }

    /**
     * Add new department destination to specified location
     */
    @Test
    public void testAddDestinationDepartment() {

        mNode3A.addDestination("Optometry", DestinationType.DEPARTMENT);
        ArrayList<Destination> destinations = mNode3A.getDestinations(DestinationType.DEPARTMENT);

        Assert.assertEquals(destinations.get(0).toString(), "Optometry");
    }

    /**
     * Add new physician destination to specified location
     */
  @Test
    public void testAddDestinationPhysician() {

        mNode3B.addDestination("Dr. Lisa Grossi", DestinationType.PHYSICIAN);
        ArrayList<Destination> destinations = mNode3B.getDestinations(DestinationType.PHYSICIAN);

      Assert.assertEquals(destinations.get(0).toString(), "Dr. Lisa Grossi");
    }

    /**
     * Add new elevator destination to specified location
     */
    @Test
    public void testAddDestinationElevator() {

        mNode3C.addDestination("Elevator", DestinationType.ELEVATOR);
        ArrayList<Destination> destinations = mNode3C.getDestinations(DestinationType.ELEVATOR);

        Assert.assertEquals(destinations.get(0).toString(), "Elevator");
    }

    /**
     * Add new kiosk destination to specified location, then remove kiosk destination
     */
    @Test
    public void testAddRemoveDestinationKiosk()  throws NodeDoesNotExistException  {

        mNode3D.addDestination("Kiosk", DestinationType.KIOSK);
        ArrayList<Destination> destinations = mNode3D.getDestinations(DestinationType.KIOSK);
        Assert.assertEquals(destinations.get(0).toString(), "Kiosk");
        mNode3D.removeDestination(destinations.get(0));
        destinations = mNode3D.getDestinations(DestinationType.KIOSK);
        Assert.assertTrue(destinations.isEmpty());
    }

    /**
     * Add adjacent nodes
     */
    @Test
    public void testAddAdjacentNodes() throws NodeDoesNotExistException {
        mNode3A.addEdge(mNode3B);
        mNode3A.addEdge(mNode3C);
        mNode3A.addEdge(mNode3D);
        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();
        ArrayList<LocationNode> expectedVals = new ArrayList<>();
        expectedVals.add(mNode3B);
        expectedVals.add(mNode3C);
        expectedVals.add(mNode3D);
        Assert.assertEquals(expectedVals, adjEyeCareNodes);
    }

    /**
     * Remove an edge connecting an adjacent node
     */
    @Test
    public void testRemoveEdgeConnection() throws NodeDoesNotExistException {

        // Add edge connections to node
        mNode3A.addEdge(mNode3B);
        mNode3A.addEdge(mNode3C);
        mNode3A.addEdge(mNode3D);

        // Store adjacent nodes in list
        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();

        // Create list for expected values
        ArrayList<LocationNode> expectedVals = new ArrayList<>();

        // Add nodes expected to be in list
        expectedVals.add(mNode3B);
        expectedVals.add(mNode3C);
        expectedVals.add(mNode3D);

        // Verify that actual values and expected values are equal
        Assert.assertEquals(expectedVals, adjEyeCareNodes);

        // Get the edges of mNode3A
        ArrayList<LocationNodeEdge> edges = mNode3A.getEdges();

        // Get the edge from the list of edges that connects mNode3A and mNode3B
        LocationNodeEdge mEdge1 = LocationNodeEdge.getEdgeBetween(edges, mNode3A, mNode3B);

        // Remove the edge connecting mNode3A and mNode3B
        mNode3A.removeEdgeConnection(mEdge1);

        // Get updated list of adjacent nodes
        adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();

        // mNode3B should no longer be in list
        expectedVals.remove(mNode3B);

        Assert.assertEquals(expectedVals, adjEyeCareNodes);
    }

    /**
     * Get distance between two nodes
     */
    @Test
    public void testGetDistanceBetween() {

        double distance = mNode3A.getDistanceBetweenNodes(mNode3B);
        Assert.assertEquals(distance, 5.0, 0.001);

    }

    /**
     * Delete all edges connected to a node
     */
    @Test
    public void testDeleteLocationNodeEdgeConnections() throws NodeDoesNotExistException {

        // Add edge connections to node
        mNode3A.addEdge(mNode3B);
        mNode3A.addEdge(mNode3C);
        mNode3A.addEdge(mNode3D);

        // Store adjacent nodes in list
        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();

        // Create list for expected values
        ArrayList<LocationNode> expectedVals = new ArrayList<>();

        // Add nodes expected to be in list
        expectedVals.add(mNode3B);
        expectedVals.add(mNode3C);
        expectedVals.add(mNode3D);

        // Verify that actual values and expected values are equal
        Assert.assertEquals(expectedVals, adjEyeCareNodes);

        mNode3A.deleteLocationNodeEdgeConnections();

        // Get the updated list edges of mNode3A (expected to be empty)
        ArrayList<LocationNodeEdge> edges = mNode3A.getEdges();

        Assert.assertTrue(edges.isEmpty());
    }

}
