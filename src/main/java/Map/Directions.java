package Map;

import java.util.ArrayList;
import java.util.List;
import Map.Enums.CardinalDirection;
import Map.Enums.RelativeDirection;

import static Map.Enums.RelativeDirection.*;

/**
 * Contains information about Directions which will contain
 */
public class Directions {

    List<LocationNode> path;

    public Directions(List<LocationNode> path) {

       this.path = path;

    }

    /**
     * Method getRelativeDirections creates a list of ENUM's which have direction
     *  instructions.
     *
     * Uses the method called getDirectionsTo() in the node class to get compass
     *   directions (NORTH, SOUTH, EAST, WEST) and turns them into to relative
     *   directions (goStrait, turnLeft, turnRight)
     *
     * @return List of RelativeDirections which shows directions in the form of
     *  an Enum List
     */
    public List<RelativeDirection> getRelativeDirections () {

        //Created a RelativeDirection array which is to be returned
        List<RelativeDirection> relativeDirections = new ArrayList<RelativeDirection>();

        //Create three LocationNodes to create two CardinalDirections
        LocationNode firstLNode, secondLNode, thirdLNode;

        //Create two CardinalDirections to create one RelativeDirection
        CardinalDirection pastCDirection, currentCDirection;

        //Create one RelativeDirection
        RelativeDirection currentRDirection;

        //If there is only one node in the path or less, then return nothing.
        if (path.size() < 2) {
            return relativeDirections; //Return empty array
        }

        //Get the direction (only requires two nodes)
        switch(path.get(0).getDirectionsTo(path.get(1))) {
            case NORTH:
                relativeDirections.add(STRAIGHT);
                break;
            case EAST:
                relativeDirections.add(RIGHT);
                break;
            case WEST:
                relativeDirections.add(LEFT);
                break;
            case SOUTH:
                relativeDirections.add(BACK);
        }

        //Will run once if path.size() is 3
        //Go through the path, and create 1 RelativeDirection through 2 CardinalDirections
        // by using 3 LocationNodes each loop
        for(int i = 1; i < path.size()-1; i++) {

            //Declare first three nodes
            firstLNode  = path.get(i-1);
            secondLNode = path.get(i);
            thirdLNode  = path.get(i+1);

            //Create CardinalDirections
            pastCDirection = firstLNode.getDirectionsTo(secondLNode);
            currentCDirection = secondLNode.getDirectionsTo(thirdLNode);

            //Create RelationalDirection based on the two cardinal direction
            //If the directions are the same, go forward
            if (pastCDirection == currentCDirection) {

                currentRDirection = STRAIGHT;
                relativeDirections.add(currentRDirection);

            }
            //If the directions are opposites
            else if (pastCDirection.opposite() == currentCDirection) {

                currentRDirection = BACK;
                relativeDirections.add(currentRDirection);

            }
            //Also check if it turns right
            else if (pastCDirection.right() == currentCDirection) {

                currentRDirection = RIGHT;
                relativeDirections.add(currentRDirection);

            }
            //Also check if it turns left
            else if (pastCDirection.left() == currentCDirection) {

                currentRDirection = LEFT;
                relativeDirections.add(currentRDirection);

            }

        }

        return relativeDirections;
    }


    /**
     * Method getTextualDirections creates a list of strings which have direction
     *  instructions.
     *
     * o
     * Uses the method called getDirectionsTo() in the node class to get compass
     *   directions (NORTH, SOUTH, EAST, WEST) and turns them into to relative
     *   directions (goStrait, turnLeft, turnRight)
     *
     * @return List of Strings which shows directions in the form of GPS directions
     */
    public List<String> getTextualDirections () {

        //A String array which may or may not be used
        List<String> textualDirections = new ArrayList<String>();

        //Create three LocationNodes to create two CardinalDirections
        LocationNode firstLNode, secondLNode, thirdLNode;

        //Create two CardinalDirections to create one RelativeDirection
        CardinalDirection pastCDirection, currentCDirection;

        //Create one RelativeDirection
        RelativeDirection currentRDirection;

        //A count to help calculate junctions
        int junctionCount = 0;

        for(int i = 1; i < path.size()-1; i++) {

            //Declare first three nodes
            firstLNode  = path.get(i-1);
            secondLNode = path.get(i);
            thirdLNode  = path.get(i+1);

            //Create CardinalDirections
            pastCDirection = firstLNode.getDirectionsTo(secondLNode);
            currentCDirection = secondLNode.getDirectionsTo(thirdLNode);

            //Create a string to put into the textualDirections arraylist
            String currentTextDirection = "";

            //If the directions are the same, go forward
            if (pastCDirection == currentCDirection) {
                junctionCount++; //Increment junctionCount to know the number of junctions
                if (i == path.size()-2) { //If it's the last part of the path
                    if (junctionCount > 0) {
                        currentTextDirection = "Keep going forward, and you'll reach your destination!";
                    }

                    //Add to textualDirections
                    textualDirections.add(currentTextDirection);
                }

            } else {

                //Change how sentence junction is structured depending on junctionCount number
                currentTextDirection = "Take the ";
                switch(junctionCount) {
                    case 0:
                        currentTextDirection += "next ";
                        break;
                    case 1:
                        currentTextDirection += (junctionCount + 1) + "nd "; //2nd
                        break;
                    case 2:
                        currentTextDirection += (junctionCount + 1) + "rd "; //3rd
                        break;
                    default:
                        currentTextDirection += (junctionCount + 1) + "th "; //nth
                        break;
                }


                //Check cardinalDirection relations, and output the right direction
                if (pastCDirection.right() == currentCDirection) {
                    currentTextDirection += "Right.";
                } else if (pastCDirection.left() == currentCDirection) {
                    currentTextDirection += "Left.";
                } else if (pastCDirection.opposite() == currentCDirection) {
                    currentTextDirection += "Back."; //Should actually not happen
                }

                //Add to textualDirections
                textualDirections.add(currentTextDirection);
                //Reset junctionCount
                junctionCount = 0;

            }


        }

        return textualDirections;
    }
}
