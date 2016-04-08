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

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        //.sort comparator figure it out form there
        // comparator uses a generic class so you have to use <>

        ObservableList<String> names = FXCollections.observableArrayList(
                      "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
        listDirectory.setItems(names);


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
    	// TODO: showSearch should have parameter for the input
    	kioskApp.showSearch();
    }

    /**
     * Called when the user clicks the Physicians button.
     */
    @FXML
    private void handlePhysicians() {
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
        //kioskApp.showDirectory("physicians");
    }

    /**
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {
        //kioskApp.showDirectory("departments");
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
    }

    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {
        // kioskApp.showDirectory("services");
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
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

        //listDirectory.getSelectionModel().getSelectedItem();

        kioskApp.showMap();
    }

}