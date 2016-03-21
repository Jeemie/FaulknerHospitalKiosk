package BuildingTests;

import Building.Building;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by matthewlemay on 3/21/16.
 */

public class BuildingTest {

    // Current directory with the path to the FaulknerHospitalBuilding.json file
    private String directory = System.getProperty("user.dir")
            + "/Resources/json/building/FaulknerHospitalBuilding.json";

    Building testBuilding = new Building(directory);

    @Test
    public void BuildingInstantiated() {
        assertEquals(testBuilding.getClass().toString(), "class Building.Building");
    }

}
