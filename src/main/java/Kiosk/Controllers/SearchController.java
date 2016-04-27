package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.*;
import Map.Enums.DestinationType;
import Map.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.util.*;
import java.util.stream.Collectors;

public class SearchController {

    // Reference to the main application.

    private boolean okClicked = false;
    private KioskApp kioskApp;
    private Building building;
    private Map faulknerHospitalMap;
    private String inValue;
    List searchResult;


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

    List<Destination> allDestinations = new ArrayList<Destination>();
    List<Destination> filteredDestinations = new ArrayList<Destination>();
    ObservableList<String> destinations = FXCollections.observableArrayList();
    ObservableList<Destination> searchResults = FXCollections.observableArrayList();

    @FXML
    private ListView listDirectory;

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

                if (event.getClickCount() == 2) {
                    ArrayList<Floor> floors = building.getFloors();

                    for (Floor f : floors) {

                        ArrayList<LocationNode> nodes = f.getFloorNodes();

                        for (LocationNode n : nodes) {

//                            if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {
//
//                                timer.cancel();
//                                running = false;
//                                timerThread.interrupt();
//                                kioskApp.showMap(n.getCurrentFloor().getCurrentBuilding().getStartNode(), n);
//
//                            }

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

                } else if (event.getCode().equals(KeyCode.BACK_SPACE)) {

                    String backSpaced = searchTextBox.getText();

                    if(backSpaced.length() != 0) {
                        backSpaced = backSpaced.substring(0, backSpaced.length() - 1);
                    }
                    displayResult(backSpaced);
                    counter = 0;

                } else {
                    displayResult(searchTextBox.getText() + event.getText());
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

//                if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {
//
//                    timer.cancel();
//                    atimer.cancel();
//                    running = false;
//                    timerThread.interrupt();
//                    kioskApp.showMap(n.getCurrentFloor().getStartNode(), n);
//
//                }

            }

        }

    }

    @FXML
    public void displayResult(String value) {

        allDestinations = this.faulknerHospitalMap.allDirectory();

        for (Destination d : allDestinations) {
            filteredDestinations = allDestinations.stream().filter((p) -> p.getName().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toList());
        }
        inValue = value;

        Collections.sort(filteredDestinations, new Comparator<Destination>() {
            public int compare(Destination o1, Destination o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        searchResults.setAll(filteredDestinations);


        listDirectory.setItems(searchResults);

    }

    @FXML
    public void sortResult(DestinationType destinationType) {


        if (destinationType == DestinationType.PHYSICIAN) {

            counter = 0;

            allDestinations = this.faulknerHospitalMap.getPhysicianDirectory();

            for (Destination d : allDestinations) {
                filteredDestinations = allDestinations.stream().filter((p) -> p.getName().toLowerCase().contains(inValue.toLowerCase())).collect(Collectors.toList());
            }
            Collections.sort(filteredDestinations, new Comparator<Destination>() {
                public int compare(Destination o1, Destination o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            searchResults.setAll(filteredDestinations);

            searchResults.sorted();
            listDirectory.setItems(searchResults);

        }

        if (destinationType == DestinationType.DEPARTMENT) {

            counter = 0;

            allDestinations = this.faulknerHospitalMap.getDepartmentDirectory();

            for (Destination d : allDestinations) {
                filteredDestinations = allDestinations.stream().filter((p) -> p.getName().toLowerCase().contains(inValue.toLowerCase())).collect(Collectors.toList());
            }
            Collections.sort(filteredDestinations, new Comparator<Destination>() {
                public int compare(Destination o1, Destination o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            searchResults.setAll(filteredDestinations);

            listDirectory.setItems(searchResults);


        }

        if (destinationType == DestinationType.SERVICE) {

            counter = 0;

            allDestinations = this.faulknerHospitalMap.getServiceDirectory();

            for (Destination d : allDestinations) {
                filteredDestinations = allDestinations.stream().filter((p) -> p.getName().toLowerCase().contains(inValue.toLowerCase())).collect(Collectors.toList());
            }
            Collections.sort(filteredDestinations, new Comparator<Destination>() {
                public int compare(Destination o1, Destination o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            searchResults.setAll(filteredDestinations);

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

    public void setFaulknerHospitalMap(Map map) {

        this.faulknerHospitalMap = map;

    }

    public void shutOff(){
        atimer.cancel();
        atimer.purge();
        timer.cancel();
        timer.purge();
        running = false;
        timerThread.interrupt();
        kioskApp.reset();
    }
}