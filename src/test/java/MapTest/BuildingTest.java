package MapTest;

import Map.*;

import Map.Exceptions.FloorDoesNotExistException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
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
    private Node mOne, mTwo;
    private Location mLocation1, mLocation;

    @Before
    public void setUp() {
        mainBuilding = new Building();
        mainBuilding.addFloor(3);
        try {
            mainBuilding.addNode(3, new Location(100,100));
        } catch (FloorDoesNotExistException e) {
            e.printStackTrace();
        }

        mTestBuilding = new Building();

    }

    @Test
    public void testSaveToFile()  {
        try {
            mainBuilding.saveToFile(new URL("file:///Users/maryannoconnell/Desktop/file.json"));
        }
        catch(java.io.IOException e) {
            // exception handler code here
            // ...
        }
    }

    @Test
    public void addFloor() {
        mTestBuilding.addFloor(1);
        mTestBuilding.addFloor(2);
        mTestBuilding.addFloor(7);
    }

}
