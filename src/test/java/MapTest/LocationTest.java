package MapTest;

import Map.Location;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {


    @Test
    public void locationInitialized() {

        Location testLocation = new Location(1, 2);

        assertEquals(testLocation.getClass().toString(), "class Map.Location");

    }







}
