package Kiosk.Controllers;

import Map.Building;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by matt on 4/12/16.
 */
public class AdminDashboardController {


    private Building building;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDashboardController.class);


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

    








}
