package MapTest;

import Map.*;
import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Map.Floor class.
 */
public class FloorTest {

    private Building mTestBuilding;
    private Floor mTestFloor1, mTestFloor2;

    @Before
    public void setUp() {

        mTestBuilding = new Building();
        mTestFloor1 = new Floor("Floor 1", ImageType.FLOOR, mTestBuilding);

    }

    /**
     * Add node to floor
     */
   @Test
    public void testAddLocationNode() throws FloorDoesNotExistException {

        mTestFloor1.addLocationNode("test node", new Location(100, 100), ImageType.POINT);

        Assert.assertTrue(mTestFloor1.getLocationNodes().size() == 1);

    }

    /**
     * Get Kiosk destination from floor
     */

    @Test
   public void testGetKioskFloorDestination() throws FloorDoesNotExistException {

        mTestFloor1.addLocationNode("test node1", new Location(100, 100), ImageType.POINT);
        mTestFloor1.addLocationNode("test node2", new Location(200, 100), ImageType.POINT);
        mTestFloor1.addLocationNode("test node3", new Location(300, 100), ImageType.POINT);

        mTestFloor1.getLocationNodes().get(0).addDestination(DestinationType.SERVICE, "Test Service1");
        mTestFloor1.getLocationNodes().get(1).addDestination(DestinationType.DEPARTMENT, "Test Department1");
        mTestFloor1.getLocationNodes().get(1).addDestination(DestinationType.DEPARTMENT, "Test Department2");
        mTestFloor1.getLocationNodes().get(2).addDestination(DestinationType.KIOSK, "Test Kiosk");

        Assert.assertEquals(mTestFloor1.getFloorDestinations(DestinationType.KIOSK).get(0).toString(), "Test Kiosk");
    }

    /**
     * Get all destinations from floor
     */
    @Test
    public void testGetAllFloorDestinations() throws FloorDoesNotExistException {

        mTestFloor1.addLocationNode("test node1", new Location(100, 100), ImageType.POINT);
        mTestFloor1.addLocationNode("test node2", new Location(200, 100), ImageType.POINT);

        mTestFloor1.getLocationNodes().get(0).addDestination(DestinationType.SERVICE, "Test Service1");
        mTestFloor1.getLocationNodes().get(1).addDestination(DestinationType.DEPARTMENT, "Test Department1");
        mTestFloor1.getLocationNodes().get(1).addDestination(DestinationType.DEPARTMENT, "Test Department2");

        Assert.assertEquals(mTestFloor1.getFloorDestinations().get(0).toString(), "Test Service1");
        Assert.assertEquals(mTestFloor1.getFloorDestinations().get(1).toString(), "Test Department1");
        Assert.assertEquals(mTestFloor1.getFloorDestinations().get(2).toString(), "Test Department2");
    }

    /**
     * Remove node from floor
     */
    @Test
    public void testRemoveLocationNode() {

        mTestFloor1.addLocationNode("test node1", new Location(100, 100), ImageType.POINT);

        mTestFloor1.getLocationNodes().get(0).addDestination(DestinationType.SERVICE, "Test Service1");

        mTestFloor1.removeLocationNode(mTestFloor1.getLocationNodes().get(0));

        Assert.assertTrue(mTestFloor1.getLocationNodes().isEmpty());
    }

}
