package Map.EventHandlers;

import Map.Enums.DestinationType;
import Map.Map;
import Map.Destination;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class EditLocationEventHandler extends AbstractTabEventHandler implements EventHandler<MouseEvent> {


    public EditLocationEventHandler(Map map, Label selectedButtonLabel, TabPane mapTabPane, Tab editTab, ListView typeListView, TextField nameTextField, Button addButton, Button discardButton) {
        super(map, selectedButtonLabel, mapTabPane, editTab, typeListView, nameTextField, addButton, discardButton);
    }

    @Override
    public void handle(MouseEvent event) {


    }

//    @Override
//    public void saveEdit() {
//
//    }
//
//    @Override
//    public void discardEdit() {
//
//    }

}
