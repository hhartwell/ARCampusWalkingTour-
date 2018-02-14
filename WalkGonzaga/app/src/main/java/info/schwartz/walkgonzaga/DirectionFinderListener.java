package info.schwartz.walkgonzaga;


import java.util.List;

import info.schwartz.walkgonzaga.Route;


public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}

