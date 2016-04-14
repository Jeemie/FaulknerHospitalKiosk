package MapTest;

import Map.*;
import Map.Exceptions.FloorDoesNotExistException;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


/**
 * Created by mharris382 on 4/4/2016.
 */


public class BuildingTest {

    private Building mainBuilding;
    private Building mTestBuilding;
    private Floor mFloor1, mFloor2, mFloor7;
    private LocationNode mOne, mTwo, mThree;
    private Location mLocation1, mLocation;

    @Before
    public void setUp() {
        mainBuilding = new Building();
        Floor mFloor3 = new Floor(3, mainBuilding, "Floor3_Final.png");
        try {
            mainBuilding.addNode(3, new Location(100,100));
        } catch (FloorDoesNotExistException e) {
            e.printStackTrace();
        }

        mTestBuilding = new Building();
        mFloor1 = mTestBuilding.addFloor(1, "Floor1_Final.png");
        mFloor2 = mTestBuilding.addFloor(2, "Floor3_Final.png");
        mOne = mFloor1.addNode(new Location(123, 546));
        mTwo = mFloor1.addNode(new Location(100, 200));
        mThree = mFloor2.addNode(new Location(100, 250));



    }

  /*  @Test
    public void testSaveToFile() throws URISyntaxException {
        try {
            mTestBuilding.addNode(1, new Location(100, 100));
            mTestBuilding.addNode(2, new Location(100, 200));
            mTwo.addAdjacentNode(mThree);
            mTestBuilding.saveToFile("Kiosk/Controllers/mapdata.json");
        }
        catch(java.io.IOException e) {
            e.printStackTrace();
        }
         catch (FloorDoesNotExistException e) {
           e.printStackTrace();
        }
    }*/

    @Test
    public void addFloor() {
       // mTestBuilding.addFloor(1);
       // mTestBuilding.addFloor(2);
       // mTestBuilding.addFloor(7);
    }
/*
    @Test
    public void testLoadFromFile() throws URISyntaxException,FloorDoesNotExistException {
        try {
            mFloor1 = new Floor(1, mTestBuilding);
            mFloor2 = new Floor(2, mTestBuilding);
            mTestBuilding.addFloor(1);
            mTestBuilding.addFloor(2);
            mOne.addAdjacentNode(mTwo);
            mTwo.addAdjacentNode(mThree);
            mTestBuilding.addNode(1, new Location(100, 100));
            mTestBuilding.addNode(2, new Location(100, 200));
            mFloor1.addNode(new Location(100, 100));

            mTwo.addDestination(Destination.DEPARTMENT, "ER");
            mTwo.addDestination(Destination.PHYSICIAN, "Dr. Binam");
            mOne.addDestination(Destination.KIOSK, "Kiosk");


            mTestBuilding.saveToFile("Kiosk/Controllers/mapdata.json");
            //mTestBuilding.loadFromFile("Kiosk/Controllers/mapdata.json");
        }
        catch(java.io.IOException e) {
            e.printStackTrace();
        }
    } */

}
