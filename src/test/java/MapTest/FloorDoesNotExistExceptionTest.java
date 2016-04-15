package MapTest;

import Map.Building;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Floor;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maryannoconnell on 4/14/16.
 */
public class FloorDoesNotExistExceptionTest {

    private Building mTestBuilding;

    @Before
    public void setUp() {
        mTestBuilding = new Building();
    }

    /**
     * Floor one not added to mTestBuilding (Building is empty)
     * @throws Exception
     */
    @Test(expected = FloorDoesNotExistException.class)
    public void testFloorDoesNotExist1() throws Exception {
        mTestBuilding.getFloor(1);
    }

    /**
     * Floor two not added to mTestBuilding (Building contains only floor one)
     * @throws Exception
     */
    @Test(expected = FloorDoesNotExistException.class)
    public void testFloorDoesNotExist2() throws Exception {
        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.getFloor(2);
    }
}
