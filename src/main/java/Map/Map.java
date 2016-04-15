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


    public Map(Floor currentFloor) {

        this.currentFloor = currentFloor;

    }

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

            defaultFilePath = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "default.json");

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        try {

            File specifiedFile = new File(specifiedFilePath.toURI());

            File defaultFile = new File(defaultFilePath.toURI());

            /* if (specifiedFile.exists() && specifiedFile.length() > 0) {

                // Load specified file
                mMainHospital = loadFromFile(specifiedFile);
                LOGGER.info("Loaded map from file " + specifiedFile.toString());

            } else
             */
            if (defaultFile.exists()) {

                mMainHospital = loadFromFile(defaultFile);
                LOGGER.info("Loaded map from file " + defaultFile.toString());

                if (defaultFile.length() <= 2) {
                    LOGGER.warn("Loaded file is empty");
                    mMainHospital = starterMap();

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
    /**
     * Data for starter map
     * @return
     */
    public static Building starterMap() throws FloorDoesNotExistException {
        Building mMainHospital = new Building();

         mMainHospital = new Building();

        // Floors

        mMainHospital.addFloor(1, "Floor1_Final.png");
         mMainHospital.addFloor(2, "Floor2_Final.png");
        mMainHospital.addFloor(3, "Floor3_Final.png");
         mMainHospital.addFloor(4, "Floor4_Final.png");


        // FLOOR1

        // Floor 1 Nodes
        mMainHospital.getFloor(1).addNode(new Location(10,10)); //get(0)
         mMainHospital.getFloor(1).addNode( new Location (10, 20)); //get(1)
        mMainHospital.getFloor(1).addNode( new Location (10, 30)); //get(2)
        mMainHospital.getFloor(1).addNode( new Location (10, 40)); //get(3)
         mMainHospital.getFloor(1).addNode( new Location (10, 50)); //get(4)
        mMainHospital.getFloor(1).addNode( new Location (10, 60)); //get(5)
       mMainHospital.getFloor(1).addNode( new Location (10, 70)); //get(6)
        mMainHospital.getFloor(1).addNode( new Location (10, 80)); //get(7)
         mMainHospital.getFloor(1).addNode( new Location (10, 90)); //get(8)
        mMainHospital.getFloor(1).addNode( new Location (10, 100)); //get(9)
        mMainHospital.getFloor(1).addNode( new Location (10, 110)); //get(10)
        mMainHospital.getFloor(1).addNode( new Location (10, 120)); //get(11)
        mMainHospital.getFloor(1).addNode( new Location (10, 130)); //get(12)
        mMainHospital.getFloor(1).addNode( new Location (10, 140)); //get(13)

        // Floor 1 Departments
        mMainHospital.getFloor(1).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Audiology");
        mMainHospital.getFloor(1).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT, "Cardiac Rehabilitation");
        mMainHospital.getFloor(1).getLocationNodes().get(2).addDestination(Destination.DEPARTMENT, "Center for Preoperative Evaluation");
        mMainHospital.getFloor(1).getLocationNodes().get(3).addDestination(Destination.DEPARTMENT, "Emergency Room");
        mMainHospital.getFloor(1).getLocationNodes().get(4).addDestination(Destination.DEPARTMENT, "GI Endoscopy");
        mMainHospital.getFloor(1).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Laboratory");
        mMainHospital.getFloor(1).getLocationNodes().get(6).addDestination(Destination.DEPARTMENT, "Patient Financial Services");
        mMainHospital.getFloor(1).getLocationNodes().get(7).addDestination(Destination.DEPARTMENT, "Radiology");
        mMainHospital.getFloor(1).getLocationNodes().get(8).addDestination(Destination.DEPARTMENT, "Special Testing");
        mMainHospital.getFloor(1).getLocationNodes().get(9).addDestination(Destination.DEPARTMENT, "Taiclet Family Center");


        // Floor 1 Services
        mMainHospital.getFloor(1).getLocationNodes().get(10).addDestination(Destination.SERVICE, "Information");
        mMainHospital.getFloor(1).getLocationNodes().get(11).addDestination(Destination.SERVICE, "Admitting/Registration");
        mMainHospital.getFloor(1).getLocationNodes().get(12).addDestination(Destination.SERVICE, "Atrium Cafe");
        mMainHospital.getFloor(1).getLocationNodes().get(13).addDestination(Destination.SERVICE, "Valet Parking");



        //FLOOR 3

        // Floor 3 Nodes
        mMainHospital.getFloor(3).addNode( new Location (30, 10));//get(0)
        mMainHospital.getFloor(3).addNode( new Location (30, 20));//get(1)
        mMainHospital.getFloor(3).addNode( new Location (30, 30));//get(2)
        mMainHospital.getFloor(3).addNode( new Location (30, 40));//get(3)
        mMainHospital.getFloor(3).addNode( new Location (30, 50));//get(4)
        mMainHospital.getFloor(3).addNode( new Location (30, 60));//get(5)
        mMainHospital.getFloor(3).addNode( new Location (30, 70));//get(6)
        mMainHospital.getFloor(3).addNode( new Location (30, 80));//get(7)
        mMainHospital.getFloor(3).addNode( new Location (30, 90));//get(8)
        mMainHospital.getFloor(3).addNode( new Location (30, 100));//get(9)
        mMainHospital.getFloor(3).addNode( new Location (30, 110));//get(10)
        mMainHospital.getFloor(3).addNode( new Location (30, 120));//get(11)

        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Roslindale Pediatric Associates");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Byrne, Jennifer, RN, CPNP");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Grossi, Lisa, RN, MS, CPNP");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Keller, Elisabeth, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Malone, Linda, DNP, RN, CPNP");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Morrison, Beverly, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "O'Connor, Elizabeth, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Saluti, Andrew, DO");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Scheff, David, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Stacks, Robert, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Tunick, Mitchell, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Viola, Julianne, MD");

        mMainHospital.getFloor(3).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT, "Eye Care Specialists");
        mMainHospital.getFloor(3).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT, "Suburban Eye Specialists");
        mMainHospital.getFloor(3).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Dann, Harriet, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Frangieh, George, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Micley, Bruce, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Patten, James, MD");

        mMainHospital.getFloor(3).getLocationNodes().get(2).addDestination(Destination.DEPARTMENT, "Obstetrics and Gynecology Associates");
        mMainHospital.getFloor(3).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Greenberg, James Adam, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Miner, Julie, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Nadarajah, Sarah, WHNP");
        mMainHospital.getFloor(3).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Schueler, Leila, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Smith, Shannon, MD");

        mMainHospital.getFloor(3).getLocationNodes().get(3).addDestination(Destination.SERVICE, "ATM");
        mMainHospital.getFloor(3).getLocationNodes().get(4).addDestination(Destination.SERVICE, "Huvos Auditorium");
        mMainHospital.getFloor(3).getLocationNodes().get(5).addDestination(Destination.SERVICE, "Cafeteria");
        mMainHospital.getFloor(3).getLocationNodes().get(5).addDestination(Destination.SERVICE, "Chapel");
        mMainHospital.getFloor(3).getLocationNodes().get(6).addDestination(Destination.SERVICE, "Gift Shop");
        mMainHospital.getFloor(3).getLocationNodes().get(7).addDestination(Destination.SERVICE, "Patient Relations");
        mMainHospital.getFloor(3).getLocationNodes().get(8).addDestination(Destination.SERVICE, "Volunteer Services");

        mMainHospital.getFloor(3).getLocationNodes().get(9).addDestination(Destination.KIOSK, "Kiosk");

        mMainHospital.getFloor(3).getLocationNodes().get(10).addDestination(Destination.BATHROOM, "Floor 1 Bathroom");

        mMainHospital.getFloor(3).getLocationNodes().get(11).addDestination(Destination.ELEVATOR, "Elevatoe");
        return mMainHospital;
    }



    public Floor getCurrentFloor() {

        return currentFloor;
    }
    public void setCurrentFloor(Floor currentFloor) {

        this.currentFloor = currentFloor;

    }
}


