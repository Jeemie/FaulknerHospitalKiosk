package AStar;

import org.graphstream.algorithm.AStar;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkDGS;
import org.graphstream.stream.file.FileSource;
import org.graphstream.stream.file.FileSourceDGS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.krb5.internal.crypto.Des;

import java.io.IOException;
import java.util.*;


/**
 * Created by Matt on 3/26/2016.
 */
public class Map {

    private Graph graph;
//    private final Logger log = LoggerFactory.getLogger(Map.class);
    private final String pathToGraph;

    public Map(String pathToGraph) {

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

    public Node addLocation(Location location, int level) {

        Node currentNode = addLocation(location);
        currentNode.addAttribute("z", level);

        return currentNode;
    }

    public Node addLocation(Location location, Destination destination) {

        Node currentNode = addLocation(location);

        String arr[] = {destination.toString()};

        currentNode.addAttribute(destination.toString(), arr);

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

    public ArrayList<Node> getNodes(Destination destination) {

        ArrayList<Node> nodes = new ArrayList<>();

        for (Node n : graph.getEachNode()) {

            if (n.hasAttribute(destination.toString())) {

                String dest = n.getAttribute(destination.toString());

                if (dest != null) {

                    System.out.println(dest);

//                    for (String d : dest) {
//
//                        System.out.println(d);
//                    }
                }
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
