package MapTest;

import Map.*;

import Map.Exceptions.FloorDoesNotExistException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

import java.util.UUID;


/**
 * Created by mharris382 on 4/4/2016.
 */


public class BuildingTest {

    private Building mainBuilding;
    private Building mTestBuilding;
    private Floor mFloor1, mFloor2, mFloor7;
    private Node mOne, mTwo, mThree;
    private Location mLocation1, mLocation;

    @Before
    public void setUp() {
        mTestBuilding = new Building();
        mFloor1 = new Floor(1, mTestBuilding);
        mFloor2 = new Floor(2, mTestBuilding);
        mTestBuilding.addFloor(1);
        mTestBuilding.addFloor(2);
        mOne = new Node(0, new Location(100, 100), mFloor1);
        mTwo = new Node(0, new Location(100, 200), mFloor2);
        mThree = new Node(0, new Location(100, 250), mFloor2);

    }

    @Test
    public void testSaveToFile() throws URISyntaxException {
        try {
            mTestBuilding.addNode(1, new Location(100, 100));
            mTestBuilding.addNode(2, new Location(100, 200));
            mTwo.addAdjacentNode(mThree);
            mTestBuilding.saveToFile("mapdata.json");
        }
        catch(java.io.IOException e) {
            e.printStackTrace();
        }
         catch (FloorDoesNotExistException e) {
           e.printStackTrace();
        }
    }

    @Test
    public void addFloor() {
        mTestBuilding.addFloor(1);
        mTestBuilding.addFloor(2);
        mTestBuilding.addFloor(7);
    }

}
