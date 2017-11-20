package harvey.com.walkgujava;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by Harvey on 11/17/2017.
 */

public class GeoFenceHelperService extends IntentService{
    final String TAG = "GeoFenceHelperService";
    public GeoFenceHelperService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()){
            Log.d(TAG, "there was an error");
            return;
        }
        // get the transition types
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
               geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT){
            // get the geofences that were triggered
            List<Geofence> triggeredFences = geofencingEvent.getTriggeringGeofences();

            Log.d(TAG, triggeredFences.get(0).getRequestId() + " has been triggered");
            Toast.makeText(this, triggeredFences.get(0).getRequestId() + " has been triggered", Toast.LENGTH_SHORT).show();

        }
    }
}
