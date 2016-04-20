package Kiosk.Controllers.EventHandlers;

import Map.Building;
import Map.Enums.BuildingState;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by matt on 4/11/16.
 */
public class ChangeBuildingStateEventHandler implements EventHandler<MouseEvent> {

    // The state you want to change to building to
    private final BuildingState state;

    // Building that you want to change the state of
    private Building building;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeBuildingStateEventHandler.class);


    /**
     * Default Constructor for this class
     *
     * @param building The building you want to change the state of.
     * @param state The state you want to change the building to.
     */
    public ChangeBuildingStateEventHandler(Building building, BuildingState state) {

        this.building = building;
        this.state = state;

    }

    /**
     *
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {

        building.setState(state);

        LOGGER.info("Building " + building.toString() + " State changed to " +  state.toString());

    }

}
