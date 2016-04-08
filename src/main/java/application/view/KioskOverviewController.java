package application.view;

import javafx.fxml.FXML;
import application.MainApp;

public class KioskOverviewController {

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public KioskOverviewController() {
    }

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
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Called when the user clicks the admin button.
     */
    @FXML
    private void handleAdmin() {
        mainApp.showAdminLogin();
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
        //mainApp.showDirectory("services");
    }
    
    /**
     * Called when the user clicks enter in the search bar.
     */
    @FXML
    private void handleSearch() {
    	// TODO: showSearch should have parameter for the input
    	mainApp.showSearch();
    }
}