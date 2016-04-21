package MapTest;

import Map.*;
import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class BuildingTest {

    private Building mTestBuilding;

    @Before
    public void setUp() {

        mTestBuilding = new Building();
    }

    /**
     * Add floor to building
     */
    @Test
    public void testAddFloor() {

        mTestBuilding.addFloor("Floor 1", ImageType.FLOOR);

        Assert.assertTrue(mTestBuilding.getFloors().size() == 1);

    }

    /**
     * Add multiple floors to building
     */
    @Test
    public void testAddMultipleFloors() {

        mTestBuilding.addFloor("Floor 1", ImageType.FLOOR);
        mTestBuilding.addFloor("Floor 2", ImageType.FLOOR);
        mTestBuilding.addFloor("Floor 3", ImageType.FLOOR);

        Assert.assertTrue(mTestBuilding.getFloors().size() == 3);

    }

    /**
     * Add node to building
     */
   /* @Test
    public void testAddNode() throws FloorDoesNotExistException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.addNode(1, new Location(100, 100));

        Assert.assertTrue(mTestBuilding.getFloor(1).getLocationNodes().size() == 1);

    }

    *//**
     * Get service destinations from floor one
     *//*

    @Test
    public void testGetServiceDestinations() throws FloorDoesNotExistException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.addNode(1, new Location(100, 100));
        mTestBuilding.addNode(1, new Location(200, 200));
        mTestBuilding.getFloor(1).getFloorNodes().get(0).addDestination(Map.DestinationType.SERVICE, "Test Service1");
        mTestBuilding.getFloor(1).getFloorNodes().get(1).addDestination(Map.DestinationType.SERVICE, "Test Service2");

        Assert.assertTrue(mTestBuilding.getDestinations(Map.DestinationType.SERVICE).contains("Test Service1"));
        Assert.assertTrue(mTestBuilding.getDestinations(Map.DestinationType.SERVICE).contains("Test Service2"));
    }

    *//**
     * Get all building destinations from floor one
     *//*
    @Test
    public void testGetDestinations() throws FloorDoesNotExistException {

        mTestBuilding.addFloor(1, "Floor1_Final.png");
        mTestBuilding.addNode(1, new Location(100, 100));
        mTestBuilding.addNode(1, new Location(200, 200));
        mTestBuilding.getFloor(1).getFloorNodes().get(0).addDestination(Map.DestinationType.DEPARTMENT, "Test Department");
        mTestBuilding.getFloor(1).getFloorNodes().get(1).addDestination(Map.DestinationType.KIOSK, "Test Kiosk");

        Assert.assertTrue(mTestBuilding.getDestinations().contains("Test Department"));
        Assert.assertTrue(mTestBuilding.getDestinations().contains("Test Kiosk"));

    }*/

}
