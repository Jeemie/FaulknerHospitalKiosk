package Map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by mharris382 on 4/5/2016.
 */
public class NodeObserver implements Observer{


    public NodeObserver(){}

    @Override
    public void update(Observable o, Object arg) {

    }
/*
    public void observeHeuristicCost(double value){

        if(watchedHeuristicCost != value){

            watchedHeuristicCost = value;

            setChanged();

            notifyObservers();
        }
    }

    public void observeAdjacentNodes(ArrayList<Node> nodes){


        if(!nodes.equals(watchedAdjacentNodes)){
            if(watchedAdjacentNodes.size() < nodes.size()){
                System.out.println("Node was added");
            }
            else {
                System.out.println("Node was removed");
            }

            watchedAdjacentNodes = nodes;

            setChanged();
        }
        notifyObservers();
    }

    public void observeDestinations(ArrayList<String> destinations){

        //checks if list of destinations is different from the observer's most up-to-date list of destinations
        if(destinations != watchedDestinations){

            //if the list is different, update watchedDestinations
            watchedDestinations = destinations;

            print("destination changed");

            //set the observer to changed
            setChanged();


        }

        notifyObservers();

    }*/
}
