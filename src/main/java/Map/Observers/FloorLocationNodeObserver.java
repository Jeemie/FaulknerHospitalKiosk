package Map.Observers;

import Map.Building;
import Map.LocationNodeRefactored;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 * Created by matt on 4/18/16.
 */
public class FloorLocationNodeObserver extends Observable implements Observer {



    private String floorName;

    private UUID uniqueID;

    private Building currentBuilding;



    //
    private LocationNodeRefactored currentlySelectedLocationNode;

    //
    private ObservableList<LocationNodeRefactored> currentlySelectedAdjacentLocationNodes;

    //
    private ObservableList<String> currentlySelectedLocationNodeDestinatioms;

    //
    private Pane locationNodePane;

    //
    private Pane locationNodeEdgePane;

    //
    private ImageView floorImage;

    //
    private ArrayList<LocationNodeRefactored> floorNodes;

    //
    private static final Logger LOGGER = LoggerFactory.getLogger(FloorLocationNodeObserver.class);





    @Override
    public void update(Observable o, Object arg) {

        LOGGER.info("Updating LocationNode: " + o.toString());

        LocationNodeRefactored currentLocationNode = ((LocationNodeRefactored) o);

        currentLocationNode.drawAdmin(this.locationNodePane);

    }

}
