package Kiosk.Controllers;

import Map.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import Kiosk.KioskApp;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.handler.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SearchController {

    // Reference to the main application.

    private boolean okClicked = false;
    private KioskApp kioskApp;
    private Building building;
    private String inValue;
    List<String> searchResult;


    Timer timer = new Timer("A Timer");
    Timer atimer = new Timer();
    int counter = 0;
    private volatile boolean running = true;

    @FXML
    ToggleButton departments;
    @FXML
    ToggleButton physicians;
    @FXML
    ToggleButton services;


    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {

                System.out.println("I'm at Start Timer");
                counter++;
        }
    };


    ObservableList<String> destinations = FXCollections.observableArrayList();
    ObservableList<String> searchResults = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listDirectory;


    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    @FXML
    private TextField searchTextBox;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (running) {
                try {
                    System.out.println(counter + " seconds have passed.");
                    if (counter == 5) {
                        System.out.println("Timeout Sucker.");
                        running = false;
                        timer.cancel();
                        atimer.cancel();
                        timerTask.cancel();
                        Platform.runLater(resetKiosk);
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    System.out.println("I'm outta here");
                    atimer.cancel();
                    timer.cancel();
                    timerTask.cancel();
                    running = false;
                    exception.printStackTrace();
                    break;
                }

            }
        }
    };

    Thread timerThread = new Thread(runnable);
    Runnable resetKiosk = new Runnable() {

        @Override
        public void run() {
            handleBack();
        }
    };

    TimerTask noFreeze = new TimerTask() {

        @Override
        public void run() {

            System.out.println("Reset");
     //       running = true;
            atimer.cancel();
        }
    };

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {


        listDirectory.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if(event.getClickCount() == 2) {
                    ArrayList<Floor> floors = building.getFloors();

                    for (Floor f : floors) {

                        ArrayList<LocationNode> nodes = f.getFloorNodes();

                        for (LocationNode n : nodes) {

                            if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {

                                timer.cancel();
                                running = false;
                                timerThread.interrupt();
                                kioskApp.showMap(n.getNodeFloor().getStartNode(), n);

                            }

                        }

                    }
                }
            }

        });


        searchTextBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.ENTER)) {

                    timer.cancel();
                    running = false;
                    timerThread.interrupt();

                    System.out.println("Merp");
                    LOGGER.info("Search Controller " + searchTextBox.getText());
                    kioskApp.showSearch(searchTextBox.getText());

                }
                else {

                    counter = 0;

                }
            }
        });


        listDirectory.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                counter = 0;
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
     * Is called by the main application to give a reference back to itself.
     *
     * @param building
     */
    public void setBuilding(Building building) {
        this.building = building;
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
    @FXML
    private void handleBack() {

        atimer.cancel();
        atimer.purge();
        timer.cancel();
        timer.purge();
        running = false;
        timerThread.interrupt();
        //timerTask.cancel();
        kioskApp.reset();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {


        //timer.cancel();
        //running = false;
        //timerThread.interrupt();
        //timerTask.cancel();
        this.handleBack();
    }

    /**
     * Called when the user clicks forward.
     */
    @FXML
    private void handleForward() {

        ArrayList<Floor> floors = building.getFloors();

        for (Floor f : floors) {

            ArrayList<LocationNode> nodes = f.getFloorNodes();

            for (LocationNode n : nodes) {

                if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {

                    timer.cancel();
                    running = false;
                    timerThread.interrupt();
                    kioskApp.showMap(n.getNodeFloor().getStartNode(), n);

                }

            }

        }

    }

    @FXML
    public void displayResult(String value) {

        // TODO Maryann was here
        //building = Map.storeMapData();
        destinations.setAll(building.getDestinations());


        searchResult = destinations.stream().filter(a -> a.contains(value)).collect(Collectors.toList());

        inValue = value;

        searchResults.setAll(searchResult);

        listDirectory.setItems(searchResults);


    }

    @FXML
    public void sortResult(Destination destinationType) {


        if (destinationType == Destination.PHYSICIAN) {

            System.out.println("HEE " + inValue);
            destinations.setAll(building.getDestinations(Destination.PHYSICIAN));
            searchResult = destinations.stream().filter(a -> a.contains(inValue)).collect(Collectors.toList());
            searchResults.setAll(searchResult);
            listDirectory.setItems(searchResults);

        }

        if (destinationType == Destination.DEPARTMENT) {
            System.out.println("GRR " + inValue);
            destinations.setAll(building.getDestinations(Destination.DEPARTMENT));
            searchResult = destinations.stream().filter(a -> a.contains(inValue)).collect(Collectors.toList());
            searchResults.setAll(searchResult);
            listDirectory.setItems(searchResults);

        }

        if (destinationType == Destination.SERVICE) {

            System.out.println("HELLO THERE " + inValue);
            destinations.setAll(building.getDestinations(Destination.SERVICE));
            searchResult = destinations.stream().filter(a -> a.contains(inValue)).collect(Collectors.toList());
            searchResults.setAll(searchResult);
            listDirectory.setItems(searchResults);

        }

    }

    @FXML
    private void sortPhysicians() {

        sortResult(Destination.PHYSICIAN);

    }

    @FXML
    private void sortDepartments() {

        sortResult(Destination.DEPARTMENT);

    }

    @FXML
    private void sortServices() {

        sortResult(Destination.SERVICE);

    }
}