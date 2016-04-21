package MapTest;

import Map.*;
import Map.Enums.ImageType;
import Map.Enums.RelativeDirection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binam on 4/14/16.
 */
public class DirectionsTest {

    private Building mTestBuilding;
    private Floor mFloor3;
    List<LocationNode> mpath, mpath2, mpath3;

    @Before
    public void setup() {
        mTestBuilding = new Building();

        mFloor3 = new Floor("Floor 3", ImageType.FLOOR, mTestBuilding);

        mpath = new ArrayList<LocationNode>();

        mpath.add(new LocationNode("Hallway", new Location(0, 50), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(0, 40), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(0, 30), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(10, 30), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(20, 30), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(20, 40), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(30, 40), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(30, 20), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(30, 0), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(15, 0), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(5, 0), mFloor3, ImageType.POINT));
        mpath.add(new LocationNode("Hallway", new Location(2, 0), mFloor3, ImageType.POINT));

        mpath2 = new ArrayList<LocationNode>();

        mpath2.add(new LocationNode("Hallway", new Location(10, 20), mFloor3, ImageType.POINT)); //Should just be Right
        mpath2.add(new LocationNode("Hallway", new Location(10, 40), mFloor3, ImageType.POINT));
        mpath2.add(new LocationNode("Hallway", new Location(15, 40), mFloor3, ImageType.POINT));

        mpath3 = new ArrayList<LocationNode>();

        mpath3.add(new LocationNode("Hallway", new Location(0, 50), mFloor3, ImageType.POINT)); //Should just be Straight
        mpath3.add(new LocationNode("Hallway", new Location(0, 40), mFloor3, ImageType.POINT));

    }

    @Test
    public void testGetRelativeDirections() {
        List<RelativeDirection> mRelativeDirections = new ArrayList<RelativeDirection>();

        mRelativeDirections.add(RelativeDirection.STRAIGHT);
        mRelativeDirections.add(RelativeDirection.STRAIGHT);
        mRelativeDirections.add(RelativeDirection.RIGHT);
        mRelativeDirections.add(RelativeDirection.STRAIGHT);
        mRelativeDirections.add(RelativeDirection.RIGHT);
        mRelativeDirections.add(RelativeDirection.LEFT);
        mRelativeDirections.add(RelativeDirection.LEFT);
        mRelativeDirections.add(RelativeDirection.STRAIGHT);
        mRelativeDirections.add(RelativeDirection.LEFT);
        mRelativeDirections.add(RelativeDirection.STRAIGHT);
        mRelativeDirections.add(RelativeDirection.STRAIGHT);

        Directions result = new Directions(mpath2);

        Assert.assertEquals(mRelativeDirections, result.getRelativeDirections());
    }

    @Test
    public void testGetRelativeDirectionsTwo() {

        List<RelativeDirection> mRelativeDirections = new ArrayList<RelativeDirection>();
        Directions result = new Directions(mpath2);

        mRelativeDirections.add(RelativeDirection.BACK);
        mRelativeDirections.add(RelativeDirection.LEFT);
        Assert.assertEquals(mRelativeDirections, result.getRelativeDirections());
    }

    @Test
    public void testGetRelativeDirectionsSTRAIGHT() {

        List<RelativeDirection> mRelativeDirections = new ArrayList<RelativeDirection>();
        Directions result = new Directions(mpath2);

        mRelativeDirections.add(RelativeDirection.STRAIGHT);
        Assert.assertEquals(mRelativeDirections, result.getRelativeDirections());
    }

    /**
     * Testing the notifications. Note that these are very likely to change, so these test-cases
     * must be updated.
     */
    @Test
    public void testGetTextualDirections() {
        mTestBuilding = new Building();
        List<String> mTextualDirections = new ArrayList<String>();

        mTextualDirections.add("Take the 2nd Right.");
        mTextualDirections.add("Take the 2nd Right.");
        mTextualDirections.add("Take the next Left.");
        mTextualDirections.add("Take the next Left.");
        mTextualDirections.add("Take the 2nd Left.");
        mTextualDirections.add("Keep going forward, and you'll reach your destination!");

        Directions result = new Directions(mpath);

        Assert.assertEquals(mTextualDirections, result.getTextualDirections());
    }

    @Test
    public void testGetTextualDirectionsTwo() {

        List<String> mTextualDirections = new ArrayList<String>();
        Directions result = new Directions(mpath2);

        mTextualDirections.add("Take the next Left.");
        Assert.assertEquals(mTextualDirections, result.getTextualDirections());
    }

    @Test
    public void testGetTextualDirectionsSTRAIGHT() {

        List<String> mTextualDirections = new ArrayList<String>();
        Directions result = new Directions(mpath2);

        mTextualDirections.add("Go forward until you reach your destination.");
        Assert.assertEquals(mTextualDirections, result.getTextualDirections());
    }

}
