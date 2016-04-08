package Kiosk.Controllers;

<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
import application.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
=======
import javafx.fxml.FXML;
import Kiosk.KioskApp;
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java


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
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
     *
     * @param mainApp
=======
     * 
     * @param kioskApp
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
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
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
        // TODO: showSearch should have parameter for the input
        mainApp.showSearch();
=======
    	// TODO: showSearch should have parameter for the input
    	kioskApp.showSearch();
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
    }

    /**
     * Called when the user clicks the Physicians button.
     */
    @FXML
    private void handlePhysicians() {
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
        // TODO: showDirectory should have parameter for category
        mainApp.showDirectory();
        //mainApp.showDirectory("physicians");
=======
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
    }

    /**
     * Called when the user clicks the Departments button.
     */
    @FXML
    private void handleDepartments() {
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
        // TODO: showDirectory should have parameter for category
        mainApp.showDirectory();
        //mainApp.showDirectory("departments");
=======
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
    }

    /**
     * Called when the user clicks the Services button.
     */
    @FXML
    private void handleServices() {
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
        // TODO: showDirectory should have parameter for category
        mainApp.showDirectory();
        // mainApp.showDirectory("services");
=======
    	// TODO: showDirectory should have parameter for category
    	kioskApp.showDirectory();
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
    }

    /**
     * Called when the user clicks back.
     */
    @FXML
    private void handleBack() {
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
        mainApp.reset();
=======
    	kioskApp.reset();
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
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
<<<<<<< HEAD:src/main/java/application/view/DirectoryController.java
        //listDirectory.getSelectionModel().getSelectedItem();
        mainApp.showMap();
=======
    	kioskApp.showMap();
>>>>>>> 8644737955f8082fc87e0d9e2c499d7a927ba83c:src/main/java/Kiosk/Controllers/DirectoryController.java
    }



}