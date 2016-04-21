package Kiosk.Controllers;

import Kiosk.Controllers.AdminDashboardSubControllers.AdminDashboardAddFloorController;
import Kiosk.Controllers.AdminDashboardSubControllers.AdminSubControllerLoader;
import Kiosk.Controllers.AdminDashboardSubControllers.SubViewLoader;
//import Kiosk.Controllers.EventHandlers.ChangeBuildingStateEventHandler;
import Kiosk.KioskApp;
import Map.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Controller for the admin dashboard view
 */
public class AdminDashboardController {

    private Map faulknerHospitalMap;

    private KioskApp kioskApp;


    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDashboardController.class);

    @FXML
    private ScrollPane mapScrollPane;

    @FXML
    private Label alabel;
    @FXML
    private Button setStartNode;

    @FXML
    private StackPane mapStackPane;

    @FXML
    private Button zoomOutButton;

    @FXML
    private Button zoomInButton;

    @FXML
    private Slider zoomSlider;

    @FXML
    private TabPane mapTabPane;



    //\\ Building Tab //\\
    @FXML
    private Tab buildingTab;

    @FXML
    private Accordion buildingAccordion;


    // Floors Titled Pane //
    @FXML
    private TitledPane buildingFloorsTitledPane;

    @FXML
    private ListView buildingFloorsListView;

    @FXML
    private Button buildingFloorsAddButton;

    @FXML
    private Button buildingFloorsModifyButton;

    @FXML
    private Button buildingFloorsDeleteButton;


    // Destinations Titled Pane //
    @FXML
    private TitledPane buildingDestinationsTitledPane;

    @FXML
    private ListView buildingDestinationsListView;


    // Destinations Titled Pane //
    @FXML
    private TitledPane buildingMiscTitledPane;



    //\\ Floor Tab //\\
    @FXML
    private Tab floorTab;

    @FXML
    private Accordion floorAccordion;


    // Locations Titled Pane //
    @FXML
    private TitledPane floorLocationsTitledPane;

    @FXML
    private ListView floorLocationsListView;

    @FXML
    private Button floorLocationsAddButton;

    @FXML
    private Button floorLocationsModifyButton;

    @FXML
    private Button floorLocationsDeleteButton;


    // Floor Destinations Titled Pane //
    @FXML
    private TitledPane floorDestinationsTitledPane;

    @FXML
    private ListView floorDestinationsListView;


    // Floor Information Titled Pane //
    @FXML
    private TitledPane floorInformationTitledPane;



    //\\ Location Tab //\\
    @FXML
    private Tab locationTab;

    @FXML
    private Accordion locationAccordion;


    // Connected Locations Titled Pane //
    @FXML
    private TitledPane locationConnectedLocationsTitledPane;

    @FXML
    private Button locationConnectedLocationsAddButton;

    @FXML
    private Button locationConnectedLocationsDeleteButton;
    @FXML
    private ListView locationConnectedLocationListView;
    // Destinations Titled Pane //
    @FXML
    private TitledPane locationDestinationsTitledPane;

    @FXML
    private ListView locationDestinationsListView;

    @FXML
    private Button locationDestinationsAddButton;

    @FXML
    private Button locationDestinationsModifyButton;

    @FXML
    private Button locationDestinationsDeleteButton;


    // Information Titled Pane //
    @FXML
    private TitledPane locationInformationTitledPane;



    @FXML
    private Button discardChangesButton;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button logoutButton;







    public void setListeners() {

        makeShitHappen();
        // Setup Listeners
        setCoreFunctionalityListeners();





        setBuildingTabListeners1();
        setFloorTabListeners1();
        setLocationTabListeners1();
//        deleteThis();

    }

    private void makeShitHappen() {

        this.faulknerHospitalMap = new Map("Faulkner Hospital");

    }



