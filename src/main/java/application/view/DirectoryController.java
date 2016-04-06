package application.view;

import javafx.fxml.FXML;
import application.MainApp;


public class DirectoryController {

    private boolean okClicked = false;
    // Reference to the main application.
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
    }
    
    /** 
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {
    	// TODO: showDirectory should have parameter for category
    	mainApp.showDirectory();
    }
    
    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {
    	// TODO: showDirectory should have parameter for category
    	mainApp.showDirectory();
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
    	mainApp.showMap();
    }

 }