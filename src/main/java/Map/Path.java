package Map;

import Map.Enums.ImageType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;

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

    private Directions directions;

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

    }

    public void  setup() {

        ArrayList<LocationNode> tempPath = new ArrayList<>();

        for (int i = 0; i < this.originalPath.size() - 1; i++) {

            LOGGER.info("i: " + i + " size: " + this.originalPath.size());

            if (this.originalPath.get(i).isSameFloor(this.originalPath.get(i+1))) {

                tempPath.add(this.originalPath.get(i));

            } else {

                tempPath.add(this.originalPath.get(i));

                this.splitPath.add(tempPath);
                tempPath = new ArrayList<>();

            }

        }

        tempPath.add(this.originalPath.get(this.originalPath.size() - 1));

        if (tempPath.size() > 0) {

            this.splitPath.add(tempPath);

        }

        //Set the directions
        directions = new Directions(originalPath);




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
        LOGGER.info("Drawing floor " + this.currentIndex);

        ArrayList<LocationNode> temp = this.splitPath.get(this.currentIndex);
        Floor tempFloor = temp.get(0).getCurrentFloor();

        tempFloor.drawFloor(this.imageView);

        this.edgePane.getChildren().clear();
        this.nodePane.getChildren().clear();

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

    public Directions getDirections() {
        return directions;
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

