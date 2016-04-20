package Map.EventHandlers;

import Map.LocationNode;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Matt on 4/18/2016.
 */
public class LocationNodeDraggedEventHandler implements EventHandler<MouseEvent> {

    // Location Node associated with the circle event handler
    private final LocationNode locationNode;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationNodeDraggedEventHandler.class);


    /**
     * Default constructor for this class.
     *
     * @param locationNode The location Node that is associated with the circle.
     */
    public LocationNodeDraggedEventHandler(LocationNode locationNode) {

        this.locationNode = locationNode;

        LOGGER.info("Created new LocationNodeDraggedHandler for the Node: " + this.locationNode.toString());

    }


    @Override
    public void handle(MouseEvent event) {

    }

}
