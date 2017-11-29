package harvey.com.walkgujava;

import java.util.ArrayList;

/**
 * Created by Harvey on 11/17/2017.
 *
 * data structure responsible for keeping track of which dorms have been visited
 * and how many steps the user has taken
 */

public class TourManager {
    private final static int totalDorms = 1;
    private ArrayList<Boolean> dormsVisited;
    private Integer stepsTaken;

    public TourManager() {
        this.dormsVisited = new ArrayList<Boolean>();
        for (int i = 0; i < totalDorms; i++) {
            dormsVisited.add(false);
        }
        this.stepsTaken = 0;
    }

    public ArrayList<Boolean> getDormsVisited() {
        return dormsVisited;
    }

    public void setDormsVisited(ArrayList<Boolean> dormsVisited) {
        this.dormsVisited = dormsVisited;
    }

    public Integer getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(Integer stepsTaken) {
        this.stepsTaken = stepsTaken;
    }
}
