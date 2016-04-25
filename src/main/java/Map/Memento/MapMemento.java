package Map.Memento;

import Map.*;
import Map.Enums.MapState;
import Map.SearchAlgorithms.ISearchAlgorithm;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by binam on 4/24/16.
 */
public class MapMemento {

//    HashMap<UUID, Object> UUIDtoObject; //Maybe use for quick loading

    String name;

    UUID uniqueID;

    UUID startLocationNodeID;

    // Make an array list of BuildingMomentos, because, then you won't have
    //  circular dependency issue (Building would hold a Mapz)
    ArrayList <BuildingMemento> buildingMementos;

    // TODO do we need this?
    //ISearchAlgorithm searchAlgorithm;

    MapState currentMapState;


    public MapMemento(String name, UUID uniqueID, LocationNode startLocationNode, ArrayList < Building > mapBuildings, ISearchAlgorithm searchAlgorithm) {

        this.name = name;

        this.uniqueID = uniqueID;

        this.startLocationNodeID = startLocationNode.getUniqueID();


        for(Building building : mapBuildings) {

            buildingMementos.add(new BuildingMemento(building.getName(), building.getUniqueID(), building.getFloors(), building.getCurrentMap()));

        }


//        this.searchAlgorithm = searchAlgorithm;

//        this.directoryList = directoryList;
    }

}
