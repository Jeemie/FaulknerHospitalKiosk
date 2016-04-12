package Kiosk.Controllers;

import Map.Building;
import Map.Floor;
import Map.LocationNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import Kiosk.KioskApp;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;


public class MapViewController {
	
    // Reference to the main application.

    private boolean okClicked = false;
    private KioskApp kioskApp;
    private Building building;
    private LocationNode startNode;
    private LocationNode destinationNode;

    @FXML
    private StackPane imageStackPane;

    @FXML
    private Button confirmButton;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        confirmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                building.drawShortestPath(startNode, destinationNode);

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
    
    
    /**
     * Called when the user clicks back.
     */
    // TODO: handleBack should have an if statement, which will go to
    // either userUI2 or userUI3 depending on which screen userUI4 was
    // accessed from
    @FXML
    private void handleBack() {
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
    	kioskApp.reset();
    }
    
    /**
     * Called when the user clicks enter on the search bar.
     */
    @FXML
    private void handleSearch() {
    	kioskApp.showSearch();
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setDestinationNode(LocationNode destinationNode) {
        this.destinationNode = destinationNode;
        destinationNode.getCurrentFloor().drawFloorNormal(this.imageStackPane);
    }

    public void setStartNode(LocationNode startNode) {
        this.startNode = startNode;
    }

 }