package MapTest;

import Map.Building;
import Map.Floor;
import Map.Location;
import Map.Node;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by mharris382 on 4/4/2016.
 */


public class BuildingTest {
    private Building mTestBuilding;
    private Floor mFloor1, mFloor2, mFloor7;
    private Node mOne, mTwo;
    private Location mLocation1, mLocation;



    @Test
    public void initialize(){
        mTestBuilding = new Building();
    }

    @Test
    public void addFloor(){
        mTestBuilding.getFloor(1);
        mTestBuilding.getFloor(2);
        mTestBuilding.getFloor(7);
    }

    @Test
    public void run(){
        initialize();
        addFloor();
    }


}
