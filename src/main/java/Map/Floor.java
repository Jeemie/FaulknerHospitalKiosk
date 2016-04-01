package Map;

import java.util.ArrayList;
import java.util.UUID;

public class Floor {

    private final int floor;
    private final UUID uniqueID;
    private ArrayList<Node> nodes;
    private final Building currentBuilding;

    public Floor(int floor, Building currentBuilding) {

        this.floor = floor;
        this.uniqueID = UUID.randomUUID();
        this.nodes = new ArrayList<>();
        this.currentBuilding = currentBuilding;

    }

    public Floor(int floor, UUID uniqueID, Building currentBuilding) {

        this.floor = floor;
        this.uniqueID = uniqueID;
        this.nodes = new ArrayList<>();
        this.currentBuilding = currentBuilding;

    }

    public Node addNode(Location location) {
        return null;
    }

    public ArrayList<Destination> getFloorDestinations(Destination destinationType) {
        return null;
    }

    public ArrayList<Destination> getFloorDestinations() {
        return null;
    }

    public ArrayList<Node> getFloorNodes() {
        return null;
    }


}