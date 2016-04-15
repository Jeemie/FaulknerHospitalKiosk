package Map.EventHandlers;

import Kiosk.Controllers.AdminDashboardSubControllers.AdminSubControllerLoader;
import Map.BuildingState;
import Map.LocationNode;
import Utils.FixedSizedStack;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractMap;
import java.util.Map;

/**
 * An event handler for the Location Node when it has been clicked in the Admin Panel.
 */
public class LocationNodeClickedEventHandler implements EventHandler<MouseEvent> {

    // Location Node associated with the circle event handler
    private final LocationNode locationNode;

    // Stack of the past 10 node click actions
    private static FixedSizedStack<Map.Entry<LocationNode, BuildingState>> previousActions = new FixedSizedStack<>(10);

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeClickedEventHandler.class);


    /**
     * Default constructor for this class.
     *
     * @param locationNode The location Node that is associated with the circle.
     */
    public LocationNodeClickedEventHandler(LocationNode locationNode) {

        this.locationNode = locationNode;

        LOGGER.info("Created new LocationNodeClickedHandler for the Node: " + this.locationNode.toString());

    }

    /**
     * Handler for when the node's circle is clicked.
     *
     * @param event Event that describes the scenario in which the circle was clicked.
     */
    @Override
    public void handle(MouseEvent event) {

        LOGGER.info("Node " + this.locationNode.toString() + " was clicked with the state " +
                this.locationNode.getState().toString());

        // Switch statement that is dependant of the state of the node
        switch(locationNode.getState()) {

            case NORMAL:

                break;


            case ADDNODE:

                break;


            case REMOVENODE:

                LOGGER.info("Deleting Node: " + toString());

                this.locationNode.deleteNode();

                break;


            case ADDADJACENTNODE:

                if (previousActions.isEmpty()) {

                    break;

                }

                Map.Entry<LocationNode, BuildingState> lastAction = previousActions.peek();

                if (lastAction.getValue() == BuildingState.ADDADJACENTNODE) {

                    LOGGER.info("Adding a connection between " + this.locationNode.toString() + " and " +
                            lastAction.getKey().toString());

                    this.locationNode.addAdjacentNode(lastAction.getKey());

                    this.locationNode.setState(BuildingState.NORMAL);

                }

                break;


            case MODIFYDESTINATIONS:

                this.modifyNodeView();

                break;


            case SETFLOORSTARTNODE:

                this.locationNode.setAsFloorStartNode();

                break;


            case MOVENODE:

                break;



            default:

                break;

        }

        // Add current action
        Map.Entry<LocationNode, BuildingState> entry =
                new AbstractMap.SimpleEntry<LocationNode, BuildingState>(this.locationNode, this.locationNode.getState());
        previousActions.push(entry);

    }

    /**
     * Creates a new window where the the Admin can modify the current node.
     */
    private void modifyNodeView() {

        LOGGER.info("Opening Admin department Editor");

        // TODO change the AdminDepartmentPanel to an inline editor in the admin panel (Left listview spot)

//        try {
//
//            Stage stage;
//            stage = new Stage();
//
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Kiosk/Views/AdminDepartmentPanel.fxml"));
//            Parent root = loader.load();
//            AdminDepartmentPanelController controller = loader.getController();
//            controller.setNode(this.locationNode);
//
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.show();
//
//        } catch (IOException e) {
//
//            LOGGER.info("Unable to open the modify node view ", e);
//
//        }
        AdminSubControllerLoader loader = new AdminSubControllerLoader();

        loader.setStackPane(this.locationNode.getCurrentFloor().getStackPane());
        loader.setCurrentLocationNode(this.locationNode);
        loader.load();

    }

}
