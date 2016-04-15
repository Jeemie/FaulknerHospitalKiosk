package Map;

import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.Exceptions.FloorDoesNotExistException;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static Map.ObjectToJsonToJava.loadFromFile;


/**
 * Created by maryannoconnell on 4/12/16.
 */
public class Map {

    // Floor of Kiosk
    private Floor currentFloor;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class);

    // TODO @return Map initialized with data from loaded from JSON file
    // TODO Store data from JSON file in Map object for use during runtime
    /**
     * Store data from JSON file in Building object for use during runtime
     * @return Building initialized with data from loaded from JSON file
     */
    public static Building storeMapData(URL specifiedFilePath) throws DefaultFileDoesNotExistException {

        Building mMainHospital = new Building();    // TODO Change mMainBuilding to mMainMap
        ArrayList<Floor> floors;
        ArrayList<LocationNode> node;
        URL defaultFilePath = null;

        try {
            /**
             *
             *This is for OSx use this while testing.
             *
             *defaultFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
             */
            //This is for Windows. Use this while testing.

            defaultFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
            //specifiedFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        // TODO file does not exist (according to exist) when file:/ is at front of file path
        // TODO create file if one does not exisit

        try {

            File specifiedFile = new File(specifiedFilePath.toURI());

            File defaultFile = new File(defaultFilePath.toURI());

            if (specifiedFile.exists() && specifiedFile.length() > 0) {

                // Load specified file
                mMainHospital = loadFromFile(specifiedFile);
                LOGGER.info("Loaded map from file " + specifiedFile.toString());

            } else if (defaultFile.exists()) {

                mMainHospital = loadFromFile(defaultFile);
                LOGGER.info("Loaded map from file " + defaultFile.toString());

                if (defaultFile.length() > 0) {

                    LOGGER.warn("Loaded file is empty");

                }

            } else {

                throw new DefaultFileDoesNotExistException();

            }

        } catch (FloorDoesNotExistException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return mMainHospital;
    }

    /*
     * TODO @param mMainMap Map object with fields requiring initialization
     * TODO @return Map object you want to initialize
     */


    /**
     *
     * @param mMainHospital Building object you want to initialize
     * @return Building Building with all required fields initialized
     *
     */
    public static Building initMapComponents(Building mMainHospital) {

        for (Floor floor: mMainHospital.getFloors()) {

            //Initialize view-associated fields for each building floor
            //String filePath = System.getProperty("user.dir") + "/resources/" + floor.getImagePath();
            floor.setImagePath(floor.getRelativePath());
            floor.setFloorImage(floor.getImagePath());

            // Check if the floor contains nodes
            if (floor.getFloorNodes().size() > 0) {

                for (LocationNode node : floor.getFloorNodes()) {
                    node.setNodeCircle(new Circle(node.getLocation().getX(), node.getLocation().getY(), 5.0));
                    node.initObserver();
                    node.initAdjacentLines();
                }

            }

        }

        return mMainHospital;
    }


    public Floor getNodeFloor() {

        return currentFloor;
    }
    public void setCurrentFloor(Floor currentFloor) {

        this.currentFloor = currentFloor;

    }
}


