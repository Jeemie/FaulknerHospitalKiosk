package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.*;
import Map.Enums.DestinationType;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class DirectoryController {

    private boolean okClicked = false;

    private Map faulknerHospitalMap;

    private Destination currentDestination;

    // Reference to the main application.
    private KioskApp kioskApp;

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryController.class);

    @FXML
    private TextField searchTextField;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button physiciansButton;

    @FXML
    private Button departmentsButton;

    @FXML
    private Button servicesButton;

    @FXML
    private ListView directoryListView;


    Timer timer = new Timer("A Timer");
    Timer atimer = new Timer();
    int counter = 0;
    private volatile boolean running = true;

    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            counter++;
        }
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            while (running) {

                try {

                    // Time out after 30 minutes
                    if (counter == 30000) {

                        LOGGER.info("Timed Out");

                        running = false;
                        timer.cancel();
                        atimer.cancel();
                        timerTask.cancel();
                        Platform.runLater(resetKiosk);
                        break;

                    }

                    Thread.sleep(1000);

                } catch (InterruptedException exception) {

                    LOGGER.info("Switching Views");

                    atimer.cancel();
                    timer.cancel();
                    timerTask.cancel();
                    running = false;
                    //exception.printStackTrace();
                    break;

                }

            }

        }

    };

    Thread timerThread = new Thread(runnable);

    Runnable resetKiosk = new Runnable() {

        @Override
        public void run() {

            atimer.cancel();
            atimer.purge();
            timer.cancel();
            timer.purge();
            running = false;
            timerThread.interrupt();
            kioskApp.reset();

        }

    };


    public void setupListeners() {

        this.searchTextField.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.ENTER)) {

                    timer.cancel();
                    running = false;
                    timerThread.interrupt();

                    kioskApp.showSearch(searchTextField.getText());

                } else {

                    counter = 0;

                }

            }

        });


        this.okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (currentDestination != null) {

                    LOGGER.info("Switching to the Map View");

                    timer.cancel();
                    running = false;
                    timerThread.interrupt();

                    faulknerHospitalMap.setCurrentDestination(currentDestination);

                    kioskApp.showMap();

                }

            }

        });

        this.cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                atimer.cancel();
                atimer.purge();
                timer.cancel();
                timer.purge();
                running = false;
                timerThread.interrupt();
                kioskApp.reset();

            }

        });

        this.physiciansButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                counter = 0;

                LOGGER.info("Showing the Physician Directory");

                faulknerHospitalMap.physicianDirectory();

            }

        });

        this.departmentsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                counter = 0;

                LOGGER.info("Showing the Department Directory");

                faulknerHospitalMap.departmentDirectory();

            }

        });

        this.servicesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                counter = 0;

                LOGGER.info("Showing the Services Directory");

                faulknerHospitalMap.serviceDirectory();

            }

        });

        this.directoryListView.setItems(this.faulknerHospitalMap.getDirectoryList());
        this.directoryListView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                counter = 0;

                currentDestination = ((Destination) directoryListView.getSelectionModel().getSelectedItem());

            }

        });

        timer.scheduleAtFixedRate(timerTask, 30, 1000);
        timerThread.start();


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


    public void setFaulknerHospitalMap(Map map) {

        this.faulknerHospitalMap = map;

    }

    public void setStartSelection(DestinationType destinationType) {

        switch (destinationType) {

            case PHYSICIAN:
                this.faulknerHospitalMap.physicianDirectory();
                break;

            case DEPARTMENT:
                this.faulknerHospitalMap.departmentDirectory();
                break;

            default:
                this.faulknerHospitalMap.serviceDirectory();
                break;

        }

    }

}