
package Map;


import Map.Enums.DestinationType;
import Map.Enums.ImageType;

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
                map.addFloor("Floor 2", "floor2.png"); // Index 1
                map.addFloor("Floor 3", "floor3.png"); // Index 2
                map.addFloor("Floor 4", "floor4.png"); // Index 3
                map.addFloor("Floor 5", "floor5.png"); // Index 4
                map.addFloor("Floor 6", "floor6.png"); // Index 5
                map.addFloor("Floor 7", "floor7.png"); // Index 6



                Floor f1 = map.getCurrentBuilding().getFloors().get(0);
                Floor f2 = map.getCurrentBuilding().getFloors().get(1);
                Floor f3 = map.getCurrentBuilding().getFloors().get(2);
                Floor f4 = map.getCurrentBuilding().getFloors().get(3);
                Floor f5 = map.getCurrentBuilding().getFloors().get(4);
                Floor f6 = map.getCurrentBuilding().getFloors().get(5);
                Floor f7 = map.getCurrentBuilding().getFloors().get(6);



// FLOOR 1
                // Set current floor, then set start node
                map.setCurrentFloor(f1);


                map.addLocationNode("Audiology",new Location(20, 10), ImageType.WAITINGROOM);        //get(0) Audiology //TODO fix location
                map.addDestination("Audiology Destination", DestinationType.DEPARTMENT);
                map.addLocationNode("Cardiac", new Location(20, 20), ImageType.WAITINGROOM); // TODO fix location
                map.addDestination("Cardiac Rehabilitation", DestinationType.DEPARTMENT);
                map.addLocationNode("Lab", new Location(30, 30), ImageType.WAITINGROOM);
                map.addDestination("Laboratory", DestinationType.DEPARTMENT);

                map.addLocationNode("Center for Preoperative Evaluation",new Location(20, 40), ImageType.WAITINGROOM);     //get(2) Center for Preoperative Evaluation
                map.addDestination("Center for Preoperative Evaluation", DestinationType.DEPARTMENT);

                map.addLocationNode("Emergency Department",new Location(20, 50), ImageType.WAITINGROOM);     //get(3) Emergency Department
                map.addDestination("Emergency Department", DestinationType.DEPARTMENT);

                map.addLocationNode("GI Endoscopy",new Location(20, 60), ImageType.WAITINGROOM);     //get(4) GI Endoscopy
                map.addDestination("GI Endoscopy", DestinationType.DEPARTMENT);

                map.addLocationNode("Admitting",new Location(20, 70), ImageType.SERVICE);     //get(5) Finance
                map.addDestination("Admitting", DestinationType.SERVICE);
                map.addDestination("Financial Counseling", DestinationType.SERVICE);
                map.addDestination("Patient Financial Services", DestinationType.SERVICE);
                map.addDestination("Patient Registration", DestinationType.SERVICE);

                map.addLocationNode("Radiology",new Location(20, 80), ImageType.WAITINGROOM); //get(6) Radiology
                map.addDestination("Radiology", DestinationType.DEPARTMENT);

                map.addLocationNode("Testing",new Location(20, 90), ImageType.WAITINGROOM);     //get(7) Test
                map.addDestination("Special Testing", DestinationType.DEPARTMENT);

                map.addLocationNode("Family Center",new Location(20, 100), ImageType.SERVICE);     //get(8) Family
                map.addDestination("Taiclet Family Center", DestinationType.DEPARTMENT);

                map.addLocationNode("Admitting",new Location(20, 110), ImageType.SERVICE);       //get(9) Admit
                addDestinationOld(DestinationType.SERVICE, "Admitting/Registration");

                map.addLocationNode("Cafe",new Location(20, 120), ImageType.SERVICE);     //get(10) Cafe
                addDestinationOld(DestinationType.SERVICE, "Atrium Cafe");
                addDestinationOld(DestinationType.SERVICE, "Starbucks");

                map.addLocationNode("Valet Parking",new Location(20, 130), ImageType.SERVICE);       //get(11) Valet
                addDestinationOld(DestinationType.SERVICE, "Valet Parking");

                map.addLocationNode("Floor 1 Kiosk",new Location(20, 140), ImageType.KIOSK);   //get(12) Floor 1 Kiosk
                addDestinationOld(DestinationType.SERVICE, "Information");
                addDestinationOld(DestinationType.KIOSK, "Kiosk");

                map.addLocationNode("Floor 1 Bathroom",new Location(20, 150), ImageType.BATHROOM);     //get(13) Floor 1 Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Floor 1 Bathroom");

                map.addLocationNode("Floor 1 Hillside Elevator",new Location(20, 160), ImageType.ELEVATOR);    //get(14) Floor 1 Hillside Elevatoe
                addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");

                map.addLocationNode("Floor 1 Atrium Elevator",new Location(20, 170), ImageType.ELEVATOR);     //get(15) Floor 1 Atrium Elevatoe
                addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");

                map.addLocationNode("Floor 1 Hillside Stairs",new Location(20, 180), ImageType.STAIRS);    //get(16) Floor 1 Hillside Stairs
                addDestinationOld(DestinationType.STAIR, "Hillside Stair");

                map.addLocationNode("Floor 1 Atrium Stairs",new Location(20, 190), ImageType.STAIRS);     //get(17) Floor 1 Atrium Stairs
                addDestinationOld(DestinationType.STAIR, "Atrium Stair");

                // TODO change to Kiosk - starting at a waiting room currently
                map.setStartLocationNode(f1.getLocationNodes().get(13));


