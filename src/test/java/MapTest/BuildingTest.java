package MapTest;

import Map.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuildingTest {


    private Map mTestMap;

    @Before
    public void setUp() {

        mTestMap = new Map("test map");
        mTestMap.addBuilding("test building");
        
    }

    /**
     * Add floor to building
     */
    @Test
    public void testAddFloor() {

        String floorName = "Floor 1";
        mTestMap.getMapBuildings().get(0).addFloor(floorName, "floor1.png");

        Assert.assertEquals(mTestMap.getMapBuildings().get(0).getFloors().get(0).getFloorName(), floorName);
    }

    /**
     * Attempt to add floor to building when a floor with the specified name already exists
     */
    @Test
    public void testAddFloorAlreadyExists() {

        mTestMap.getMapBuildings().get(0).addFloor("Floor 1", "floor1.png");

        // The existing floor with the same name should be returned
        Assert.assertEquals(mTestMap.getMapBuildings().get(0).addFloor("Floor 1", "floor1.png"), mTestMap.getMapBuildings().get(0).getFloors().get(0));

        // The building should still have only one floor
        Assert.assertTrue(mTestMap.getMapBuildings().get(0).getFloors().size() == 1);
    }


    /**
     * Add multiple floors to building
     */
    @Test
    public void testAddMultipleFloors() {

        String floorName1 = "Floor 1";
        String floorName2 = "Floor 2";
        String floorName3 = "Floor 3";

        Assert.assertTrue(mTestMap.getMapBuildings().get(0).getFloors().size() == 0);

        mTestMap.getMapBuildings().get(0).addFloor(floorName1,"floor1.png");
        mTestMap.getMapBuildings().get(0).addFloor(floorName2,"floor2.png");
        mTestMap.getMapBuildings().get(0).addFloor(floorName3,"floor3.png");

        Assert.assertTrue(mTestMap.getMapBuildings().get(0).getFloors().size() == 3);
    }

    /**
     * Remove floor from building
     */
    @Test
    public void testRemoveFloor() {

        String floorName1 = "Floor 1";
        String floorName2 = "Floor 2";
        String floorName3 = "Floor 3";

        mTestMap.getMapBuildings().get(0).addFloor(floorName1,"floor1.png");
        mTestMap.getMapBuildings().get(0).addFloor(floorName2,"floor2.png");
        mTestMap.getMapBuildings().get(0).addFloor(floorName3,"floor3.png");


        Floor oldFloor = mTestMap.getMapBuildings().get(0).getFloors().get(0);

        // Remove Floor 1
        mTestMap.getMapBuildings().get(0).removeFloor(oldFloor);

        Assert.assertFalse(mTestMap.getMapBuildings().get(0).getFloors().contains(oldFloor));
        Assert.assertTrue(mTestMap.getMapBuildings().get(0).getFloors().size() == 2);
    }

    /**
     * Get all building destinations in building
     */

    /**
     * Get kiosk destinations in building
     */

    /**
     * Get service destinations in building
     */

    /**
     * Get department destinations in building
     */


}
