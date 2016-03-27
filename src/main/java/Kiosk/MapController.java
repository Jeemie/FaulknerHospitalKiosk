package Kiosk;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Matt on 3/26/2016.
 */
public class MapController implements Initializable {

    @FXML
    private AnchorPane anchor;

    @FXML
    private Canvas mapCanvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        GraphicsContext graphicsContext = mapCanvas.getGraphicsContext2D();

        anchor.setPrefWidth(Main.primaryStage.getWidth());
        anchor.setPrefHeight(Main.primaryStage.getHeight());

        DrawMap(graphicsContext);





        mapCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mapClicked(event.getX(), event.getY());
            }
        });

    }

    private void DrawMap(GraphicsContext graphicsContext) {

        mapCanvas.setWidth(Main.primaryStage.getWidth());
        mapCanvas.setHeight(Main.primaryStage.getHeight());


        double canvasWidth = graphicsContext.getCanvas().getWidth();
        double canvasHeight = graphicsContext.getCanvas().getHeight();

        Image image = new Image(getClass().getResource("f1.png").toString());

        graphicsContext.drawImage(image, 0, 0, canvasWidth, canvasHeight);
    }

    private void mapClicked(double x, double y) {

    }

}
