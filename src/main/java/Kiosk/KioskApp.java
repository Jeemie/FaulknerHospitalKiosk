package Kiosk;

import Kiosk.Controllers.*;
import Map.Enums.DestinationType;
import Map.Exceptions.DefaultFileDoesNotExistException;
import Map.Exceptions.FloorDoesNotExistException;
import Map.Map;
import Map.FaulknerHospitalData;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class KioskApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    protected Map faulknerHospitalMap;
    private URL filePath;


    private ListView<String> listDirectory;

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {

        this.primaryStage = primaryStage;

        this.filePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");
        try {

            this.faulknerHospitalMap = Map.loadFromFile(this.filePath);

        } catch (FloorDoesNotExistException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (DefaultFileDoesNotExistException e) {
            // Create new default file
            try {

                File newFile = new File(this.filePath.toURI());
                newFile.createNewFile();

                this.faulknerHospitalMap = new Map("Faulkner Hospital Map");
                this.faulknerHospitalMap = FaulknerHospitalData.starterMap();

            } catch (URISyntaxException exception) {

                exception.printStackTrace();

            } catch (IOException exception) {

                exception.printStackTrace();
            }

        }

        this.faulknerHospitalMap.initMapComponents();


        this.primaryStage.setTitle("Pathfinding Application");

        initRootLayout();

        showKioskOverview();
//        showAdminControls();
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

            // Debugger works better when full screen is off
            primaryStage.setFullScreen(false);
            //primaryStage.setFullScreen(true);

            scene.getStylesheets().add((new URL("file:///" + System.getProperty("user.dir") + "/src/main/java/Kiosk/" + "Style.css")).toExternalForm());
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

//            controller.setListeners();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
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
     */
    public boolean showAdminLogin() {
        showAdminControls();

//        try {
//            // Load AdminLogin
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(KioskApp.class.getResource("Views/AdminLogin.fxml"));
//            AnchorPane page = loader.load();
//
//            // Replace KioskOverview with AdminLogin
//            primaryStage.setTitle("Admin Login");
//            primaryStage.getScene().setRoot(page);
///*            Scene scene = new Scene(page);
//            primaryStage.setScene(scene);
//            primaryStage.setFullScreen(true);
//            primaryStage.show();*/
//            scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
//
//            // Give controller access to Main App
//            AdminLoginController controller = loader.getController();
//            controller.setKioskApp(this);
//            controller.setListeners();
//
        //      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        //          public void handle(WindowEvent we) {
//                controller.shutOff();
        //           }
        //       });
//            return controller.isOkClicked();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }

        return true;
    }

    /**
     * Changes screen to allow admins to edit the map
     */
    public boolean showAdminControls() {

        try {
            // Load AdminDashboard
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/AdminDashboard.fxml"));
            SplitPane page = loader.load();

            // Replace KioskOverview with AdminLogin
            primaryStage.setTitle("Administrator Dashboard");
            primaryStage.getScene().setRoot(page);

            // Give controller access to Main App
            AdminDashboardController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setFaulknerHospitalMap(this.faulknerHospitalMap);
            controller.setListeners();


/*            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    controller.shutOff();
                }
            });*/
//            return controller.isOkClicked();
            return false;


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Changes screen to allow users to see results of search
     */
    // TODO: showSearch should have parameter for input
    public boolean showSearch(String searchText) {
        try {
            // Load SearchScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/SearchScreen.fxml"));
            AnchorPane page = loader.load();

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
            controller.setFaulknerHospitalMap(this.faulknerHospitalMap);
            controller.displayResult(searchText);

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    controller.shutOff();
                }
            });

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Changes screen to allow users to select by directory
     */
    public boolean showDirectory(DestinationType destinationType) {

        try {

            // Load DirectoryScreen
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/DirectoryScreen.fxml"));
            AnchorPane scene = loader.load();

            primaryStage.setTitle("Directory");
            primaryStage.getScene().setRoot(scene);

            // Give controller access to Main App.
            DirectoryController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setFaulknerHospitalMap(this.faulknerHospitalMap);
            controller.setupListeners();
            controller.setStartSelection(destinationType);

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    controller.shutOff();
                }
            });
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * Changes screen to allow users to view the map
     */
    // TODO: showMap should have parameter for chosen destination from previous screen
    public boolean showMap() {

        try {
            // Load MapView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/MapView.fxml"));
            AnchorPane scene = loader.load();


            // Replaces previous screen with userUI4.
            primaryStage.setTitle("Map");
            primaryStage.getScene().setRoot(scene);

            // Give controller access to Main App.
            MapViewController controller = loader.getController();
            controller.setKioskApp(this);
            controller.setFaulknerHospitalMap(this.faulknerHospitalMap);
            controller.setListeners();
//            controller.setBuilding(this.hospitalBuilding);
//            controller.setStartNode(startNode);
//            controller.setDestinationNode(destinationNode);

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    controller.shutOff();
                }
            });

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Shows the about page.
     */
    public void showAboutPage() {
        try {
            // Load About Page.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/AboutPage.fxml"));
            AnchorPane page = loader.load();

            // Set kiosk overview into the center of root layout.
            primaryStage.setTitle("About the Developers");
            primaryStage.getScene().setRoot(page);

            // Give the controller access to the main app.
            AboutPageController controller = loader.getController();
            controller.setKioskApp(this);
//            controller.setListeners();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the screen to the KioskOverview
     */
    public void reset() {
        try {
            // Load KioskOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(KioskApp.class.getResource("Views/KioskOverview.fxml"));
            AnchorPane page = loader.load();

            // Replace previous screen with KioskOverview.
            primaryStage.setTitle("Pathfinding Application");
            primaryStage.getScene().setRoot(page);

            // Give controller access to Main App.
            KioskOverviewController controller = loader.getController();
            controller.setKioskApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}