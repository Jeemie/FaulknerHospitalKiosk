package Map.Observers;

import Map.LocationNode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by matt on 4/18/16.
 */
public class FloorLocationNodeObserver implements Observer {

    //
    private Pane locationNodePane;

    //
    private Pane locationNodeEdgePane;

    //
    private ArrayList<LocationNode> floorNodes;

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(FloorLocationNodeObserver.class);





    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating LocationNode: " + o.toString());

        LocationNode currentLocationNode = ((LocationNode) o);

        currentLocationNode.drawAdmin

    }

}
