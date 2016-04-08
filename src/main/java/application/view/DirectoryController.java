package application.view;

import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class DirectoryController {

    private boolean okClicked = false;
    // Reference to the main application.
    private MainApp mainApp;

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
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
        mainApp.showSearch();
    }

    /**
     * Called when the user clicks the Physicians button.
     */
    @FXML
    private void handlePhysicians() {
        // TODO: showDirectory should have parameter for category
        mainApp.showDirectory();
        //mainApp.showDirectory("physicians");
    }

    /**
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {
        // TODO: showDirectory should have parameter for category
        mainApp.showDirectory();
        //mainApp.showDirectory("departments");
    }

    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {
        // TODO: showDirectory should have parameter for category
        mainApp.showDirectory();
        // mainApp.showDirectory("services");
    }

    /**
     * Called when the user clicks back.
     */
    @FXML
    private void handleBack() {
        mainApp.reset();
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
        mainApp.showMap();
    }



}