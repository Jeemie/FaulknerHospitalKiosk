package Map.EventHandlers;

import Map.Enums.MapState;
import Map.Map;
import Map.LocationNode;
import Map.Location;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
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

        Map currentMap = this.locationNode.getCurrentFloor().getCurrentBuilding().getCurrentMap();

        if (currentMap.getCurrentMapState().equals(MapState.MOVENODE)) {

            LOGGER.info("Moving LocationNode " + this.locationNode.toString());

            Location circleLocation = this.locationNode.getLocation();

            double offsetX = event.getSceneX() - circleLocation.getX();
            double offsetY = event.getSceneY() - circleLocation.getY();
            double newTranslateX = ((ImageView)(event.getSource())).getTranslateX() + offsetX;
            double newTranslateY = ((ImageView)(event.getSource())).getTranslateY() + offsetY;

            ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
            ((ImageView)(event.getSource())).setTranslateY(newTranslateY);

            circleLocation.setX(event.getSceneX());
            circleLocation.setY(event.getSceneY());

        }

    }

}
