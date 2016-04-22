package Kiosk.Controllers.EventHandlers;

import Map.Map;
import Map.Enums.MapState;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by matt on 4/11/16.
 */

public class ChangeMapStateEventHandler implements EventHandler<MouseEvent> {

    // The state you want to change to building to
    private final MapState state;

    // Building that you want to change the state of
    private final Map map;

    private final String labelMessage;

    private final Label selectedButtonLabel;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeMapStateEventHandler.class);

    /**
     * Default Constructor for this class
     *
     * @param map The map you want to change the state of.
     * @param state The state you want to change the building to.
     */

    public ChangeMapStateEventHandler(Map map, MapState state, String labelMessage, Label selectedButtonLabel) {

        this.map = map;
        this.state = state;
        this.labelMessage = labelMessage;
        this.selectedButtonLabel = selectedButtonLabel;

    }

    /**
     *
     * @param event
     */

    @Override
    public void handle(MouseEvent event) {

        map.setCurrentMapState(state);

        this.selectedButtonLabel.setText(this.labelMessage);

        LOGGER.info("Building " + map.toString() + " State changed to " +  state.toString());

    }


}