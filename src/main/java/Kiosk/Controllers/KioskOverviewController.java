package Kiosk.Controllers;

import Kiosk.KioskApp;
import javafx.fxml.FXML;

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

    	kioskApp.showDirectory(0);
    }
    

    /**
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {

    	kioskApp.showDirectory(1);
    }
    
    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {

    	kioskApp.showDirectory(2);
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