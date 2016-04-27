
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
        map.setStartLocationNode(f1.getLocationNodes().get(1));



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


        //TODO replace with actual values
        // Random edges for testing:
        try {
            // Add edge from Audiology to Cardiac Rehabilitation
            f1.getLocationNodes().get(0).addEdge(f1.getLocationNodes().get(1));
            f2.getLocationNodes().get(0).addEdge(f1.getLocationNodes().get(0));
            f2.getLocationNodes().get(1).addEdge(f2.getLocationNodes().get(0));

        } catch (NodeDoesNotExistException e) {

            System.err.println("Couldn't draw path from Audiology to Cardiac Rehabilatation\n");
            e.printStackTrace();

        }
/*
        mMainHospital.getFloor(1).addNode(new Location(1090, 1100)); //get(10) Info

        mMainHospital.getFloor(1).addNode(new Location(1210, 1145)); //get(12) Cafe
        mMainHospital.getFloor(1).addNode(new Location(1050, 1300)); //get(13) Valet
        mMainHospital.getFloor(1).addNode(new Location(1080, 1100)); //get(14) kiosk
        mMainHospital.getFloor(1).addNode(new Location(1140, 934)); //get(15) Bathroom
        mMainHospital.getFloor(1).addNode(new Location(1089, 376)); //get(16) H Elevatoe
        mMainHospital.getFloor(1).addNode(new Location(1054, 1000)); //get(17) A Elevatoe
*/


        addDestinationOld(DestinationType.SERVICE, "Information");
        addDestinationOld(DestinationType.SERVICE, "Admitting/Registration");
        addDestinationOld(DestinationType.SERVICE, "Atrium Cafe");
        addDestinationOld(DestinationType.SERVICE, "Starbucks");
        addDestinationOld(DestinationType.SERVICE, "Valet Parking");

        addDestinationOld(DestinationType.KIOSK, "Kiosk");

        addDestinationOld(DestinationType.BATHROOM, "Floor 1 Bathroom");

        addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");
        addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");


        //FLOOR 2
//        mMainHospital.getFloor(2).addNode(new Location(1120, 1100)); //get(0) 2A
//        mMainHospital.getFloor(2).addNode(new Location(955, 1100)); //get(1) 2B
//        mMainHospital.getFloor(2).addNode(new Location(881, 615)); //get(2) Physical Therapy
//        mMainHospital.getFloor(2).addNode(new Location(1089, 301)); //get(3) Psychiatry
//        mMainHospital.getFloor(2).addNode(new Location(20, 20)); //get(4) Addiction recovery
//        mMainHospital.getFloor(2).addNode(new Location(10, 10)); //get(5) rehab
//        mMainHospital.getFloor(2).addNode(new Location(939, 1027)); //get(6) bathroom
//        mMainHospital.getFloor(2).addNode(new Location(1089, 376)); //get(7) H Elevatoe
//        mMainHospital.getFloor(2).addNode(new Location(1054, 1000)); //get(8) A Elevatoe

        addDestinationOld(DestinationType.DEPARTMENT, "Otolarngology");
        addDestinationOld(DestinationType.PHYSICIAN, "Corrales, Carleton Eduardo, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Prince, Anthony, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Roditi, Rachel, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Plastic Surgery");
        addDestinationOld(DestinationType.PHYSICIAN, "Carty, Matthew, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Caterson, Stephanie, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Chun, Yoon Sun, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Hajj, Micheline, RN");
        addDestinationOld(DestinationType.PHYSICIAN, "Halvorson, Eric, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Hergrueter, Charles, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Ingram, Abbie, PA-C");
        addDestinationOld(DestinationType.PHYSICIAN, "Lafleur, Emily, PA-C");
        addDestinationOld(DestinationType.PHYSICIAN, "Lahair, Tracy, PA-C");

        addDestinationOld(DestinationType.DEPARTMENT, "Physical Therapy");
        addDestinationOld(DestinationType.DEPARTMENT, "Psychology");
        addDestinationOld(DestinationType.DEPARTMENT, "Addiction Recovery Program");
        addDestinationOld(DestinationType.DEPARTMENT, "Rehabilitation Services");

        addDestinationOld(DestinationType.BATHROOM, "Floor 2 Bathroom ");

        addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");
        addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");


        addDestinationOld(DestinationType.PHYSICIAN, "Berman, Dan, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Cotter, Lindsay, LCSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Doherty, Meghan, LCSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Donnelly, Kevin, PhD");
        addDestinationOld(DestinationType.PHYSICIAN, "Dowd, Erin, LCSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Ecker, Vivian, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Fromson, John, MD");
        addDestinationOld(DestinationType.PHYSICIAN,"Haimovici, Florina, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Howard, Neal Anthony, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Humbert, Timberly, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Keller, Beth, RN, PsyD");
        addDestinationOld(DestinationType.PHYSICIAN, "Lai, Leonard, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Leone, Amanda, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Mariano, Timothy, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Matwin, Sonia, PhD");
        addDestinationOld(DestinationType.PHYSICIAN, "Perry, David, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Rodriguez, Claudia, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Samadi, Farrah, NP");
        addDestinationOld(DestinationType.PHYSICIAN, "Schoenfeld, Paul, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Stevens, Erin, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Stewart, Carl, MEd, LADC I");
        addDestinationOld(DestinationType.PHYSICIAN, "Trumble, Julia, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Yudkoff, Benjamin, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Issa, Mohammed, MD");


        //FLOOR 3