// FLOOR 2
                // Set current floor, then set start node
                map.setCurrentFloor(f2);


               // map.addFloor("Floor 2", "floor2.png"); // Index 1
               // Floor f2 = map.getCurrentBuilding().getFloors().get(1);
/*
                map.addLocationNode("Addiction Recovery",new Location(20, 10), ImageType.WAITINGROOM);        //get(0) Addiction recovery
                addDestinationOld(DestinationType.DEPARTMENT, "Addiction Recovery Program");
                map.addLocationNode("Rehabilitation Services",new Location(20, 20), ImageType.WAITINGROOM);        //get(1) rehab
                addDestinationOld(DestinationType.DEPARTMENT, "Rehabilitation Services");
                map.addDestination("Berman, Dan, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Cotter, Lindsay, LCSW", DestinationType.PHYSICIAN);
                map.addDestination("Doherty, Meghan, LCSW", DestinationType.PHYSICIAN);
                map.addDestination("Donnelly, Kevin, PhD", DestinationType.PHYSICIAN);
                map.addDestination("Dowd, Erin, LCSW", DestinationType.PHYSICIAN);
                map.addDestination("Ecker, Vivian, MD", DestinationType.PHYSICIAN);
                map.addDestination("Fromson, John, MD", DestinationType.PHYSICIAN);
                map.addDestination("Haimovici, Florina, MD", DestinationType.PHYSICIAN);
                map.addDestination("Howard, Neal Anthony, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Humbert, Timberly, MD", DestinationType.PHYSICIAN);
                map.addDestination("Keller, Beth, RN, PsyD", DestinationType.PHYSICIAN);
                map.addDestination("Lai, Leonard, MD", DestinationType.PHYSICIAN);
                map.addDestination("Leone, Amanda, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Mariano, Timothy, MD", DestinationType.PHYSICIAN);
                map.addDestination("Matwin, Sonia, PhD", DestinationType.PHYSICIAN);
                map.addDestination("Perry, David, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Rodriguez, Claudia, MD", DestinationType.PHYSICIAN);
                map.addDestination("Samadi, Farrah, NP", DestinationType.PHYSICIAN);
                map.addDestination("Schoenfeld, Paul, MD", DestinationType.PHYSICIAN);
                map.addDestination("Stevens, Erin, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Stewart, Carl, MEd, LADC I", DestinationType.PHYSICIAN);
                map.addDestination("Trumble, Julia, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Yudkoff, Benjamin, MD", DestinationType.PHYSICIAN);
                map.addDestination("Issa, Mohammed, MD", DestinationType.PHYSICIAN);
*/
                map.addLocationNode("2A",new Location(20, 30), ImageType.WAITINGROOM);        //get(2)
                addDestinationOld(DestinationType.DEPARTMENT, "Otolarngology");
                addDestinationOld(DestinationType.PHYSICIAN, "Corrales, Carleton Eduardo, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Prince, Anthony, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Roditi, Rachel, MD");

                map.addLocationNode("2B",new Location(20, 40), ImageType.WAITINGROOM);        //get(3) 2B
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

                map.addLocationNode("Physical Therapy",new Location(20, 50), ImageType.WAITINGROOM);        //get(4)
                addDestinationOld(DestinationType.DEPARTMENT, "Physical Therapy");

                map.addLocationNode("Outpatient Psychiatry",new Location(20, 60), ImageType.WAITINGROOM);        //get(5) Psychiatry
                addDestinationOld(DestinationType.DEPARTMENT, "Outpatient Psychiatry");

                map.addLocationNode("Floor 2 Bathroom",new Location(20, 70), ImageType.BATHROOM);        //get(6) bathroom
                addDestinationOld(DestinationType.BATHROOM, "Floor 2 Bathroom");

                map.addLocationNode("Floor 2 Hillside Elevator",new Location(20, 80), ImageType.ELEVATOR);    //get(7) Hillside Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");

                map.addLocationNode("Floor 2 Atrium Elevator",new Location(20, 90), ImageType.ELEVATOR);      //get(8) A Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");

                map.addLocationNode("Floor 2 Hillside Stairs",new Location(20, 100), ImageType.STAIRS);    //get(9) Hillside Stairs
                addDestinationOld(DestinationType.STAIR, "Hillside Elevator");

                map.addLocationNode("Floor 2 Atrium Stairs",new Location(20, 110), ImageType.STAIRS);     //get(10) Atrium Stairs
                addDestinationOld(DestinationType.STAIR, "Atrium Elevator");

