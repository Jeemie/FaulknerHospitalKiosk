package Kiosk.Controllers;

import Map.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import Kiosk.KioskApp;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchController {

    // Reference to the main application.

    private boolean okClicked = false;
    private KioskApp kioskApp;
    private Building building;

    ObservableList<String> destinations = FXCollections.observableArrayList();
    ObservableList<String> searchresults = FXCollections.observableArrayList();

    @FXML
    private ListView<String> listDirectory;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    @FXML
    private TextField searchTextBox;

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

                    LOGGER.info("Search Controller " + searchTextBox.getText());
                    kioskApp.showSearch(searchTextBox.getText());

                }

            }

        });

        listDirectory.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                ArrayList<Floor> floors = building.getFloors();

                for (Floor f : floors) {

                    ArrayList<LocationNode> nodes = f.getFloorNodes();

                    for (LocationNode n : nodes) {

                        if (n.getBuildingDestinations().contains(listDirectory.getSelectionModel().getSelectedItem())) {

                            kioskApp.showMap(n.getCurrentFloor().getStartNode(), n);

                        }

                    }

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
     * Is called by the main application to give a reference back to itself.
     *
     * @param building
     *
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
//    	kioskApp.showMap();
    }

    @FXML
    public void displayResult(String value){

        building = Map.storeMapData();
        destinations.setAll(building.getDestinations());

        //System.out.println("Hello");
        List<String> searchresult = destinations.stream().filter(a -> a.contains(value)).collect(Collectors.toList());


        searchresults.setAll(searchresult);

        listDirectory.setItems(searchresults);

    }

}