
package Map;


import Map.Enums.DestinationType;
import Map.Enums.ImageType;
import Map.Exceptions.FloorDoesNotExistException;

/**
 * Created by maryannoconnell on 4/21/16.
 */

public class FaulknerHospitalData {

    /**
     * // TODO refactor - currently used to create any empty building
     * Data for starter map
     */

    public static Map starterMap(Map map) throws FloorDoesNotExistException {

        // Add main hospital building to map
        map.addBuilding("Faulkner Hospital");

        // Floors
        map.addFloor("Floor 1", "floor1.png"); // Index 0

        System.out.println("Floor added");

        Floor f1 = map.getCurrentBuilding().getFloors().get(0);

        //FLOOR 1
        f1.addLocationNode("Audiology", new Location(10, 10), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(0).addDestination("Audiology", DestinationType.DEPARTMENT);
        f1.addLocationNode("Cardiac", new Location(20, 20), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(1).addDestination("Cardiac Rehabilitation", DestinationType.DEPARTMENT);
        f1.addLocationNode("Preop", new Location(1036, 885), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(2).addDestination("Center for Preoperative Evaluation", DestinationType.DEPARTMENT);
        f1.addLocationNode("ER", new Location(1380, 906), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(3).addDestination("Emergency Room", DestinationType.DEPARTMENT);
        f1.addLocationNode("GI", new Location(1249, 640), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(4).addDestination("GI Endoscopy", DestinationType.DEPARTMENT);
        f1.addLocationNode("Lab", new Location(30, 30), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(5).addDestination("Laboratory", DestinationType.DEPARTMENT);
        f1.addLocationNode("Finance", new Location(1230, 934), ImageType.SERVICE);
        f1.getLocationNodes().get(6).addDestination("Patient Financial Services", DestinationType.DEPARTMENT);
        f1.addLocationNode("Radiology", new Location(900, 900), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(7).addDestination("Radiology", DestinationType.DEPARTMENT);
        f1.addLocationNode("Test", new Location(1150, 906), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(8).addDestination("Special Testing", DestinationType.DEPARTMENT);
        f1.addLocationNode("Family", new Location(1308, 945), ImageType.WAITINGROOM);
        f1.getLocationNodes().get(9).addDestination("Taiclet Family Center", DestinationType.DEPARTMENT);

/*
        mMainHospital.getFloor(1).addNode(new Location(1090, 1100)); //get(10) Info
        mMainHospital.getFloor(1).addNode(new Location(40, 40)); //get(11) Admit
        mMainHospital.getFloor(1).addNode(new Location(1210, 1145)); //get(12) Cafe
        mMainHospital.getFloor(1).addNode(new Location(1050, 1300)); //get(13) Valet
        mMainHospital.getFloor(1).addNode(new Location(1080, 1100)); //get(14) kiosk
        mMainHospital.getFloor(1).addNode(new Location(1140, 934)); //get(15) Bathroom
        mMainHospital.getFloor(1).addNode(new Location(1089, 376)); //get(16) H Elevatoe
        mMainHospital.getFloor(1).addNode(new Location(1054, 1000)); //get(17) A Elevatoe
*/

        /*
        mMainHospital.getFloor(1).getLocationNodes().get(10).addDestination(Destination.SERVICE, "Information");
        mMainHospital.getFloor(1).getLocationNodes().get(11).addDestination(Destination.SERVICE, "Admitting/Registration");
        mMainHospital.getFloor(1).getLocationNodes().get(12).addDestination(Destination.SERVICE, "Atrium Cafe");
        mMainHospital.getFloor(1).getLocationNodes().get(12).addDestination(Destination.SERVICE, "Starbucks");
        mMainHospital.getFloor(1).getLocationNodes().get(13).addDestination(Destination.SERVICE, "Valet Parking");

        mMainHospital.getFloor(1).getLocationNodes().get(14).addDestination(Destination.KIOSK, "Kiosk");

        mMainHospital.getFloor(1).getLocationNodes().get(15).addDestination(Destination.BATHROOM, "Floor 1 Bathroom");

        mMainHospital.getFloor(1).getLocationNodes().get(16).addDestination(Destination.ELEVATOR, "Hillside Elevator");
        mMainHospital.getFloor(1).getLocationNodes().get(17).addDestination(Destination.ELEVATOR, "Atrium Elevator");


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


        */
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
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.DEPARTMENT, "Ruiz, Emily, MD");
        mMainHospital.getFloor(4).getLocationNodes().get(9).addDestination(Destination.DEPARTMENT, "Schmults, Chrysalyne, MD");
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

        mMainHospital.getFloor(4).getLocationNodes().get(20).addDestination(Destination.BATHROOM, "Floor 4 Bathroom");

        mMainHospital.getFloor(4).getLocationNodes().get(21).addDestination(Destination.ELEVATOR, "Hillside Elevator");
        mMainHospital.getFloor(4).getLocationNodes().get(22).addDestination(Destination.ELEVATOR, "Atrium Elevator");


    */

         return map;
    }


}
