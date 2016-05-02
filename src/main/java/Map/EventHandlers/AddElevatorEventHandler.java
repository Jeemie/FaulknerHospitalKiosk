package Map.EventHandlers;

import Map.Map;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddElevatorEventHandler extends AbstractTabEventHandler implements EventHandler<MouseEvent> {

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(AddElevatorEventHandler.class);

    public AddElevatorEventHandler(Map map, Label selectedButtonLabel, TabPane mapTabPane, Tab editTab, ListView typeListView, TextField nameTextField, Button addButton, Button discardButton) {

        super(map, selectedButtonLabel, mapTabPane, editTab, typeListView, nameTextField, addButton, discardButton);

    }

    @Override
    public void handle(MouseEvent event) {


        this.selectedButtonLabel.setText("");

        this.mapTabPane.getTabs().add(this.editTab);

        this.mapTabPane.getSelectionModel().select(this.editTab);

    }

}
