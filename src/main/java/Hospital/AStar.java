package Hospital;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by matthewlemay on 3/25/16.
 */
public class AStar {

    private ArrayList<INode> nodes;




    public AStar(INode start, INode destination) {

        nodes = createList(start, new ArrayList<INode>());

    }



    private ArrayList<INode> createList(INode node, ArrayList<INode> list) {

        for (INode n : node.getNeighbors()) {

            if (!list.contains(n)) {
                list.add(n);
            }
        }

        return list;
    }
}
