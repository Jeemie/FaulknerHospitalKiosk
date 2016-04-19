package Map.EventHandlers;

import Map.BuildingState;
import Map.LocationNodeRefactored;
import Utils.FixedSizedStack;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Matt on 4/18/2016.
 */
public class LocationNodeRefactoredClickedEventHandler implements EventHandler<MouseEvent> {


    // Location Node associated with the circle event handler
    private final LocationNodeRefactored locationNode;

    // Stack of the past 10 node click actions
    private static FixedSizedStack<java.util.Map.Entry<LocationNodeRefactored, BuildingState>> previousActions = new
            FixedSizedStack<>(10);

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeClickedEventHandler.class);


    /**
     * Default constructor for this class.
     *
     * @param locationNode The location Node that is associated with the circle.
     */
    public LocationNodeRefactoredClickedEventHandler(LocationNodeRefactored locationNode) {

        this.locationNode = locationNode;

        LOGGER.info("Created new LocationNodeClickedHandler for the Node: " + this.locationNode.toString());

    }

    @Override
    public void handle(MouseEvent event) {

    }

}
