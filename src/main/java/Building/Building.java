package Building;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by matthewlemay on 3/21/16.
 */
public class Building implements IStructure {

    private java.util.ArrayList<Level> levels;
    private String name;

    public Building(String pathToJson) {

    }

    public ArrayList<String> getPathDirections(UUID currentLocation, UUID destination)
            throws NameNotFoundException, NoPathFound {

        return null;
    }
}
