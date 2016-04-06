package application.view;

import javafx.fxml.FXML;
import application.MainApp;


public class MapViewController {
	
    // Reference to the main application.

    private boolean okClicked = false;
    private MainApp mainApp;


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
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    
    /**
     * Called when the user clicks back.
     */
    // TODO: handleBack should have an if statement, which will go to
    // either userUI2 or userUI3 depending on which screen userUI4 was
    // accessed from
    @FXML
    private void handleBack() {
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
    	mainApp.reset();
    }
    
    /**
     * Called when the user clicks enter on the search bar.
     */
    @FXML
    private void handleSearch() {
    	mainApp.showSearch();
    }

 }