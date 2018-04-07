package com.example.schwartz.myapplication;

/**
 * Imports
 */
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import java.util.List;

/**
 * Class that helps with the geofence
 */
public class GeoFenceHelperService extends IntentService{

    /**
     * Initiations
     */
    private final static String TAG = "GeoFenceHelperService";
    private final static String DEBUG_TAG = "GEOFENCEHELPERSERVICE";

    /**
     *
     * @param name
     */
    public GeoFenceHelperService(String name) {
        super(name);
    }

    /**
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError())
        {
            return;
        }

        /**
         * Gets the transition types
         */
        int geofenceTransition = geofencingEvent.getGeofenceTransition();
        if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER || geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT || geofenceTransition == Geofence.GEOFENCE_TRANSITION_DWELL)
        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);


            Toast.makeText(this, "Geofence triggered", Toast.LENGTH_SHORT).show();

            //Log.d(TAG, triggeredFences.get(0).getRequestId() + " has been triggered");

            Log.d(TAG, "onHandleIntent: ");
            Intent i = new Intent(this, ARCameraActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        else{
            Log.e(TAG, "ERROR IN ONHANDLEINTENT");

            /**
             * Gets the geofences that were triggered
             */
            List<Geofence> triggeredFences = geofencingEvent.getTriggeringGeofences();
            Intent i = new Intent(this, ARCameraActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
    }
}
