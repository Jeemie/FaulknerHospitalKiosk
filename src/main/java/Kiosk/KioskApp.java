package Kiosk;

import Kiosk.Controllers.*;
import Map.Building;
import Map.LocationNode;
import Map.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static Map.Map.storeMapData;

public class KioskApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Building hospitalBuilding;
    private LocationNode startNode;

    private static final Logger LOGGER = LoggerFactory.getLogger(KioskApp.class);
    @FXML
    private TableView<String> listDirectory;

    @Override
    public void start(Stage primaryStage) throws UnsupportedEncodingException, MalformedURLException {

        this.primaryStage = primaryStage;
        //this.hospitalBuilding = new Building();
        this.hospitalBuilding = Map.storeMapData(); //TODO Change to map by iteration 3
        this.primaryStage.setTitle("Pathfinding Application");

        initRootLayout();
        
        showKioskOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
//            System.out.println(getClass().getResource("stylesheet.css"));
//            scene.getStylesheets().add(getClass().getResource("Controllers/stylesheet.css").toExternalForm());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the kiosk overview inside the root layout.
     */
    public void showKioskOverview() {
        try {
            // Load kiosk overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/KioskOverview.fxml"));
            AnchorPane kioskOverview = loader.load();

            // Set kiosk overview into the center of root layout.
            rootLayout.setCenter(kioskOverview);
            
         // Give the controller access to the main app.
            KioskOverviewController controller = loader.getController();
            controller.setKioskApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Changes scene to allow admins to log in
     * 
     */
    public boolean showAdminLogin() {
        try {
            // Load AdminLogin
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/AdminLoginAdam.fxml"));
            AnchorPane page = loader.load();

            // Replace KioskOverview with AdminLogin
            primaryStage.setTitle("Admin Login");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App
            AdminLoginController controller = loader.getController();
            controller.setKioskApp(this);

            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Changes screen to allow admins to edit the map
     * 
     */
    public boolean showAdminControls() {

        try {

            Stage stage;
            stage = new Stage();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/AdminPanel.fxml"));
            Parent root = loader.load();
            AdminPanelController controller = loader.getController();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            controller.setBuilding(this.hospitalBuilding);
            controller.setKioskApp(this);

            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    /**
     * Changes screen to allow users to see results of search
     * 
     */
 // TODO: showSearch should have parameter for input
    public boolean showSearch(String value) {
        try {
            // Load SearchScreen

            System.out.println(value);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/SearchScreen.fxml"));
            AnchorPane page = loader.load();

            // Replace KioskOverview with userUI3.
            primaryStage.setTitle("Search Results");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();




            // Give controller access to Main App.
            SearchController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setBuilding(hospitalBuilding);

            System.out.println(value);
            controller.displayResult(value);




            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Changes screen to allow users to select by directory
     * 
     */
    public boolean showDirectory(Destination destinationType) {

        try {
            // Load DirectoryScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/DirectoryScreen.fxml"));
            AnchorPane page = loader.load();

            // Replace KioskOverview with userUI3.
            primaryStage.setTitle("Directories");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            DirectoryController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setBuilding(hospitalBuilding);
//            controller.setStartNode(startNode);

            //set the selected directory view to appear
            controller.setList(destinationType);

            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    
    /**
     * Changes screen to allow users to view the map
     * 
     */
    // TODO: showMap should have parameter for chosen destination from previous screen
    public boolean showMap(LocationNode startNode, LocationNode destinationNode) {

        try {
            // Load MapView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/MapView.fxml"));
            AnchorPane page = loader.load();

            // Replaces previous screen with userUI4.
            primaryStage.setTitle("Map");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            MapViewController controller = loader.getController();
            controller.setStartNode(startNode);
            controller.setDestinationNode(destinationNode);

            controller.setKioskApp(this);
            controller.setBuilding(this.hospitalBuilding);

            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Resets the screen to the KioskOverview
     * 
     */
    public void reset() {
        try {
            // Load KioskOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/KioskOverview.fxml"));
            AnchorPane page = loader.load();

            // Replace previous screen with KioskOverview.

            primaryStage.setTitle("Pathfinding Application");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            KioskOverviewController controller = loader.getController();
            controller.setKioskApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setStartNode(LocationNode startNode) {

        this.startNode = startNode;

    }

}