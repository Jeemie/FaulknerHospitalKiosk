package Kiosk.Controllers.EventHandlers;

import Map.Enums.MapState;
import Map.Map;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by matt on 4/24/16.
 */
public class AddTabEventHandler implements EventHandler<MouseEvent> {

    // The state you want to change to building to
    private final MapState state;

    // Building that you want to change the state of
    private final Map map;

    private final String labelMessage;

    private final Label selectedButtonLabel;

    private final TabPane mapTabPane;

    private final Tab addTab;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeMapStateEventHandler.class);

    public AddTabEventHandler(MapState state, Map map, String labelMessage, Label selectedButtonLabel, TabPane mapTabPane, Tab addTab) {

        this.state = state;
        this.map = map;
        this.labelMessage = labelMessage;
        this.selectedButtonLabel = selectedButtonLabel;
        this.mapTabPane = mapTabPane;
        this.addTab = addTab;

    }


    @Override
    public void handle(MouseEvent event) {

        map.setCurrentMapState(state);

        this.selectedButtonLabel.setText(this.labelMessage);

        LOGGER.info("Building " + map.toString() + " State changed to " +  state.toString());

        this.mapTabPane.getTabs().add(this.addTab);

        this.mapTabPane.getSelectionModel().select(this.addTab);

    }

}
