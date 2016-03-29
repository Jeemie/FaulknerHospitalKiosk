package Kiosk;

import Map.Location;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.graphstream.graph.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Matt on 3/26/2016.
 */
public class MapController implements Initializable {

    final Logger logger = LoggerFactory.getLogger(MapController.class);
    private MapState mapState;
    private ArrayList<Circle> clickedCircles;
    private boolean clickedCirclesFlag;

    private HashMap<Circle, Node> circleNodes;
    private ArrayList<Line> edges;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView mapImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mapImage.setFitWidth(Main.primaryStage.getWidth());
        mapImage.setFitHeight(Main.primaryStage.getHeight());

        Image image = new Image(getClass().getResource("f1.png").toString());
        mapImage.setImage(image);

        loadNodes();
        loadEdges();

        mapState = MapState.NoEditing;


        anchor.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                switch (mapState) {
                    case AddEdge:
                        logger.info("Add edge mode");

                    case AddNode:
                        logger.info("Add Node mode");
                        addNodeCircle(event.getSceneX(), event.getSceneY());

                    default:
                        logger.info("No Mode");

                }

            }
        });

    }

    private void loadNodes() {

        circleNodes = new HashMap<>();
        clickedCircles = new ArrayList<>();
        clickedCirclesFlag = false;

        ArrayList<Node>  nodes =  Main.map.getNodes();

        for (Node n : nodes) {

            int x = n.getAttribute("x", Integer.class);
            int y = n.getAttribute("y", Integer.class);

            logger.info("Adding node at  x: {} and y: {}", x, y);

            Circle circle = new Circle(x, y, 5);

            circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    checkPoints((Circle)event.getSource());

                    logger.info(event.toString());
                }
            });

            anchor.getChildren().add(circle);


            circleNodes.put(circle, n);
        }

    }

    private void loadEdges() {
        edges = new ArrayList<>();

        for (HashMap.Entry<Circle, Node> e : circleNodes.entrySet()) {

            ArrayList<Node> edgeNodes = Main.map.getEdges(e.getValue());

            if (edgeNodes != null) {

                for (Node n : edgeNodes) {

                    int x = n.getAttribute("x", Integer.class);
                    int y = n.getAttribute("y", Integer.class);

                    Line edgeLine = new Line(e.getKey().getCenterX(), e.getKey().getCenterY(), x, y);
                    edgeLine.setStroke(Color.AQUA);
                    edges.add(edgeLine);
                    anchor.getChildren().add(edgeLine);

                }

            }


        }
    }

    private void checkPoints(Circle circle) {

        clickedCircles.add(circle);

        logger.info("cc", circle.toString());
        logger.info("ccf", clickedCirclesFlag);

        if (clickedCirclesFlag) {

            logger.info("asdad");
            Circle c1 = clickedCircles.get(0);
            Circle c2 = clickedCircles.get(1);

            Node n1 = circleNodes.get(c1);
            Node n2 = circleNodes.get(c2);

            logger.info(n1.toString());
            logger.info(n2.toString());

            Main.map.addEdge(n1, n2);

            Line edgeMarker = new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
            edgeMarker.setStroke(Color.CRIMSON);
            edges.add(edgeMarker);
            anchor.getChildren().add(edgeMarker);

            clickedCircles.clear();

        } else {
            clickedCirclesFlag = true;
        }
    }

    private void addNodeCircle(double x, double y) {

        logger.info("The map was clicked at the position x: {} and y: {}", x, y);

        Circle newNode = new Circle(x, y, 5);

        newNode.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                checkPoints((Circle)event.getSource());

                logger.info(event.toString());
            }
        });

        anchor.getChildren().add(newNode);

        Location location = new Location((int) Math.round(x), ((int) Math.round(y)));

        Node node = Main.map.addLocation(location);

        circleNodes.put(newNode, node);

    }

    @FXML
    private void handleAddNodeButton(ActionEvent event) {
        logger.info("Changing mode to AddNode");
        mapState = MapState.AddNode;
    }

    @FXML
    private void handleAddEdgeButton(ActionEvent event) {
        logger.info("Changing mode to AddEdge");
        mapState = MapState.AddEdge;
    }

    @FXML
    private void handleAddDestinationButton(ActionEvent event) {
        logger.info("Changing mode to AddDestination");
        mapState = MapState.AddDestination;
    }

    @FXML
    private void handleSaveChangesButton(ActionEvent event) {
        logger.info("Saving changes");

        try {
            Main.map.saveGraph();
        } catch (IOException e) {
            logger.error("Error saving map", e);
        }

    }

}
