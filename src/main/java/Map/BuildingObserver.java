package Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A class that observes Buildings.
 */
public class BuildingObserver implements Observer {

    private ArrayList<Building> observedBuildings; // List of observed Buildings
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingObserver.class); // Logger for this class

    
    public BuildingObserver(){

        LOGGER.info("Creating new BuildingObserver: " + this.toString());

        this.observedBuildings = new ArrayList<>();

    }

    /**
     *  called from Building constructor, makes an observer to watch building and adds the building to the list of observed buildings
     *
     * @param building Building object to be observed
     */
    public void observeBuilding(Building building) {

        // check that the building is not already being observed
        if (!observedBuildings.contains(building)) {

            LOGGER.info("Observing new Building: " + building.toString());

            // add an observer watching the building
            building.addObserver(building.getBuildingObserver());

            // add building to the list of observed buildings
            observedBuildings.add(building);

        }

    }

    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating Building: " + o.toString());
        
    }
}
