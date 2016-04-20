package Map;

import Map.Exceptions.FloorDoesNotExistException;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import static Map.AStar.constructPath;
import static Map.AStar.getShortestPathTo;

/**
 * A class the represents a building.
 */

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="uniqueID", scope=Building.class)
public class Building extends Observable implements Observer {

    //
    private String name;









    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public String toString() {

        return this.name;
    }
}
