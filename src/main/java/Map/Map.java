package Map;

import Map.Enums.UpdateType;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Map implements Observer {


    //
    private String name;

    //
    private ArrayList<Building> mapBuildings;


    //||\\ Current LocationNode //||\\

    //
    private LocationNode currentLocationNode;

    //
    private ObservableList<LocationNode> currentAdjacentLocationNodes;

    //
    private ObservableList<Destination> currentLocationNodeDestinations;


    //||\\ Current Floor //||\\

    //
    private Floor currentFloor;

    //
    private ObservableList<LocationNode> currentFloorLocatioNodes;

    //
    private ObservableList<Destination> currentFloorDestinations;

    //
    private Pane currentFloorLocationNodePane;

    //
    private Pane currentFloorEdgePane;

    //
    private ImageView currentFloorImage;


    //||\\ Current Building //||\\

    //
    private Building currentBuilding;

    //
    private ObservableList<Floor> currentBuildingFloors;

    //
    private ObservableList<Destination> currentBuildingDestinations;



    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Map.class);










    @Override
    public void update(Observable o, Object arg) {

        // Check to see if the argument is null
        if (arg == null) {

            LOGGER.debug("Observer was updated but the argument was null: ", arg);

            return;
        }

        LOGGER.info("Updating the Map");

        UpdateType updateType = ((UpdateType) arg);

        switch (updateType) {

            case DESTINATIONCHANGE:


                // remove current location node destinations from current floor destinations and building destinations
                this.currentFloorDestinations.removeAll(this.currentLocationNodeDestinations);
                this.currentBuildingDestinations.removeAll(this.currentLocationNodeDestinations);

                // Update currentLocationNodeDestinations by clearing the list, and replacing it with the getDestinations function
                this.currentLocationNodeDestinations.clear();
                this.currentLocationNodeDestinations.addAll(this.currentLocationNode.getDestinations());

                // Add current location node destinations from current floor and building destinations
                this.currentFloorDestinations.addAll(this.currentLocationNodeDestinations);
                this.currentBuildingDestinations.addAll(this.currentLocationNodeDestinations);


                break;

            case LOCATIONNODEPOSITION:

                this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                break;

            case LOCATIONNODEADDED:

                this.currentLocationNode.drawAdmin(this.currentFloorLocationNodePane);
                this.currentLocationNode.drawEdgesAdmin(this.currentFloorEdgePane);

                // TODO decide whether or not we are going to redraw the entire floor
                break;

            case FLOORADDED:

                // TODO
                break;


            default:

                break;




        }

    }

}