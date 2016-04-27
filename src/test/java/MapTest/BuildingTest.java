package MapTest;

import Map.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BuildingTest {

    private Building mTestBuilding;

    @Before
    public void setUp() {

        mTestBuilding = new Building("test building", new Map("test map"));
    }

    /**
     * Add floor to building
     */
    @Test
    public void testAddFloor() {

        String floorName = "Floor 1";
        mTestBuilding.addFloor(floorName, "floor1.png");

        Assert.assertEquals(mTestBuilding.getFloors().get(0).getFloorName(), floorName);
    }

    /**
     * Attempt to add floor to building when a floor with the specified name already exists
     */
    @Test
    public void testAddFloorAlreadyExists() {

        mTestBuilding.addFloor("Floor 1", "floor1.png");

        // The existing floor with the same name should be returned
        Assert.assertEquals(mTestBuilding.addFloor("Floor 1", "floor1.png"), mTestBuilding.getFloors().get(0));

        // The building should still have only one floor
        Assert.assertTrue(mTestBuilding.getFloors().size() == 1);
    }


    /**
     * Add multiple floors to building
     */
    @Test
    public void testAddMultipleFloors() {

        String floorName1 = "Floor 1";
        String floorName2 = "Floor 2";
        String floorName3 = "Floor 3";

        Assert.assertTrue(mTestBuilding.getFloors().size() == 0);

        mTestBuilding.addFloor(floorName1,"floor1.png");
        mTestBuilding.addFloor(floorName2,"floor2.png");
        mTestBuilding.addFloor(floorName3,"floor3.png");

        Assert.assertTrue(mTestBuilding.getFloors().size() == 3);
    }

    /**
     * Remove floor from building
     */
    @Test
    public void testRemoveFloor() {

        String floorName1 = "Floor 1";
        String floorName2 = "Floor 2";
        String floorName3 = "Floor 3";

        mTestBuilding.addFloor(floorName1,"floor1.png");
        mTestBuilding.addFloor(floorName2,"floor2.png");
        mTestBuilding.addFloor(floorName3,"floor3.png");


        Floor oldFloor = mTestBuilding.getFloors().get(0);

        // Remove Floor 1
        mTestBuilding.removeFloor(oldFloor);

        Assert.assertFalse(mTestBuilding.getFloors().contains(oldFloor));
        Assert.assertTrue(mTestBuilding.getFloors().size() == 2);
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
