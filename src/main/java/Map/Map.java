package Map;

import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.Exceptions.FloorDoesNotExistException;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static Map.ObjectToJsonToJava.loadFromFile;


/**
 * Created by maryannoconnell on 4/12/16.
 */
public class Map {

    private Floor currentFloor; // Floor of Kiosk


    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class); // Logger for this class

    /**
     * Store data from JSON file in Building object for use during runtime
     * TODO Store data from JSON file in Map object for use during runtime
     * @return Building initialized with data from loaded from JSON file
     * TODO @return Map initialized with data from loaded from JSON file
     */
    public static Building storeMapData(URL specifiedFilePath) throws DefaultFileDoesNotExistException {
        Building mMainHospital = new Building();    // TODO Change mMainBuilding to mMainMap
        ArrayList<Floor> floors;
        ArrayList<LocationNode> node;
        URL defaultFilePath = null;
        try {
            defaultFilePath = new URL("file:/" + System.getProperty("user.dir") + "/resources/" + "default.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
// TODO file does not exist (according to exist) when file:/ is at front of file path
// TODO save is not executing
       // TODO create file if one does not exisit

        try {
            File specifiedFile = new File(String.valueOf(specifiedFilePath));
            File defaultFile = new File(String.valueOf(defaultFilePath));
            //if (specifiedFile.exists() && specifiedFile.length() > 0) { // Check if file exists and is not empty
            if(specifiedFile.length() > 0) {
                mMainHospital = loadFromFile(specifiedFile); // Load specified file
                // } else if (defaultFile.exists()) {
            }else if (defaultFile.length() > 0) {
                mMainHospital = loadFromFile(defaultFile); // Load from default.json
                LOGGER.info("Loaded map from file " + defaultFile.toString());
            } else{
                LOGGER.warn("Loaded file is empty");
                throw new DefaultFileDoesNotExistException();
            }

        } catch (FloorDoesNotExistException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
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
    public static Building initMapComponents(Building mMainHospital) {
        for (Floor floor: mMainHospital.getFloors()) { //Initialize view-associated fields for each building floor
            //String filePath = System.getProperty("user.dir") + "/resources/" + floor.getImagePath();

            floor.setFloorImage(floor.getImagePath());

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


    public Floor getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}


