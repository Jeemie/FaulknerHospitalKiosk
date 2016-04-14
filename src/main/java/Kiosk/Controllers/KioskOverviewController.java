package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.Destination;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KioskOverviewController {

    // Reference to the main application.
    private KioskApp kioskApp;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(KioskOverviewController.class);

    @FXML
    private Button adminButton;

    @FXML
    private Button providersButton;

    @FXML
    private Button departmentsButton;

    @FXML
    private Button servicesButton;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param kioskApp
     */
    public void setKioskApp(KioskApp kioskApp) {
        this.kioskApp = kioskApp;
    }

    /**
     * Setup the Button listeners for the Kiosk Overview
     */
    public void setListeners() {

        this.adminButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Admin Button pressed");

                kioskApp.showAdminLogin();

            }

        });

        this.providersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Providers Button pressed");

                kioskApp.showDirectory(Destination.PHYSICIAN);

            }

        });

        this.departmentsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Departments Button pressed");

                kioskApp.showDirectory(Destination.DEPARTMENT);

            }

        });

        this.servicesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Services Button pressed");

                kioskApp.showDirectory(null);

            }

        });

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