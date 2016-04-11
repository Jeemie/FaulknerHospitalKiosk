package Map;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An event handler for the Location Node when it has been dragged in the Admin Panel.
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

        LOGGER.info("Created new LocationNodeDraggedHandler for the Node: " + this.toString());

        this.locationNode = locationNode;

    }


    /**
     * Handler for when the node's circle is dragged.
     *
     * @param event Event that describes the scenario in which the circle was dragged.
     */
    @Override
    public void handle(MouseEvent event) {

        LOGGER.info("Node " + this.locationNode.toString() + " was dragged with the state " +
                this.locationNode.getState().toString());

        if (locationNode.getState() == BuildingState.MOVENODE) {

            LOGGER.info("Moving Node " + this.locationNode.toString());

            Location circleLocation = this.locationNode.getLocation();

            double offsetX = event.getSceneX() - circleLocation.getX();
            double offsetY = event.getSceneY() - circleLocation.getY();
            double newTranslateX = ((Circle)(event.getSource())).getTranslateX() + offsetX;
            double newTranslateY = ((Circle)(event.getSource())).getTranslateY() + offsetY;

            ((Circle)(event.getSource())).setTranslateX(newTranslateX);
            ((Circle)(event.getSource())).setTranslateY(newTranslateY);



            this.locationNode.setLocation(new Location(event.getSceneX(), event.getSceneY()));

        }

    }

}
