package Kiosk.Controllers;

import javafx.fxml.FXML;
import Kiosk.KioskApp;


public class AdminControlsController {
	
    

    private boolean okClicked = false;
 // Reference to the main application.
    private KioskApp kioskApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param kioskApp
     */
    public void setKioskApp(KioskApp kioskApp) {
        this.kioskApp = kioskApp;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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
     * Called when the user clicks log out.
     */
    @FXML
    private void handleLogout() {
    	kioskApp.reset();
    }

 }