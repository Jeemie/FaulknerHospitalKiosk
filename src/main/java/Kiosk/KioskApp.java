package Kiosk;

import Kiosk.Controllers.*;
import Map.Building;
import Map.Destination;
import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.LocationNode;
import Map.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class KioskApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    protected Map faulknerMap;
    private Building hospitalBuilding;
    private LocationNode startNode;
    private URL filePath;


    private ListView<String> listDirectory;

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {

        this.primaryStage = primaryStage;

        this.filePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
        try {

            this.hospitalBuilding = Map.storeMapData(this.filePath); //TODO Change to map by iteration 3

        } catch (DefaultFileDoesNotExistException e) {
            // Create new default file
            try {

                File newFile = new File(this.filePath.toURI());
                newFile.createNewFile();

            } catch (URISyntaxException exception) {

                exception.printStackTrace();

            } catch (IOException exception) {

                exception.printStackTrace();

            }

        }


        this.hospitalBuilding = Map.initMapComponents(this.hospitalBuilding);


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
            primaryStage.setFullScreen(true);
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
            AnchorPane kioskOverview = (AnchorPane) loader.load();

            // Set kiosk overview into the center of root layout.
            rootLayout.setCenter(kioskOverview);
            
         // Give the controller access to the main app.
            KioskOverviewController controller = loader.getController();
            controller.setKioskApp(this);
//            controller.setListeners();

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
            loader.setLocation(KioskApp.class.getResource("Views/AdminLogin.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace KioskOverview with AdminLogin
            primaryStage.setTitle("Admin Login");
            primaryStage.getScene().setRoot(page);
/*            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();*/

            // Give controller access to Main App
            AdminLoginController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setListeners();

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
            // Load AdminDashboard
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/AdminDashboard.fxml"));
            SplitPane page = (SplitPane) loader.load();

            // Replace KioskOverview with AdminLogin
            primaryStage.setTitle("Administrator Dashboard");
            primaryStage.getScene().setRoot(page);
/*            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();*/

            // Give controller access to Main App
            AdminDashboardController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setBuilding(this.hospitalBuilding);
            controller.setListeners();

//            return controller.isOkClicked();
            return false;


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
    public boolean showSearch(String searchText) {
        try {
            // Load SearchScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/SearchScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace KioskOverview with userUI3.
            primaryStage.setTitle("Search Results");
            primaryStage.getScene().setRoot(page);
/*
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();
*/

            // Give controller access to Main App.
            SearchController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setBuilding(this.hospitalBuilding);
            controller.displayResult(searchText);

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
            AnchorPane page = (AnchorPane) loader.load();

            //Replace KioskOverview with userUI3.
            primaryStage.setTitle("Directories");
            primaryStage.getScene().setRoot(page);
//            Scene scene = new Scene(page, width, height);
//            primaryStage.setScene(scene);
//            primaryStage.setFullScreen(false);
//            primaryStage.setFullScreen(true);
            //primaryStage.show();




            // Give controller access to Main App.
            DirectoryController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setBuilding(hospitalBuilding);
            controller.setStartNode(startNode);

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
            AnchorPane page = (AnchorPane) loader.load();

            // Replaces previous screen with userUI4.
            primaryStage.setTitle("Map");
            primaryStage.getScene().setRoot(page);
/*            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();*/

            // Give controller access to Main App.
            MapViewController controller = loader.getController();

            controller.setKioskApp(this);
            controller.setBuilding(this.hospitalBuilding);
            controller.setDestinationNode(destinationNode);
            controller.setStartNode(startNode);



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
            AnchorPane page = (AnchorPane) loader.load();

            // Replace previous screen with KioskOverview.
            primaryStage.setTitle("Pathfinding Application");
            primaryStage.getScene().setRoot(page);
/*            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();*/

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