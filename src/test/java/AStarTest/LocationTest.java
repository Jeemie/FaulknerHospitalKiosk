package AStarTest;

import Map.Location;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matt on 3/27/2016.
 */
public class LocationTest {


    @Test
    public void locationInitialized() {
        Location testLocation = new Location(1, 2);

        assertEquals(testLocation.getClass().toString(), "class Map.Location");
    }

    @Test
    public void locationGetX() {
        Location testLocation = new Location(200, 300);

        assertEquals(testLocation.getX(), 200);
    }

    @Test
    public void locationGetY() {
        Location testLocation = new Location(123, 456);

        assertEquals(testLocation.getY(), 456);
    }
}
