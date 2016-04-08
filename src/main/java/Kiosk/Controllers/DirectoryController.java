package Kiosk.Controllers;

import Kiosk.KioskApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;



public class DirectoryController {

    private boolean okClicked = false;
    // Reference to the main application.
    private KioskApp kioskApp;



    @FXML
    private ListView<String> listDirectory;

    ObservableList<String> currentNames;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {


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
     */
    @FXML
    private void handlePhysicians() {
        System.out.println("physicians");

        currentNames.setAll (
                "Byrne, Jennifer, RN, CPNP",
                "Dann, Harriet, MD",
                "Frangieh, George, MD",
                "Greenberg, James Adam, MD",
                "Grossi, Lisa, RN, MS, CPNP",
                "Keller, Elisabeth, MD",
                "Malone, Linda, DNP, RN, CPNP",
                "Micley, Bruce, MD",
                "Miner, Julie, MD",
                "Morrison, Beverly, MD",
                "Nadarajah, Sarah, WHNP",
                "O'Connor, Elizabeth, MD",
                "Patten, James, MD",
                "Saluti, Andrew, DO",
                "Scheff, David, MD",
                "Smith, Shannon, MD",
                "Stacks, Robert, MD",
                "Tunick, Mitchell, MD",
                "Viola, Julianne, MD");

        listDirectory.setItems(currentNames);

    }

    /**
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {
        System.out.println("departments");

        currentNames.setAll (
                "Audiology ",
                "Cardiac Rehabilitation",
                "Center for Preoperative Evaluation",
                "Emergency Department",
                "Eye Care Specialists ",
                "GI Endoscopy",
                "Laboratory",
                "Obstetrics and Gynecology Associates",
                "Patient Financial Services",
                "Radiology",
                "Roslindale Pediatric Associates ",
                "Suburban Eye Specialists ",
                "Taiclet Family Center");

        listDirectory.setItems(currentNames);

    }

    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {
        System.out.println("services");

        currentNames.setAll (
                "Admitting/Registration",
                "ATM",
                "Atrium Café",
                "Atrium Elevators",
                "Atrium/Main Entrance",
                "Cafeteria",
                "Chapel and Chaplaincy Services",
                "Day Surgery",
                "Gift Shop",
                "Hillside Elevators",
                "Information",
                "Kiosk Location",
                "Patient Registration",
                "Patient Relations",
                "Starbucks",
                "Valet Parking",
                "Volunteer Services");

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

        String name;
        name = listDirectory.getSelectionModel().getSelectedItem();
        System.out.println(name);

        kioskApp.showMap();
    }

   public void setList(int flag) {

        if(flag == 0) {
            this.currentNames = FXCollections.observableArrayList(
                    "Byrne, Jennifer, RN, CPNP",
                    "Dann, Harriet, MD",
                    "Frangieh, George, MD",
                    "Greenberg, James Adam, MD",
                    "Grossi, Lisa, RN, MS, CPNP",
                    "Keller, Elisabeth, MD",
                    "Malone, Linda, DNP, RN, CPNP",
                    "Micley, Bruce, MD",
                    "Miner, Julie, MD",
                    "Morrison, Beverly, MD",
                    "Nadarajah, Sarah, WHNP",
                    "O'Connor, Elizabeth, MD",
                    "Patten, James, MD",
                    "Saluti, Andrew, DO",
                    "Scheff, David, MD",
                    "Smith, Shannon, MD",
                    "Stacks, Robert, MD",
                    "Tunick, Mitchell, MD",
                    "Viola, Julianne, MD");

            listDirectory.setItems(currentNames);
        }

       if(flag == 1) {
           this.currentNames = FXCollections.observableArrayList(
                   "Audiology ",
                   "Cardiac Rehabilitation",
                   "Center for Preoperative Evaluation",
                   "Emergency Department",
                   "Eye Care Specialists ",
                   "GI Endoscopy",
                   "Laboratory",
                   "Obstetrics and Gynecology Associates",
                   "Patient Financial Services",
                   "Radiology",
                   "Roslindale Pediatric Associates ",
                   "Suburban Eye Specialists ",
                   "Taiclet Family Center");

           listDirectory.setItems(currentNames);
       }

       if(flag == 2) {
           this.currentNames = FXCollections.observableArrayList(
                   "Admitting/Registration",
                   "ATM",
                   "Atrium Café",
                   "Atrium Elevators",
                   "Atrium/Main Entrance",
                   "Cafeteria",
                   "Chapel and Chaplaincy Services",
                   "Day Surgery",
                   "Gift Shop",
                   "Hillside Elevators",
                   "Information",
                   "Kiosk Location",
                   "Patient Registration",
                   "Patient Relations",
                   "Starbucks",
                   "Valet Parking",
                   "Volunteer Services");

           listDirectory.setItems(currentNames);
       }
    }

}