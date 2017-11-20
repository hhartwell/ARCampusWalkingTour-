package harvey.com.walkgujava;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private final static String TAG = "MapActivity";
    private GoogleMap mMap;

    /**
     * objects used for the geofence
     * to create and use a geo fence we need four parts
     * We need a geofencingclient to access api
     * We need a geofence for obvious reasons
     * We need a pending intent to add or remove a geofence
     * We need a geofencing request to add geofence to geofencingClient
     *
     * I am also including a list of geofences for future development, but
     * right now there will only be one geofence in the list
     */
    private GeofencingClient geofencingClient;
    private Geofence geofence;
    private PendingIntent pendingIntent;
    private GeofencingRequest geofencingRequest;
    private ArrayList<Geofence> geofenceList;

    // fused location services client
    // used for finding current location
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        createFusedLocationServices();
        CreateGeofenceToComplete();
    }
    private void createFusedLocationServices(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    private void CreateGeofenceToComplete() {
        //create the geofence list
        geofenceList = new ArrayList<>();
        Toast.makeText(this, "from create geofence", Toast.LENGTH_SHORT).show();
        // create the client
        geofencingClient = getGeofencingClient();

        // build the geofence
        // this uses a builder to assign all attributes.
        // the builder is a lot like a layout manager

        geofence = new Geofence.Builder()
                // string used to refer to the geo fence
                .setRequestId("desmetGeofence")
                // bounds of the fence
                // double longitude
                // double latitude
                // float radius in meters
                .setCircularRegion(
                        47.668,
                        -117.401,
                        20)
                // how long the geo fence stays active
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                // how the geo fence will be triggered
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .setLoiteringDelay(1000)
                // create it
                .build();

        // add geofence to geofence list
        geofenceList.add(geofence);


        // permissions check
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permisions denied fine location");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        geofencingClient.addGeofences(getGeofencingRequest(), getGeofencingPendingIntent())
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "successfully added geofence: " + geofence.getRequestId());
                        Toast.makeText(MapActivity.this, "geo fence created", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, e.getMessage());
                        Log.d(TAG, "failed to add geofence: " + geofence.getRequestId());
                        Toast.makeText(MapActivity.this, "geo fence not created", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private GeofencingClient getGeofencingClient() {
        // check to see if client has already been created or not
        if (geofencingClient != null) {
            return geofencingClient;
        }
        // retrieve the geofencing client from locationServices
        return LocationServices.getGeofencingClient(this);
    }

    /**
     * builds and returns a geofending request. Specifies the list of geofences to be monitored.
     * also specifies how the geofence notifications are initially triggered
     * @return geofence request
     */
    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();

        // the INITIAL_TRIGGER_ENTER flag indicates taht geofencing service should trigger a
        // GEOFENCE_TRANSITION_ENTER notification when the geofence is added and if the device
        // is already inside that geofence
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);

        // add the geofences to be monitered by geofencing services.
        builder.addGeofence(geofenceList.get(0));
        assert(geofenceList != null);
        Log.d(TAG, geofenceList.get(0).toString() + " from getGeoFencingRequest");
        // build and return the request
        return builder.build();
    }


    private PendingIntent getGeofencingPendingIntent() {
        // reuse old intent if it exists
        if (pendingIntent != null) {
            return pendingIntent;
        }
        // need to send this to unity scene when it is imported into the project
        Intent intent = new Intent(this, GeoFenceHelperService.class);
        // we need to use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences()
        pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d(TAG, "INSIDE PENDING INTENT");
        return pendingIntent;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(this, "MapReady", Toast.LENGTH_SHORT).show();
        // Add a marker in Sydney and move the camera
        LatLng desmet = new LatLng(47.667826, -117.401336 );
        mMap.addMarker(new MarkerOptions().position(desmet).title("Marker at desmet"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(desmet));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LatLng currLocation;
        /*
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                location.getLongitude();
                location.getLatitude();
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
        */

    }
}
