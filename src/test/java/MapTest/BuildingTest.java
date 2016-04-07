package MapTest;

import Map.Building;
import Map.Floor;
import Map.Location;
import Map.Node;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mharris382 on 4/4/2016.
 */


public class BuildingTest {

    private Building mTestBuilding;
    private Floor mFloor1, mFloor2, mFloor7;
    private Node mOne, mTwo;
    private Location mLocation1, mLocation;



    @Before
    public void setUp(){
        mTestBuilding = new Building();
    }

    @Test
    public void addFloor() {
        mTestBuilding.addFloor(1);
        mTestBuilding.addFloor(2);
        mTestBuilding.addFloor(7);
    }


}
