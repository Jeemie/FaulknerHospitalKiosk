package Map;

import sun.security.krb5.internal.crypto.Des;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.UUID;

public class Node {

    private double heuristicCost;
    private final UUID uniqueID;
    private Location location;
    private ArrayList<Node> adjacentNodes;
    private EnumMap<Destination, ArrayList<String>> destinations;
    private Floor currentFloor;

    public Node(double heuristicCost, Location location, Floor currentFloor) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = UUID.randomUUID();
        this.location = location;
        this.adjacentNodes = new ArrayList<>();
        this.destinations = new EnumMap<Destination, ArrayList<String>>(Destination.class);
        this.currentFloor = currentFloor;


    }

    public Node(double heuristicCost, UUID uniqueID, Location location, Floor currentFloor, EnumMap<Destination, ArrayList<String>> destinations) {

        this.heuristicCost = heuristicCost;
        this.uniqueID = uniqueID;
        this.location = location;
        this.adjacentNodes = new ArrayList<>();
        this.destinations = destinations;
        this.currentFloor = currentFloor;

    }

    public void addDestination(Destination destination) {

        //temporary variable to hold values
        ArrayList<String> temp;

        //check if the DestinationType exists already
        if(destinations.containsKey(destination)){

            //sets temp equal to
            temp = destinations.get(destination);
            temp.add(destination.getName());

        } else {

            temp = new ArrayList<String>();
            temp.add(destination.getName());

            destinations.put(destination,temp);

        }

    }

    public void removeDestination(Destination destination) {

        //check if destination of current type exists
        if(destinations.containsKey(destination)){
            //remove destination from list
            destinations.remove(destination);
        }

    }

    //changed from ArrayList<Destination> to ArrayList<String>
    public ArrayList<String> getDestinations(Destination destinationType) {

        //checks if the key entered is a valid key
        if (destinations.containsKey(destinationType)) {

            //returns the ArrayList<String> associated with the entered key
            return destinations.get(destinationType);

        }else { //throw exception - tried to enter an invalid destinationType

            //return empty ArrayList
            return new ArrayList<>();
        }
    }
    //changed from ArrayList<Destination> to ArrayList<String>
    public ArrayList<String> getDestinations() {



        //create temporary variable to hold values
        ArrayList<String> temp= new ArrayList<>();

        //iterate through Physicians
        for(int i = 0; i < destinations.get("Physician").size(); i++){
            temp.add(destinations.get("Physician").get(i));
        }

        //iterate through Departments
        for(int i = 0; i < destinations.get("Department").size(); i++){
            temp.add(destinations.get("Department").get(i));
        }

        //iterate through Bathrooms
        for(int i = 0; i < destinations.get("Bathroom").size(); i++){
            temp.add(destinations.get("Bathroom").get(i));
        }

        //iterate through Elevators
        for(int i = 0; i < destinations.get("Elevator").size(); i++){
            temp.add(destinations.get("Elevator").get(i));
        }

        //iterate through Stairs
        for(int i = 0; i < destinations.get("Stairs").size(); i++){
            temp.add(destinations.get("Stairs").get(i));
        }

        //returns the temporary array containing all the destination Strings in destinations
        return temp;
        

    }

    public void addAdjacentNode(Node adjacentNode) {

        //if the list does not already contain the adjacentNode
        if(adjacentNodes.contains(adjacentNode)!=true){

            //add the Node
            adjacentNodes.add(adjacentNode);
        }

        else{} //throw exception - tried to add a node that already exists in adjacentNodes
    }

    public double getDistanceBetweenNodes(Node destinationNode) {

        Location temp = destinationNode.getLocation();

        //returns the distance between the destinationNode location and the current location
        return getDistance(temp.getX(),temp.getY(), location.getX(), location.getY());

    }

    public double getHueristicCost() {

        return heuristicCost;
    }

    public Location getLocation() {

        return location;
    }

    public void removeAdjacentNode(Node adjacentNode) {

        //iterates through the list of adjacentNodes
        for(int i = 0; i < adjacentNodes.size(); i++){

            //if the node is found
            if(adjacentNodes.get(i)==adjacentNode){

                //removes the node at the current index
                adjacentNodes.remove(i);
                return;
            }
        }

        //reached end of list and did not find adjacentNodes

    }

    public void setHeuristicCost(double heuristicCost) {

        this.heuristicCost = heuristicCost;
    }

    //helper method for findDistanceBetweenNodes
    private double getDistance(int x1, int y1, int x2, int y2){

        //finds distance using Pythagorean Theorem
        return Math.sqrt(Math.abs((x2-x1)^2)+Math.abs((y2-y1)^2));
    }

}
