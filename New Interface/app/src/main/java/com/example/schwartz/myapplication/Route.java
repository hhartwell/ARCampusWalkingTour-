package com.example.schwartz.myapplication;

/**
 * imports
 */
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

/**
 * Gets the route to a residence hall
 */
public class Route {
    /**
     * Initiations
     */
    public Distance distance;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;
    public List<LatLng> points;
}
