package Kiosk.Controllers.AdminDashboardSubControllers;

import Kiosk.Controllers.AdminDashboardController;
import Kiosk.KioskApp;
import Map.LocationNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by matt on 4/14/16.
 */
public class AdminSubControllerLoader {

    private LocationNode currentLocationNode;
    private StackPane stackPane;
    private Node currentNode;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminSubControllerLoader.class);

    public void load() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/AdminDashboardSubViews/AdminDashboardDestinationAdd.fxml"));
            Pane pane = loader.load();

            currentNode = pane;

            AdminDashboardDestinationAddController controller = loader.getController();
            controller.setCurrentLocationNode(this.currentLocationNode);
            controller.setCurrentLocationNode(this.currentLocationNode);
            controller.setParentController(this);
            controller.setListeners();

            this.stackPane.getChildren().add(pane);

        } catch(IOException e) {

            LOGGER.error("Unable to load the fxml file", e);

        }

    }


    public void setCurrentLocationNode(LocationNode currentLocationNode) {

        this.currentLocationNode = currentLocationNode;

    }

    public void setStackPane(StackPane stackPane) {

        this.stackPane = stackPane;

    }

    public void removeFromScene() {

        if (this.currentNode != null) {

            this.stackPane.getChildren().remove(this.currentNode);

        }

    }


}
