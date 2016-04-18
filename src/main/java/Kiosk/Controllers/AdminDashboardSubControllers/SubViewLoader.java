package Kiosk.Controllers.AdminDashboardSubControllers;

import Kiosk.KioskApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by matt on 4/17/16.
 */
public class SubViewLoader<T> {

    private String filePath;

    private StackPane mapStackPane;

    private Node currentNode;

    private static final Logger LOGGER = LoggerFactory.getLogger(SubViewLoader.class);

    public SubViewLoader(String filePath, StackPane mapStackPane) {

        this.filePath = filePath;
        this.mapStackPane = mapStackPane;

    }


    public T loadView() {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource(filePath));
            Pane pane = loader.load();

            currentNode = pane;

            T controller = loader.getController();

            this.mapStackPane.getChildren().add(pane);

            return controller;

        } catch(IOException e) {

            LOGGER.error("Unable to loadAddDestination fxml file", e);

        }

        return null;
    }

    public void removeFromStackPane() {

        this.mapStackPane.getChildren().remove(currentNode);

    }

    public StackPane getMapStackPane() {

        return mapStackPane;
    }
}
