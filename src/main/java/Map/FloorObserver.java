package Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class that observes Floors.
 */
public class FloorObserver implements Observer {

    ArrayList<Floor> observedFloors; // List of observed Floors
    private static final Logger LOGGER = LoggerFactory.getLogger(FloorObserver.class); // Logger for this class

    /**
     * Default constructor for the FloorObserver class.
     */
    public FloorObserver() {

        LOGGER.info("Creating new FloorObserver: " + this.toString());

        this.observedFloors = new ArrayList<>();

    }

    /**
     * Adds a floor you want to Observe to the FloorObserver instance.
     *
     * @param floor The Floor that you want to observe.
     */
    public void observeFloor(Floor floor) {

        // check that the floor is not already being observed
        if (!observedFloors.contains(floor)) {

            LOGGER.info("Observing new Floor: " + floor.toString());

            // add an observer watching the floor
            floor.addObserver(floor.getFloorObserver());

            // add floor to the list of floors being observed
            observedFloors.add(floor);

        }

    }

    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating Floor: " + o.toString());

    }

}
