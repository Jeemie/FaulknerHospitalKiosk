package Kiosk.Controllers;


import Kiosk.Admin;
import Kiosk.Controllers.EventHandlers.ChangeBuildingStateEventHandler;
import Kiosk.KioskApp;
import Map.Map;
import Map.*;
import Map.Exceptions.FloorDoesNotExistException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static Map.ObjectToJsonToJava.loadFromFile;

public class AdminPanelController implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPanelController.class);
    private URL saveFilePath;


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
    private  Button modifyLocationButton;
    @FXML
    private Button addLocationButton;
    @FXML
    private Button removeLocationButton;
    @FXML
    private Button addConnectedLocationButton;
    @FXML
    private Button setStartLocationButton;
    @FXML
    private Button moveLocationButton;
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



    private final HashMap<String, ArrayList<Comparable<?>>> hm = new HashMap<>();
    Group zoomGroup;

    private boolean okClicked = false;
    private KioskApp kioskApp;

    private Building mMainHospital;

    private Admin print;

    AdminDepartmentPanelController control = new AdminDepartmentPanelController();


    private LocationNode
            mNode1Audiology,
            mNode1Cardiac,
            mNode1Preop,
            mNode1ER,
            mNode1GI,
            mNode1Lab,
            mNode1Financial,
            mNode1Radiology,
            mNode1SpecialTesting,
            mNode1FamilyCenter,
            mNode1Info,
            mNode1Admitting,
            mNode1Cafe,
            mNode1Valet,
            mNode2A,
            mNode2B,
            mNode2,
            mNode2PhyscialTherapy,
            mNode2Psychology,
            mNode2Addiction,
            mNode2Rehab,
            mNode3A,
            mNode3B,
            mNode3C,
            mNode3ATM,
            mNode3Cafeteria,
            mNode3Chapel,
            mNode3Gifts,
            mNode3Auditorium,
            mNode3PatientRelations,
            mNode3VolunteerServices,
            mNode4A,
            mNode4B,
            mNode4C,
            mNode4D,
            mNode4F,
            mNode4G,
            mNode4H,
            mNode4I,
            mNode4J,
            mNode4N,
            mNode4S,
            mNode4Doherty,
            mNode4Tynan,
            mNode4Sadowsky,
            mNode4Saslow,
            mNode4Interpreter,
            mNode4Library,
            mNode4Records,
            mNode4SocialWork;


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
        try {
            this.saveFilePath = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "default.json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mMainHospital = new Building();
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
//        stage.initOwner(modifyLocationButton.getScene().getWindow());
//        stage.show();
//    }

    public void  print() {
        System.out.print(print.getDept());

    }

    public void setBuilding(Building building) {

        this.mMainHospital = building;

        setupListeners();
    }


    private void setupListeners() {
        this.mMainHospital = new Building();
        try {
            URL floor1Url = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "Floor1_Final.png");
            URL floor2Url = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "Floor2_Final.png");
            URL floor3Url = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "Floor3_Final.png");
            URL floor4Url = new URL("file://" + System.getProperty("user.dir") + "/resources/" + "Floor4_Final.png");
            mMainHospital.addFloor(1, floor1Url);
            mMainHospital.addFloor(2, floor2Url);
            mMainHospital.addFloor(3, floor3Url);
            mMainHospital.addFloor(4, floor4Url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mMainHospital = Map.initMapComponents(mMainHospital);

        addLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new ChangeBuildingStateEventHandler(mMainHospital, BuildingState.ADDNODE));

        removeLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new ChangeBuildingStateEventHandler(mMainHospital, BuildingState.REMOVENODE));

        addConnectedLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new ChangeBuildingStateEventHandler(mMainHospital, BuildingState.ADDADJACENTNODE));

        moveLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new ChangeBuildingStateEventHandler(mMainHospital, BuildingState.MOVENODE));

        modifyLocationButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new ChangeBuildingStateEventHandler(mMainHospital, BuildingState.MODIFYDESTINATIONS));


        saveToFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println(saveFilePath.toString());

                try {
                    mMainHospital.saveToFile(saveFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
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
