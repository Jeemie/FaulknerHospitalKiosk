package Kiosk.Controllers;


import Kiosk.Admin;
import Kiosk.KioskApp;
import Map.*;
import Map.Exceptions.FloorDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static Map.ObjectToJsonToJava.loadFromFile;

public class AdminPanelController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPanelController.class);

    @FXML
    VBox root_vbox;
    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private Slider zoom_slider;
//    @FXML
//    private MenuButton map_pin;
//    @FXML
//    private MenuItem pin_info;
    @FXML
    private ToggleButton contrast_togglebutton;
    @FXML
    private ToggleButton size_togglebutton;
    @FXML
    private  Button btn1;
    @FXML
    private Button addLocationButton;
    @FXML
    private Button removeLocationButton;
    @FXML
    private Button addConnectedLocationButton;
    @FXML
    private StackPane imageStackPane;
    @FXML
    private Button saveToFileButton;
    @FXML
    private Button changeFloorButton1;
    @FXML
    private Button changeFloorButton2;
    @FXML
    private Button changeFloorButton3;
    @FXML
    private Button changeFloorButton4;
    @FXML
    private Button changeFloorButton5;
    @FXML
    private Button changeFloorButton6;
    @FXML
    private Button changeFloorButton7;
    @FXML
    private Button setStartLocationButton;



    private final HashMap<String, ArrayList<Comparable<?>>> hm = new HashMap<>();
    Group zoomGroup;

    private boolean okClicked = false;
    private KioskApp kioskApp;

    private Building mMainHospital;

    private Admin print;

    AdminDepartmentPanelController control = new AdminDepartmentPanelController();



    @FXML
    public void initialize(URL location, ResourceBundle resources) {
//        mMainHospital = new Building();
//        mFloor3 = new Floor(3, mMainHospital);
//        mLocation3B = new Location(100.0, 100.0);
//        mEyeCareSpecialists3B = new LocationNode(0, mLocation3B, mFloor3);
//        mSuburbanEyeSpecialists3B = new LocationNode(0, mLocation3B, mFloor3);
//        mPattenJamesMd3B = new LocationNode(0, mLocation3B, mFloor3);
//        mDannHarrietMd3B = new LocationNode(0, mLocation3B, mFloor3);
//        mGrossiLisaRN = new LocationNode(0, new Location(10, 15), mFloor3);
//        mPatientRelations3 = new LocationNode(0, new Location(10, 20), mFloor3);
//        mKiosk3 = new LocationNode(0, new Location(10, 30), mFloor3);
//        mElevator3 = new LocationNode(0, new Location(10, 40), mFloor3);
//        mStairs3 = new LocationNode(0, new Location(10, 50), mFloor3);
//        mErrorAddingPhysician = new LocationNode(0, mLocation3B, mFloor3);
//        mEyeCareSpecialists3B.addDestination(Destination.DEPARTMENT, "dr.haha");

       // String deptname = control.addTolist();
        //System.out.println(deptname);
//        System.out.println("Controllers.initialize");
//
//        hm.put(mEyeCareSpecialists3B.getDestinations().get(0), new ArrayList<>(Arrays.asList(mLocation3B.getX(), mLocation3B.getY(), "mlkjklm")));
//
//        ObservableList<String> names = FXCollections.observableArrayList();
//        Set<Entry<String, ArrayList<Comparable<?>>>> set = hm.entrySet();
//        Iterator<Entry<String, ArrayList<Comparable<?>>>> i = set.iterator();
//        while (i.hasNext()) {
//            Map.Entry<String, ArrayList<Comparable<?>>> me = i.next();
//            names.addAll(me.getKey());
//        }
//
//        map_listview.setItems(names);
////        map_pin.setVisible(false);
//
//        zoom_slider.setMin(0.5);
//        zoom_slider.setMax(1.5);
//        zoom_slider.setValue(1.0);
//        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));
//
//        // Wrap scroll content in a Group so ScrollPane re-computes scroll bars
//        Group contentGroup = new Group();
//        zoomGroup = new Group();
//        contentGroup.getChildren().add(zoomGroup);
//        zoomGroup.getChildren().add(map_scrollpane.getContent());
//        map_scrollpane.setContent(contentGroup);
//
//        // Add large UI styling and make full screen if we are on device
//        if (Platform.isSupported(ConditionalFeature.INPUT_TOUCH)) {
//            System.out.println("airportapp.Controllers.initialize, device detected");
//            size_togglebutton.setSelected(true);
//            root_vbox.getStyleClass().add("touch-sizes");
//            Screen screen = Screen.getPrimary();
//            Rectangle2D bounds = screen.getVisualBounds();
//            root_vbox.setPrefSize(bounds.getWidth(), bounds.getHeight());
//        }


        if (mMainHospital != null) {
            setupListeners();
        }


    }

    @FXML
    void listClicked(MouseEvent event) {
//        String item = map_listview.getSelectionModel().getSelectedItem();
//        List<Comparable<?>> list = hm.get(item);
//
//        // animation scroll to new position
//        double mapWidth = zoomGroup.getBoundsInLocal().getWidth();
//        double mapHeight = zoomGroup.getBoundsInLocal().getHeight();
//        double scrollH = (Double) list.get(0) / mapWidth;
//        double scrollV = (Double) list.get(1) / mapHeight;
//        final Timeline timeline = new Timeline();
//        final KeyValue kv1 = new KeyValue(map_scrollpane.hvalueProperty(), scrollH);
//        final KeyValue kv2 = new KeyValue(map_scrollpane.vvalueProperty(), scrollV);
//        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
//        timeline.getKeyFrames().add(kf);
//        timeline.play();

//        // move the pin and set it's info
//        double pinW = map_pin.getBoundsInLocal().getWidth();
//        double pinH = map_pin.getBoundsInLocal().getHeight();
//        map_pin.setLayoutX((Double) list.get(0) - (pinW / 2));
//        map_pin.setLayoutY((Double) list.get(1) - (pinH));
//        pin_info.setText((String) list.get(2));
//        map_pin.setVisible(true);
    }

    @FXML
    void zoomIn(ActionEvent event) {
//    System.out.println("airportapp.Controllers.zoomIn");
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    @FXML
    void zoomOut(ActionEvent event) {
//    System.out.println("airportapp.Controllers.zoomOut");
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }

    private void zoom(double scaleValue) {
//    System.out.println("airportapp.Controllers.zoom, scaleValue: " + scaleValue);
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }

//    @FXML
//    private void adminSwitchMode(ActionEvent event) throws IOException {
//        Stage stage;
//        stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("../Views/AdminDepartmentPanel.fxml"));
//        stage.setScene(new Scene(root));
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(btn1.getScene().getWindow());
//        stage.show();
//    }

    public void  print() {
        System.out.print(print.getDept());

    }

    public void setBuilding(Building building) {

        this.mMainHospital = building;

        try {
            File file = new File(getClass().getClassLoader().getResource("Kiosk/Controllers/mapdata.json").toURI());
            if (file.exists() && file.length() > 0) { // Check if file exists and is not empty
                this.mMainHospital = loadFromFile(file); // Load mapdata.json
            } else
                file = new File(getClass().getClassLoader().getResource("Kiosk/Controllers/default.json").toURI());
            if (file.exists() && file.length() > 0) { // Check that default file exists and is not empty
                this.mMainHospital = loadFromFile(file); // Load default.json
            } else {
                LOGGER.info("Cannot load " + file.toString() + ". File does not exist or is empty.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            System.out.println("Loaded from file e2");
        } catch (FloorDoesNotExistException e) {
            System.out.println("Loaded from file e3");
        }

        setupListeners();
    }


    private void setupListeners() {

/*
        mMainHospital.addFloor(1, "Floor1_Final.png");
        mMainHospital.addFloor(2, "Floor2_Draft.png");
        mMainHospital.addFloor(3, "Floor3_Final.png");
        mMainHospital.addFloor(4, "Floor4_Draft.png");
*/
        try {
            LocationNode node3A = new LocationNode(0, new Location(100, 100), mMainHospital.getFloor(3));
            node3A.addDestination(Destination.KIOSK, "Kiosk3");
        } catch (FloorDoesNotExistException e) {
            e.printStackTrace();
        }


        for (Floor floor: mMainHospital.getFloors()) {
            floor.setFloorImage(getClass().getResource(floor.getImagePath()));
            if(floor.getFloorNodes().size() > 0) { // Check if the floor contains nodes
                for (LocationNode node : floor.getFloorNodes()) {
                    node.setNodeCircle(new Circle(node.getLocation().getX(), node.getLocation().getY(), 5.0));
                    node.initObserver();
                    node.initAdjacentLines();
                }
            }
            floor.drawFloorAdmin(imageStackPane);
        }


        addLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mMainHospital.setState(BuildingState.ADDNODE);
                LOGGER.info("Building State changed to " +  mMainHospital.getState().name());
            }

        });

        removeLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mMainHospital.setState(BuildingState.REMOVENODE);
                LOGGER.info("Building State changed to " +  mMainHospital.getState().name());
            }

        });

        addConnectedLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mMainHospital.setState(BuildingState.ADDADJACENTNODE);
                LOGGER.info("Building State changed to " +  mMainHospital.getState().name());
            }

        });


        btn1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                mMainHospital.setState(BuildingState.MODIFYDESTINATIONS);
                LOGGER.info("Building State changed to " +  mMainHospital.getState().name());
            }

        });

        saveToFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {

                    mMainHospital.saveToFile("Kiosk/Controllers/mapdata.json");

                } catch (IOException e) {

                } catch (URISyntaxException e) {

                }

            }

        });

        changeFloorButton1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    mMainHospital.getFloor(1).drawFloorAdmin(imageStackPane);
                } catch (FloorDoesNotExistException e) {
                    e.printStackTrace();
                }

            }

        });

        changeFloorButton2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    mMainHospital.getFloor(2).drawFloorAdmin(imageStackPane);
                } catch (FloorDoesNotExistException e) {
                    e.printStackTrace();
                }

            }

        });

        changeFloorButton3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    mMainHospital.getFloor(3).drawFloorAdmin(imageStackPane);
                } catch (FloorDoesNotExistException e) {
                    e.printStackTrace();
                }

            }

        });

        changeFloorButton4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                try {
                    mMainHospital.getFloor(4).drawFloorAdmin(imageStackPane);
                } catch (FloorDoesNotExistException e) {
                    e.printStackTrace();
                }

            }

        });

        setStartLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                mMainHospital.setState(BuildingState.SETFLOORSTARTNODE);
                LOGGER.info("Building State changed to " +  mMainHospital.getState().name());

            }

        });



    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setKioskApp(KioskApp kioskApp) {
        this.kioskApp = kioskApp;
    }


}
