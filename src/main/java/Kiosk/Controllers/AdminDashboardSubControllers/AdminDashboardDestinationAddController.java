package Kiosk.Controllers.AdminDashboardSubControllers;

import Map.Enums.DestinationType;
import Map.LocationNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by matt on 4/14/16.
 */
public class AdminDashboardDestinationAddController {

    private LocationNode currentLocationNode;
    private AdminSubControllerLoader loader;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDashboardDestinationAddController.class);

    @FXML
    private ChoiceBox destinationsChoiceBox;

    @FXML
    private TextField destinationNameTextField;

    @FXML
    private Button discardDestinationButton;

    @FXML
    private Button createDestinationButton;

    public void setListeners() {

        ObservableList<DestinationType> destinationTypes = FXCollections.observableArrayList();
        destinationTypes.addAll(DestinationType.values());
        this.destinationsChoiceBox.setItems(destinationTypes);


        this.discardDestinationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                loader.removeFromScene();

            }

        });

        this.createDestinationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if ((destinationsChoiceBox.getSelectionModel().getSelectedItem() != null) &&
                        (destinationNameTextField.getText().length() > 0)) {

                    currentLocationNode.addDestination(
                            (DestinationType)destinationsChoiceBox.getSelectionModel().getSelectedItem(), destinationNameTextField.getText());

                    loader.removeFromScene();

                }

            }

        });

    }

    public void setCurrentLocationNode(LocationNode currentLocationNode) {

        this.currentLocationNode = currentLocationNode;

    }

    public void setParentController(AdminSubControllerLoader loader) {

        this.loader = loader;

    }



}
