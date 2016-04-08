package Map;

/**
 * Created by trietpham on 4/7/16.
 */
public class Neighbors {
    private final LocationNode tempGoal;
    private int x;
    private int y;
    private final double cost = 0;

    public Neighbors(LocationNode tempGoal) {
        this.tempGoal = tempGoal;
    }

    public double getCost() {
        return cost;
    }

    public LocationNode getTempGoal() {
        return tempGoal;
    }
}