//FLOOR 3
                // Set current floor, then set start node
                map.setCurrentFloor(f3);
/*
                map.addLocationNode("Patient Relations",new Location(10, 90), ImageType.SERVICE);//get(0) Patient Relations
                addDestinationOld(DestinationType.SERVICE, "Patient Relations");
                map.addLocationNode("ATM",new Location(10, 40), ImageType.SERVICE);        //get(1) ATM
                addDestinationOld(DestinationType.SERVICE, "ATM");
                addDestinationOld(DestinationType.SERVICE, "Information");
*/
                map.addLocationNode("3A",new Location(20, 10), ImageType.WAITINGROOM);     //get(2) 3A
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

                map.addLocationNode("3B",new Location(20, 20), ImageType.WAITINGROOM);     //get(3)
                addDestinationOld(DestinationType.DEPARTMENT, "Eye Care Specialists");
                addDestinationOld(DestinationType.DEPARTMENT, "Suburban Eye Specialists");
                addDestinationOld(DestinationType.PHYSICIAN, "Dann, Harriet, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Frangieh, George, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Micley, Bruce, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Patten, James, MD");

                map.addLocationNode("3C",new Location(20, 30), ImageType.WAITINGROOM);     //get(4)
                addDestinationOld(DestinationType.DEPARTMENT, "Obstetrics and Gynecology Associates");
                addDestinationOld(DestinationType.PHYSICIAN, "Greenberg, James Adam, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Miner, Julie, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Nadarajah, Sarah, WHNP");
                addDestinationOld(DestinationType.PHYSICIAN, "Schueler, Leila, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Smith, Shannon, MD");

                map.addLocationNode("Auditorium",new Location(20, 40), ImageType.SERVICE);     //get(5)
                addDestinationOld(DestinationType.SERVICE, "Huvos Auditorium");

                map.addLocationNode("Cafeteria",new Location(20, 50), ImageType.SERVICE);     //get(6)
                addDestinationOld(DestinationType.SERVICE, "Cafeteria");

                map.addLocationNode("Chapel",new Location(20, 60), ImageType.SERVICE);    //get(7)
                addDestinationOld(DestinationType.SERVICE, "Chapel and Chaplaincy Services");

                map.addLocationNode("Gift Shop",new Location(20, 70), ImageType.SERVICE);     //get(8)
                addDestinationOld(DestinationType.SERVICE, "Gift Shop");

                map.addLocationNode("Volunteer Services",new Location(20, 80), ImageType.SERVICE);    //get(9)
                addDestinationOld(DestinationType.SERVICE, "Volunteer Services");

                map.addLocationNode("Floor 3 Kiosk",new Location(20, 90), ImageType.KIOSK);    //get(10)
                addDestinationOld(DestinationType.KIOSK, "Kiosk");

                map.addLocationNode("Floor 3 Atrium North Bathroom",new Location(20, 100), ImageType.BATHROOM);     //get(11)
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 3 Atrium South Bathroom",new Location(20, 110), ImageType.BATHROOM);     //get(12)
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 3 Hillside Bathroom",new Location(20, 120), ImageType.BATHROOM);       //get(13)
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 3 Auditorium Bathroom",new Location(20, 130), ImageType.BATHROOM);       //get(14)
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 3 Hillside Elevator",new Location(20, 140), ImageType.ELEVATOR);    //get(15)
                addDestinationOld(DestinationType.ELEVATOR, " Hillside Elevator");

                map.addLocationNode("Floor 3 Atrium Elevator",new Location(20, 150), ImageType.ELEVATOR);     //get(16)
                addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");

                map.addLocationNode("Floor 3 Hillside Stairs",new Location(20, 160), ImageType.STAIRS);    //get(17)
                addDestinationOld(DestinationType.STAIR, " Hillside Stair");

                map.addLocationNode("Floor 3 Atrium Stairs",new Location(20, 170), ImageType.STAIRS);     //get(18)
                addDestinationOld(DestinationType.STAIR, "Atrium Stair");


