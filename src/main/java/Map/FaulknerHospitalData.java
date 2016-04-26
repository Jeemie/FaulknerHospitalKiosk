
package Map;


import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Exceptions.NodeDoesNotExistException;

/**
 * Created by maryannoconnell on 4/21/16.
 */

public class FaulknerHospitalData {


    private static Map map;

    /**
     * // TODO refactor - currently used to create any empty building
     * Data for starter map
     */
    public static Map starterMap() {

        map = new Map("Faulkner Hospital Map");

        // ----- MAIN HOSPITAL -----

        // Add main hospital building to map and set it as current building
        map.addBuilding("Faulkner Hospital");
        Building mMainHospital = map.getMapBuildings().get(0);
        map.setCurrentBuilding(mMainHospital);

        // Main Hospital Building Floors
        map.addFloor("Floor 1", "floor1.png"); // Index 0


        Floor f1 = map.getCurrentBuilding().getFloors().get(0);

        // FLOOR 1

        // Set current floor, then set start node
        map.setCurrentFloor(f1);

        map.addLocationNode("Audiology", new Location(1000, 500), ImageType.WAITINGROOM); //TODO fix location
        map.addDestination("Audiology Destination", DestinationType.DEPARTMENT);
        map.addLocationNode("Cardiac", new Location(20, 20), ImageType.WAITINGROOM); // TODO fix location
        map.addDestination("Cardiac Rehabilitation", DestinationType.DEPARTMENT);
        map.addLocationNode("Preop", new Location(1036, 885), ImageType.WAITINGROOM);
        map.addDestination("Center for Preoperative Evaluation", DestinationType.DEPARTMENT);
        map.addLocationNode("ER", new Location(1380, 906), ImageType.WAITINGROOM);
        map.addDestination("Emergency Room", DestinationType.DEPARTMENT);
        map.addLocationNode("GI", new Location(1249, 640), ImageType.WAITINGROOM);
        map.addDestination("GI Endoscopy", DestinationType.DEPARTMENT);
        map.addLocationNode("Lab", new Location(30, 30), ImageType.WAITINGROOM);
        map.addDestination("Laboratory", DestinationType.DEPARTMENT);
        map.addLocationNode("Finance", new Location(1230, 934), ImageType.SERVICE);
        map.addDestination("Patient Financial Services", DestinationType.DEPARTMENT);
        map.addLocationNode("Radiology", new Location(900, 900), ImageType.WAITINGROOM);
        map.addDestination("Radiology", DestinationType.DEPARTMENT);
        map.addLocationNode("Test", new Location(1150, 906), ImageType.WAITINGROOM);
        map.addDestination("Special Testing", DestinationType.DEPARTMENT);
        map.addLocationNode("Family", new Location(1308, 945), ImageType.WAITINGROOM);
        map.addDestination("Taiclet Family Center", DestinationType.DEPARTMENT);



        // TODO change to Kiosk - starting at a waiting room currently
        map.setStartLocationNode(f1.getLocationNodes().get(0));

        //TODO replace with actual values
        // Random edges for testing:
        try {
            // Add edge from Audiology to Cardiac Rehabilitation
            f1.getLocationNodes().get(0).addEdge(f1.getLocationNodes().get(1));

        } catch (NodeDoesNotExistException e) {

            System.err.println("Couldn't draw path from Audiology to Cardiac Rehabilatation\n");
            e.printStackTrace();

        }


        map.addFloor("Floor 2", "floor2.png"); // Index 1
        Floor f2 = map.getCurrentBuilding().getFloors().get(1);

        // FLOOR 2

        map.addLocationNode("Bathroom", new Location(10, 10), ImageType.WAITINGROOM);
        map.addDestination("Audiology", DestinationType.DEPARTMENT);
        map.addLocationNode("Cardiac", new Location(20, 20), ImageType.WAITINGROOM);
        map.addDestination("Cardiac Rehabilitation", DestinationType.DEPARTMENT);
        map.addLocationNode("Preop", new Location(1036, 885), ImageType.WAITINGROOM);
        map.addDestination("Center for Preoperative Evaluation", DestinationType.DEPARTMENT);
        map.addLocationNode("ER", new Location(1380, 906), ImageType.WAITINGROOM);
        map.addDestination("Emergency Room", DestinationType.DEPARTMENT);
        map.addLocationNode("GI", new Location(1249, 640), ImageType.WAITINGROOM);
        map.addDestination("GI Endoscopy", DestinationType.DEPARTMENT);
        map.addLocationNode("Lab", new Location(30, 30), ImageType.WAITINGROOM);
        map.addDestination("Laboratory", DestinationType.DEPARTMENT);
        map.addLocationNode("Finance", new Location(1230, 934), ImageType.SERVICE);
        map.addDestination("Patient Financial Services", DestinationType.DEPARTMENT);
        map.addLocationNode("Radiology", new Location(900, 900), ImageType.WAITINGROOM);
        map.addDestination("Radiology", DestinationType.DEPARTMENT);
        map.addLocationNode("Test", new Location(1150, 906), ImageType.WAITINGROOM);
        map.addDestination("Special Testing", DestinationType.DEPARTMENT);
        map.addLocationNode("Family", new Location(1308, 945), ImageType.WAITINGROOM);
        map.addDestination("Taiclet Family Center", DestinationType.DEPARTMENT);

/*
        mMainHospital.getFloor(1).addNode(new Location(1090, 1100)); //get(10) Info

        mMainHospital.getFloor(1).addNode(new Location(1210, 1145)); //get(12) Cafe
        mMainHospital.getFloor(1).addNode(new Location(1050, 1300)); //get(13) Valet
        mMainHospital.getFloor(1).addNode(new Location(1080, 1100)); //get(14) kiosk
        mMainHospital.getFloor(1).addNode(new Location(1140, 934)); //get(15) Bathroom
        mMainHospital.getFloor(1).addNode(new Location(1089, 376)); //get(16) H Elevatoe
        mMainHospital.getFloor(1).addNode(new Location(1054, 1000)); //get(17) A Elevatoe
*/

        /*
        map.addDestinationOld(DestinationType.SERVICE, "Information");
        map.addDestinationOld(DestinationType.SERVICE, "Admitting/Registration");
        map.addDestinationOld(DestinationType.SERVICE, "Atrium Cafe");
        map.addDestinationOld(DestinationType.SERVICE, "Starbucks");
        map.addDestinationOld(DestinationType.SERVICE, "Valet Parking");

        map.addDestinationOld(DestinationType.KIOSK, "Kiosk");

        map.addDestinationOld(DestinationType.BATHROOM, "Floor 1 Bathroom");

        map.addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");
        map.addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");


        //FLOOR 2
        mMainHospital.getFloor(2).addNode(new Location(1120, 1100)); //get(0) 2A
        mMainHospital.getFloor(2).addNode(new Location(955, 1100)); //get(1) 2B
        mMainHospital.getFloor(2).addNode(new Location(881, 615)); //get(2) Physical Therapy
        mMainHospital.getFloor(2).addNode(new Location(1089, 301)); //get(3) Psychiatry
        mMainHospital.getFloor(2).addNode(new Location(20, 20)); //get(4) Addiction recovery
        mMainHospital.getFloor(2).addNode(new Location(10, 10)); //get(5) rehab
        mMainHospital.getFloor(2).addNode(new Location(939, 1027)); //get(6) bathroom
        mMainHospital.getFloor(2).addNode(new Location(1089, 376)); //get(7) H Elevatoe
        mMainHospital.getFloor(2).addNode(new Location(1054, 1000)); //get(8) A Elevatoe

        map.addDestinationOld(DestinationType.DEPARTMENT, "Otolarngology");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Corrales, Carleton Eduardo, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Prince, Anthony, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Roditi, Rachel, MD");

        map.addDestinationOld(DestinationType.DEPARTMENT, "Plastic Surgery");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Carty, Matthew, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Caterson, Stephanie, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Chun, Yoon Sun, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Hajj, Micheline, RN");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Halvorson, Eric, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Hergrueter, Charles, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Ingram, Abbie, PA-C");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Lafleur, Emily, PA-C");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Lahair, Tracy, PA-C");

        map.addDestinationOld(DestinationType.DEPARTMENT, "Physical Therapy");
        map.addDestinationOld(DestinationType.DEPARTMENT, "Psychology");
        map.addDestinationOld(DestinationType.DEPARTMENT, "Addiction Recovery Program");
        map.addDestinationOld(DestinationType.DEPARTMENT, "Rehabilitation Services");

        map.addDestinationOld(DestinationType.BATHROOM, "Floor 2 Bathroom ");

        map.addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");
        map.addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");


        */
/* Unknown Locction
        map.addDestinationOld(DestinationType.PHYSICIAN, "Berman, Dan, LICSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Cotter, Lindsay, LCSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Doherty, Meghan, LCSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Donnelly, Kevin, PhD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Dowd, Erin, LCSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Ecker, Vivian, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Fromson, John, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN,"Haimovici, Florina, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Howard, Neal Anthony, LICSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Humbert, Timberly, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Keller, Beth, RN, PsyD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Lai, Leonard, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Leone, Amanda, LICSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Mariano, Timothy, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Matwin, Sonia, PhD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Perry, David, LICSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Rodriguez, Claudia, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Samadi, Farrah, NP");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Schoenfeld, Paul, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Stevens, Erin, LICSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Stewart, Carl, MEd, LADC I");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Trumble, Julia, LICSW");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Yudkoff, Benjamin, MD");
        map.addDestinationOld(DestinationType.PHYSICIAN, "Issa, Mohammed, MD");
*//*


        //FLOOR 3
        mMainHospital.getFloor(3).addNode(new Location(1154, 1105));//get(0) 3A
        mMainHospital.getFloor(3).addNode(new Location(999, 1140));//get(1) 3B
        mMainHospital.getFloor(3).addNode(new Location(951, 1122));//get(2) 3C
        mMainHospital.getFloor(3).addNode(new Location(30, 40));//get(3) ATM
        mMainHospital.getFloor(3).addNode(new Location(1143, 756));//get(4) Auditorium
        mMainHospital.getFloor(3).addNode(new Location(1034, 800));//get(5) Cafeteria
        mMainHospital.getFloor(3).addNode(new Location(1158, 225));//get(6) Chapel
        mMainHospital.getFloor(3).addNode(new Location(1080, 580));//get(7) Gift Shop
        mMainHospital.getFloor(3).addNode(new Location(30, 90));//get(8) Patient Relations
        mMainHospital.getFloor(3).addNode(new Location(948, 225));//get(9) Volunteer Services
        mMainHospital.getFloor(3).addNode(new Location(1060, 256));//get(10) Kiosk
        mMainHospital.getFloor(3).addNode(new Location(1053, 1102));//get(11) Bathrooms near atrium
        mMainHospital.getFloor(3).addNode(new Location(1103, 297));//get(12) Bathrooms near kiosk
        mMainHospital.getFloor(3).addNode(new Location(1186, 821));//get(13) Bathrooms near auditorium
        mMainHospital.getFloor(3).addNode(new Location(1089, 376));//get(14) H Elevatoe
        mMainHospital.getFloor(3).addNode(new Location(1054, 1000));//get(15) A Elevatoe

        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.DEPARTMENT, "Roslindale Pediatric Associates");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Byrne, Jennifer, RN, CPNP");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Grossi, Lisa, RN, MS, CPNP");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Keller, Elisabeth, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Malone, Linda, DNP, RN, CPNP");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Morrison, Beverly, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "O'Connor, Elizabeth, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Saluti, Andrew, DO");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Scheff, David, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Stacks, Robert, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Tunick, Mitchell, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Viola, Julianne, MD");

        mMainHospital.getFloor(3).getLocationNodes().get(1.addDestinationOld(DestinationType.DEPARTMENT, "Eye Care Specialists");
        mMainHospital.getFloor(3).getLocationNodes().get(1.addDestinationOld(DestinationType.DEPARTMENT, "Suburban Eye Specialists");
        mMainHospital.getFloor(3).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Dann, Harriet, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Frangieh, George, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Micley, Bruce, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Patten, James, MD");

        mMainHospital.getFloor(3).getLocationNodes().get(2.addDestinationOld(DestinationType.DEPARTMENT, "Obstetrics and Gynecology Associates");
        mMainHospital.getFloor(3).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Greenberg, James Adam, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Miner, Julie, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Nadarajah, Sarah, WHNP");
        mMainHospital.getFloor(3).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Schueler, Leila, MD");
        mMainHospital.getFloor(3).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Smith, Shannon, MD");

        mMainHospital.getFloor(3).getLocationNodes().get(3.addDestinationOld(DestinationType.SERVICE, "ATM");
        mMainHospital.getFloor(3).getLocationNodes().get(4.addDestinationOld(DestinationType.SERVICE, "Huvos Auditorium");
        mMainHospital.getFloor(3).getLocationNodes().get(5.addDestinationOld(DestinationType.SERVICE, "Cafeteria");
        mMainHospital.getFloor(3).getLocationNodes().get(6.addDestinationOld(DestinationType.SERVICE, "Chapel");
        mMainHospital.getFloor(3).getLocationNodes().get(7.addDestinationOld(DestinationType.SERVICE, "Gift Shop");
        mMainHospital.getFloor(3).getLocationNodes().get(8.addDestinationOld(DestinationType.SERVICE, "Patient Relations");
        mMainHospital.getFloor(3).getLocationNodes().get(9.addDestinationOld(DestinationType.SERVICE, "Volunteer Services");

        mMainHospital.getFloor(3).getLocationNodes().get(10.addDestinationOld(DestinationType.KIOSK, "Kiosk");

        mMainHospital.getFloor(3).getLocationNodes().get(11.addDestinationOld(DestinationType.BATHROOM, "Floor 3 Bathroom");
        mMainHospital.getFloor(3).getLocationNodes().get(12.addDestinationOld(DestinationType.BATHROOM, "Floor 3 Bathroom");
        mMainHospital.getFloor(3).getLocationNodes().get(13.addDestinationOld(DestinationType.BATHROOM, "Floor 3 Bathroom");

        mMainHospital.getFloor(3).getLocationNodes().get(14.addDestinationOld(DestinationType.ELEVATOR, " Hillside Elevator");
        mMainHospital.getFloor(3).getLocationNodes().get(15.addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevatoe");


        //FLOOR 4
        mMainHospital.getFloor(4).addNode(new Location(40, 10));//get(0)
        mMainHospital.getFloor(4).addNode(new Location(40, 20));//get(1)
        mMainHospital.getFloor(4).addNode(new Location(40, 30));//get(2)
        mMainHospital.getFloor(4).addNode(new Location(40, 40));//get(3)
        mMainHospital.getFloor(4).addNode(new Location(40, 50));//get(4)
        mMainHospital.getFloor(4).addNode(new Location(40, 60));//get(5)
        mMainHospital.getFloor(4).addNode(new Location(40, 70));//get(6)
        mMainHospital.getFloor(4).addNode(new Location(40, 80));//get(7)
        mMainHospital.getFloor(4).addNode(new Location(40, 90));//get(8)
        mMainHospital.getFloor(4).addNode(new Location(40, 100));//get(9)
        mMainHospital.getFloor(4).addNode(new Location(40, 110));//get(10)
        mMainHospital.getFloor(4).addNode(new Location(40, 120));//get(11)
        mMainHospital.getFloor(4).addNode(new Location(40, 130));//get(12)
        mMainHospital.getFloor(4).addNode(new Location(40, 140));//get(13)
        mMainHospital.getFloor(4).addNode(new Location(40, 150));//get(14)
        mMainHospital.getFloor(4).addNode(new Location(40, 160));//get(15)
        mMainHospital.getFloor(4).addNode(new Location(40, 170));//get(16)
        mMainHospital.getFloor(4).addNode(new Location(40, 180));//get(17)
        mMainHospital.getFloor(4).addNode(new Location(40, 190));//get(18)
        mMainHospital.getFloor(4).addNode(new Location(40, 200));//get(19)
        mMainHospital.getFloor(4).addNode(new Location(40, 210));//get(20)
        mMainHospital.getFloor(4).addNode(new Location(40, 220));//get(21)
        mMainHospital.getFloor(4).addNode(new Location(40, 220));//get(22)


        //Floor 4
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Caplan, Laura, PA-C");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Cohen, Natalie, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Copello, Maria, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Healy, Barbara, RN");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Lauretti, Linda, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "McCord, Laura, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Nuspl, Kristen, PA-C");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Oliver, Lynn, RN");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Walsh Samp, Kathy, LICSW");
        mMainHospital.getFloor(4).getLocationNodes().get(0.addDestinationOld(DestinationType.PHYSICIAN, "Welker, Roy, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.DEPARTMENT, "Gastroenterology Associates");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Conant, Alene, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Drewniak, Stephen, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Homenko, Daria, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Lo, Amy, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Matloff, Daniel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Mutinga, Muthoka, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Preneta, Ewa, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(1.addDestinationOld(DestinationType.PHYSICIAN, "Smith, Benjamin, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.DEPARTMENT, "Neurology/Sleep Division");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.DEPARTMENT, "Sleep Disorders Service");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Horowitz, Sandra, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Mullally, William, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Novak, Peter, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Pavlova, Milena, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Pilgrim, David, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Vardeh, Daniel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Weisholtz, Daniel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(2.addDestinationOld(DestinationType.PHYSICIAN, "Whitman, Gregory, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(3.addDestinationOld(DestinationType.DEPARTMENT, "Arthritis Center");
        mMainHospital.getFloor(4).getLocationNodes().get(3.addDestinationOld(DestinationType.DEPARTMENT, "Rheumatology Center");
        mMainHospital.getFloor(4).getLocationNodes().get(3.addDestinationOld(DestinationType.PHYSICIAN, "Hoover, Paul, MD, PhD");
        mMainHospital.getFloor(4).getLocationNodes().get(3.addDestinationOld(DestinationType.PHYSICIAN, "Pariser, Kenneth, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(3.addDestinationOld(DestinationType.PHYSICIAN, "Todd, Derrick, MD, PhD");
        mMainHospital.getFloor(4).getLocationNodes().get(3.addDestinationOld(DestinationType.PHYSICIAN, "Wei, Kevin, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(4.addDestinationOld(DestinationType.DEPARTMENT, "Infectious Diseases ");
        mMainHospital.getFloor(4).getLocationNodes().get(4.addDestinationOld(DestinationType.PHYSICIAN, "Clark, Roger, DO");
        mMainHospital.getFloor(4).getLocationNodes().get(4.addDestinationOld(DestinationType.PHYSICIAN, "Cohen, Jeffrey, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(4.addDestinationOld(DestinationType.PHYSICIAN, "McGowan, Katherine, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Allergy");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Cardiology");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Endocrinology");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Gastroenterology");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Geriatrics/Senior Health");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Hematology");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Medical Specialties");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Pulmonary");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Renal");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.DEPARTMENT, "Sleep Medicine");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Ash, Samuel, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Bachman, William, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Batool-Anwar, Salma, MD, MPH");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Bonaca, Marc, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Budhiraja, Rohit, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Cardet, Juan Carlos, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Cardin, Kristin, NP");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Chan, Walter, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Connell, Nathan, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "D'Ambrosio, Carolyn, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Dave, Jatin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Epstein, Lawrence, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Fanta, Christopher, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Halperin, Florencia, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Hentschel, Dirk, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Hsu, Joyce, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Lilly, Leonard Stuart, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Parnes, Aric, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Quan, Stuart F., MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Ramirez, Alberto, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Romano, Keith, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Ruff, Christian, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Saldana, Fidencio, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Schissel, Scott, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Shah, Amil, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Sheth, Samira, NP");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Smith, Colleen, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Stephens, Kelly, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Sweeney, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Tucker, J. Kevin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Voiculescu, Adina, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Wellman, David, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "White, David, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Wickner, Paige, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(5.addDestinationOld(DestinationType.PHYSICIAN, "Angell, Trevor, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.DEPARTMENT, "Headache Center");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.DEPARTMENT, "John R. Graham Headache Center");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.DEPARTMENT, "Neurology");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Bernstein, Carolyn, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Burch, Rebecca, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Cochrane, Thomas, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Friedman, Pamela, PsyD, ABPP");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Loder, Elizabeth, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Mathew, Paul, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(6.addDestinationOld(DestinationType.PHYSICIAN, "Rizzoli, Paul, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(7.addDestinationOld(DestinationType.PHYSICIAN, "Cua, Christopher, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(7.addDestinationOld(DestinationType.PHYSICIAN, "Lahive, Karen, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(7.addDestinationOld(DestinationType.PHYSICIAN, "Tarpy, Robert, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(8.addDestinationOld(DestinationType.DEPARTMENT, "Mohs and Dermatologic Surgery");
        mMainHospital.getFloor(4).getLocationNodes().get(8.addDestinationOld(DestinationType.PHYSICIAN, "Tarpy, Robert, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.DEPARTMENT, "Men's Health Center");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.DEPARTMENT, "Ruiz, Emily, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.DEPARTMENT, "Schmults, Chrysalyne, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.PHYSICIAN, "Chang, Steven, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.PHYSICIAN, "Kathrins, Martin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.PHYSICIAN, "Malone, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.PHYSICIAN, "McDonald, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.PHYSICIAN, "O'Leary, Michael, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9.addDestinationOld(DestinationType.PHYSICIAN, "Steele, Graeme, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(10.addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
        mMainHospital.getFloor(4).getLocationNodes().get(10.addDestinationOld(DestinationType.PHYSICIAN, "Goldman, Jill, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(10.addDestinationOld(DestinationType.PHYSICIAN, "Lilienfeld, Armin, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(10.addDestinationOld(DestinationType.PHYSICIAN, "Owens, Lisa Michelle, MD");

        mMainHospital.getFloor(4).getLocationNodes().get(11.addDestinationOld(DestinationType.SERVICE, "Doherty Conference Room");
        mMainHospital.getFloor(4).getLocationNodes().get(12.addDestinationOld(DestinationType.SERVICE, "Interpreter Services");
        mMainHospital.getFloor(4).getLocationNodes().get(13.addDestinationOld(DestinationType.SERVICE, "Mary Ann Tynan Conference Rooms");
        mMainHospital.getFloor(4).getLocationNodes().get(14.addDestinationOld(DestinationType.SERVICE, "Medical Library");
        mMainHospital.getFloor(4).getLocationNodes().get(15.addDestinationOld(DestinationType.SERVICE, "Medical Records");
        mMainHospital.getFloor(4).getLocationNodes().get(16.addDestinationOld(DestinationType.SERVICE, "Sadowsky Conference Room");
        mMainHospital.getFloor(4).getLocationNodes().get(17.addDestinationOld(DestinationType.SERVICE, "Saslow Conference Room");
        mMainHospital.getFloor(4).getLocationNodes().get(18.addDestinationOld(DestinationType.SERVICE, "Social Work");

        mMainHospital.getFloor(4).getLocationNodes().get(20.addDestinationOld(DestinationType.BATHROOM, "Floor 4 Bathroom");

        mMainHospital.getFloor(4).getLocationNodes().get(21.addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");
        mMainHospital.getFloor(4).getLocationNodes().get(22.addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");


    */

         return map;
    }


    private static void addDestinationOld(DestinationType destinationType, String name) {

        map.addDestination(name, destinationType);

    }

}
