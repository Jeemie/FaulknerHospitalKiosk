package application.view;

import Map.Building;
import Map.BuildingState;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Floor;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import application.MainApp;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;


public class AdminControlsController {


    @FXML
    private StackPane mapStackPane;

    @FXML
    private Button addAdjacentNode;


    

    private boolean okClicked = false;
 // Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        Building b = new Building();
        b.setState(BuildingState.ADDNODE);
        b.addFloor(1);

        try {

            Floor f1 = b.getFloor(1);

            f1.setFloorImage(this.getClass().getResource("diagram.png"));

            f1.drawFloorAdmin(mapStackPane);


        } catch (FloorDoesNotExistException e) {

            System.out.println("FDE exception");

        }


        addAdjacentNode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                b.setState(BuildingState.ADDADJACENTNODE);
            }

        });


    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
     * Called when the user clicks log out.
     */
    @FXML
    private void handleLogout() {
    	mainApp.reset();
    }

 }