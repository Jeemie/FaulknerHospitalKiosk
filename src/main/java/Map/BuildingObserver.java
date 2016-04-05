package Map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mharris382 on 4/5/2016.
 */
public class BuildingObserver implements Observer{

    private ArrayList<Building> observed_buildings = new ArrayList<>();

    
    public BuildingObserver(){

        System.out.println("observing new building");
    }

    /**
     *  called from Building constructor, makes an observer to watch building and adds the building to the list of observed buildings
     *
     * @param b Building object to be observed
     */
    public void observeBuilding(Building b){

        if(!observed_buildings.contains(b)){

            //add observer to watch the building object b
            b.addObserver(b.getBuildingObserver());

            //add building to the list of observed buildings
            observed_buildings.add(b);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
