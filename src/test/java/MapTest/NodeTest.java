package MapTest;

import Map.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by maryannoconnell on 4/3/16.
 */
public class NodeTest {

    /**
     * placeholder
     */
    private Building mMainHospital;
    private Floor mFloor3;
    private Location mLocation3B;
    private MapNode mEyeCareSpecialists3B;
    private MapNode mSuburbanEyeSpecialists3B;
    private MapNode mPattenJamesMd3B;
    private MapNode mDannHarrietMd3B;
    private MapNode mGrossiLisaRN;
    private MapNode mPatientRelations3;
    private MapNode mKiosk3;
    private MapNode mElevator3;
    private MapNode mStairs3;
    private MapNode mErrorAddingPhysician;

    //rename Floor objectFloor
    //public MapNode(double heuristicCost, Location location, Floor currentFloor)
    @Before
    public void setUp() throws Exception {

        mMainHospital = new Building();
        mFloor3 = new Floor(3, mMainHospital);
        mLocation3B = new Location(10, 10);
        mEyeCareSpecialists3B = new MapNode(0, mLocation3B, mFloor3);
        mSuburbanEyeSpecialists3B = new MapNode(0, mLocation3B, mFloor3);
        mPattenJamesMd3B = new MapNode(0, mLocation3B, mFloor3);
        mDannHarrietMd3B = new MapNode(0, mLocation3B, mFloor3);
        mGrossiLisaRN = new MapNode(0, new Location (10, 15), mFloor3);
        mPatientRelations3 = new MapNode(0, new Location(10, 20), mFloor3);
        mKiosk3 = new MapNode(0, new Location(10, 30), mFloor3);
        mElevator3 = new MapNode(0, new Location(10, 40), mFloor3);
        mStairs3 = new MapNode(0, new Location(10, 50), mFloor3);
        mErrorAddingPhysician = new MapNode(0, mLocation3B, mFloor3);

    }

    /**
     * Add new department destination to specified location
     */
    @Test
    public void testAddDestinationDepartment() {
        mEyeCareSpecialists3B.addDestination(Destination.DEPARTMENT, "Optometry");
        ArrayList<String> destinations = mEyeCareSpecialists3B.getDestinations(Destination.DEPARTMENT);
        Assert.assertEquals(destinations.contains("Optometry"), true);
    }

    /**
     * Add new physician destination to specified location
     */
    @Test
    public void testAddDestinationPhysician() {
        mGrossiLisaRN.addDestination(Destination.PHYSICIAN, "Dr. Lisa Grossi");
        ArrayList<String> destinations = mGrossiLisaRN.getDestinations(Destination.PHYSICIAN);
        Assert.assertEquals(destinations.contains("Dr. Lisa Grossi"), true);
    }

    /**
     * Add new elevator destination to specified location
     */
    @Test
    public void testAddDestinationElevator() {
        mElevator3.addDestination(Destination.ELEVATOR, "Elevator");
        ArrayList<String> destinations = mElevator3.getDestinations(Destination.ELEVATOR);
        Assert.assertEquals(destinations.contains("Elevator"), true);
    }


}
