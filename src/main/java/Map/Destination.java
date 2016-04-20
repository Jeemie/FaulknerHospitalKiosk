package Map;

import Map.Enums.DestinationType;
import Map.Enums.UpdateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;

/**
 * Created by matt on 4/19/16.
 */
public class Destination extends Observable {

    //
    private String name;

    //
    private DestinationType destinationType;

    //
    private LocationNode currentLocationNode;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Destination.class);


    /**
     * Constructor for Jackson
     */
    public Destination() {

        super();

    }

    /**
     * TODO
     *
     * @param name
     * @param destinationType
     * @param currentLocationNode
     */
    public Destination(String name, DestinationType destinationType, LocationNode currentLocationNode) {

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


    //||\\ Overrides //||\\

    @Override
    public String toString() {

        return this.name;
    }

}
