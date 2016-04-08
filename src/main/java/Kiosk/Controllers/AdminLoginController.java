package Kiosk.Controllers;

import Kiosk.KioskApp;
import javafx.fxml.FXML;

public class AdminLoginController {

    private boolean okClicked = false;
 // Reference to the main application.
    private KioskApp kioskApp;

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
     * Called when the user clicks login.
     */
    @FXML
    private void handleLogin() {
    	okClicked = true;
    	kioskApp.showAdminControls();
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        kioskApp.reset();
    }

 }