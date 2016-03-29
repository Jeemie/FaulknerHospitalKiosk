package Map;

import org.apache.log4j.BasicConfigurator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkDGS;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceDGS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


/**
 * Created by Matt on 3/26/2016.
 */
public class Map {

    private Graph graph;
    private final Logger log = LoggerFactory.getLogger(Map.class);
    private final String pathToGraph;

    public Map() {
        BasicConfigurator.configure();

        this.pathToGraph = getClass().getResource("graph.DGS").toString();

        log.info("Creating a graph with the file " + pathToGraph);

        graph = new SingleGraph("Faulkner Hospital");
        FileSource fileSource = new FileSourceDGS();

        fileSource.addSink(graph);

        try {
            fileSource.readAll(getClass().getResource("graph.DGS"));
        } catch (IOException e) {
            log.error("could read from file " + pathToGraph, e);
        } finally {
            fileSource.removeSink(graph);
        }

    }

    public Map(String pathToGraph) {
        BasicConfigurator.configure();

        this.pathToGraph = pathToGraph;

//        log.info("Creating a graph with the file " + pathToGraph);

        graph = new SingleGraph("Faulkner Hospital");
        FileSource fileSource = new FileSourceDGS();

        fileSource.addSink(graph);

        try {
            fileSource.readAll(pathToGraph);
        } catch (IOException e) {
//            log.error("could read from file " + pathToGraph, e);
        } finally {
            fileSource.removeSink(graph);
        }

    }

    public void reloadGraph() throws IOException {

        FileSource fileSource = new FileSourceDGS();

        fileSource.addSink(graph);
        fileSource.readAll(pathToGraph);
    }

    public void reloadGraph(String pathToGraph) throws IOException {

        FileSource fileSource = new FileSourceDGS();

        fileSource.addSink(graph);
        fileSource.readAll(pathToGraph);
    }


    public void saveGraph() throws IOException {

        FileSinkDGS fileSink = new FileSinkDGS();

        fileSink.writeAll(graph, pathToGraph);
    }

    public void saveGraph(String pathToGraph) throws IOException {

        FileSinkDGS fileSink = new FileSinkDGS();

        fileSink.writeAll(graph, pathToGraph);
    }

    public Node addLocation(Location location) {

        String suuid  = UUID.randomUUID().toString();

        Node currentNode = graph.addNode(suuid);

        currentNode.addAttribute("x", location.getX());
        currentNode.addAttribute("y", location.getY());

        return currentNode;
    }

    public void addEdge(Node node1, Node node2) {

        String suuid  = UUID.randomUUID().toString();

        graph.addEdge(suuid, node1, node2);

        log.info("adding edge for node");

    }

    public void addEdge(Location node1, Location node2) {

        String suuid  = UUID.randomUUID().toString();

        Node n1 = getNode(node1);
        Node n2 = getNode(node2);


        assert n1 != null;
        assert n2 != null;

        graph.addEdge(suuid, n1, n2);

        log.info("adding edge");

    }

    public Node getNode(Location location) {

        for (Node n : graph.getEachNode()) {

            if (location.getX() == n.getAttribute("x", Integer.class) && location.getY() == n.getAttribute("y", Integer.class)) {
                return n;
            }
        }

        return null;
    }

    public Node addLocation(Location location, int level) {

        Node currentNode = addLocation(location);
        currentNode.addAttribute("z", level);

        return currentNode;
    }

    public Node addLocation(Location location, Destination destination) {

        Node currentNode = addLocation(location);

        currentNode.addAttribute(destination.toString(), destination.getName());

        return currentNode;

    }

    public Node addLocation(Location location, int level, Destination destination) {

        Node currentNode = addLocation(location, destination);
        currentNode.addAttribute("z", level);

        return currentNode;
    }

    public ArrayList<Node> getNodes() {

        ArrayList<Node> nodes = new ArrayList<>();

        for (Node n : graph.getEachNode()) {
            nodes.add(n);
        }

        return nodes;
    }

    public ArrayList<Node> getEdges(Node node) {

        ArrayList<Node> edgeNodes = new ArrayList<>();

        while(node.getNeighborNodeIterator().hasNext()) {

            Node n = node.getNeighborNodeIterator().next();

            if (edgeNodes.contains(n)) {
                return edgeNodes;
            }

            edgeNodes.add(node.getNeighborNodeIterator().next());
        }

        return edgeNodes;
    }

    public ArrayList<Node> getNodes(Destination destination) {

        ArrayList<Node> nodes = new ArrayList<>();

        for (Node n : graph.getEachNode()) {

            if (n.hasAttribute(destination.toString())) {

                String dest = n.getAttribute(destination.toString());

                if (dest != null) {
                    nodes.add(n);
                }

//                try {
//                    String dest[] = n.getAttribute(destination.toString());
//
//                    if (dest != null) {
//                        nodes.add(n);
//                    }
//
//                } catch (ClassCastException e) {
//                    String dest = n.getAttribute(destination.toString());
//
//
//                }

            }
        }

        return nodes;
    }

    public static void main(String args[]) {

        Map map = new Map("C:\\Users\\Matt\\Desktop\\graph.DGS");

        String name  = "apples";

        for (Node n : map.getNodes()) {
            System.out.println(n.toString());
        }

        System.out.println("doctors");

        for (Node n : map.getNodes(Destination.Physician)) {
            System.out.println(n.toString());
        }

        Random rand = new Random();
        Location location = new Location(rand.nextInt(100), rand.nextInt(100));
        List<Destination> destinations = Collections.unmodifiableList(Arrays.asList(Destination.values()));

        map.addLocation(location, destinations.get(rand.nextInt(6)));

        try {
            map.saveGraph();
        } catch (IOException e) {
            System.out.println("Not Saved");
        }
    }

}
