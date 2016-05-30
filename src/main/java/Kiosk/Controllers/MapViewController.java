package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.*;
import Map.Destination;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MapViewController {

    // Reference to the main application.

    private boolean okClicked = false;

    private Map faulknerHospitalMap;

    private KioskApp kioskApp;

    @FXML
    private Label clock;


    private final DateFormat date = DateFormat.getInstance();


    private int numThreads = 0;


    private static final Logger LOGGER = LoggerFactory.getLogger(MapViewController.class);

    @FXML
    private Label currentFloorLabel;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button zoomIn;

    @FXML
    private Button zoomOut;

    @FXML
    private Slider slider;

    @FXML
    private StackPane imageStackPane;

    @FXML
    private Button confirmButton;

    @FXML
    private ScrollPane zoomScrollPane;

    @FXML
    private Button changeFloorButtonUp;

    @FXML
    private Button changeFloorButtonDown;

    @FXML
    private ListView directionsList;

    @FXML
    private Button backButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView mapViewsListView;

    @FXML
    private Button physiciansButton;

    @FXML
    private Button departmentsButton;

    @FXML
    private Button servicesButton;

    private Destination currentDestination;

    private Group zoomGroup;


    public Timer timer;
    public Timer atimer;

    int counter = 0;
    int getCounterFloor = 1;
    private volatile boolean running = true;

    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            counter++;
        }
    };


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (running) {
                try {
                    if (counter == 60000) {
                        System.out.println("Timed Out.");
                        running = false;
                        timer.cancel();
                        timer.purge();
                        atimer.cancel();
                        atimer.purge();
                        timerTask.cancel();
                        Platform.runLater(resetKiosk);
                        break;
                    }

                    Thread.sleep(1000);

                } catch (InterruptedException exception) {
                    atimer.cancel();
                    timer.cancel();
                    timerTask.cancel();
                    running = false;
                    exception.printStackTrace();
                    break;
                }

            }
        }
    };

    Thread timerThread;

    Runnable resetKiosk = new Runnable() {

        @Override
        public void run() {

            handleBack();

        }

    };


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {


        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mma");
                clock.setText(sdf.format(cal.getTime()));
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

//        setListeners();



        slider.setMin(0.5);
        slider.setMax(5.0);
        slider.setValue(2.5);
        slider.valueProperty().addListener((o, oldVal, newVal) -> zoom((Double) newVal));

        // Wrap scroll content in a Group so zoomScrollPane re-computes scroll bars
        Group contentGroup = new Group();
        zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(zoomScrollPane.getContent());
        zoomScrollPane.setContent(contentGroup);

        searchTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.ENTER)) {

                    timer.cancel();
                    running = false;
                    timerThread.interrupt();
                    kioskApp.showSearch(searchTextField.getText());

                } else {

                    counter = 0;

                }
            }
        });


        changeFloorButtonDown.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                faulknerHospitalMap.pathPreviousFloor();
                currentFloorLabel.setText(faulknerHospitalMap.getStartLocationNode().getCurrentFloor().getFloorName());


            }
        });


        zoomScrollPane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (numThreads == 0) {
                    numThreads += 1;
                    running = true;
                    timer = new Timer("A Timer");
                    atimer = new Timer("A Timer2");
                    timerThread = new Thread(runnable);
                    timer.scheduleAtFixedRate(timerTask, 30, 1000);
                    timerThread.start();
                }
                counter = 0;
            }
        });


        //timer.scheduleAtFixedRate(timerTask, 30, 1000);

        //timerThread.start();

        changeFloorButtonUp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                counter = 0;
                faulknerHospitalMap.pathNextFloor();
                currentFloorLabel.setText(faulknerHospitalMap.getCurrentFloor().getFloorName());

            }

        });

        // Return to KioskOverview
        // Return to home menu
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                kioskApp.reset();

            }

        });


        zoomIn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                double sliderVal = slider.getValue();
                slider.setValue(sliderVal += 0.1);

            }
        });

        zoomOut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double sliderVal = slider.getValue();
                slider.setValue(sliderVal -= 0.1);

            }
        });


        directionsList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

        @Override
            public void handle(MouseEvent event) {

                LocationNode node = ((Direction) directionsList.getSelectionModel().getSelectedItem()).getTurningPoint();
                double sliderVal = slider.getValue();
                slider.setValue(sliderVal =3);
                double mapWidth = zoomGroup.getBoundsInLocal().getWidth();
                double mapHeight = zoomGroup.getBoundsInLocal().getHeight();
                double scrollH =  node.getLocation().getX() / mapWidth;
                double scrollV = node.getLocation().getY() / mapHeight;
                final Timeline timeline = new Timeline();
                final KeyValue kv1 = new KeyValue(zoomScrollPane.hvalueProperty(), scrollH);
                final KeyValue kv2 = new KeyValue(zoomScrollPane.vvalueProperty(), scrollV);
                final KeyFrame kf = new KeyFrame(Duration.millis(500), kv1, kv2);
                timeline.getKeyFrames().add(kf);
                timeline.play();



            }

        });



        directionsList.setCellFactory(listView -> new ListCell<Direction>() {

            private final ImageView imageView = new ImageView();
            {
                imageView.setFitHeight(25);
                imageView.setFitWidth(25);
                imageView.setPreserveRatio(true);
            }

            @Override
            public void updateItem(Direction item, boolean empty) {

                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {

                    setText(item.getDirectionString());

                    try {

                        Image icon = new Image(new URL("file:///" + System.getProperty("user.dir") + "/resources" +
                                item.getRelativeDirection().getResourceFileName()).toString(), true);

                        imageView.setImage(icon);

                    } catch (MalformedURLException e) {

                        LOGGER.error("Unable to show the icon  in the addLocationIconsListView", e);

                    }

                    setGraphic(imageView);
                }
            }
        });
    }

    public void setListeners() {


        this.faulknerHospitalMap.setupPathStackPane(imageStackPane);
        this.faulknerHospitalMap.setupDirections(directionsList);

        zoomScrollPane.setVvalue(this.faulknerHospitalMap.getXAverage()/1300+0.2);
        zoomScrollPane.setHvalue(this.faulknerHospitalMap.getYAverage()/2250-0.1);

        if(this.faulknerHospitalMap.getCurrentPath().getSplitPath().size()!=1){

            this.changeFloorButtonDown.setVisible(true);
            this.changeFloorButtonUp.setVisible(true);
        }
        else{

            this.changeFloorButtonDown.setVisible(false);
            this.changeFloorButtonUp.setVisible(false);
        }

        currentFloorLabel.setText(faulknerHospitalMap.getStartLocationNode().getCurrentFloor().getFloorName());

        this.faulknerHospitalMap.setupPathStackPane(imageStackPane);
        this.faulknerHospitalMap.setupDirections(directionsList);

    }


    private void zoom(double scaleValue) {

        double scrollH = zoomScrollPane.getHvalue();
        double scrollV = zoomScrollPane.getVvalue();
        zoomGroup.setScaleX(scaleValue);
        zoomGroup.setScaleY(scaleValue);
        zoomScrollPane.setHvalue(scrollH);
        zoomScrollPane.setVvalue(scrollV);
    }


    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param kioskApp
     */
    public void setKioskApp(KioskApp kioskApp) {
        this.kioskApp = kioskApp;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }


    /**
     * Called when the user clicks back.
     */
    // TODO: handleBack should have an if statement, which will go to
    // either userUI2 or userUI3 depending on which screen userUI4 was
    // accessed from
    @FXML
    private void handleBack() {

        handleCancel();
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {

        atimer.cancel();
        atimer.purge();
        timer.cancel();
        timer.purge();
        running = false;
        timerThread.interrupt();
        kioskApp.reset();
    }

    public void setFaulknerHospitalMap(Map faulknerHospitalMap) {

        this.faulknerHospitalMap = faulknerHospitalMap;

    }

    public void shutOff() {

        atimer.cancel();
        atimer.purge();
        timer.cancel();
        timer.purge();
        running = false;
        timerThread.interrupt();
        kioskApp.reset();

    }

}