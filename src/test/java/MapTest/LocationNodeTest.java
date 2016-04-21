package MapTest;

import Map.*;
import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by maryannoconnell on 4/3/16.
 */
public class LocationNodeTest {

    /**
     * LocationNodes for testing
     */
    private LocationNode mNode3A, mNode3B, mNode3C, mNode3D;


    @Before
    public void setUp() throws Exception {

        Building mMainHospital = new Building();

        Floor mFloor3 = new Floor("Floor 3", ImageType.FLOOR, mMainHospital);

        Location mLocation3B = new Location(10, 10);

        mNode3A = new LocationNode("Hallway", mLocation3B, mFloor3, ImageType.POINT);
        mNode3B = new LocationNode("Hallway", new Location (10, 15), mFloor3, ImageType.POINT);
        mNode3C = new LocationNode("Hallway", new Location(10, 30), mFloor3, ImageType.POINT);
        mNode3D = new LocationNode("Hallway", new Location(10, 40), mFloor3, ImageType.POINT);

    }

    /**
     * Add new department destination to specified location
     * Also tests getDestinations
     */
    @Test
    public void testAddDestinationDepartment() {
        Destination destination = new Destination ("Optometry", DestinationType.DEPARTMENT, mNode3A);
        ArrayList<Destination> destinations = mNode3A.getDestinations(DestinationType.DEPARTMENT);
        Assert.assertEquals(destinations.contains("Optometry"), true);
    }

    /**
     * Add new physician destination to specified location
     * Also tests getDestinations
     */
    @Test
    public void testAddDestinationPhysician() {
        Destination destination = new Destination ("Dr. Lisa Grossi", DestinationType.PHYSICIAN, mNode3B);
        ArrayList<Destination> destinations = mNode3B.getDestinations(DestinationType.PHYSICIAN);
        Assert.assertEquals(destinations.contains("Dr. Lisa Grossi"), true);
    }

    /**
     * Add new elevator destination to specified location
     * Also tests getDestinations
     */
    @Test
    public void testAddDestinationElevator() {
        Destination destination = new Destination ("Elevator", DestinationType.ELEVATOR, mNode3C);
        ArrayList<Destination> destinations = mNode3C.getDestinations(DestinationType.ELEVATOR);
        Assert.assertEquals(destinations.contains("Elevator"), true);
    }

    /**
     * Add new kiosk destination to specified location, then remove kiosk destination
     */
    @Test
    public void testAddRemoveDestinationKiosk() {
        Destination destination = new Destination ("Kiosk", DestinationType.KIOSK, mNode3D);
        ArrayList<Destination> destinations = mNode3D.getDestinations(DestinationType.KIOSK);
        Assert.assertEquals(destinations.contains("Kiosk"), true);
        mNode3D.removeDestination(destination);
        destinations = mNode3D.getDestinations(DestinationType.KIOSK);
        Assert.assertEquals(destinations.contains("Kiosk"), false);
    }

    /**
     * Add adjacent nodes
     */
    //Does not exist yet
//    @Test
//    public void testAddAdjacentNodes() {
//        mNode3A.addAdjacentNode(mNode3B);
//        mNode3A.addAdjacentNode(mNode3C);
//        mNode3A.addAdjacentNode(mNode3D);
//        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();
//        ArrayList<LocationNode> expectedVals = new ArrayList<>();
//        expectedVals.add(mNode3B);
//        expectedVals.add(mNode3C);
//        expectedVals.add(mNode3D);
//        Assert.assertEquals(expectedVals, adjEyeCareNodes);
//    }

    /**
     * Remove an adjacent node
     */
//    @Test
//    public void testRemoveAdjacentNode() {
//        mNode3A.addAdjacentNode(mNode3B);
//        mNode3A.addAdjacentNode(mNode3C);
//        mNode3A.addAdjacentNode(mNode3D);
//        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();
//        ArrayList<LocationNode> expectedVals = new ArrayList<>();
//        expectedVals.add(mNode3B);
//        expectedVals.add(mNode3C);
//        expectedVals.add(mNode3D);
//        Assert.assertEquals(expectedVals, adjEyeCareNodes);
//        mNode3A.removeAdjacentNode(mNode3C);
//        expectedVals.remove(mNode3C);
//        Assert.assertEquals(expectedVals, adjEyeCareNodes);
//    }

    /**
     * Get distance between two nodes
     */
    @Test
    public void testGetDistanceBetween() {
        double distance = mNode3A.getDistanceBetweenNodes(mNode3B);
        Assert.assertEquals(5.0, distance, 0.001);
    }

    /**
     * Tests to make sure it fails
     */
    @Test
    public void testDrawAdminNULL() {
        Exception ex = null;
        try {
            mNode3A.drawAdmin(null);
        } catch (NullPointerException e) {
           ex = e;
        }
        Assert.assertEquals(new NullPointerException(), ex);
    }

    /**
     * DeleteNode
     */
    @Test
    public void testDeleteNode() {

        mNode3A.deleteNode();
        Assert.assertEquals(null, mNode3A);

    }

    /**
     * Test to make sure that add edge actually adds an edge
     */
    @Test
    public void testAddEdge() {
        boolean containsEdgeToB = false;
        boolean containsEdgeToA = false;
        mNode3A.addEdge(mNode3B);

        for (LocationNodeEdge e: mNode3A.getEdges()) {
            if(e.getLocationNode1().equals(mNode3B) || e.getLocationNode2().equals(mNode3B)) {
                containsEdgeToB = true;
            }
        }

        for (LocationNodeEdge e: mNode3B.getEdges()) {
            if(e.getLocationNode1().equals(mNode3A) || e.getLocationNode2().equals(mNode3A)) {
                containsEdgeToA = true;
            }
        }

        Assert.assertTrue(containsEdgeToA && containsEdgeToB);

    }

    /**
     * Test to get the location of the node, and check x and y
     */
    @Test
    public void testGetLocation() {
        Assert.assertTrue(10 == mNode3A.getLocation().getX());
        Assert.assertTrue(10 == mNode3A.getLocation().getY());
    }

    /**
     * Test to see get edges of a node
     */
    @Test
    public void testGetEdges() {

    }

    /**
     * Test the toString function
     */
    @Test
    public void testToString() {

    }

    /**
     * Test the compareTo function
     */
    @Test
    public void testCompareTo() {

    }

    /**
     * Test if the location actually updates
     */
    @Test
    public void testUpdate() {

    }
}
