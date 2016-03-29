package Kiosk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Matt on 3/26/2016.
 */
public class DashboardController implements Initializable {

    @FXML
    Parent root;

    @FXML
    private Button openMapButton;

    @FXML
    private void handleOpenMapButton(ActionEvent event) throws IOException {

        Parent root;
        Stage stage = Main.primaryStage;

        root = FXMLLoader.load(getClass().getResource("Map.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    @FXML
    private void handleOpenEdgeMapButton(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
