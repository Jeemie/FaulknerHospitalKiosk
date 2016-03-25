package HospitalTest;

import Hospital.Building;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * TODO
 * Created by matthewlemay on 3/25/16.
 */
public class BuildingTest {

    private Building testBuilding = new Building();

    @Test
    public void buildingInstantiated() {
        assertEquals(testBuilding.getClass().toString(), "class Hospital.Building");
    }

}
