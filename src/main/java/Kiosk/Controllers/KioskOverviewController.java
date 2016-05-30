package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.Destination;
import Map.Enums.DestinationType;
import Map.Map;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Locale;


public class KioskOverviewController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KioskOverviewController.class);
    // Reference to the main application.
    private KioskApp kioskApp;

    private KeyCombination adminHotkey = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);

    private Map FauknerhospitalMap;


    @FXML
    private TextField searchTextBox;

    @FXML
    private MenuButton language;

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
                System.out.println(event.getCode());

                if (event.getCode().equals(KeyCode.ENTER)) {

                    kioskApp.showSearch(searchTextBox.getText());

                }
                if (event.getCode().equals(KeyCode.INSERT)) {

                    kioskApp.showAdminLogin();

                }
                if (adminHotkey.match(event)) {

                    kioskApp.showAdminLogin();

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

    @FXML
    private void changeToSpanish(){
        Locale spanish = new Locale("sp","VE");

        kioskApp.changeLanguage(spanish);


    }

    @FXML
    private void changeToEnglish(){
        Locale english = new Locale("en","US");

        kioskApp.changeLanguage(english);
    }


    @FXML
    private void changetoVietnamese(){
        Locale vietnamese = new Locale("vi","VN");

        kioskApp.changeLanguage(vietnamese);

    }

    @FXML
    private void changetoChinese(){
        Locale chinese = new Locale("zh","CN");

        kioskApp.changeLanguage(chinese);


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

    @FXML
    private void handleAbout() {
        kioskApp.showAboutPage();
    }
}