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
    private Node mEyeCareSpecialists3B;
    private Node mSuburbanEyeSpecialists3B;
    private Node mPattenJamesMd3B;
    private Node mDannHarrietMd3B;
    private Node mGrossiLisaRN;
    private Node mPatientRelations3;
    private Node mKiosk3;
    private Node mElevator3;
    private Node mStairs3;
    private Node mErrorAddingPhysician;

    //rename Floor objectFloor
    //public Node(double heuristicCost, Location location, Floor currentFloor)
    @Before
    public void setUp() throws Exception {

        mMainHospital = new Building();
        mFloor3 = new Floor(3, mMainHospital);
        mLocation3B = new Location(10, 10);
        mEyeCareSpecialists3B = new Node(0, mLocation3B, mFloor3);
        mSuburbanEyeSpecialists3B = new Node(0, mLocation3B, mFloor3);
        mPattenJamesMd3B = new Node(0, mLocation3B, mFloor3);
        mDannHarrietMd3B = new Node(0, mLocation3B, mFloor3);
        mGrossiLisaRN = new Node(0, new Location (10, 15), mFloor3);
        mPatientRelations3 = new Node(0, new Location(10, 20), mFloor3);
        mKiosk3 = new Node(0, new Location(10, 30), mFloor3);
        mElevator3 = new Node(0, new Location(10, 40), mFloor3);
        mStairs3 = new Node(0, new Location(10, 50), mFloor3);
        mErrorAddingPhysician = new Node(0, mLocation3B, mFloor3);

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
