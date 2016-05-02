package Map;

import Map.Enums.CardinalDirection;
import Map.Enums.ImageType;
import Map.Enums.RelativeDirection;
import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by matt on 4/22/16.
 */
public class Path {

    private int currentIndex;

    private ArrayList<LocationNode> originalPath;

    private ImageView imageView;

    private Pane nodePane;

    private Pane edgePane;

    private LocationNode startLocationNode;

    private LocationNode destinationLocationNode;

    private ArrayList<ArrayList<LocationNode>> splitPath;

    private ArrayList<Direction> directions;

    private double xMin, xMax,yMin,yMax,xAverage, yAverage;

    // Logger for this class
    private static final Logger LOGGER = LoggerFactory.getLogger(Path.class);


    public Path(ImageView imageView, Pane nodePane, Pane edgePane, ArrayList<LocationNode> locationNodes) {

        this.currentIndex = -1;
        this.originalPath = locationNodes;
        this.imageView = imageView;
        this.nodePane = nodePane;
        this.edgePane = edgePane;
        this.startLocationNode = locationNodes.get(0);
        this.destinationLocationNode = locationNodes.get(locationNodes.size() - 1);
        this.splitPath = new ArrayList<>();
        this.directions = new ArrayList<>();

    }

    public void  setup() {

        ArrayList<LocationNode> tempPath = new ArrayList<>();

        for (int i = 0; i < this.originalPath.size() - 1; i++) {

            LOGGER.info("i: " + i + " size: " + this.originalPath.size());

            if (this.originalPath.get(i).isSameFloor(this.originalPath.get(i+1))) {

                tempPath.add(this.originalPath.get(i));

            } else {

                tempPath.add(this.originalPath.get(i));

                if (tempPath.size() > 1) {

                    this.splitPath.add(tempPath);

                }

                tempPath = new ArrayList<>();

            }

        }

        tempPath.add(this.originalPath.get(this.originalPath.size() - 1));

        if (tempPath.size() > 0) {

            this.splitPath.add(tempPath);

        }

        // Set the directions object according to the path given
        setupDirections(originalPath);




        drawNextFloor();
    }

    public void drawNextFloor() {

        if (!(this.currentIndex == (this.splitPath.size() - 1))) {

            this.currentIndex++;

        }

        drawFloorPath();


    }

    public void drawPreviousFloor() {

        if (!(this.currentIndex == 0)) {

            this.currentIndex--;

        }

        drawFloorPath();

    }

    private void drawFloorPath() {

        LOGGER.info(this.splitPath.toString());
        LOGGER.info("Path part: " + this.currentIndex);

        ArrayList<LocationNode> temp = this.splitPath.get(this.currentIndex);
        Floor tempFloor = temp.get(0).getCurrentFloor();

        tempFloor.drawFloor(this.imageView);

        this.edgePane.getChildren().clear();
        this.nodePane.getChildren().clear();

        for (LocationNode n: tempFloor.getFloorLocationNodes(ImageType.BATHROOM)){
            n.drawNormal(this.nodePane, ImageType.BATHROOM, -10, 0);
        }

        for (LocationNode n: tempFloor.getFloorLocationNodes(ImageType.STORE)){
            n.drawNormal(this.nodePane, ImageType.STORE, -10, 0);
        }

        if (this.startLocationNode.equals(temp.get(0))) {

            this.startLocationNode.drawNormal(this.nodePane, ImageType.STARTLOCATION, -10, 0);

        } else {

            temp.get(0).drawNormal(this.nodePane, -10, 0);

        }


        if (this.destinationLocationNode.equals(temp.get(temp.size() - 1))) {

            this.destinationLocationNode.drawNormal(this.nodePane, ImageType.DESTINATION, -10, 20);

        } else {

            temp.get(temp.size() - 1).drawNormal(this.nodePane, -10, 10);

        }

        drawEdgesNormal(this.edgePane, temp);
        yMin = temp.get(0).getLocation().getY();
        xMin = temp.get(0).getLocation().getX();
        yMax = temp.get(0).getLocation().getY();
        xMax = temp.get(0).getLocation().getX();

        for( int i =0 ; i <= temp.size()-1;i++){
                double xnum =  temp.get(i).getLocation().getX();
                double ynum =  temp.get(i).getLocation().getY();
                if(xnum<xMin){
                    xMin=xnum;
                }
                if(xnum>xMax){
                    xMax=xnum;
                }
                if(ynum<yMin){
                    yMin =ynum;
                }
                if(ynum>yMax){
                    yMax =ynum;
                }


        }
        xAverage = (xMax +xMin)/2.0;
        yAverage = (yMax +yMin)/2.0;

    }

    private void drawEdgesNormal(Pane pane, ArrayList<LocationNode> path) {

        LocationNode current;
        LocationNode next;

        // For each node in the path
        // Number of edges = number of nodes in path - 1
        for (int i = 0; i < path.size() - 1; i++) {

            current = path.get(i);
            next = path.get(i+1);

            // Find edge between specified nodes
            for (LocationNodeEdge edge : current.getEdges()) {

                if(edge.isEdgeBetweenNodes(current, next)) {

                    // Found edge between two specified nodes
                    edge.drawEdge(pane);
                    break;

                }

            }

        }

    }