//        mMainHospital.getFloor(3).addNode(new Location(1154, 1105));//get(0) 3A
//        mMainHospital.getFloor(3).addNode(new Location(999, 1140));//get(1) 3B
//        mMainHospital.getFloor(3).addNode(new Location(951, 1122));//get(2) 3C
//        mMainHospital.getFloor(3).addNode(new Location(30, 40));//get(3) ATM
//        mMainHospital.getFloor(3).addNode(new Location(1143, 756));//get(4) Auditorium
//        mMainHospital.getFloor(3).addNode(new Location(1034, 800));//get(5) Cafeteria
//        mMainHospital.getFloor(3).addNode(new Location(1158, 225));//get(6) Chapel
//        mMainHospital.getFloor(3).addNode(new Location(1080, 580));//get(7) Gift Shop
//        mMainHospital.getFloor(3).addNode(new Location(30, 90));//get(8) Patient Relations
//        mMainHospital.getFloor(3).addNode(new Location(948, 225));//get(9) Volunteer Services
//        mMainHospital.getFloor(3).addNode(new Location(1060, 256));//get(10) Kiosk
//        mMainHospital.getFloor(3).addNode(new Location(1053, 1102));//get(11) Bathrooms near atrium
//        mMainHospital.getFloor(3).addNode(new Location(1103, 297));//get(12) Bathrooms near kiosk
//        mMainHospital.getFloor(3).addNode(new Location(1186, 821));//get(13) Bathrooms near auditorium
//        mMainHospital.getFloor(3).addNode(new Location(1089, 376));//get(14) H Elevatoe
//        mMainHospital.getFloor(3).addNode(new Location(1054, 1000));//get(15) A Elevatoe

        addDestinationOld(DestinationType.DEPARTMENT, "Roslindale Pediatric Associates");
        addDestinationOld(DestinationType.PHYSICIAN, "Byrne, Jennifer, RN, CPNP");
        addDestinationOld(DestinationType.PHYSICIAN, "Grossi, Lisa, RN, MS, CPNP");
        addDestinationOld(DestinationType.PHYSICIAN, "Keller, Elisabeth, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Malone, Linda, DNP, RN, CPNP");
        addDestinationOld(DestinationType.PHYSICIAN, "Morrison, Beverly, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "O'Connor, Elizabeth, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Saluti, Andrew, DO");
        addDestinationOld(DestinationType.PHYSICIAN, "Scheff, David, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Stacks, Robert, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Tunick, Mitchell, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Viola, Julianne, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Eye Care Specialists");
        addDestinationOld(DestinationType.DEPARTMENT, "Suburban Eye Specialists");
        addDestinationOld(DestinationType.PHYSICIAN, "Dann, Harriet, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Frangieh, George, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Micley, Bruce, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Patten, James, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Obstetrics and Gynecology Associates");
        addDestinationOld(DestinationType.PHYSICIAN, "Greenberg, James Adam, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Miner, Julie, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Nadarajah, Sarah, WHNP");
        addDestinationOld(DestinationType.PHYSICIAN, "Schueler, Leila, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Smith, Shannon, MD");

        addDestinationOld(DestinationType.SERVICE, "ATM");
        addDestinationOld(DestinationType.SERVICE, "Huvos Auditorium");
        addDestinationOld(DestinationType.SERVICE, "Cafeteria");
        addDestinationOld(DestinationType.SERVICE, "Chapel");
        addDestinationOld(DestinationType.SERVICE, "Gift Shop");
        addDestinationOld(DestinationType.SERVICE, "Patient Relations");
        addDestinationOld(DestinationType.SERVICE, "Volunteer Services");

        addDestinationOld(DestinationType.KIOSK, "Kiosk");

        addDestinationOld(DestinationType.BATHROOM, "Floor 3 Bathroom");
        addDestinationOld(DestinationType.BATHROOM, "Floor 3 Bathroom");
        addDestinationOld(DestinationType.BATHROOM, "Floor 3 Bathroom");

        addDestinationOld(DestinationType.ELEVATOR, " Hillside Elevator");
        addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevatoe");


//        //FLOOR 4
//        mMainHospital.getFloor(4).addNode(new Location(40, 10));//get(0)
//        mMainHospital.getFloor(4).addNode(new Location(40, 20));//get(1)
//        mMainHospital.getFloor(4).addNode(new Location(40, 30));//get(2)
//        mMainHospital.getFloor(4).addNode(new Location(40, 40));//get(3)
//        mMainHospital.getFloor(4).addNode(new Location(40, 50));//get(4)
//        mMainHospital.getFloor(4).addNode(new Location(40, 60));//get(5)
//        mMainHospital.getFloor(4).addNode(new Location(40, 70));//get(6)
//        mMainHospital.getFloor(4).addNode(new Location(40, 80));//get(7)
//        mMainHospital.getFloor(4).addNode(new Location(40, 90));//get(8)
//        mMainHospital.getFloor(4).addNode(new Location(40, 100));//get(9)
//        mMainHospital.getFloor(4).addNode(new Location(40, 110));//get(10)
//        mMainHospital.getFloor(4).addNode(new Location(40, 120));//get(11)
//        mMainHospital.getFloor(4).addNode(new Location(40, 130));//get(12)
//        mMainHospital.getFloor(4).addNode(new Location(40, 140));//get(13)
//        mMainHospital.getFloor(4).addNode(new Location(40, 150));//get(14)
//        mMainHospital.getFloor(4).addNode(new Location(40, 160));//get(15)
//        mMainHospital.getFloor(4).addNode(new Location(40, 170));//get(16)
//        mMainHospital.getFloor(4).addNode(new Location(40, 180));//get(17)
//        mMainHospital.getFloor(4).addNode(new Location(40, 190));//get(18)
//        mMainHospital.getFloor(4).addNode(new Location(40, 200));//get(19)
//        mMainHospital.getFloor(4).addNode(new Location(40, 210));//get(20)
//        mMainHospital.getFloor(4).addNode(new Location(40, 220));//get(21)
//        mMainHospital.getFloor(4).addNode(new Location(40, 220));//get(22)


        //Floor 4
        addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
        addDestinationOld(DestinationType.PHYSICIAN, "Caplan, Laura, PA-C");
        addDestinationOld(DestinationType.PHYSICIAN, "Cohen, Natalie, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Copello, Maria, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Healy, Barbara, RN");
        addDestinationOld(DestinationType.PHYSICIAN, "Lauretti, Linda, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "McCord, Laura, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Nuspl, Kristen, PA-C");
        addDestinationOld(DestinationType.PHYSICIAN, "Oliver, Lynn, RN");
        addDestinationOld(DestinationType.PHYSICIAN, "Walsh Samp, Kathy, LICSW");
        addDestinationOld(DestinationType.PHYSICIAN, "Welker, Roy, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Gastroenterology Associates");
        addDestinationOld(DestinationType.PHYSICIAN, "Conant, Alene, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Drewniak, Stephen, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Homenko, Daria, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Lo, Amy, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Matloff, Daniel, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Mutinga, Muthoka, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Preneta, Ewa, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Smith, Benjamin, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Neurology/Sleep Division");
        addDestinationOld(DestinationType.DEPARTMENT, "Sleep Disorders Service");
        addDestinationOld(DestinationType.PHYSICIAN, "Horowitz, Sandra, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Mullally, William, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Novak, Peter, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Pavlova, Milena, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Pilgrim, David, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Vardeh, Daniel, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Weisholtz, Daniel, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Whitman, Gregory, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Arthritis Center");
        addDestinationOld(DestinationType.DEPARTMENT, "Rheumatology Center");
        addDestinationOld(DestinationType.PHYSICIAN, "Hoover, Paul, MD, PhD");
        addDestinationOld(DestinationType.PHYSICIAN, "Pariser, Kenneth, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Todd, Derrick, MD, PhD");
        addDestinationOld(DestinationType.PHYSICIAN, "Wei, Kevin, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Infectious Diseases ");
        addDestinationOld(DestinationType.PHYSICIAN, "Clark, Roger, DO");
        addDestinationOld(DestinationType.PHYSICIAN, "Cohen, Jeffrey, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "McGowan, Katherine, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Allergy");
        addDestinationOld(DestinationType.DEPARTMENT, "Cardiology");
        addDestinationOld(DestinationType.DEPARTMENT, "Endocrinology");
        addDestinationOld(DestinationType.DEPARTMENT, "Gastroenterology");
        addDestinationOld(DestinationType.DEPARTMENT, "Geriatrics/Senior Health");
        addDestinationOld(DestinationType.DEPARTMENT, "Hematology");
        addDestinationOld(DestinationType.DEPARTMENT, "Medical Specialties");
        addDestinationOld(DestinationType.DEPARTMENT, "Pulmonary");
        addDestinationOld(DestinationType.DEPARTMENT, "Renal");
        addDestinationOld(DestinationType.DEPARTMENT, "Sleep Medicine");
        addDestinationOld(DestinationType.PHYSICIAN, "Ash, Samuel, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Bachman, William, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Batool-Anwar, Salma, MD, MPH");
        addDestinationOld(DestinationType.PHYSICIAN, "Bonaca, Marc, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Budhiraja, Rohit, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Cardet, Juan Carlos, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Cardin, Kristin, NP");
        addDestinationOld(DestinationType.PHYSICIAN, "Chan, Walter, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Connell, Nathan, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "D'Ambrosio, Carolyn, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Dave, Jatin, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Epstein, Lawrence, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Fanta, Christopher, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Halperin, Florencia, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Hentschel, Dirk, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Hsu, Joyce, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Lilly, Leonard Stuart, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Parnes, Aric, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Quan, Stuart F., MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Ramirez, Alberto, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Romano, Keith, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Ruff, Christian, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Saldana, Fidencio, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Schissel, Scott, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Shah, Amil, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Sheth, Samira, NP");
        addDestinationOld(DestinationType.PHYSICIAN, "Smith, Colleen, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Stephens, Kelly, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Sweeney, Michael, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Tucker, J. Kevin, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Voiculescu, Adina, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Wellman, David, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "White, David, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Wickner, Paige, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Angell, Trevor, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
        addDestinationOld(DestinationType.DEPARTMENT, "Headache Center");
        addDestinationOld(DestinationType.DEPARTMENT, "John R. Graham Headache Center");
        addDestinationOld(DestinationType.DEPARTMENT, "Neurology");
        addDestinationOld(DestinationType.PHYSICIAN, "Bernstein, Carolyn, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Burch, Rebecca, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Cochrane, Thomas, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Friedman, Pamela, PsyD, ABPP");
        addDestinationOld(DestinationType.PHYSICIAN, "Loder, Elizabeth, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Mathew, Paul, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Rizzoli, Paul, MD");

        addDestinationOld(DestinationType.PHYSICIAN, "Cua, Christopher, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Lahive, Karen, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Tarpy, Robert, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Mohs and Dermatologic Surgery");
        addDestinationOld(DestinationType.PHYSICIAN, "Tarpy, Robert, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Men's Health Center");
        addDestinationOld(DestinationType.DEPARTMENT, "Ruiz, Emily, MD");
        addDestinationOld(DestinationType.DEPARTMENT, "Schmults, Chrysalyne, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Chang, Steven, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Kathrins, Martin, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Malone, Michael, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "McDonald, Michael, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "O'Leary, Michael, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Steele, Graeme, MD");

        addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
        addDestinationOld(DestinationType.PHYSICIAN, "Goldman, Jill, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Lilienfeld, Armin, MD");
        addDestinationOld(DestinationType.PHYSICIAN, "Owens, Lisa Michelle, MD");

        addDestinationOld(DestinationType.SERVICE, "Doherty Conference Room");
        addDestinationOld(DestinationType.SERVICE, "Interpreter Services");
        addDestinationOld(DestinationType.SERVICE, "Mary Ann Tynan Conference Rooms");
        addDestinationOld(DestinationType.SERVICE, "Medical Library");
        addDestinationOld(DestinationType.SERVICE, "Medical Records");
        addDestinationOld(DestinationType.SERVICE, "Sadowsky Conference Room");
        addDestinationOld(DestinationType.SERVICE, "Saslow Conference Room");
        addDestinationOld(DestinationType.SERVICE, "Social Work");

        addDestinationOld(DestinationType.BATHROOM, "Floor 4 Bathroom");

        addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");
        addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");


         return map;
    }


    private static void addDestinationOld(DestinationType destinationType, String name) {

        map.addDestination(name, destinationType);

    }

}
