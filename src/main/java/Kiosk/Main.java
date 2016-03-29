package Kiosk;/**
 * Created by Matt on 3/26/2016.
 */

import Map.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;
    public static Map map = new Map("graph.DGS");
    private Parent root;

    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        primaryStage = stage;

        root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main");
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
