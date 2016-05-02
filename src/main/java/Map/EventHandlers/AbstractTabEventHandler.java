package Map.EventHandlers;

import Map.Map;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

abstract class AbstractTabEventHandler implements EventHandler<MouseEvent> {

    // Building that you want to change the state of
    protected final Map map;

    protected final Label selectedButtonLabel;

    protected final TabPane mapTabPane;

    protected final Tab editTab;

    protected final ListView typeListView;

    protected final TextField nameTextField;

    protected final Button addButton;

    protected final Button discardButton;


    public AbstractTabEventHandler(Map map, Label selectedButtonLabel, TabPane mapTabPane, Tab editTab, ListView typeListView, TextField nameTextField, Button addButton, Button discardButton) {

        this.map = map;
        this.selectedButtonLabel = selectedButtonLabel;
        this.mapTabPane = mapTabPane;
        this.editTab = editTab;
        this.typeListView = typeListView;
        this.nameTextField = nameTextField;
        this.addButton = addButton;
        this.discardButton = discardButton;

    }

}