//    private void deleteThis() {
//
//
//        this.building.addFloor(2, "Floor4_Draft.png").addNode(new Location(500.0,500.0)).addDestination(Destination.BATHROOM,"triet");
//
//        try {
//            LocationNode node3A = new LocationNode(0, new Location(100, 100), this.building.getFloor(3));
//            node3A.addDestination(Destination.KIOSK, "Kiosk3");
//        } catch (FloorDoesNotExistException e) {
//            e.printStackTrace();
//        }
//
//
//        for (Floor floor: this.building.getFloors()) {
//            floor.setFloorImage(getClass().getResource(String.valueOf(floor.getImagePath())));
//            if(floor.getFloorNodes().size() > 0) { // Check if the floor contains nodes
//                for (LocationNode node : floor.getFloorNodes()) {
//                    node.setNodeCircle(new Circle(node.getLocation().getX(), node.getLocation().getY(), 5.0));
//                    node.initObserver();
//                    node.initAdjacentLines();
//                }
//            }
//            floor.drawFloorAdmin(this.mapStackPane);
//        }
//
//
//    }





    private void setCoreFunctionalityListeners() {

        this.faulknerHospitalMap.setupAdminStackPane(this.mapStackPane);

        // Setup Logout Button
        this.logoutButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // TODO Add saving prompt if changes have been made

                LOGGER.info("Logging out of the Admin Dashboard");

                kioskApp.reset();

            }

        });

        // Setup Discard Changes Button
        this.discardChangesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                // TODO Reload building from file

                LOGGER.info("Discarding changes to the map");

            }

        });

        // Setup Save Changes Button
        this.saveChangesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            public URL saveFilePath;

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Attempting to save changes to the map");


                try {

                    this.saveFilePath = new URL("file:///" + System.getProperty("user.dir") + "/resources/" + "default.json");

                } catch (MalformedURLException e) {

                    e.printStackTrace();
                }

               try {

                   File saveFile = new File(saveFilePath.toURI());

                   faulknerHospitalMap.saveToFile(saveFile);

                    LOGGER.info("Changes were saved");

              } catch (IOException e) {

                   LOGGER.error("An error occurred while saving changes to the map", e);

              } catch (URISyntaxException e) {

                   LOGGER.error("An error occurred while saving changes to the map", e);

                }

            }

        });

        // Setup Zoom Slider
        this.zoomSlider.setMax(1.5);
        this.zoomSlider.setMin(0.5);
        this.zoomSlider.setValue(1.0);
        this.zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                LOGGER.info("Zoom slider has been moved from " + oldValue + " to " + newValue);

                // TODO fix zooming

//                double scrollH = mapScrollPane.getHvalue();
//                double scrollV = mapScrollPane.getVvalue();
//
//                LOGGER.info("" + scrollH);
//                LOGGER.info("" + scrollV);
//                zoomSlider.setScaleX(newValue.doubleValue());
//                zoomSlider.setScaleY(newValue.doubleValue());
//                mapScrollPane.setHvalue(scrollH);
//                mapScrollPane.setVvalue(scrollV);

            }

        });






        // Setup Zoom In Button
        this.zoomInButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Zooming In");

                zoomSlider.setValue(zoomSlider.getValue() + 0.1);

            }

        });

        // Setup Zoom Out Button
        this.zoomOutButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                LOGGER.info("Zooming out");

                zoomSlider.setValue(zoomSlider.getValue() - 0.1);

            }

        });


        // Setup Tab Pane
        this.mapTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {

            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {

                LOGGER.info("The selected tab is currently " + newValue.getText());

            }

        });

    }

    private void setBuildingTabListeners1() {

        // TODO do something with the building tab


        // Setup Building Accordion
        this.buildingAccordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {

            @Override
            public void changed(ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) {

                if (newValue != null) {

                    LOGGER.info("In the building tab the " + newValue.getText() + " Titled Pane has been expanded");

                } else {

                    LOGGER.info("In the building tab the " + oldValue.getText() + " Titled Pane has been closed");

                }

            }

        });

        this.buildingFloorsTitledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {

                    LOGGER.info("Building Floors Titled Pane Opened");

                }

            }

        });

        this.buildingFloorsListView.setItems(this.faulknerHospitalMap.getCurrentBuildingFloors());
        this.buildingFloorsListView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

//                ((Floor)buildingFloorsListView.getSelectionModel().getSelectedItem()).drawFloorAdmin(mapStackPane);
//                building.setCurrentFloor(((Floor)buildingFloorsListView.getSelectionModel().getSelectedItem()));
//                building.setCurrentDestination(((LocationNode) buildingFloorsListView.getSelectionModel().getSelectedItem()));
//                building.setCurrentNodes(((LocationNode) buildingFloorsListView.getSelectionModel().getSelectedItem()));
            }

        });

        this.buildingFloorsAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

