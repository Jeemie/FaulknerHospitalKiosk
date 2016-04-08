package MapTest;

import Map.*;
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

    @Before
    public void setUp() throws Exception {

        Building mMainHospital = new Building();
        Floor mFloor3 = new Floor(3, mMainHospital);
        Location mLocation3B = new Location(10, 10);
        mNode3A = new LocationNode(0, mLocation3B, mFloor3);
        mNode3B = new LocationNode(0, new Location (10, 15), mFloor3);
        mNode3C = new LocationNode(0, new Location(10, 30), mFloor3);
        mNode3D = new LocationNode(0, new Location(10, 40), mFloor3);
    }

    /**
     * Add new department destination to specified location
     */
    @Test
    public void testAddDestinationDepartment() {
        mNode3A.addDestination(Destination.DEPARTMENT, "Optometry");
        ArrayList<String> destinations = mNode3A.getDestinations(Destination.DEPARTMENT);
        Assert.assertEquals(destinations.contains("Optometry"), true);
    }

    /**
     * Add new physician destination to specified location
     */
    @Test
    public void testAddDestinationPhysician() {
        mNode3B.addDestination(Destination.PHYSICIAN, "Dr. Lisa Grossi");
        ArrayList<String> destinations = mNode3B.getDestinations(Destination.PHYSICIAN);
        Assert.assertEquals(destinations.contains("Dr. Lisa Grossi"), true);
    }

    /**
     * Add new elevator destination to specified location
     */
    @Test
    public void testAddDestinationElevator() {
        mNode3C.addDestination(Destination.ELEVATOR, "Elevator");
        ArrayList<String> destinations = mNode3C.getDestinations(Destination.ELEVATOR);
        Assert.assertEquals(destinations.contains("Elevator"), true);
    }

    /**
     * Add new kiosk destination to specified location, then remove kiosk destination
     */
    @Test
    public void testAddRemoveDestinationKiosk() {
        mNode3D.addDestination(Destination.KIOSK, "Kiosk");
        ArrayList<String> destinations = mNode3D.getDestinations(Destination.KIOSK);
        Assert.assertEquals(destinations.contains("Kiosk"), true);
        mNode3D.removeDestination(Destination.KIOSK, "Kiosk");
        destinations = mNode3D.getDestinations(Destination.KIOSK);
        Assert.assertEquals(destinations.contains("Kiosk"), false);
    }

    /**
     * Add adjacent nodes
     */
    @Test
    public void testAddAdjacentNodes() {
        mNode3A.addAdjacentNode(mNode3B);
        mNode3A.addAdjacentNode(mNode3C);
        mNode3A.addAdjacentNode(mNode3D);
        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();
        ArrayList<LocationNode> expectedVals = new ArrayList<>();
        expectedVals.add(mNode3B);
        expectedVals.add(mNode3C);
        expectedVals.add(mNode3D);
        Assert.assertEquals(expectedVals, adjEyeCareNodes);
    }

    /**
     * Remove an adjacent node
     */
    @Test
    public void testRemoveAdjacentNode() {
        mNode3A.addAdjacentNode(mNode3B);
        mNode3A.addAdjacentNode(mNode3C);
        mNode3A.addAdjacentNode(mNode3D);
        ArrayList<LocationNode> adjEyeCareNodes = mNode3A.getAdjacentLocationNodes();
        ArrayList<LocationNode> expectedVals = new ArrayList<>();
        expectedVals.add(mNode3B);
        expectedVals.add(mNode3C);
        expectedVals.add(mNode3D);
        Assert.assertEquals(expectedVals, adjEyeCareNodes);
        mNode3A.removeAdjacentNode(mNode3C);
        expectedVals.remove(mNode3C);
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
}
