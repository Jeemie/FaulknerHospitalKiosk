package Kiosk.Controllers;

import javafx.fxml.FXML;
import Kiosk.KioskApp;


public class SearchController {
	
    // Reference to the main application.

    private boolean okClicked = false;
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
     * Called when the user clicks enter in the search bar.
     */
    @FXML
    private void handleSearch() {
    	// TODO: showSearch should have parameter for the input
    	kioskApp.showSearch();
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
     * Called when the user clicks forward.
     */
    @FXML
    private void handleForward() {
//    	kioskApp.showMap();
    }
    
 }