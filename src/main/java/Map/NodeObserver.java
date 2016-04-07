package Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class that observes a Nodes.
 */
public class NodeObserver implements Observer {

    private ArrayList<MapNode> observedNodes; // List of observed Nodes
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeObserver.class); // Logger for this class

    /**
     * Default constructor for the NodeObserver Class.
     */
    public NodeObserver() {

        LOGGER.info("Creating new NodeObserver: " + this.toString());

        this.observedNodes = new ArrayList<>();

    }

    /**
     * Adds a node you want to Observe to the NodeObserver instance.
     *
     * @param node The node you want to observe.
     */
    public void observeNode(MapNode node){

        // check that the node is not already being observed
        if (!observedNodes.contains(node)) {

            LOGGER.info("Observing new MapNode: " + node.toString());

            // add this node to list of watching nodes
            observedNodes.add(node);

            // add an observer to watch the node
            node.addObserver(node.getNodeObserver());

        }

    }

    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating MapNode: " + o.toString());

    }

}
