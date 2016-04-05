package Map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mharris382 on 4/5/2016.
 */
public class NodeObserver implements Observer{
    private ArrayList<Node> observed_nodes;


    public NodeObserver(){
        this.observed_nodes = new ArrayList<>();
    }


    public void observeNode(Node n){

        //if not already watching this node
        if(!observed_nodes.contains(n)){

            System.out.println("observing new node");

            //add this node to list of watching nodes
            observed_nodes.add(n);

            //add an observer to watch the node
            n.addObserver(n.getNodeObserver());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("NodeObserver called update");
    }

}
