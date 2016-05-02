package Kiosk.Controllers;

import Kiosk.KioskApp;
import Map.Enums.DestinationType;
import Map.Map;
import Map.Destination;
import Map.Exceptions.FloorDoesNotExistException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
                    if (counter == 60) {
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
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                clock.setText("Current Time: " + sdf.format(cal.getTime()));
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

//        setListeners();

        zoomScrollPane.setHvalue(0.5);
        zoomScrollPane.setVvalue(0.5);

        slider.setMin(0.5);
        slider.setMax(1.5);
        slider.setValue(1.0);
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
                    LOGGER.info("Blah " + searchTextField.getText());
                    kioskApp.showSearch(searchTextField.getText());

                } else {

                    counter = 0;

                }
            }
        });
        
        changeFloorButtonDown.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                counter = 0;

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

    }


    public void setListeners() {

        this.faulknerHospitalMap.setupPathStackPane(imageStackPane);
        this.faulknerHospitalMap.setupDirections(directionsList);
        currentFloorLabel.setText(faulknerHospitalMap.getStartLocationNode().getCurrentFloor().getFloorName());


    }

    //    @FXML
    void zoomIn(ActionEvent event) {

        counter = 0;

        double sliderVal = slider.getValue();
        slider.setValue(sliderVal += 0.1);

    }

    //    @FXML
    void zoomOut(ActionEvent event) {

        counter = 0;

        double sliderVal = slider.getValue();
        slider.setValue(sliderVal + -0.1);

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