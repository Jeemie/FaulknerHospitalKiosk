package Kiosk.Controllers;

import Map.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import Kiosk.KioskApp;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SearchController {

    // Reference to the main application.

    private boolean okClicked = false;
    private KioskApp kioskApp;
    private Building building;
    private String inValue;
    List<String> searchResult;



    ToggleButton departments;
    ToggleButton physicians;
    ToggleButton services;



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

                    if (counter == 60) {
                        System.out.println("Timed Out.");
                        running = false;
                        timer.cancel();
                        atimer.cancel();
                        timerTask.cancel();
                        Platform.runLater(resetKiosk);
                        break;
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    atimer.cancel();
                    timer.cancel();
                    timerTask.cancel();
                    running = false;
                   // exception.printStackTrace();
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

    ObservableList<String> destinations = FXCollections.observableArrayList();
    ObservableList<String> searchResults = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listDirectory;

    @FXML
    private TextField searchTextBox;



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
                                kioskApp.showMap(n.getCurrentFloor().getCurrentBuilding().getStartNode(), n);

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
        kioskApp.reset();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {

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
                    atimer.cancel();
                    running = false;
                    timerThread.interrupt();
                    kioskApp.showMap(n.getCurrentFloor().getStartNode(), n);

                }

            }

        }

    }

    @FXML
    public void displayResult(String value) {

        destinations.setAll(building.getDestinations());

        searchResult = destinations.stream().filter(a -> a.contains(value)).collect(Collectors.toList());

        inValue = value;

        searchResults.setAll(searchResult);

        listDirectory.setItems(searchResults);


    }

    @FXML
    public void sortResult(DestinationType destinationTypeType) {


        if (destinationTypeType == DestinationType.PHYSICIAN) {

            counter = 0;
            destinations.setAll(building.getDestinations(DestinationType.PHYSICIAN));
            searchResult = destinations.stream().filter(a -> a.contains(inValue)).collect(Collectors.toList());
            searchResults.setAll(searchResult);
            listDirectory.setItems(searchResults);

        }

        if (destinationTypeType == DestinationType.DEPARTMENT) {

            counter = 0;
            destinations.setAll(building.getDestinations(DestinationType.DEPARTMENT));
            searchResult = destinations.stream().filter(a -> a.contains(inValue)).collect(Collectors.toList());
            searchResults.setAll(searchResult);
            listDirectory.setItems(searchResults);

        }

        if (destinationTypeType == DestinationType.SERVICE) {

            counter = 0;
            destinations.setAll(building.getDestinations(DestinationType.SERVICE));
            searchResult = destinations.stream().filter(a -> a.contains(inValue)).collect(Collectors.toList());
            searchResults.setAll(searchResult);
            listDirectory.setItems(searchResults);

        }

    }

    @FXML
    private void sortPhysicians() {

        sortResult(DestinationType.PHYSICIAN);

    }

    @FXML
    private void sortDepartments() {

        sortResult(DestinationType.DEPARTMENT);

    }

    @FXML
    private void sortServices() {

        sortResult(DestinationType.SERVICE);

    }
}