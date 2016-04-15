package MapTest;

import Map.Building;
import Map.Floor;
import Map.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maryannoconnell on 4/12/16.
 */
public class MapTest {

    private Map mMap = new Map();
    private Building mTestBuilding;
    private Floor mFloor;

    @Before
    public void setup() {
        mTestBuilding = new Building();
        mFloor = new Floor (1, mTestBuilding, "picName");
    }

    @Test
    public void testInitMapComponentsEmptyBuilding() {
        Exception ex = null;
        try {
            mMap.initMapComponents(mTestBuilding);
        } catch (Exception e) {
            ex = e;
        }
        Assert.assertEquals(null, ex);
    }

    @Test
    public void testInitMapComponents() {
        mTestBuilding.addFloor(1, "picName");
        Exception ex = null;
        try {
            mMap.initMapComponents(mTestBuilding);
        } catch (Exception e) {
        ex = e;
        }
        Assert.assertEquals(null, ex);
    }

    @Test
    public void testEmptyGetCurrentFloor() {
        Assert.assertEquals(null, mMap.getNodeFloor());
    }

    @Test
    public void testSetAndGetCurrentFloor() {
        mMap.setCurrentFloor(mFloor);

        Assert.assertEquals(1, mMap.getNodeFloor().getFloor());
    }




}
