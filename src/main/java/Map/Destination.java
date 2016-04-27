package Map;

import Map.Enums.DestinationType;
import Map.Enums.UpdateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.UUID;

/**
 * Created by matt on 4/19/16.
 */
public class Destination extends Observable {

    // Unique ID for this edge
    private UUID uniqueID;

    private String name;

    private DestinationType destinationType;

    // LocationNode where this destination is located
    private LocationNode currentLocationNode;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Destination.class);

    /**
     * TODO
     *
     * @param name
     * @param destinationType
     * @param currentLocationNode
     */
    public Destination(String name, DestinationType destinationType, LocationNode currentLocationNode) {

        this.uniqueID = UUID.randomUUID();
        this.name = name;
        this.destinationType = destinationType;
        this.currentLocationNode = currentLocationNode;

        this.addObserver(this.currentLocationNode);

    }

    /**
     * Constructor used only for loading purposes.
     * @param name
     * @param uniqueID
     * @param destinationType
     * @param currentLocationNode
     */
    public Destination(String name, UUID uniqueID, DestinationType destinationType, LocationNode currentLocationNode) {

        this.uniqueID = uniqueID;
        this.name = name;
        this.destinationType = destinationType;
        this.currentLocationNode = currentLocationNode;

        this.addObserver(this.currentLocationNode);

    }

    /**
     * TODO
     *
     * @param destinationType
     * @return
     */
    public boolean isDestinationType(DestinationType destinationType) {

        return this.destinationType.equals(destinationType);
    }


    //||\\ Getters and Setters //||\\

    public void setName(String name) {

        this.name = name;

        setChanged();
        notifyObservers(UpdateType.DESTINATIONCHANGE);

    }

    public void setDestinationType(DestinationType destinationType) {

        this.destinationType = destinationType;

        setChanged();
        notifyObservers(UpdateType.DESTINATIONCHANGE);
    }


    public String getName() {

        return name;
    }

    public DestinationType getDestinationType() {

        return destinationType;
    }

    public LocationNode getCurrentLocationNode() {

        return currentLocationNode;
    }

    public UUID getUniqueID() {
        
        return uniqueID;
    }

    //||\\ Overrides //||\\

    @Override
    public String toString() {

        return this.name;
    }

}