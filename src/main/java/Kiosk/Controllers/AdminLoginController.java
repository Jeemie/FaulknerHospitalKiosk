package Kiosk.Controllers;

import Kiosk.KioskApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminLoginController {

    private boolean okClicked = false;

    // Reference to the main application.
    private KioskApp kioskApp;

    // Logger for this classs
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginController.class);


    @FXML
    private TextField adminUsernameTextField;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;


    public void setListeners() {

        this.loginButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {


                LOGGER.info("Attempting to login");

                if (adminPasswordField.getText().equals("password") && adminUsernameTextField.getText().equals("Admin")) {

                    LOGGER.info("Logging in");

                    kioskApp.showAdminControls();

                } else {

                    LOGGER.info("Incorrect Username or Password");

                }

            }

        });

        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Leaving Admin Login screen");

                kioskApp.reset();

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
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {

        return okClicked;

    }

 }