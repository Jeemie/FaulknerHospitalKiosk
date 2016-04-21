package Map.SearchAlgorithms;

import Map.Exceptions.NoPathException;
import Map.LocationNode;

import java.util.ArrayList;

/**
 * Created by Matt on 4/21/2016.
 */
public interface ISearchAlgorithm {

    ArrayList<LocationNode> getPath(LocationNode startNode, LocationNode destinationNode) throws NoPathException;

}
