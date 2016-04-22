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
            /**
             *
             *This is for OSx use this while testing.
             *
             *defaultFilePath = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "default.json");
             */
            //This is for Windows. Use this while testing.

            defaultFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
            //specifiedFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");

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
        mMainHospital.addFloor(1, "floor1.png");
        mMainHospital.addFloor(2, "floor2.png");
        mMainHospital.addFloor(3, "floor3.png");
        mMainHospital.addFloor(4, "floor4.png");
        mMainHospital.addFloor(5, "floor5.png");
        mMainHospital.addFloor(6, "floor6.png");
        mMainHospital.addFloor(7, "floor7.png");




        //FLOOR 1
        mMainHospital.getFloor(1).addNode(new Location(20, 10)); //get(0) Audiology
        mMainHospital.getFloor(1).addNode(new Location(20, 20)); //get(1) Cardiac
        mMainHospital.getFloor(1).addNode(new Location(20, 30)); //get(2) Preop
        mMainHospital.getFloor(1).addNode(new Location(20, 40)); //get(3) ER
        mMainHospital.getFloor(1).addNode(new Location(20, 50)); //get(4) GI
        mMainHospital.getFloor(1).addNode(new Location(20, 60)); //get(5) Lab
        mMainHospital.getFloor(1).addNode(new Location(20, 70)); //get(6) Finance
        mMainHospital.getFloor(1).addNode(new Location(20, 80)); //get(7) Radiology
        mMainHospital.getFloor(1).addNode(new Location(20, 90)); //get(8) Test
        mMainHospital.getFloor(1).addNode(new Location(20, 100)); //get(9) Family
        mMainHospital.getFloor(1).addNode(new Location(20, 110)); //get(10) Info
        mMainHospital.getFloor(1).addNode(new Location(20, 120)); //get(11) Admit
        mMainHospital.getFloor(1).addNode(new Location(20, 130)); //get(12) Cafe
        mMainHospital.getFloor(1).addNode(new Location(20, 140)); //get(13) Valet
        mMainHospital.getFloor(1).addNode(new Location(20, 150)); //get(14) kiosk
        mMainHospital.getFloor(1).addNode(new Location(20, 160)); //get(15) Bathroom
        mMainHospital.getFloor(1).addNode(new Location(20, 170)); //get(16) H Elevatoe
        mMainHospital.getFloor(1).addNode(new Location(20, 180)); //get(17) A Elevatoe

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

        mMainHospital.getFloor(1).getLocationNodes().get(10).addDestination(Destination.SERVICE, "Information");
        mMainHospital.getFloor(1).getLocationNodes().get(11).addDestination(Destination.SERVICE, "Admitting/Registration");
        mMainHospital.getFloor(1).getLocationNodes().get(12).addDestination(Destination.SERVICE, "Atrium Cafe");
        mMainHospital.getFloor(1).getLocationNodes().get(12).addDestination(Destination.SERVICE, "Starbucks");
        mMainHospital.getFloor(1).getLocationNodes().get(13).addDestination(Destination.SERVICE, "Valet Parking");

        mMainHospital.getFloor(1).getLocationNodes().get(14).addDestination(Destination.KIOSK, "Kiosk");
        (mMainHospital.getFloor(1)).setStartNode(mMainHospital.getFloor(1).getLocationNodes().get(14));

        mMainHospital.getFloor(1).getLocationNodes().get(15).addDestination(Destination.BATHROOM, "Floor 1 Bathroom");

        mMainHospital.getFloor(1).getLocationNodes().get(16).addDestination(Destination.ELEVATOR, "Hillside Elevator");
        mMainHospital.getFloor(1).getLocationNodes().get(17).addDestination(Destination.ELEVATOR, "Atrium Elevator");


        //FLOOR 2
        mMainHospital.getFloor(2).addNode(new Location(20, 10)); //get(0) 2A
        mMainHospital.getFloor(2).addNode(new Location(20, 20)); //get(1) 2B
        mMainHospital.getFloor(2).addNode(new Location(20, 30)); //get(2) Physical Therapy
        mMainHospital.getFloor(2).addNode(new Location(20, 40)); //get(3) Psychiatry
        mMainHospital.getFloor(2).addNode(new Location(20, 50)); //get(4) Addiction recovery
        mMainHospital.getFloor(2).addNode(new Location(20, 60)); //get(5) rehab
        mMainHospital.getFloor(2).addNode(new Location(20, 70)); //get(6) bathroom
        mMainHospital.getFloor(2).addNode(new Location(20, 80)); //get(7) H Elevatoe
        mMainHospital.getFloor(2).addNode(new Location(20, 90)); //get(8) A Elevatoe

        mMainHospital.getFloor(2).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Otolarngology");
        mMainHospital.getFloor(2).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Corrales, Carleton Eduardo, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Prince, Anthony, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Roditi, Rachel, MD");

        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT, "Plastic Surgery");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Carty, Matthew, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Caterson, Stephanie, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Chun, Yoon Sun, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Hajj, Micheline, RN");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Halvorson, Eric, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Hergrueter, Charles, MD");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Ingram, Abbie, PA-C");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Lafleur, Emily, PA-C");
        mMainHospital.getFloor(2).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Lahair, Tracy, PA-C");

        mMainHospital.getFloor(2).getLocationNodes().get(2).addDestination(Destination.DEPARTMENT, "Physical Therapy");
        mMainHospital.getFloor(2).getLocationNodes().get(3).addDestination(Destination.DEPARTMENT, "Psychology");
        mMainHospital.getFloor(2).getLocationNodes().get(4).addDestination(Destination.DEPARTMENT, "Addiction Recovery Program");
        mMainHospital.getFloor(2).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Rehabilitation Services");

        mMainHospital.getFloor(2).getLocationNodes().get(6).addDestination(Destination.BATHROOM, "Floor 2 Bathroom ");

        mMainHospital.getFloor(2).getLocationNodes().get(7).addDestination(Destination.ELEVATOR, "Hillside Elevator");
        mMainHospital.getFloor(2).getLocationNodes().get(8).addDestination(Destination.ELEVATOR, "Atrium Elevator");


        /* Unknown Locction
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Berman, Dan, LICSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Cotter, Lindsay, LCSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Doherty, Meghan, LCSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Donnelly, Kevin, PhD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Dowd, Erin, LCSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Ecker, Vivian, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Fromson, John, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN,"Haimovici, Florina, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Howard, Neal Anthony, LICSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Humbert, Timberly, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Keller, Beth, RN, PsyD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Lai, Leonard, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Leone, Amanda, LICSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Mariano, Timothy, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Matwin, Sonia, PhD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Perry, David, LICSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Rodriguez, Claudia, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Samadi, Farrah, NP");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Schoenfeld, Paul, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Stevens, Erin, LICSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Stewart, Carl, MEd, LADC I");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Trumble, Julia, LICSW");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Yudkoff, Benjamin, MD");
        mMainHospital.getFloor(2).getLocationNodes().get().addDestination(Destination.PHYSICIAN, "Issa, Mohammed, MD");
*/

        //FLOOR 3
        mMainHospital.getFloor(3).addNode(new Location(10, 10));//get(0) 3A
        mMainHospital.getFloor(3).addNode(new Location(10, 20));//get(1) 3B
        mMainHospital.getFloor(3).addNode(new Location(10, 30));//get(2) 3C
        mMainHospital.getFloor(3).addNode(new Location(10, 40));//get(3) ATM
        mMainHospital.getFloor(3).addNode(new Location(10, 50));//get(4) Auditorium
        mMainHospital.getFloor(3).addNode(new Location(10, 60));//get(5) Cafeteria
        mMainHospital.getFloor(3).addNode(new Location(10, 70));//get(6) Chapel
        mMainHospital.getFloor(3).addNode(new Location(10, 80));//get(7) Gift Shop
        mMainHospital.getFloor(3).addNode(new Location(10, 90));//get(8) Patient Relations
        mMainHospital.getFloor(3).addNode(new Location(10, 100));//get(9) Volunteer Services
        mMainHospital.getFloor(3).addNode(new Location(10, 110));//get(10) Kiosk
        mMainHospital.getFloor(3).addNode(new Location(10, 120));//get(11) Bathrooms near atrium
        mMainHospital.getFloor(3).addNode(new Location(10, 130));//get(12) Bathrooms near kiosk
        mMainHospital.getFloor(3).addNode(new Location(10, 140));//get(13) Bathrooms near auditorium
        mMainHospital.getFloor(3).addNode(new Location(10, 150));//get(14) H Elevatoe
        mMainHospital.getFloor(3).addNode(new Location(10, 160));//get(15) A Elevatoe

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
        mMainHospital.getFloor(3).getLocationNodes().get(6).addDestination(Destination.SERVICE, "Chapel");
        mMainHospital.getFloor(3).getLocationNodes().get(7).addDestination(Destination.SERVICE, "Gift Shop");
        mMainHospital.getFloor(3).getLocationNodes().get(8).addDestination(Destination.SERVICE, "Patient Relations");
        mMainHospital.getFloor(3).getLocationNodes().get(9).addDestination(Destination.SERVICE, "Volunteer Services");

        mMainHospital.getFloor(3).getLocationNodes().get(10).addDestination(Destination.KIOSK, "Kiosk");

        mMainHospital.getFloor(3).getLocationNodes().get(11).addDestination(Destination.BATHROOM, "Floor 3 Bathroom");
        mMainHospital.getFloor(3).getLocationNodes().get(12).addDestination(Destination.BATHROOM, "Floor 3 Bathroom");
        mMainHospital.getFloor(3).getLocationNodes().get(13).addDestination(Destination.BATHROOM, "Floor 3 Bathroom");

        mMainHospital.getFloor(3).getLocationNodes().get(14).addDestination(Destination.ELEVATOR, " Hillside Elevator");
        mMainHospital.getFloor(3).getLocationNodes().get(15).addDestination(Destination.ELEVATOR, "Atrium Elevatoe");


        //FLOOR 4
        mMainHospital.getFloor(4).addNode(new Location(40, 10));//get(0) 4A
        mMainHospital.getFloor(4).addNode(new Location(40, 20));//get(1) 4B
        mMainHospital.getFloor(4).addNode(new Location(40, 30));//get(2) 4C
        mMainHospital.getFloor(4).addNode(new Location(40, 40));//get(3) 4D
        mMainHospital.getFloor(4).addNode(new Location(40, 50));//get(4) 4F
        mMainHospital.getFloor(4).addNode(new Location(40, 60));//get(5) 4G
        mMainHospital.getFloor(4).addNode(new Location(40, 70));//get(6) 4H
        mMainHospital.getFloor(4).addNode(new Location(40, 80));//get(7) 4I
        mMainHospital.getFloor(4).addNode(new Location(40, 90));//get(8) 4J
        mMainHospital.getFloor(4).addNode(new Location(40, 100));//get(9) 4N
        mMainHospital.getFloor(4).addNode(new Location(40, 110));//get(10) 4S
        mMainHospital.getFloor(4).addNode(new Location(40, 120));//get(11) Doherty Conference Room
        mMainHospital.getFloor(4).addNode(new Location(40, 130));//get(12) Interpreter Services
        mMainHospital.getFloor(4).addNode(new Location(40, 140));//get(13) Mary Ann Tynan Conference Rooms
        mMainHospital.getFloor(4).addNode(new Location(40, 150));//get(14) Medical Library
        mMainHospital.getFloor(4).addNode(new Location(40, 160));//get(15) Medical Records
        mMainHospital.getFloor(4).addNode(new Location(40, 170));//get(16) Sadowsky Conference Room
        mMainHospital.getFloor(4).addNode(new Location(40, 180));//get(17) Saslow Conference Room
        mMainHospital.getFloor(4).addNode(new Location(40, 190));//get(18) Social Work
        mMainHospital.getFloor(4).addNode(new Location(40, 200));//get(19) Floor 4 Bathroom
        mMainHospital.getFloor(4).addNode(new Location(40, 210));//get(20) Hillside Elevator
        mMainHospital.getFloor(4).addNode(new Location(40, 220));//get(21) Atrium Elevator



        //Floor 4
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Brigham and Women's Primary Physicians");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Caplan, Laura, PA-C");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Cohen, Natalie, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Copello, Maria, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Healy, Barbara, RN");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Lauretti, Linda, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "McCord, Laura, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Nuspl, Kristen, PA-C");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Oliver, Lynn, RN");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Walsh Samp, Kathy, LICSW");
        mMainHospital.getFloor(4).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Welker, Roy, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT, "Gastroenterology Associates");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Conant, Alene, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Drewniak, Stephen, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Homenko, Daria, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Lo, Amy, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Matloff, Daniel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Mutinga, Muthoka, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Preneta, Ewa, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1).addDestination(Destination.PHYSICIAN, "Smith, Benjamin, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.DEPARTMENT, "Neurology/Sleep Division");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.DEPARTMENT, "Sleep Disorders Service");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Horowitz, Sandra, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Mullally, William, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Novak, Peter, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Pavlova, Milena, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Pilgrim, David, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Vardeh, Daniel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Weisholtz, Daniel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Whitman, Gregory, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(3).addDestination(Destination.DEPARTMENT, "Arthritis Center");
        mMainHospital.getFloor(4).getLocationNodes().get(3).addDestination(Destination.DEPARTMENT, "Rheumatology Center");
        mMainHospital.getFloor(4).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Hoover, Paul, MD, PhD");
        mMainHospital.getFloor(4).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Pariser, Kenneth, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Todd, Derrick, MD, PhD");
        mMainHospital.getFloor(4).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Wei, Kevin, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(4).addDestination(Destination.DEPARTMENT, "Infectious Diseases ");
        mMainHospital.getFloor(4).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN, "Clark, Roger, DO");
        mMainHospital.getFloor(4).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN, "Cohen, Jeffrey, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN, "McGowan, Katherine, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Allergy");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Cardiology");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Endocrinology");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Gastroenterology");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Geriatrics/Senior Health");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Hematology");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Medical Specialties");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Pulmonary");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Renal");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Sleep Medicine");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Ash, Samuel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Bachman, William, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Batool-Anwar, Salma, MD, MPH");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Bonaca, Marc, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Budhiraja, Rohit, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Cardet, Juan Carlos, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Cardin, Kristin, NP");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Chan, Walter, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Connell, Nathan, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "D'Ambrosio, Carolyn, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Dave, Jatin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Epstein, Lawrence, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Fanta, Christopher, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Halperin, Florencia, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Hentschel, Dirk, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Hsu, Joyce, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Lilly, Leonard Stuart, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Parnes, Aric, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Quan, Stuart F., MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Ramirez, Alberto, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Romano, Keith, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Ruff, Christian, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Saldana, Fidencio, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Schissel, Scott, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Shah, Amil, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Sheth, Samira, NP");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Smith, Colleen, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Stephens, Kelly, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Sweeney, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Tucker, J. Kevin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Voiculescu, Adina, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Wellman, David, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "White, David, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Wickner, Paige, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Angell, Trevor, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.DEPARTMENT, "Brigham and Women's Primary Physicians");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.DEPARTMENT, "Headache Center");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.DEPARTMENT, "John R. Graham Headache Center");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.DEPARTMENT, "Neurology");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Bernstein, Carolyn, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Burch, Rebecca, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Cochrane, Thomas, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Friedman, Pamela, PsyD, ABPP");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Loder, Elizabeth, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Mathew, Paul, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN, "Rizzoli, Paul, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(7).addDestination(Destination.PHYSICIAN, "Cua, Christopher, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(7).addDestination(Destination.PHYSICIAN, "Lahive, Karen, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(7).addDestination(Destination.PHYSICIAN, "Tarpy, Robert, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(8).addDestination(Destination.DEPARTMENT, "Mohs and Dermatologic Surgery");
        mMainHospital.getFloor(4).getLocationNodes().get(8).addDestination(Destination.PHYSICIAN, "Tarpy, Robert, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.DEPARTMENT, "Men's Health Center");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Ruiz, Emily, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Schmults, Chrysalyne, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Chang, Steven, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Kathrins, Martin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Malone, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "McDonald, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "O'Leary, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Steele, Graeme, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(10).addDestination(Destination.DEPARTMENT, "Brigham and Women's Primary Physicians");
        mMainHospital.getFloor(4).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN, "Goldman, Jill, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN, "Lilienfeld, Armin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN, "Owens, Lisa Michelle, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(11).addDestination(Destination.SERVICE, "Doherty Conference Room");
        mMainHospital.getFloor(4).getLocationNodes().get(12).addDestination(Destination.SERVICE, "Interpreter Services");
        mMainHospital.getFloor(4).getLocationNodes().get(13).addDestination(Destination.SERVICE, "Mary Ann Tynan Conference Rooms");
        mMainHospital.getFloor(4).getLocationNodes().get(14).addDestination(Destination.SERVICE, "Medical Library");
        mMainHospital.getFloor(4).getLocationNodes().get(15).addDestination(Destination.SERVICE, "Medical Records");
        mMainHospital.getFloor(4).getLocationNodes().get(16).addDestination(Destination.SERVICE, "Sadowsky Conference Room");
        mMainHospital.getFloor(4).getLocationNodes().get(17).addDestination(Destination.SERVICE, "Saslow Conference Room");
        mMainHospital.getFloor(4).getLocationNodes().get(18).addDestination(Destination.SERVICE, "Social Work");

        mMainHospital.getFloor(4).getLocationNodes().get(19).addDestination(Destination.BATHROOM, "Floor 4 Bathroom");

        mMainHospital.getFloor(4).getLocationNodes().get(20).addDestination(Destination.ELEVATOR, "Hillside Elevator");
        mMainHospital.getFloor(4).getLocationNodes().get(21).addDestination(Destination.ELEVATOR, "Atrium Elevator");


        //FLOOR 5
        mMainHospital.getFloor(5).addNode(new Location(40, 10));//get(0) 5South
        mMainHospital.getFloor(5).addNode(new Location(40, 20));//get(1) 5North
        mMainHospital.getFloor(5).addNode(new Location(40, 30));//get(2) 5A
        mMainHospital.getFloor(5).addNode(new Location(40, 40));//get(3) 5B
        mMainHospital.getFloor(5).addNode(new Location(40, 50));//get(4) 5C
        mMainHospital.getFloor(5).addNode(new Location(40, 60));//get(5) 5D
        mMainHospital.getFloor(5).addNode(new Location(40, 70));//get(6) 5F
        mMainHospital.getFloor(5).addNode(new Location(40, 80));//get(7) 5G
        mMainHospital.getFloor(5).addNode(new Location(40, 90));//get(8) 5H
        mMainHospital.getFloor(5).addNode(new Location(40, 100));//get(9) 5I
        mMainHospital.getFloor(5).addNode(new Location(40, 110));//get(10) 5J
        mMainHospital.getFloor(5).addNode(new Location(40, 120));//get(11) 5M

        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Foot and Ankle Center");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Hand and Upper Extremity Service Neurosurgery");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Orthopedics Center");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.DEPARTMENT, "Spine Center");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Alqueza, Arnold, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Altschul, Nomee, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Bhattacharyya, Shamik, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Blazar, Phil, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Bluman, Eric, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Bono, Christopher, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Brick, Gregory, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Carleen, Mary Anne, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Chiodo, Christopher, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Cosgrove, Garth Rees, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Dawson, Courtney, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Drew, Michael, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Dyer, George, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Earp, Brandon, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Ermann, Joerg, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Fitz, Wolfgang, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Groff, Michael, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN, "Harris, Mitchel, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Hartigan, Joseph, DPM");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Higgins, Laurence, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Issa, Mohammed, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Lu, Yi, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Matzkin, Elizabeth, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Pingeton, Mallory, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Schoenfeld, Andrew, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Smith, Jeremy, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Taylor, Cristin, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Tenforde, Adam, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Vigneau, Shari, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Whitlock, Kaitlyn, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Zampini, Jay, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Issa, Mohammed, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Isaac, Zacharia, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Nelson, Ehren, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(0).addDestination(Destination.PHYSICIAN,"Yong, Jason, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT, "ICU");
        mMainHospital.getFloor(5).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT,"Inpatient Hemodialysis");
        mMainHospital.getFloor(5).getLocationNodes().get(1).addDestination(Destination.DEPARTMENT,"Outpatient Infusion Center");

        mMainHospital.getFloor(5).getLocationNodes().get(2).addDestination(Destination.DEPARTMENT, "Nutrition Clinic");
        mMainHospital.getFloor(5).getLocationNodes().get(2).addDestination(Destination.PHYSICIAN, "Oliveira, Nancy, MS, RDN, LDN");

        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.DEPARTMENT, "Boston ENT Associates");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Groden, Joseph, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Innis, William, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN, "Kessler, Joshua, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN,"Mason, William, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN,"Paperno, Halie, Au.D, CCC-A");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN,"Samara, Mariah, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(3).addDestination(Destination.PHYSICIAN,"Stone, Rebecca, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(4).addDestination(Destination.DEPARTMENT, "Orthopaedics Associates");
        mMainHospital.getFloor(5).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN,"Barr, Joseph Jr., MD");
        mMainHospital.getFloor(5).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN,"Butler, Matthew, DPM");
        mMainHospital.getFloor(5).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN,"Kornack, Fulton, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN,"Savage, Robert, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(4).addDestination(Destination.PHYSICIAN,"Webber, Anthony, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Center for Metabolic Health and Bariatric Surgery");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Colorectal Surgery");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "General Surgery");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Nutrition - Weight Loss Surgery");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Psychology - Weight Loss Surgery");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Surgical Specialties");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.DEPARTMENT, "Vascular Surgery");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Andromalos, Laura, RD, LDN ");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Ariagno, Meghan, RD, LDN");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN, "Belkin, Michael, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Davidson, Paul, PhD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Hartman, Katy, MS, RD, LDN ");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Irani, Jennifer, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Isom, Kellene, MS, RN, LDN");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Kenney, Pardon, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Kleifield, Allison, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Matthews, Robert, PA-C");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Melnitchouk, Neyla, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Nehs, Matthew, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Rangel, Erika, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Reil, Erin, RD, LDN");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Robinson, Malcolm, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Sheu, Eric, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Shoji, Brent, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Spector, David, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Tavakkoli, Ali, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(5).addDestination(Destination.PHYSICIAN,"Vernon, Ashley, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN,"Warth, James, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(6).addDestination(Destination.PHYSICIAN,"Warth, Maria, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(7).addDestination(Destination.DEPARTMENT,"Brigham Dermatology Associates");
        mMainHospital.getFloor(5).getLocationNodes().get(7).addDestination(Destination.PHYSICIAN,"Balash, Eva, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(7).addDestination(Destination.PHYSICIAN,"Divito, Sherrie, MD, PhD");
        mMainHospital.getFloor(5).getLocationNodes().get(7).addDestination(Destination.PHYSICIAN,"Frangos, Jason, MD");


        mMainHospital.getFloor(5).getLocationNodes().get(8).addDestination(Destination.PHYSICIAN, "Family Care Associates");
        mMainHospital.getFloor(5).getLocationNodes().get(8).addDestination(Destination.PHYSICIAN,"Monaghan, Colleen, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(8).addDestination(Destination.PHYSICIAN,"O'Hare, Kitty, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(8).addDestination(Destination.PHYSICIAN,"Sharma, Niraj, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(8).addDestination(Destination.PHYSICIAN,"Joyce, Eileen, LICSW");

        mMainHospital.getFloor(5).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Bana, Dhirendra, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Cahan, David, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(9).addDestination(Destination.PHYSICIAN, "Gopal, Malavalli, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.DEPARTMENT, "Brigham and Women's Primary Physicians\n");
        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN,"Berman, Stephanie, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN,"Healey, Michael, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN,"Laskowski, Karl, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN,"Litwak, Katy, LICSW");
        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN,"Miatto, Orietta, MD");
        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.PHYSICIAN,"Wagle, Neil, MD");

        mMainHospital.getFloor(5).getLocationNodes().get(10).addDestination(Destination.DEPARTMENT, "Sleep Testing Center");




        return mMainHospital;
    }



    public Floor getCurrentFloor() {

        return currentFloor;
    }
    public void setCurrentFloor(Floor currentFloor) {

        this.currentFloor = currentFloor;

    }
}


