package com.example.schwartz.myapplication;

import java.util.List;

public interface DirectionFinderListener {
    /**
     * WHAT DOES THIS DO?
     */
    void onDirectionFinderStart();

    /**
     * WHAT DOES THIS DO?
     * @param route
     */
    void onDirectionFinderSuccess(List<Route> route);
}
