package Kiosk.Controllers.AdminDashboardSubControllers;

import Map.Building;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by matt on 4/15/16.
 */
public class AdminDashboardAddFloorController {

    private Building currentBuilding;
    private AdminSubControllerLoader loader;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDashboardAddFloorController.class);






    public void setListeners() {





    }


    public void setCurrentBuilding(Building currentBuilding) {

        this.currentBuilding = currentBuilding;

    }

    public void setParentController(AdminSubControllerLoader loader) {

        this.loader = loader;

    }
}
