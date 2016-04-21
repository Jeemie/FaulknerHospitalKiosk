package MapTest;

import Map.*;
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
   /* @Test
    public void testAddNode() throws FloorDoesNotExistException {

        mTestFloor1.addNode(new Location(100, 100));

        Assert.assertTrue(mTestFloor1.getLocationNodes().size() == 1);

    }
*/
    /**
     * Get Kiosk destination from floor
     */

    //@Test
  /*  public void testGetKioskFloorDestination() throws FloorDoesNotExistException {

        mTestFloor1.addNode(new Location(100, 100));
        mTestFloor1.addNode(new Location(200, 200));
        mTestFloor1.addNode(new Location(300, 300));
        mTestFloor1.getFloorNodes().get(0).addDestination(Map.DestinationType.SERVICE, "Test Service1");
        mTestFloor1.getFloorNodes().get(1).addDestination(Map.DestinationType.DEPARTMENT, "Test Department1");
        mTestFloor1.getFloorNodes().get(1).addDestination(Map.DestinationType.DEPARTMENT, "Test Department2");
        mTestFloor1.getFloorNodes().get(2).addDestination(Map.DestinationType.KIOSK, "Test Kiosk");

        Assert.assertTrue(mTestFloor1.getFloorDestinations(Map.DestinationType.KIOSK).contains("Test Kiosk"));

    }*/

    /**
     * Get destinations from floor
     */

    //@Test
    /*public void testGetAllFloorDestinations() throws FloorDoesNotExistException {

        mTestFloor1.addNode(new Location(100, 100));
        mTestFloor1.addNode(new Location(200, 200));
        mTestFloor1.getFloorNodes().get(0).addDestination(Map.DestinationType.SERVICE, "Test Service1");
        mTestFloor1.getFloorNodes().get(1).addDestination(Map.DestinationType.DEPARTMENT, "Test Department1");
        mTestFloor1.getFloorNodes().get(1).addDestination(Map.DestinationType.DEPARTMENT, "Test Department2");

        Assert.assertTrue(mTestFloor1.getFloorDestinations().contains("Test Service1"));
        Assert.assertTrue(mTestFloor1.getFloorDestinations().contains("Test Department1"));
        Assert.assertTrue(mTestFloor1.getFloorDestinations().contains("Test Department2"));
    }*/


}
