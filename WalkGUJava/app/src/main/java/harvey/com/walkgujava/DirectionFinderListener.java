package harvey.com.walkgujava;

/**
 * Created by Danielle on 11/16/2017.
 */


import java.util.List;



public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);
}
