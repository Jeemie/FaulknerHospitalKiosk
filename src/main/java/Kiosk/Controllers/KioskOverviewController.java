package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.Enums.DestinationType;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KioskOverviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KioskOverviewController.class);
    // Reference to the main application.
    private KioskApp kioskApp;

    @FXML
    private TextField searchTextBox;

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

        this.searchTextBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.ENTER)) {

                    kioskApp.showSearch(searchTextBox.getText());

                }

            }

        });

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
     * The flag is set to 0 to show the physician list
     */
    @FXML
    private void handlePhysicians() {
        kioskApp.showDirectory(DestinationType.PHYSICIAN);
    }


    /**
     * Called when the user clicks the Departments button.
     * The flag is set to 1 to show the department list
     */
    @FXML
    private void handleDepartments() {
        kioskApp.showDirectory(DestinationType.DEPARTMENT);
    }

    /**
     * Called when the user clicks the Services button.
     * The flag is set to 2 to show the services list
     */
    @FXML
    private void handleServices() {
        kioskApp.showDirectory(DestinationType.SERVICE);
    }


}