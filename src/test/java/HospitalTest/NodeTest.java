package HospitalTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * TODO
 * Created by matthewlemay on 3/25/16.
 */
public class NodeTest {

    private Node testNode = new Node();

    @Test
    public void nodeInstantiated() {
        assertEquals(testNode.getClass().toString(), "class Hospital.Node");
    }
}


