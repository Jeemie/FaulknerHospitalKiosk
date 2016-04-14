package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class DirectoryController {

    private boolean okClicked = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryController.class);
    // Reference to the main application.
    private KioskApp kioskApp;

    @FXML
    private TextField searchTextBox;
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

                if(event.getClickCount() == 2) {
                    ArrayList<Floor> floors = building.getFloors();

                    for (Floor f : floors) {

                        ArrayList<LocationNode> nodes = f.getFloorNodes();

                        for (LocationNode n : nodes) {

                            if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {


                            kioskApp.showMap(n.getNodeFloor().getStartNode(), n);

                            }


                        }

                    }

                }
            }

        });

        this.searchTextBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.ENTER)) {
                    LOGGER.info("Directory Controller" + searchTextBox.getText());
                    kioskApp.showSearch(searchTextBox.getText());


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
        currentNames.setAll(building.getDestinations());
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

        ArrayList<Floor> floors = building.getFloors();

        for (Floor f : floors) {

            ArrayList<LocationNode> nodes = f.getFloorNodes();

            for (LocationNode n : nodes) {

                if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {

                    kioskApp.showMap(n.getNodeFloor().getStartNode(), n);

                }

            }

        }
    }

    public void setList(Destination destinationType) {

        switch (destinationType) {

            case PHYSICIAN:
                currentNames.setAll(building.getDestinations(Destination.PHYSICIAN));
                listDirectory.setItems(currentNames);

            case DEPARTMENT:
                currentNames.setAll(building.getDestinations(Destination.DEPARTMENT));
                listDirectory.setItems(currentNames);

            case SERVICE:
                currentNames.setAll(building.getDestinations(Destination.SERVICE));
                listDirectory.setItems(currentNames);

            default:
                currentNames.setAll(building.getDestinations());
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