    public double getxMin() {
        System.out.println("xMin" + xMin);
        return xMin;

    }

    public double getxMax() {
        System.out.println("xMax"+xMax);
        return xMax;
    }

    public double getyMin() {
        System.out.println("Ymin "+yMin);
        return yMin;
    }

    public double getyMax() {
        System.out.println("Ymax "+yMin);
        return yMax;
    }

    public double getYAverage() {
        System.out.println("YAverage "+yAverage);
        return xAverage;
    }

    public double getxAverage() {
        System.out.println("xAverage "+xAverage);
        return yAverage;
    }

    private void setupDirections(ArrayList<LocationNode> path) {

        // Create three LocationNodes to create two CardinalDirections
        LocationNode firstLNode, secondLNode, thirdLNode;

        // Create two CardinalDirections to create one RelativeDirection
        CardinalDirection pastCDirection, currentCDirection;

        // Two locations used to display distance between two nodes
        Location startTurnLoc, endTurnLocation;

        // Two locations used to determine the end elevator
        LocationNode elevator;

        // The three values to add to the directions list at the end of each loop
        RelativeDirection   currentRelativeDirection = null;
        String              currentTextualDirection = "";
        int                 currentDistanceBetweenLocations = 0;
        Direction           currentDirection;

        // Boolean endDirection
        boolean endDirection = false;

        // Boolean endElevetor
        boolean endElevator = false;

        // Boolean for endOfHall
        boolean  endOfHall;

        // Todo edge case if path includes only one nodes
        // Todo edge case if path includes only two nodes

        startTurnLoc = path.get(0).getLocation();

        for(int i = 1; i < (path.size() - 1); i++) {

            // Declare first three nodes
            firstLNode = path.get(i - 1);
            secondLNode = path.get(i);
            thirdLNode = path.get(i + 1);

            // Create CardinalDirections
            pastCDirection = firstLNode.getDirectionsTo(secondLNode);
            currentCDirection = secondLNode.getDirectionsTo(thirdLNode);

//            endOfHall = true;
//            for (LocationNode neighbor : secondLNode.getAdjacentLocationNodes())
//                if(secondLNode.getDirectionsTo(neighbor) == firstLNode.getDirectionsTo(secondLNode)) {
//                    endOfHall = false;
//                }
//            }


            if (!secondLNode.onSameFloor(thirdLNode)) {

                if (!endElevator) {
                    endElevator = true;
                }

            } else if (endElevator) { // Only reaches here if secondLNode and thirdLNode are on the same floor

                elevator = secondLNode;

                currentRelativeDirection = RelativeDirection.ELEVATOR;
                currentTextualDirection = "Take the elevator to " + elevator.getCurrentFloor().getFloorName();

                endElevator = false;
                endDirection = true;

            }
            else if (pastCDirection == currentCDirection) { // Next nodes is straight

                if (i == path.size() - 2) { // Reach the end of the path

                    currentRelativeDirection = RelativeDirection.STRAIGHT;
                    currentTextualDirection = "Go Straight";

                    endDirection = true;

                }

            } else { // There is a turn

                endDirection = true;

                // Check cardinalDirection relations, and out the right direction
                if (pastCDirection.right() == currentCDirection) {

                    currentRelativeDirection = RelativeDirection.RIGHT;
                    currentTextualDirection = "Turn Right";

                } else if (pastCDirection.left() == currentCDirection) {

                    currentRelativeDirection = RelativeDirection.LEFT;
                    currentTextualDirection = "Turn Left";

                } else if (pastCDirection.opposite() == currentCDirection) {

                    currentRelativeDirection = RelativeDirection.BACK;
                    currentTextualDirection = "Turn around"; //Should actually not happen

                }
            }


            if (endDirection) { //Save the destination and reset

                currentDistanceBetweenLocations = startTurnLoc.getFeetDistanceBetween(thirdLNode.getLocation());
                currentTextualDirection = "After " + currentDistanceBetweenLocations + " feet,\n" + currentTextualDirection;

                if (i == path.size() - 2) { // Reach the end of the path

                    currentTextualDirection += " and,\nYou will reach " + thirdLNode.getName();

                    endDirection = true;

                }

                currentDirection = new Direction (  currentRelativeDirection,
                                                    currentTextualDirection,
                                                    currentDistanceBetweenLocations,
                                                    thirdLNode);
                directions.add(currentDirection);

                startTurnLoc =  thirdLNode.getLocation();

                endDirection = false;
            }

        }

       /*

        // Set's the direction objects

        // Create three LocationNodes to create two CardinalDirections
        LocationNode firstLNode, secondLNode, thirdLNode;

        // Create two CardinalDirections to create one RelativeDirection
        CardinalDirection pastCDirection, currentCDirection;

        // A count to help calculate junctions
        int junctionCount = 0;

        // A two locations used to display distance between two nodes
        Location startTurnLoc, endDirectionLocation;

        // If there is only one node in the path or less, then return nothing.
        if (path.size() < 2) {

            return; //Return with empty array
        }

        // Set the direction to straight if path only has 2 nodes
        directions.add( new Direction(RelativeDirection.STRAIGHT,
                "Go straight",
                (int) path.get(0).getDistanceBetweenNodes(path.get(1))));

        // Will run if path.size() is 3
        // Go through the path, and create 1 RelativeDirection through 2 CardinalDirections
        // by using 3 LocationNodes each loop
        for (int i = 1; i < (path.size() - 1); i++) {

            //Declare first three nodes
            firstLNode = path.get(i - 1);
            secondLNode = path.get(i);
            thirdLNode = path.get(i + 1);

            // Set the startTurnLoc
            startTurnLoc = secondLNode.getLocation();


            //Create CardinalDirections
            pastCDirection = firstLNode.getDirectionsTo(secondLNode);
            currentCDirection = secondLNode.getDirectionsTo(thirdLNode);

            //Create a string to put into the textualDirections arraylist
            String currentTextDirection = "";

            //Create RelationalDirection based on the two cardinal direction
            //If the directions are the same, go forward
            if (pastCDirection == currentCDirection) {

                junctionCount++; //Increment junctionCount to know the number of junctions
                if (i == path.size() - 2) { //If it's the last part of the path

                    endDirectionLocation = thirdLNode.getLocation();

                    int currentFeetDistance = startTurnLoc.getFeetDistanceBetween(endDirectionLocation);

                    directions.add(new Direction(RelativeDirection.STRAIGHT,
                            "After " + currentFeetDistance + " feet,\n" +
                                    "Go straight, you've reached destination",
                            0));

                }

            } else {

                endDirectionLocation = thirdLNode.getLocation();

                int currentFeetDistance = startTurnLoc.getFeetDistanceBetween(endDirectionLocation);

                currentTextDirection = "After " + currentFeetDistance + " feet,\n";

                //Change how sentence junction is structured depending on junctionCount number
                currentTextDirection += "take the ";
                switch (junctionCount) {
                    case 0:
                        currentTextDirection += "next ";
                        break;
                    case 1:
                        currentTextDirection += (junctionCount + 1) + "nd "; //2nd
                        break;
                    case 2:
                        currentTextDirection += (junctionCount + 1) + "rd "; //3rd
                        break;
                    default:
                        currentTextDirection += (junctionCount + 1) + "th "; //nth
                        break;
                }

                //Check cardinalDirection relations, and output the right direction
                if (pastCDirection.right() == currentCDirection) {

                    currentTextDirection += "Right";

                    directions.add(new Direction(RelativeDirection.RIGHT,
                            currentTextDirection,
                            currentFeetDistance));

                } else if (pastCDirection.left() == currentCDirection) {
                    currentTextDirection += "Left";

                    directions.add(new Direction(RelativeDirection.LEFT,
                            currentTextDirection,
                            currentFeetDistance));

                } else if (pastCDirection.opposite() == currentCDirection) {
                    currentTextDirection += "Back"; //Should actually not happen

                    directions.add(new Direction(RelativeDirection.BACK,
                            currentTextDirection,
                            currentFeetDistance));

                }

                //Reset junctionCount
                junctionCount = 0;

            }
            if (!secondLNode.onSameFloor(thirdLNode)) { // Multi-level edge

                endDirectionLocation = thirdLNode.getLocation();

                int currentFeetDistance = startTurnLoc.getFeetDistanceBetween(thirdLNode.getLocation());

                directions.add(new Direction(RelativeDirection.ELEVATOR,
                        "After " + currentFeetDistance + " feet, \n" +
                                " take the elevator to " + thirdLNode.getCurrentFloor().getFloorName() + ".",
                        0));
            }
        }
        */
    }

    public ArrayList<Direction> getDirections() {

        return directions;

    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public ArrayList<ArrayList<LocationNode>> getSplitPath() {
        return splitPath;
    }
    //    public void getPathLocation(ArrayList<LocationNode>path){
//        yMin = path.get(0).getLocation().getY();
//        xMin = path.get(0).getLocation().getX();
//        yMax = path.get(0).getLocation().getY();
//        xMax = path.get(0).getLocation().getX();
//
//        for( int i =0 ; i <= path.size();i++){
//            if (path.get(i-1).getCurrentFloor().equals(path.get(i).getCurrentFloor())){
//                double xnum =  path.get(i).getLocation().getX();
//                double ynum =  path.get(i).getLocation().getY();
//                if(xnum<xMin){
//                    xMin=xnum;
//                }
//                if(xnum>xMax){
//                    xMax=xnum;
//                }
//                if(ynum<yMin){
//                    yMin =ynum;
//                }
//                if(ynum>yMax){
//                    yMax =ynum;
//                }
//            }
//            else{
//                break;
//            }
//        }
//        System.out.println(xMax);
//        System.out.println(yMax);
//    }

    }

