package HospitalGraph;


import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.algorithm.AStar;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * Created by matthewlemay on 3/26/16.
 */
public class Hospital {

    public static void main(String args[]) {

        ArrayList<String> uuids = new ArrayList<>();
        ArrayList<String> edges = new ArrayList<>();

        Graph graph = new SingleGraph("FaulknerHospitalGraph");
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {


            uuids.add(i, UUID.randomUUID().toString());

            Node node = graph.addNode(uuids.get(i));
            node.setAttribute("x", rand.nextInt(50));
            node.setAttribute("y", rand.nextInt(50));

            ArrayList<Destination> destinations = new ArrayList<>();
            destinations.add(Destination.Bathroom);
            node.setAttribute("destination", destinations);

        }

        Node lastNode =  null;

        for (Node n : graph.getEachNode()) {

            if (lastNode == null) {
                lastNode = n;
            } else {

                edges.add(UUID.randomUUID().toString());
                graph.addEdge(edges.get(edges.size() - 1), lastNode, n);

                lastNode = n;
            }

        }

        edges.add(UUID.randomUUID().toString());
        graph.addEdge(edges.get(edges.size() - 1), lastNode, graph.getNode(0));

        Dijkstra dijkstra = new Dijkstra();
        

        AStar aStar = new AStar(graph);

        aStar.compute(uuids.get(rand.nextInt(uuids.size())), uuids.get(rand.nextInt(uuids.size())));

        System.out.println(aStar.getShortestPath());

        for (Node n : graph.getEachNode())
            System.out.println(n.getAttribute("destination").toString());

        graph.display();
    }

}