//FLOOR 4
                // Set current floor, then set start node
                map.setCurrentFloor(f4);
/*
                map.addLocationNode("Interpreter Services",new Location(40, 130), ImageType.SERVICE);  //get(0) Interpreter Services
                addDestinationOld(DestinationType.SERVICE, "Interpreter Services");
                map.addLocationNode("Medical Library",new Location(40, 150), ImageType.SERVICE);  //get(1) Medical Library
                addDestinationOld(DestinationType.SERVICE, "Medical Library");
                map.addLocationNode("Medical Records",new Location(40, 160), ImageType.SERVICE);  //get(2) Medical Records
                addDestinationOld(DestinationType.SERVICE, "Medical Records");
                map.addLocationNode("Sadowsky Conference Room",new Location(40, 170), ImageType.SERVICE);  //get(3) Sadowsky Conference Room
                addDestinationOld(DestinationType.SERVICE, "Sadowsky Conference Room");
                map.addLocationNode("Saslow Conference Room",new Location(40, 180), ImageType.SERVICE);  //get(4) Saslow Conference Room
                addDestinationOld(DestinationType.SERVICE, "Saslow Conference Room");
                map.addLocationNode("Social Work",new Location(40, 190), ImageType.SERVICE);  //get(5) Social Work
                addDestinationOld(DestinationType.SERVICE, "Social Work");
*/
                map.addLocationNode("4A",new Location(20, 10), ImageType.WAITINGROOM);     //get(6) 4A
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

                map.addLocationNode("4B",new Location(20, 20), ImageType.WAITINGROOM);     //get(7) 4B
                addDestinationOld(DestinationType.DEPARTMENT, "Gastroenterology Associates");
                addDestinationOld(DestinationType.PHYSICIAN, "Conant, Alene, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Drewniak, Stephen, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Homenko, Daria, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Lo, Amy, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Matloff, Daniel, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Mutinga, Muthoka, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Preneta, Ewa, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Smith, Benjamin, MD");

                map.addLocationNode("4C",new Location(20, 30), ImageType.WAITINGROOM);     //get(8) 4C
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

                map.addLocationNode("4D",new Location(20, 40), ImageType.WAITINGROOM);     //get(9) 4D
                addDestinationOld(DestinationType.DEPARTMENT, "Arthritis Center");
                addDestinationOld(DestinationType.DEPARTMENT, "Rheumatology Center");
                addDestinationOld(DestinationType.PHYSICIAN, "Hoover, Paul, MD, PhD");
                addDestinationOld(DestinationType.PHYSICIAN, "Pariser, Kenneth, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Todd, Derrick, MD, PhD");
                addDestinationOld(DestinationType.PHYSICIAN, "Wei, Kevin, MD");

                map.addLocationNode("4F",new Location(20, 50), ImageType.WAITINGROOM);      //get(10) 4F
                addDestinationOld(DestinationType.DEPARTMENT, "Infectious Diseases ");
                addDestinationOld(DestinationType.PHYSICIAN, "Clark, Roger, DO");
                addDestinationOld(DestinationType.PHYSICIAN, "Cohen, Jeffrey, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "McGowan, Katherine, MD");

                map.addLocationNode("4G",new Location(20, 60), ImageType.WAITINGROOM);     //get(11) 4G
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

                map.addLocationNode("4H",new Location(20, 70), ImageType.WAITINGROOM);     //get(12) 4H
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

                map.addLocationNode("4I",new Location(20, 80), ImageType.WAITINGROOM);     //get(13) 4I
                addDestinationOld(DestinationType.PHYSICIAN, "Cua, Christopher, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Lahive, Karen, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Tarpy, Robert, MD");

                map.addLocationNode("4J",new Location(20, 90), ImageType.WAITINGROOM);    //get(14) 4J
                addDestinationOld(DestinationType.DEPARTMENT, "Mohs and Dermatologic Surgery");
                addDestinationOld(DestinationType.PHYSICIAN, "Tarpy, Robert, MD");

                map.addLocationNode("4N",new Location(20, 100), ImageType.WAITINGROOM);     //get(15) 4N
                addDestinationOld(DestinationType.DEPARTMENT, "Men's Health Center");
                addDestinationOld(DestinationType.DEPARTMENT, "Ruiz, Emily, MD");
                addDestinationOld(DestinationType.DEPARTMENT, "Schmults, Chrysalyne, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Chang, Steven, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Kathrins, Martin, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Malone, Michael, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "McDonald, Michael, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "O'Leary, Michael, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Steele, Graeme, MD");

                map.addLocationNode("4S",new Location(20, 110), ImageType.WAITINGROOM);    //get(16) 4S
                addDestinationOld(DestinationType.DEPARTMENT, "Brigham and Women's Primary Physicians");
                addDestinationOld(DestinationType.PHYSICIAN, "Goldman, Jill, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Lilienfeld, Armin, MD");
                addDestinationOld(DestinationType.PHYSICIAN, "Owens, Lisa Michelle, MD");

                map.addLocationNode("Doherty Conference Room",new Location(20, 120), ImageType.SERVICE);    //get(17) Doherty Conference Room
                addDestinationOld(DestinationType.SERVICE, "Doherty Conference Room");

                map.addLocationNode("Mary Ann Tynan Conference Rooms",new Location(20, 130), ImageType.SERVICE);     //get(18) Mary Ann Tynan Conference Rooms
                addDestinationOld(DestinationType.SERVICE, "Mary Ann Tynan Conference Rooms");

                map.addLocationNode("Floor 4 Atrium North Bathroom",new Location(20, 140), ImageType.BATHROOM);     //get(19) Floor 4 Atrium Left Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 4 Atrium South Bathroom",new Location(20, 150), ImageType.BATHROOM);     //get(20) Floor 4 Atrium Right Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 4 North Bathroom",new Location(20, 160), ImageType.BATHROOM);    //get(21) Floor 4 North Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 4 Hillside Elevator",new Location(20, 170), ImageType.ELEVATOR);    //get(22) Hillside Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");

                map.addLocationNode("Floor 4 Atrium Elevator",new Location(20, 180), ImageType.ELEVATOR);     //get(23) Atrium Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");

                map.addLocationNode("Floor 4 Hillside Stair",new Location(20, 190), ImageType.STAIRS);    //get(24) Hillside Stair
                addDestinationOld(DestinationType.STAIR, "Hillside Stair");

                map.addLocationNode("Floor 4 Atrium Stair",new Location(20, 200), ImageType.STAIRS);     //get(25) Atrium Stair
                addDestinationOld(DestinationType.STAIR, "Atrium Stair");


//FLOOR 5
                // Set current floor, then set start node
                map.setCurrentFloor(f5);

                map.addLocationNode("5South",new Location(40, 10), ImageType.WAITINGROOM);//get(0) 5South
                map.addDestination("Foot and Ankle Center", DestinationType.DEPARTMENT);
                map.addDestination("Hand and Upper Extremity Service", DestinationType.DEPARTMENT);
                map.addDestination("Neurosurgery", DestinationType.DEPARTMENT);
                map.addDestination("rthopedics Center", DestinationType.DEPARTMENT);
                map.addDestination("Spine Center", DestinationType.DEPARTMENT);
                map.addDestination("Alqueza, Arnold, MD", DestinationType.PHYSICIAN);
                map.addDestination("Altschul, Nomee, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Bhattacharyya, Shamik, MD", DestinationType.PHYSICIAN);
                map.addDestination("Blazar, Phil, MD", DestinationType.PHYSICIAN);
                map.addDestination("Bluman, Eric, MD", DestinationType.PHYSICIAN);
                map.addDestination("Bono, Christopher, MD", DestinationType.PHYSICIAN);
                map.addDestination("Brick, Gregory, MD", DestinationType.PHYSICIAN);
                map.addDestination("Carleen, Mary Anne, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Chiodo, Christopher, MD", DestinationType.PHYSICIAN);
                map.addDestination("Cosgrove, Garth Rees, MD", DestinationType.PHYSICIAN);
                map.addDestination("Dawson, Courtney, MD", DestinationType.PHYSICIAN);
                map.addDestination("Drew, Michael, MD", DestinationType.PHYSICIAN);
                map.addDestination("Dyer, George, MD", DestinationType.PHYSICIAN);
                map.addDestination("Earp, Brandon, MD", DestinationType.PHYSICIAN);
                map.addDestination("Ermann, Joerg, MD", DestinationType.PHYSICIAN);
                map.addDestination("Fitz, Wolfgang, MD", DestinationType.PHYSICIAN);
                map.addDestination("Groff, Michael, MD", DestinationType.PHYSICIAN);
                map.addDestination("Harris, Mitchel, MD", DestinationType.PHYSICIAN);
                map.addDestination("Hartigan, Joseph, DPM", DestinationType.PHYSICIAN);
                map.addDestination("Higgins, Laurence, MD", DestinationType.PHYSICIAN);
                map.addDestination("Issa, Mohammed, MD", DestinationType.PHYSICIAN);
                map.addDestination("Lu, Yi, MD", DestinationType.PHYSICIAN);
                map.addDestination("Matzkin, Elizabeth, MD", DestinationType.PHYSICIAN);
                map.addDestination("Pingeton, Mallory, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Schoenfeld, Andrew, MD", DestinationType.PHYSICIAN);
                map.addDestination("Smith, Jeremy, MD", DestinationType.PHYSICIAN);
                map.addDestination("Taylor, Cristin, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Tenforde, Adam, MD", DestinationType.PHYSICIAN);
                map.addDestination("Vigneau, Shari, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Whitlock, Kaitlyn, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Zampini, Jay, MD", DestinationType.PHYSICIAN);
                map.addDestination("Issa, Mohammed, MD", DestinationType.PHYSICIAN);
                map.addDestination("Isaac, Zacharia, MD", DestinationType.PHYSICIAN);
                map.addDestination("Nelson, Ehren, MD", DestinationType.PHYSICIAN);
                map.addDestination("Yong, Jason, MD\n", DestinationType.PHYSICIAN);

                map.addLocationNode("5North",new Location(40, 20), ImageType.WAITINGROOM);//get(1) 5North
                map.addDestination("ICU", DestinationType.DEPARTMENT);
                map.addDestination("Inpatient Hemodialysis", DestinationType.DEPARTMENT);
                map.addDestination("Outpatient Infusion Center", DestinationType.DEPARTMENT);

                map.addLocationNode("5A",new Location(40, 30), ImageType.WAITINGROOM);//get(2) 5A
                map.addDestination("Nutrition Clinic", DestinationType.DEPARTMENT);
                map.addDestination("Oliveira, Nancy, MS, RDN, LDN", DestinationType.PHYSICIAN);

                map.addLocationNode("5B",new Location(40, 40), ImageType.WAITINGROOM);//get(3) 5B
                map.addDestination("Boston ENT Associates", DestinationType.DEPARTMENT);
                map.addDestination("Groden, Joseph, MD", DestinationType.PHYSICIAN);
                map.addDestination("Innis, William, MD", DestinationType.PHYSICIAN);
                map.addDestination("Kessler, Joshua, MD", DestinationType.PHYSICIAN);
                map.addDestination("Mason, William, MD", DestinationType.PHYSICIAN);
                map.addDestination("Paperno, Halie, Au.D, CCC-A", DestinationType.PHYSICIAN);
                map.addDestination("Samara, Mariah, MD", DestinationType.PHYSICIAN);
                map.addDestination("Stone, Rebecca, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5C",new Location(40, 50), ImageType.WAITINGROOM);//get(4) 5C
                map.addDestination("Orthopaedics Associates", DestinationType.DEPARTMENT);
                map.addDestination("Barr, Joseph Jr., MD", DestinationType.PHYSICIAN);
                map.addDestination("Butler, Matthew, DPM", DestinationType.PHYSICIAN);
                map.addDestination("Kornack, Fulton, MD", DestinationType.PHYSICIAN);
                map.addDestination("Savage, Robert, MD", DestinationType.PHYSICIAN);
                map.addDestination("Webber, Anthony, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5D",new Location(40, 60), ImageType.WAITINGROOM);//get(5) 5D
                map.addDestination("Center for Metabolic Health and Bariatric Surgery", DestinationType.DEPARTMENT);
                map.addDestination("Colorectal Surgery", DestinationType.DEPARTMENT);
                map.addDestination("General Surgery  ", DestinationType.DEPARTMENT);
                map.addDestination("Nutrition - Weight Loss Surgery", DestinationType.DEPARTMENT);
                map.addDestination("Psychology - Weight Loss Surgery", DestinationType.DEPARTMENT);
                map.addDestination("Surgical Specialties", DestinationType.DEPARTMENT);
                map.addDestination("Vascular Surgery  ", DestinationType.DEPARTMENT);
                map.addDestination("Andromalos, Laura, RD, LDN ", DestinationType.PHYSICIAN);
                map.addDestination("Ariagno, Meghan, RD, LDN", DestinationType.PHYSICIAN);
                map.addDestination("Belkin, Michael, MD", DestinationType.PHYSICIAN);
                map.addDestination("Davidson, Paul, PhD", DestinationType.PHYSICIAN);
                map.addDestination("Hartman, Katy, MS, RD, LDN", DestinationType.PHYSICIAN);
                map.addDestination("Irani, Jennifer, MD", DestinationType.PHYSICIAN);
                map.addDestination("Isom, Kellene, MS, RN, LDN", DestinationType.PHYSICIAN);
                map.addDestination("Kenney, Pardon, MD", DestinationType.PHYSICIAN);
                map.addDestination("Kleifield, Allison, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Matthews, Robert, PA-C", DestinationType.PHYSICIAN);
                map.addDestination("Melnitchouk, Neyla, MD", DestinationType.PHYSICIAN);
                map.addDestination("Nehs, Matthew, MD", DestinationType.PHYSICIAN);
                map.addDestination("Rangel, Erika, MD", DestinationType.PHYSICIAN);
                map.addDestination("Reil, Erin, RD, LDN", DestinationType.PHYSICIAN);
                map.addDestination("Robinson, Malcolm, MD", DestinationType.PHYSICIAN);
                map.addDestination("Sheu, Eric, MD", DestinationType.PHYSICIAN);
                map.addDestination("Shoji, Brent, MD", DestinationType.PHYSICIAN);
                map.addDestination("Spector, David, MD", DestinationType.PHYSICIAN);
                map.addDestination("Tavakkoli, Ali, MD", DestinationType.PHYSICIAN);
                map.addDestination("Vernon, Ashley, MD", DestinationType.PHYSICIAN);
                map.addDestination("Angell, Trevor, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5F",new Location(40, 70), ImageType.WAITINGROOM);//get(6) 5F
                map.addDestination("Warth, James, ", DestinationType.PHYSICIAN);
                map.addDestination("Warth, Maria, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5G",new Location(40, 80), ImageType.WAITINGROOM);//get(7) 5G
                map.addDestination("Brigham Dermatology Associates", DestinationType.DEPARTMENT);
                map.addDestination("Balash, Eva, MD", DestinationType.PHYSICIAN);
                map.addDestination("Divito, Sherrie, MD, PhD", DestinationType.PHYSICIAN);
                map.addDestination("Frangos, Jason, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5H",new Location(40, 90), ImageType.WAITINGROOM);//get(8) 5H
                map.addDestination("Family Care Associates", DestinationType.DEPARTMENT);
                map.addDestination("Monaghan, Colleen, MD", DestinationType.PHYSICIAN);
                map.addDestination("O'Hare, Kitty, MD", DestinationType.PHYSICIAN);
                map.addDestination("Sharma, Niraj, MD", DestinationType.PHYSICIAN);
                map.addDestination("Joyce, Eileen, LICSW", DestinationType.PHYSICIAN);

                map.addLocationNode("5I",new Location(40, 100), ImageType.WAITINGROOM);//get(9) 5I
                map.addDestination("Bana, Dhirendra, ", DestinationType.PHYSICIAN);
                map.addDestination("Cahan, David, MD", DestinationType.PHYSICIAN);
                map.addDestination("Gopal, Malavalli, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5J",new Location(40, 110), ImageType.WAITINGROOM);//get(10) 5J
                map.addDestination("Brigham and Women's Primary Physicians", DestinationType.DEPARTMENT);
                map.addDestination("Berman, Stephanie, MD", DestinationType.PHYSICIAN);
                map.addDestination("Healey, Michael, MD", DestinationType.PHYSICIAN);
                map.addDestination("Laskowski, Karl, MD", DestinationType.PHYSICIAN);
                map.addDestination("Litwak, Katy, LICSW", DestinationType.PHYSICIAN);
                map.addDestination("Miatto, Orietta, MD", DestinationType.PHYSICIAN);
                map.addDestination("Wagle, Neil, MD", DestinationType.PHYSICIAN);

                map.addLocationNode("5M",new Location(40, 120), ImageType.WAITINGROOM);//get(11) 5M
                map.addDestination("Sleep Testing Center  ", DestinationType.DEPARTMENT);

                map.addLocationNode("Floor 5 Atrium North Bathroom",new Location(40, 130), ImageType.BATHROOM);     //get(19) Floor 5 Atrium Left Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 5 Atrium South Bathroom",new Location(40, 140), ImageType.BATHROOM);     //get(20) Floor 5 Atrium Right Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 5 Hillside Bathroom",new Location(40, 150), ImageType.BATHROOM);    //get(21) Floor 5 North Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 5 Hillside Elevator",new Location(40, 160), ImageType.ELEVATOR);    //get(22) Hillside Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");

                map.addLocationNode("Floor 5 Atrium Elevator",new Location(40, 170), ImageType.ELEVATOR);     //get(23) Atrium Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");

                map.addLocationNode("Floor 5 Hillside Stair",new Location(40, 180), ImageType.STAIRS);    //get(24) Hillside Stair
                addDestinationOld(DestinationType.STAIR, "Hillside Stair");

                map.addLocationNode("Floor 5 Atrium Stair",new Location(40, 190), ImageType.STAIRS);     //get(25) Atrium Stair
                addDestinationOld(DestinationType.STAIR, "Atrium Stair");

 //FLOOR 6
                // Set current floor, then set start node
                map.setCurrentFloor(f6);
 /*
                map.addLocationNode("6North",new Location(40, 10), ImageType.WAITINGROOM);//get(8) 6North --No destinations
                map.addLocationNode("6South",new Location(40, 20), ImageType.WAITINGROOM);//get(8) 6South --No destinations
                map.addLocationNode("Case Management",new Location(40, 30), ImageType.WAITINGROOM);//get(8) Case Management
                map.addDestination("Case Management", DestinationType.DEPARTMENT);
                map.addLocationNode("Pariser Conference Room",new Location(40, 30), ImageType.WAITINGROOM);//get(8) Pariser Conference Room
                map.addDestination("Pariser Conference Room", DestinationType.DEPARTMENT);
*/
                map.addLocationNode("Floor 6 Hillside Bathroom",new Location(40, 10), ImageType.BATHROOM);    //get(21) Floor 6 North Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 6 Hillside Elevator",new Location(40, 20), ImageType.ELEVATOR);    //get(22) Hillside Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");

                map.addLocationNode("Floor 6 Atrium Elevator",new Location(40, 30), ImageType.ELEVATOR);     //get(23) Atrium Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Atrium Elevator");

                map.addLocationNode("Floor 6 Hillside Stair",new Location(40, 40), ImageType.STAIRS);    //get(24) Hillside Stair
                addDestinationOld(DestinationType.STAIR, "Hillside Stair");

                map.addLocationNode("Floor 6 Atrium Stair",new Location(40, 50), ImageType.STAIRS);     //get(25) Atrium Stair
                addDestinationOld(DestinationType.STAIR, "Atrium Stair");



//FLOOR 7
                // Set current floor, then set start node
                map.setCurrentFloor(f7);
/*
                map.addLocationNode("7North",new Location(40, 10), ImageType.WAITINGROOM);//get(8) 7North --No destinations
                map.addLocationNode("7South",new Location(40, 20), ImageType.WAITINGROOM);//get(8) 7South  --No destinations
                map.addLocationNode("Human Resources",new Location(40, 30), ImageType.SERVICE);//get(8) Human Resources
                map.addDestination("Human Resources", DestinationType.DEPARTMENT);
                map.addLocationNode("Pain Management Center",new Location(40, 30), ImageType.WAITINGROOM);//get(8) Pain Management Center
                map.addDestination("Pain Management Center", DestinationType.DEPARTMENT);
                map.addDestination("Issa, Mohammed, MD", DestinationType.PHYSICIAN);
                map.addDestination("Isaac, Zacharia, MD", DestinationType.PHYSICIAN);
                map.addDestination("Nelson, Ehren, MD", DestinationType.PHYSICIAN);
                map.addDestination("Yong, Jason, MD", DestinationType.PHYSICIAN);
*/

                map.addLocationNode("Floor 7 Bathroom",new Location(40, 10), ImageType.BATHROOM);    //get(21) Floor 7 North Bathroom
                addDestinationOld(DestinationType.BATHROOM, "Bathroom");

                map.addLocationNode("Floor 7 Hillside Elevator",new Location(40, 20), ImageType.ELEVATOR);    //get(22) Hillside Elevator
                addDestinationOld(DestinationType.ELEVATOR, "Hillside Elevator");

                map.addLocationNode("Floor 7 Hillside Stair",new Location(40, 30), ImageType.STAIRS);    //get(24) Hillside Stair
                addDestinationOld(DestinationType.STAIR, "Hillside Stair");

/*
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

                }*/

                return map;
        }


        private static void addDestinationOld(DestinationType destinationType, String name) {

                map.addDestination(name, destinationType);

        }

}
