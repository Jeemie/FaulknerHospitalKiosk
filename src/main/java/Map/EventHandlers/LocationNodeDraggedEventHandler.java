package Map.EventHandlers;

import Map.Enums.MapState;
import Map.Map;
import Map.LocationNode;
import Map.Location;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
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

        currentMap.setCurrentLocationNode(this.locationNode);

        if (currentMap.getCurrentMapState().equals(MapState.MOVENODE)) {

            LOGGER.info("Moving LocationNode " + this.locationNode.toString());

            Location labelLocation = this.locationNode.getLocation();

            double offsetX = event.getSceneX() - labelLocation.getX();
            double offsetY = event.getSceneY() - labelLocation.getY();

            double newTranslateX = ((Label)(event.getSource())).getTranslateX() + offsetX;
            double newTranslateY = ((Label)(event.getSource())).getTranslateY() + offsetY;

//            ((Label)(event.getSource())).setTranslateX(newTranslateX);
//            ((Label)(event.getSource())).setTranslateY(newTranslateY);

            labelLocation.setX(event.getSceneX());
            labelLocation.setY(event.getSceneY());

        }

    }

}
