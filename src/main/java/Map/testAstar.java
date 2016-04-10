package Map;

import java.util.List;

import static Map.AStar.constructPath;
import static Map.AStar.getShortestPathTo;

/**
 * Created by trietpham on 4/7/16.
 */
public class testAstar {

    public static void main(String[] args) {
        Building Am = new Building();

        Floor floor = Am.addFloor(3, "Floor1_Final.png");

        //floor.addNode(new Location());

        // mark all the vertices
        LocationNode A = floor.addNode(new Location(1, 1));
        LocationNode B = floor.addNode(new Location(4, 3));
        LocationNode C = floor.addNode(new Location(9, 1));
        LocationNode D = floor.addNode(new Location(9,7));
        LocationNode E = floor.addNode(new Location(2, 7));
        LocationNode F = floor.addNode(new Location(4, 4));
        LocationNode G = floor.addNode(new Location(6, 8));
        LocationNode L = floor.addNode(new Location(11, 3));
        LocationNode K = floor.addNode(new Location(10, 9));


        // set the edges and weight
        A.neighbors = new Neighbors[]{new Neighbors(F),new Neighbors(C)};
        B.neighbors = new Neighbors[]{new Neighbors(F)};
        C.neighbors = new Neighbors[]{new Neighbors(A),new Neighbors(L)};
        D.neighbors = new Neighbors[]{new Neighbors(L),new Neighbors(K)};
        E.neighbors = new Neighbors[]{new Neighbors(F),new Neighbors(G)};
        F.neighbors = new Neighbors[]{new Neighbors(A),new Neighbors(E)};
        G.neighbors = new Neighbors[]{new Neighbors(E)};
        L.neighbors = new Neighbors[]{new Neighbors(C),new Neighbors(D)};
        K.neighbors = new Neighbors[]{new Neighbors(D)};


        getPath(G, L);

    }


    public static void getPath(LocationNode start, LocationNode goal) {
        constructPath(start); // run Dijkstra
        System.out.println("Distance to " + goal + ": " + goal.minDistance);
        List<LocationNode> path = getShortestPathTo(goal);
        System.out.println("Path: " + path);
    }
}


