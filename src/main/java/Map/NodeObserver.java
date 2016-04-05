package Map;

import java.util.ArrayList;
import java.util.Observable;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by mharris382 on 4/5/2016.
 */
public class NodeObserver extends Observable{

    private double watchedHeuristicCost;
    private ArrayList<Node> watchedAdjacentNodes = new ArrayList<>();
    private ArrayList<String> watchedDestinations = new ArrayList<>();

    public NodeObserver(){}

    public void observeHeuristicCost(double value){

        if(watchedHeuristicCost != value){

            watchedHeuristicCost = value;

            setChanged();

            notifyObservers();
        }
    }

    public void observeAdjacentNodes(ArrayList<Node> nodes){
        if(!nodes.equals(watchedAdjacentNodes)){

            watchedAdjacentNodes = nodes;

            setChanged();

            notifyObservers();
        }
    }

    public void observeDestinations(ArrayList<String> destinations){
        if(destinations != watchedDestinations){

            watchedDestinations = destinations;
            print("destination changed");

            setChanged();

            notifyObservers();

        }
    }
}
