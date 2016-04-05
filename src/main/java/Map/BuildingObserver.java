package Map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by mharris382 on 4/5/2016.
 */
public class BuildingObserver implements Observer{
    private Building watching;

    
    public BuildingObserver(Building B){
        this.watching = B;
        B.addObserver(this);

        System.out.println("observing new building");
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
