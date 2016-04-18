package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryController.class);
    // Reference to the main application.
    private KioskApp kioskApp;

    @FXML
    private TextField searchTextBox;
    /**
     * Initialize the ListView and the list that fills it
     */
    @FXML
    private ListView<String> listDirectory;

    private ObservableList<String> currentNames = FXCollections.observableArrayList();

    private Building building;
    private LocationNode startNode;
//    private LocationNode destinationNode;

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
                    System.out.println("I'm outta here");
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
            handleBack();
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

                // Double Clicking!
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {

                        // Array of all the floors so we can check each floor for the directory selection.
                        ArrayList<Floor> floors = building.getFloors();

                        // These for loops will index through all available floor  and all availible LocationNodes
                        // to see which floor the selection is stored
                        for (Floor f : floors) {
                            ArrayList<LocationNode> nodes = f.getFloorNodes();
                            for (LocationNode n : nodes) {

                                // This statement is called when we find the node which corresponds to the selection
                                if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {
                                    LocationNode startNode = n.getCurrentFloor().getStartNode();// TODO: startNode is null
                                    //   kioskApp.showMap(n.getCurrentFloor().getStartNode(), n);
                                    //  kioskApp.showMap(startNode, n);

                                    timer.cancel();
                                    running = false;
                                    timerThread.interrupt();
                                    System.out.println("woop?");
                                    kioskApp.showMap(n.getCurrentFloor().getCurrentBuilding().getStartNode(), n);

                                }
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

                } else {

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
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    /**
     * Called when the user clicks the Physicians button.
     * Displays the Staff Directory
     */
    @FXML
    private void handlePhysicians() {

        counter = 0;

        //Removes previous listings and sorts the list of Care Providers.
        currentNames.removeAll();
        currentNames.setAll(building.getDestinations(Destination.PHYSICIAN));
        currentNames.sort(String.CASE_INSENSITIVE_ORDER);
        listDirectory.setItems(currentNames);

    }

    /**
     * Called when the user clicks the Departments button.
     * Displays the Departments
     */
    @FXML
    private void handleDepartments() {
        counter = 0;

        //Removes previous listings and sorts the list of Care Providers.
        currentNames.removeAll();
        currentNames.setAll(building.getDestinations(Destination.DEPARTMENT));
        currentNames.sort(String.CASE_INSENSITIVE_ORDER);
        listDirectory.setItems(currentNames);

    }

    /**
     * Called when the user clicks the Services button.
     * Displays the Services
     */
    @FXML
    private void handleServices() {
        counter = 0;

        //Removes previous listings and sorts the list of Departments.
        currentNames.removeAll();
        currentNames.setAll(building.getDestinations(Destination.SERVICE));
        currentNames.sort(String.CASE_INSENSITIVE_ORDER);
        listDirectory.setItems(currentNames);
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
     * Called when the user clicks Forward.
     */
    @FXML
    private void handleForward() {

        ArrayList<Floor> floors = building.getFloors();

        // These for loops will index through all available floor to see which floor the double clicked
        // selection is stored
        for (Floor f : floors) {

            ArrayList<LocationNode> nodes = f.getFloorNodes();

            for (LocationNode n : nodes) {

                // This statement is called when we find the node which corresponds to the selection
                if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {

                    timer.cancel();
                    running = false;
                    timerThread.interrupt();
                    kioskApp.showMap(n.getCurrentFloor().getStartNode(), n);

                }

            }

        }
    }

    public void setList(Destination destinationType) {

        switch (destinationType) {

            case PHYSICIAN:

                counter = 0;

                //Removes previous listings and sorts the list of Care Providers.
                currentNames.removeAll();
                currentNames.setAll(building.getDestinations(Destination.PHYSICIAN));
                currentNames.sort(String.CASE_INSENSITIVE_ORDER);
                listDirectory.setItems(currentNames);
                break;

            case DEPARTMENT:

                counter = 0;

                //Removes previous listings and sorts the list of Departments
                currentNames.removeAll();
                currentNames.setAll(building.getDestinations(Destination.DEPARTMENT));
                currentNames.sort(String.CASE_INSENSITIVE_ORDER);
                listDirectory.setItems(currentNames);
                break;

            case SERVICE:

                counter = 0;

                //Removes previous listings and sorts the list of Services
                currentNames.removeAll();
                currentNames.setAll(building.getDestinations(Destination.SERVICE));
                currentNames.sort(String.CASE_INSENSITIVE_ORDER);
                listDirectory.setItems(currentNames);
                break;

            default:

                counter = 0;

                //Removes previous listings and sorts the list all destinations
                currentNames.removeAll();
                currentNames.setAll(building.getDestinations());
                currentNames.sort(String.CASE_INSENSITIVE_ORDER);
                listDirectory.setItems(currentNames);
        }

    }

    public void setBuilding(Building building) {

        this.building = building;

    }

    public void setStartNode(LocationNode startNode) {

        this.startNode = startNode;

    }

}