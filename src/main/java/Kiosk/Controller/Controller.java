package Kiosk.Controller;


import Kiosk.Admin;
import Map.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

public class Controller implements Initializable {

    @FXML
    VBox root_vbox;
    @FXML
    private ListView<String> map_listview;
    @FXML
    private ScrollPane map_scrollpane;
    @FXML
    private Slider zoom_slider;
    @FXML
    private MenuButton map_pin;
    @FXML
    private MenuItem pin_info;
    @FXML
    private ToggleButton contrast_togglebutton;
    @FXML
    private ToggleButton size_togglebutton;
    @FXML
    private  Button btn1;


    private final HashMap<String, ArrayList<Comparable<?>>> hm = new HashMap<>();
    Group zoomGroup;

    private Building mMainHospital;
    private Floor mFloor3;
    private Location mLocation3B;
    private MapNode mEyeCareSpecialists3B;
    private MapNode mSuburbanEyeSpecialists3B;
    private MapNode mPattenJamesMd3B;
    private MapNode mDannHarrietMd3B;
    private MapNode mGrossiLisaRN;
    private MapNode mPatientRelations3;
    private MapNode mKiosk3;
    private MapNode mElevator3;
    private MapNode mStairs3;
    private MapNode mErrorAddingPhysician;

    private Admin print;

    AdminController control = new AdminController();


    @FXML
    public void initialize(URL location, ResourceBundle resources)

    {
        mMainHospital = new Building();
        mFloor3 = new Floor(3, mMainHospital);
        mLocation3B = new Location(100.0, 100.0);
        mEyeCareSpecialists3B = new MapNode(0, mLocation3B, mFloor3);
        mSuburbanEyeSpecialists3B = new MapNode(0, mLocation3B, mFloor3);
        mPattenJamesMd3B = new MapNode(0, mLocation3B, mFloor3);
        mDannHarrietMd3B = new MapNode(0, mLocation3B, mFloor3);
        mGrossiLisaRN = new MapNode(0, new Location(10, 15), mFloor3);
        mPatientRelations3 = new MapNode(0, new Location(10, 20), mFloor3);
        mKiosk3 = new MapNode(0, new Location(10, 30), mFloor3);
        mElevator3 = new MapNode(0, new Location(10, 40), mFloor3);
        mStairs3 = new MapNode(0, new Location(10, 50), mFloor3);
        mErrorAddingPhysician = new MapNode(0, mLocation3B, mFloor3);
        mEyeCareSpecialists3B.addDestination(Destination.DEPARTMENT, "dr.haha");

       // String deptname = control.addTolist();
        //System.out.println(deptname);
        System.out.println("Controller.initialize");

        hm.put(mEyeCareSpecialists3B.getDestinations().get(0), new ArrayList<>(Arrays.asList(mLocation3B.getX(), mLocation3B.getY(), "mlkjklm")));

        ObservableList<String> names = FXCollections.observableArrayList();
        Set<Entry<String, ArrayList<Comparable<?>>>> set = hm.entrySet();
        Iterator<Entry<String, ArrayList<Comparable<?>>>> i = set.iterator();
        while (i.hasNext()) {
            Map.Entry<String, ArrayList<Comparable<?>>> me = i.next();
            names.addAll(me.getKey());
        }

        map_listview.setItems(names);
        map_pin.setVisible(false);

        zoom_slider.setMin(0.5);
        zoom_slider.setMax(1.5);
        zoom_slider.setValue(1.0);
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));

        // Wrap scroll content in a Group so ScrollPane re-computes scroll bars
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(map_scrollpane.getContent());
        map_scrollpane.setContent(contentGroup);

        // Add large UI styling and make full screen if we are on device
        if (Platform.isSupported(ConditionalFeature.INPUT_TOUCH)) {
            System.out.println("airportapp.Controller.initialize, device detected");
            size_togglebutton.setSelected(true);
            root_vbox.getStyleClass().add("touch-sizes");
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            root_vbox.setPrefSize(bounds.getWidth(), bounds.getHeight());
        }
    }

    @FXML
    void listClicked(MouseEvent event) {
        String item = map_listview.getSelectionModel().getSelectedItem();
        List<Comparable<?>> list = hm.get(item);

        // animation scroll to new position
        double mapWidth = zoomGroup.getBoundsInLocal().getWidth();
        double mapHeight = zoomGroup.getBoundsInLocal().getHeight();
        double scrollH = (Double) list.get(0) / mapWidth;
        double scrollV = (Double) list.get(1) / mapHeight;
        final Timeline timeline = new Timeline();
        final KeyValue kv1 = new KeyValue(map_scrollpane.hvalueProperty(), scrollH);
        final KeyValue kv2 = new KeyValue(map_scrollpane.vvalueProperty(), scrollV);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        // move the pin and set it's info
        double pinW = map_pin.getBoundsInLocal().getWidth();
        double pinH = map_pin.getBoundsInLocal().getHeight();
        map_pin.setLayoutX((Double) list.get(0) - (pinW / 2));
        map_pin.setLayoutY((Double) list.get(1) - (pinH));
        pin_info.setText((String) list.get(2));
        map_pin.setVisible(true);
    }

    @FXML
    void zoomIn(ActionEvent event) {
//    System.out.println("airportapp.Controller.zoomIn");
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 0.1);
    }

    @FXML
    void zoomOut(ActionEvent event) {
//    System.out.println("airportapp.Controller.zoomOut");
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal + -0.1);
    }

    private void zoom(double scaleValue) {
//    System.out.println("airportapp.Controller.zoom, scaleValue: " + scaleValue);
        double scrollH = map_scrollpane.getHvalue();
        double scrollV = map_scrollpane.getVvalue();
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        map_scrollpane.setHvalue(scrollH);
        map_scrollpane.setVvalue(scrollV);
    }

    @FXML
    private void adminSwitchMode(ActionEvent event) throws IOException {
        Stage stage;
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Design/Admin.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btn1.getScene().getWindow());
        stage.show();
    }

    public void  print() {
        System.out.print(print.getDept());

    }
}



