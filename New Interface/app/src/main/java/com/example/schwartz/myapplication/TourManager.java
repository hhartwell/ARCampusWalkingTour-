package com.example.schwartz.myapplication;

/**
 * Imports
 */
import java.util.ArrayList;

/**
 * Data structure responsible for keeping track of which residence halls have been visited and how many steps the user has taken
 */
public class TourManager {
    /**
     * Initiations
     */
    private final static int totalDorms = 1;
    private ArrayList<Boolean> dormsVisited;
    private Integer stepsTaken;

    /**
     * Constructor
     */
    public TourManager() {
        this.dormsVisited = new ArrayList<Boolean>();
        for (int i = 0; i < totalDorms; i++) {
            dormsVisited.add(false);
        }
        this.stepsTaken = 0;
    }

    /**
     * Gets dorms visited
     * @return dormsVisited
     */
    public ArrayList<Boolean> getDormsVisited() {
        return dormsVisited;
    }

    /**
     * Sets the dorms visited
     * @param dormsVisited
     */
    public void setDormsVisited(ArrayList<Boolean> dormsVisited) {
        this.dormsVisited = dormsVisited;
    }

    /**
     * Gets the steps taken
     * @return
     */
    public Integer getStepsTaken() {
        return stepsTaken;
    }

    /**
     * Sets the steps taken
     * @param stepsTaken
     */
    public void setStepsTaken(Integer stepsTaken) {
        this.stepsTaken = stepsTaken;
    }
}
