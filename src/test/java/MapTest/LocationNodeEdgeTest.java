package MapTest;

import Map.*;
import Map.Enums.ImageType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by maryannoconnell on 4/20/16.
 */
public class LocationNodeEdgeTest {



    // LocationNodeEdge getEdgeBetween(ArrayList<LocationNodeEdge> edges, LocationNode node1, LocationNode node2)

    // boolean edgeExists(LocationNode currentNode, LocationNode adjacentNode)

    // boolean isEdgeBetweenNodes(LocationNode currentNode, LocationNode adjacentNode)

    // double computeWeight()

    // public LocationNode getOtherNode(LocationNode locationNode)

    LocationNode mNode1, mNode2, mNode3, mNode4, mNode5, mNode6;

    LocationNodeEdge mEdge1, mEdge2, mEdge3, mEdge4, mEdge5, mEdge6;

    Floor mFloor1;

    ArrayList<LocationNodeEdge> mEdges = new ArrayList<>();

    @Before
    public void setup() {

        // Floor for testing
        mFloor1 = new Floor();

        // Location Nodes
        mNode1 = new LocationNode("Test Node1", new Location(100, 100), mFloor1, ImageType.POINT);
        mNode2 = new LocationNode("Test Node2", new Location(200, 100), mFloor1, ImageType.POINT);
        mNode3 = new LocationNode("Test Node3", new Location(100, 50), mFloor1, ImageType.POINT);
        mNode4 = new LocationNode("Test Node4", new Location(100, 200), mFloor1, ImageType.POINT);
        mNode5 = new LocationNode("Test Node5", new Location(200, 200), mFloor1, ImageType.POINT);
        mNode6 = new LocationNode("Test Node5", new Location(100, 300), mFloor1, ImageType.POINT);

        // Edges
        mEdge1 = new LocationNodeEdge(mNode1, mNode2);
        mEdge2 = new LocationNodeEdge(mNode1, mNode3);
        mEdge3 = new LocationNodeEdge(mNode1, mNode4);
        mEdge4 = new LocationNodeEdge(mNode2, mNode3);
        mEdge5 = new LocationNodeEdge(mNode4, mNode5);
        mEdge6 = new LocationNodeEdge(mNode5, mNode6);

        mEdges.add(mEdge1);
        mEdges.add(mEdge2);
        mEdges.add(mEdge3);
        mEdges.add(mEdge4);
        mEdges.add(mEdge5);
        mEdges.add(mEdge6);

    }

    /**
     * Get edge between mNode4 and mNode5 (mEdge5)
     */
    @Test
    public void testGetEdgeBetween() {

        LocationNodeEdge actual = LocationNodeEdge.getEdgeBetween(mEdges, mNode4, mNode5);

        Assert.assertEquals(mEdge5, actual);
    }

    /**
     * Check that edge exists between two nodes (order does not matter)
     */
    @Test
    public void testEdgeExistsTrue() {

        Assert.assertTrue(mEdge3.edgeExists(mNode1, mNode4));
        Assert.assertTrue(mEdge3.edgeExists(mNode4, mNode5));

    }


    /**
     * Check that this edge does not connect the two specified nodes
     */
    @Test
    public void testEdgeExistsFalse() {

        Assert.assertFalse(mEdge6.edgeExists(mNode1, mNode4));

    }

    /**
     * Check that this edge connects two nodes (order matters)
     */
    @Test
    public void testIsEdgeBetweenNodes() {



    }

    /**
     * Compute the edge weight (straight line distance) between two nodes
     */
    @Test
    public void testComputeWeight() {


    }

    /**
     * Given an edge and one known node, get the other node
     */
    @Test
    public void testGetOtherNode() {

    }


}
