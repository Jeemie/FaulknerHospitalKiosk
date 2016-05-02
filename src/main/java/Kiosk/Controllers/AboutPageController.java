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


public class AboutPageController {

    private boolean okClicked = false;

    // Reference to the main application.
    private KioskApp kioskApp;

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryController.class);

    @FXML
    private Button backButton;


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

                    if (counter == 60000) {

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

    @FXML
    private void handleBack() {
        kioskApp.reset();
    }


    public void setupListeners() {

        this.backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

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
}