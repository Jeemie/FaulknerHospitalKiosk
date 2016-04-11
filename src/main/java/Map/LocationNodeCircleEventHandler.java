package Map;

import Kiosk.Controllers.AdminDepartmentPanelController;
import Utils.FixedSizedStack;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

/**
 * Created by matt on 4/10/16.
 */
public class LocationNodeCircleEventHandler implements EventHandler<MouseEvent> {

    private final LocationNode locationNode;
    private static FixedSizedStack<Map.Entry<LocationNode, BuildingState>> previousActions = new FixedSizedStack<>(10);
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeCircleEventHandler.class);

    public LocationNodeCircleEventHandler(LocationNode locationNode) {

        this.locationNode = locationNode;

    }

    @Override
    public void handle(MouseEvent event) {

        LOGGER.info("Node " + this.locationNode.toString() + " was clicked with the state " +
                this.locationNode.getState().toString());

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
        Map.Entry<LocationNode,BuildingState> entry =
                new AbstractMap.SimpleEntry<LocationNode, BuildingState>(this.locationNode, this.locationNode.getState());
        previousActions.push(entry);

    }

    /**
     * Creates a new window where the the Admin can modify the current node.
     */
    private void modifyNodeView() {

        LOGGER.info("Opening Admin department Editor");

        try {

            Stage stage;
            stage = new Stage();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Kiosk/Views/AdminDepartmentPanel.fxml"));
            Parent root = loader.load();
            AdminDepartmentPanelController controller = loader.getController();
            controller.setNode(this.locationNode);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {

            LOGGER.info("Unable to open the modify node view ", e);

        }

    }

}