//                AdminSubControllerLoader loader = new AdminSubControllerLoader();
//
//                loader.setStackPane(mapStackPane);
//                loader.setCurrentBuilding(building);
//                loader.loadAddFloor();
//
//
//                SubViewLoader<AdminDashboardAddFloorController> subViewLoader =
//                        new SubViewLoader<>("Views/AdminDashboardSubViews/AdminDashboardAddFloor.fxml", mapStackPane);
//
//                AdminDashboardAddFloorController adminDashboardAddFloorController = subViewLoader.loadView();
//
//
//                adminDashboardAddFloorController.setCurrentBuilding(building);
//                adminDashboardAddFloorController.setSubViewLoader(subViewLoader);
//                adminDashboardAddFloorController.setListeners();

            }

        });


        this.buildingDestinationsTitledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {

                    LOGGER.info("Building Destinations Titled Pane Opened");

//                    building.addBuildingDestinationsToListView(buildingDestinationsListView);

                }

            }

        });


        this.buildingDestinationsListView.setItems(this.faulknerHospitalMap.getCurrentBuildingDestinations());


    }

    private void setFloorTabListeners1() {

        // Setup Building Accordion
        this.floorAccordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {

            @Override
            public void changed(ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) {

                if (newValue != null) {

                    LOGGER.info("In the Floor tab the " + newValue.getText() + " Titled Pane has been expanded");

                } else {

                    LOGGER.info("In the Floor tab the " + oldValue.getText() + " Titled Pane has been closed");

                }

            }

        });

        this.floorLocationsTitledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {

                    LOGGER.info("Building Floors Titled Pane Opened");

//                    if (building.getCurrentFloor() != null) {
//
//                        building.getCurrentFloor().addLocationToListView(floorLocationsListView);
//
//                    }



                }

            }

        });

        this.floorLocationsListView.setItems(this.faulknerHospitalMap.getCurrentFloorLocatioNodes());

        this.floorDestinationsTitledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {

                    LOGGER.info("Building Floors Titled Pane Opened");

//                    if (building.getCurrentFloor() != null) {
//
//                        building.getCurrentFloor().addDestinationsToListView(floorDestinationsListView);
//
//
//                    }
                }
            }
        });

        floorLocationsAddButton.setOnAction(event ->{

            alabel.setText("Add Button");

//            floorLocationsAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                    new ChangeBuildingStateEventHandler(building, BuildingState.ADDNODE));

        });


        floorLocationsDeleteButton.setOnAction(event ->{

            alabel.setText("Delete Button");

//            floorLocationsDeleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                    new ChangeBuildingStateEventHandler(building, BuildingState.REMOVENODE));

        });


        floorLocationsModifyButton.setOnAction(event ->{

            alabel.setText("Modify Button");

//            floorLocationsModifyButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                    new ChangeBuildingStateEventHandler(building, BuildingState.MOVENODE));


        });



    }

    private void setLocationTabListeners1() {


        // Setup Building Accordion
        this.locationAccordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {

            @Override
            public void changed(ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) {

                if (newValue != null) {

                    LOGGER.info("In the Location tab the " + newValue.getText() + " Titled Pane has been expanded");

                } else {

                    LOGGER.info("In the Location tab the " + oldValue.getText() + " Titled Pane has been closed");

                }

            }

        });

        this.locationConnectedLocationsTitledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {

                    LOGGER.info("Building Floors Titled Pane Opened");

//                    building.getCurrentNodes().addAdjacentsToListView(locationConnectedLocationListView);


                }

            }


        });

        this.locationDestinationsTitledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {

                    LOGGER.info("Building Floors Titled Pane Opened");

//                    if (building.getCurrentNodes() != null) {
//
//                        building.getCurrentNodes().addDestinationsToListView(locationDestinationsListView);
//
//                    }


                }

            }


        });


        locationConnectedLocationsAddButton.setOnAction(event -> {

            alabel.setText("Add Connected Button");
//            this.locationConnectedLocationsAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new ChangeBuildingStateEventHandler(building, BuildingState.ADDADJACENTNODE));

        });

        locationDestinationsAddButton.setOnAction(event -> {
            alabel.setText("Add Destination Button");
//            this.locationDestinationsAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new ChangeBuildingStateEventHandler(building, BuildingState.MODIFYDESTINATIONS));
        });

        locationConnectedLocationsDeleteButton.setOnAction(event -> {

            alabel.setText("Delete Destination Button");
//            this.locationConnectedLocationsDeleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new ChangeBuildingStateEventHandler(building, BuildingState.REMOVENODE));
        });



        this.setStartNode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
//
//                LOGGER.info("Setting start node to: " + building.getCurrentNodes());
//
//                building.setStartNode(building.getCurrentNodes());

            }

        });
    }






    public void setFaulknerHospitalMap(Map faulknerHospitalMap) {

        this.faulknerHospitalMap = faulknerHospitalMap;

    }

    public void setKioskApp(KioskApp kioskApp) {

        this.kioskApp = kioskApp;

    }

}