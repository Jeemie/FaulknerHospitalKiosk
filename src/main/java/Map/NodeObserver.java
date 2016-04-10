package Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class that observes a Nodes.
 */
public class NodeObserver implements Observer {

    @JsonIgnore
    private ArrayList<LocationNode> observedLocationNodes; // List of observed Nodes
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeObserver.class); // Logger for this class

    /**
     * Default constructor for the NodeObserver Class.
     */
    public NodeObserver() {

        LOGGER.info("Creating new NodeObserver: " + this.toString());

        this.observedLocationNodes = new ArrayList<>();

    }

    /**
     * Adds a locationNode you want to Observe to the NodeObserver instance.
     *
     * @param locationNode The locationNode you want to observe.
     */
    public void observeNode(LocationNode locationNode){

        // check that the locationNode is not already being observed
        if (!observedLocationNodes.contains(locationNode)) {

            LOGGER.info("Observing new LocationNode: " + locationNode.toString());

            // add this locationNode to list of watching nodes
            observedLocationNodes.add(locationNode);

            // add an observer to watch the locationNode
            locationNode.addObserver(locationNode.getNodeObserver());

        }

    }

    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating LocationNode: " + o.toString());

        LocationNode currentLocationNode = ((LocationNode)o);

        if (currentLocationNode.getState() != BuildingState.NORMAL) {

            currentLocationNode.drawAdminNode(currentLocationNode.getCurrentFloor().getNodePane());
            currentLocationNode.drawAdjacentNodes(currentLocationNode.getCurrentFloor().getNodePane());

        }

    }

}
