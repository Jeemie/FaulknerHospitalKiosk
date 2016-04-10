package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.Building;
import Map.Destination;
import Map.Floor;
import Map.LocationNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;


public class DirectoryController {

    private boolean okClicked = false;

    // Reference to the main application.
    private KioskApp kioskApp;

    /**
     * Initialize the ListView and the list that fills it
     */
    @FXML
    private ListView<String> listDirectory;

    private ObservableList<String> currentNames = FXCollections.observableArrayList();

    private Building building;
//    private LocationNode startNode;
//    private LocationNode destinationNode;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {


        listDirectory.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                ArrayList<Floor> floors = building.getFloors();

                for (Floor f : floors) {

                    ArrayList<LocationNode> nodes = f.getFloorNodes();

                    for (LocationNode n : nodes) {

                        if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {

                            kioskApp.showMap(n.getCurrentFloor().getStartNode(), n);

                        }

                    }

                }
            }

        });
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param kioskApp
     */
    public void setKioskApp(KioskApp kioskApp) {
        this.kioskApp = kioskApp;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks enter in the search bar.
     */
    @FXML
    private void handleSearch() {
    	kioskApp.showSearch();
    }

    /**
     * Called when the user clicks the Physicians button.
     * Displays the Staff Directory
     */
    @FXML
    private void handlePhysicians() {
//        currentNames.setAll (
//                "Byrne, Jennifer, RN, CPNP",
//                "Dann, Harriet, MD",
//                "Frangieh, George, MD",
//                "Greenberg, James Adam, MD",
//                "Grossi, Lisa, RN, MS, CPNP",
//                "Keller, Elisabeth, MD",
//                "Malone, Linda, DNP, RN, CPNP",
//                "Micley, Bruce, MD",
//                "Miner, Julie, MD",
//                "Morrison, Beverly, MD",
//                "Nadarajah, Sarah, WHNP",
//                "O'Connor, Elizabeth, MD",
//                "Patten, James, MD",
//                "Saluti, Andrew, DO",
//                "Scheff, David, MD",
//                "Smith, Shannon, MD",
//                "Stacks, Robert, MD",
//                "Tunick, Mitchell, MD",
//                "Viola, Julianne, MD");
        currentNames.setAll(building.getDestinations(Destination.PHYSICIAN));
        listDirectory.setItems(currentNames);

    }

    /**
     * Called when the user clicks the Departments button.
     * Displays the Departments
     */
    @FXML
    private void handleDepartments() {
//        currentNames.setAll (
//                "Audiology ",
//                "Cardiac Rehabilitation",
//                "Center for Preoperative Evaluation",
//                "Emergency Department",
//                "Eye Care Specialists ",
//                "GI Endoscopy",
//                "Laboratory",
//                "Obstetrics and Gynecology Associates",
//                "Patient Financial Services",
//                "Radiology",
//                "Roslindale Pediatric Associates ",
//                "Suburban Eye Specialists ",
//                "Taiclet Family Center");
        currentNames.setAll(building.getDestinations(Destination.DEPARTMENT));
        listDirectory.setItems(currentNames);

    }

    /**
     * Called when the user clicks the Services button.
     * Displays the Services
     */
    @FXML
    private void handleServices() {
//        currentNames.setAll (
//                "Admitting/Registration",
//                "ATM",
//                "Atrium Café",
//                "Atrium Elevators",
//                "Atrium/Main Entrance",
//                "Cafeteria",
//                "Chapel and Chaplaincy Services",
//                "Day Surgery",
//                "Gift Shop",
//                "Hillside Elevators",
//                "Information",
//                "Kiosk Location",
//                "Patient Registration",
//                "Patient Relations",
//                "Starbucks",
//                "Valet Parking",
//                "Volunteer Services");
        currentNames.addAll(building.getDestinations());
        listDirectory.setItems(currentNames);
    }

    /**
     * Called when the user clicks back.
     */
    @FXML
    private void handleBack() {
    	kioskApp.reset();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        this.handleBack();
    }

    /**
     * Called when the user clicks Forward.
     */
    @FXML
    private void handleForward() {

//        String name;
//        name = listDirectory.getSelectionModel().getSelectedItem();
//        System.out.println(name);
//
//        kioskApp.showMap(startNode, destinationNode);
    }

   public void setList(Destination destinationType) {

        switch (destinationType) {

            case PHYSICIAN:
                currentNames.setAll(building.getBuildingDestinations(Destination.DEPARTMENT));
                listDirectory.setItems(currentNames);

            case DEPARTMENT:
                currentNames.setAll(building.getBuildingDestinations(Destination.DEPARTMENT));
                listDirectory.setItems(currentNames);

            default:
                currentNames.setAll(building.getBuildingDestinations());
                listDirectory.setItems(currentNames);

        }

    }

    public void setBuilding(Building building) {

        this.building = building;

    }

    public void setStartNode(LocationNode startNode) {

//        this.startNode = startNode;

    }

}