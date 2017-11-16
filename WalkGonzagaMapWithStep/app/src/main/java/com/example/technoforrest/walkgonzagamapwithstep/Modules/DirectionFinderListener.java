package com.example.technoforrest.walkgonzagamapwithstep.Modules;

/**
 * Created by Danielle on 11/9/2017.
 */

import java.util.List;



public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
