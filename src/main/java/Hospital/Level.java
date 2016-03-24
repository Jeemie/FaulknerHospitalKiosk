package Hospital;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import Utils.Pixel;

/**
 * Created by matthewlemay on 3/23/16.
 */
public class Level {

    private boolean level[][];
    private HashMap<String, UUID> locations;


    public Level(String pathToJson, int levelNumber) throws IOException {
    }

    public boolean hasRoom(UUID room) {

        return true;
    }

    public ArrayList<Pixel> path() throws NoPathFound, LocationDoesNotExist {

        // calls AStar that way we can change the algorithm without changing the function call.

        return new ArrayList<Pixel>();
    }

    private ArrayList<Pixel> AStar() throws NoPathFound {


        return new ArrayList<Pixel>();
    }

}
