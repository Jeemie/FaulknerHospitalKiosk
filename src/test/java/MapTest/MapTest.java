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

    private Map mMap;
    private Building mTestBuilding;
    private Floor mFloor, mFloor2;

    @Before
    public void setup() {
        mTestBuilding = new Building();
        mFloor = new Floor (1, mTestBuilding, "Floor1_Final.png");
        mFloor2 = new Floor (3, mTestBuilding, "Floor3_Final.png");
        mMap = new Map(mFloor);
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
        mTestBuilding.addFloor(1, "Floor1_Final.png");
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
        Assert.assertEquals(mFloor, mMap.getCurrentFloor());
    }

    @Test
    public void testSetAndGetCurrentFloor() {
        mMap.setCurrentFloor(mFloor2);

        Assert.assertEquals(mFloor2, mMap.getCurrentFloor());
    }




}
