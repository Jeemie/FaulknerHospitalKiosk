package Kiosk.Controllers;

import javafx.fxml.FXML;
import Kiosk.KioskApp;

public class KioskOverviewController {

    // Reference to the main application.
    private KioskApp kioskApp;

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
     * @param kioskApp
     */
    public void setKioskApp(KioskApp kioskApp) {
        this.kioskApp = kioskApp;
    }
    
    /**
     * Called when the user clicks the admin button.
     */
    @FXML
    private void handleAdmin() {
        kioskApp.showAdminLogin();
    }
    
    /**
     * Called when the user clicks the Physicians button.
     */
    @FXML
    private void handlePhysicians() {
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
    }
    
    /** 
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
    }
    
    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
    }
    
    /**
     * Called when the user clicks enter in the search bar.
     */
    @FXML
    private void handleSearch() {
    	// TODO: showSearch should have parameter for the input
    	kioskApp.showSearch();
    }
}