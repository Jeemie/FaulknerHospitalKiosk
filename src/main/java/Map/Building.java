package Map;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.util.Context;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * TODO
 */
public class Building {

    private UUID uniqueID; // A randomly generated UUID associated with the current building
    private ArrayList<Floor> floors; // TODO
    private final AStar aStarSearch; // TODO

    /**
     * TODO
     */
    public Building() {

        this.uniqueID = UUID.randomUUID();
        this.floors = new ArrayList<>();
        this.aStarSearch = new AStar(this);

    }

    /**
     * TODO
     *
     * @param filePath
     * @throws IOException
     */
    public void loadFromFile(URL filePath) throws IOException {
    }

    /**
     * TODO
     *
     * @param filePath
     * @throws IOException
     */
    public void saveToFile(URL filePath) throws IOException {
        try {
            Writer writer = new FileWriter(filePath.toString());
            Gson gson = new GsonBuilder().create();
            gson.toJson(floors, writer);
            writer.close();
        } catch(IOException e) {
            // exception handler code here
            // ...
        }
    }

    /**
     * TODO
     *
     * @param floor
     * @param location
     * @return
     */
    public Node addNode(int floor, Location location) {
        return null;
    }

    /**
     * TODO
     *
     * @param destinationType
     * @return
     */
    public ArrayList<String> getBuildingDestinations(Destination destinationType) {
        return null;
    }

    /**
     * TODO
     *
     * @return
     */
    public ArrayList<String> getBuildingDestinations() {
        return null;
    }

    /**
     * TODO
     *
     * @param floor
     * @return
     */
    public Floor getFloor(int floor) {
        return null;
    }

    /**
     * TODO
     *
     * @param startNode
     * @param destinationNode
     * @return
     */
    public ArrayList<Node> getShortestPath(Node startNode, Node destinationNode) {
        return null;
    }

}