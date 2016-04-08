package application;

import application.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ListView<String> listDirectory;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
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
            loader.setLocation(MainApp.class.getResource("view/KioskOverview.fxml"));
            AnchorPane kioskOverview = (AnchorPane) loader.load();

            // Set kiosk overview into the center of root layout.
            rootLayout.setCenter(kioskOverview);
            
         // Give the controller access to the main app.
            KioskOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
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
            loader.setLocation(MainApp.class.getResource("view/AdminLoginAdam.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace KioskOverview with AdminLogin
            primaryStage.setTitle("Admin Login");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App
            AdminLoginController controller = loader.getController();
            controller.setMainApp(this);

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
            // Load AdminControls
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AdminControls.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace AdminLogin with AdminControls
            primaryStage.setTitle("Admin Controls");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            AdminControlsController controller = loader.getController();
            controller.setMainApp(this);

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
    public boolean showSearch() {
        try {
            // Load SearchScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SearchScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace KioskOverview with userUI3.
            primaryStage.setTitle("Search Results");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            SearchController controller = loader.getController();
            controller.setMainApp(this);

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
 // TODO: showDirectory should have parameter for category
    public boolean showDirectory( ) {
        // to differentiate between directories
  //public boolean showDirectory(String listDirectory){

        try {
            // Load DirectoryScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/DirectoryScreen.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace KioskOverview with userUI3.
            primaryStage.setTitle("Directories");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            DirectoryController controller = loader.getController();
            controller.setMainApp(this);

            /*
            switch (listDirectory) {
            case "physicians":  ObservableList<String> names = FXCollections.observableArrayList(
                      "Dr. Julia", "Dr. Ian", "Dr. Sue", "Dr. Matthew", "Dr. Hannah", "Dr. Stephan",
                      "Dr. Denise, "Dr. Mathew", "Dr. Jesus", Dr. Mrs. Vandertrampp", "Dr. Ann");
                     break; //Physicians
            case "departments":  ObservableList<String> names = FXCollections.observableArrayList(
                      "Julia Dpt. ", "Ian Dpt.", "Sue Dpt.", "Matthew Dpt.", "Hannah Dpt.", "Stephan Dpt.", "Denise Dpt.");
                     break; //departments
            case "services":  ObservableList<String> names = FXCollections.observableArrayList(
                      "Julia Station", "Ian Center", "Sue Conference room ", "Matthew Services",
                      "Hannah Banana Stand", "Stephan Frys", "Just Denise");
                     break; //Services
            */
            return controller.isOkClicked();
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    public List chooseList()
            switch (listDirectory) {
            case "physicians":  ObservableList<String> names = FXCollections.observableArrayList(
                      "Dr. Julia", "Dr. Ian", "Dr. Sue", "Dr. Matthew", "Dr. Hannah", "Dr. Stephan",
                      "Dr. Denise, "Dr. Mathew", "Dr. Jesus", Dr. Mrs. Vandertrampp", "Dr. Ann");
                     break; //Physicians
            case "departments":  ObservableList<String> names = FXCollections.observableArrayList(
                      "Julia Dpt. ", "Ian Dpt.", "Sue Dpt.", "Matthew Dpt.", "Hannah Dpt.", "Stephan Dpt.", "Denise Dpt.");
                     break; //departments
            case "services":  ObservableList<String> names = FXCollections.observableArrayList(
                      "Julia Station", "Ian Center", "Sue Conference room ", "Matthew Services",
                      "Hannah Banana Stand", "Stephan Frys", "Just Denise");
                     break; //Services
            */
    
    /**
     * Changes screen to allow users to view the map
     * 
     */
    // TODO: showMap should have parameter for chosen destination from previous screen
    public boolean showMap() {
        try {
            // Load MapView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MapView.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replaces previous screen with userUI4.
            primaryStage.setTitle("Map");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            MapViewController controller = loader.getController();
            controller.setMainApp(this);


            
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
            loader.setLocation(MainApp.class.getResource("view/KioskOverview.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Replace previous screen with KioskOverview.
            primaryStage.setTitle("Pathfinding Application");
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give controller access to Main App.
            KioskOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}