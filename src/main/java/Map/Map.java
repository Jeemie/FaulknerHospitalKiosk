package Map;

import Map.Exceptions.FloorDoesNotExistException;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static Map.ObjectToJsonToJava.loadFromFile;
import Map.*;


/**
 * Created by maryannoconnell on 4/12/16.
 */
public class Map {



    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class); // Logger for this class

    /**
     * Store data from JSON file in Building object for use during runtime
     * TODO Store data from JSON file in Map object for use during runtime
     * @return Building initialized with data from loaded from JSON file
     * TODO @return Map initialized with data from loaded from JSON file
     */
    public static Building storeMapData() {
        Building mMainHospital = new Building();    // TODO Change mMainBuilding to mMainMap
        ArrayList<Floor> floors;
        ArrayList<LocationNode> node;

        try {
            File file = new File(Map.class.getClassLoader().getResource("Kiosk/Controllers/mapdata.json").toURI());
            if (file.exists() && file.length() > 0) { // Check if file exists and is not empty
                 mMainHospital = loadFromFile(file); // Load mapdata.json
            } else
                file = new File(Map.class.getClassLoader().getResource("Kiosk/Controllers/default.json").toURI());
            if (file.exists() && file.length() > 0) { // Check that default file exists and is not empty
                mMainHospital = loadFromFile(file); // Load default.json
            } else {
                LOGGER.info("Cannot load " + file.toString() + ". File does not exist or is empty.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Loaded from file e2");
        } catch (FloorDoesNotExistException e) {
            System.out.println("Loaded from file e3");
        }


        return mMainHospital;
    }

    /**
     *
     * TODO @param mMainMap Map object with fields requiring initialization
     * TODO @return Map object you want to initialize
     * @param mMainHospital Building object you want to initialize
     * @return Building Building with all required fields initialized
     *
     */
    /*public static Building initMapComponents(Building mMainHospital) {
        for (Floor floor: mMainHospital.getFloors()) { //Initialize view-associated fields for each building floor
            floor.setFloorImage(Map.class.getResource(floor.getImagePath()));
            if (floor.getFloorNodes().size() > 0) { // Check if the floor contains nodes
                for (LocationNode node : floor.getFloorNodes()) {
                    node.setNodeCircle(new Circle(node.getLocation().getX(), node.getLocation().getY(), 5.0));
                    node.initObserver();
                    node.initAdjacentLines();
                }
            }
        }

        return mMainHospital;
    }
*/
}
