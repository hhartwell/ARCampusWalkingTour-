package info.schwartz.walkgonzaga;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

import info.schwartz.walkgonzaga.fragment.ProfileFragment;

/**
 * Created by Harvey on 11/17/2017.
 */

public class GeoFenceHelperService extends IntentService{
    private final static String TAG = "GeoFenceHelperService";
    private final static String DEBUG_TAG = "GEOFENCEHELPERSERVICE";
    private View view;

    public GeoFenceHelperService(String name) {
        super(name);
        Log.d(TAG, "GeoFenceHelperService: parameter constructor");
    }
    public GeoFenceHelperService(){
        super(TAG);
        Log.d(TAG, "GeoFenceHelperService: default constructor");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(DEBUG_TAG, "IN ON_HANDLE_INTENT");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()){
            Log.d(DEBUG_TAG, "there was an error");
            return;
        }
        // get the transition types
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
               geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_DWELL){
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            // get the geofences that were triggered
            List<Geofence> triggeredFences = geofencingEvent.getTriggeringGeofences();



            Log.d(TAG, triggeredFences.get(0).getRequestId() + " has been triggered");
            //Toast.makeText(this, triggeredFences.get(0).getRequestId() + " has been triggered", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onHandleIntent: ");
            Intent i = new Intent(this, ProfileFragment.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            
        }
        else{
            Log.e(TAG, "ERROR IN ONHANDLEINTENT");
        }
    }
}
