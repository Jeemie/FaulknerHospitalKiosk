package Map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mharris382 on 4/5/2016.
 */
public class FloorObserver implements Observer {

    ArrayList<Floor> observed_floors = new ArrayList<>();

    public FloorObserver(){ }

    /** adds an observer to watch a specified floor
     *
     * @param f Floor to start observing
     */
    public void observeFloor(Floor f){
        System.out.println("Observing new floor");

        //check that the floor is not already being observed
        if(!observed_floors.contains(f)){

            //add an observer watching the floor
            f.addObserver(f.getFloorObserver());

            //add floor to the list of floors being observed
            observed_floors.add(f);

        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
