package MapTest;

import Map.*;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the Map.Building class.
 */
public class BuildingTest {

    private Building mainBuilding;
    private Floor floor1;

    @Before
    public void setUp() {
        mainBuilding = new Building();
        mainBuilding.addNode(3, new Location(100,100));

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

}
