package com.example.schwartz.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, DirectionFinderListener,View.OnClickListener {
    //private final static String TAG = "MapActivity";

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    // private CameraPosition mCameraPosition;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(47.655, -117.455);//Herak Hall
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Keys for storing activity state.
    //private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    private Button btnFindPath;
    private Spinner spinner;
    // private LatLng destinationPoint;
    private LatLng currLatLng;
    private LatLng destLatLng;
    private ProgressDialog progressDialog;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private ArrayList<LatLng> destinationPoint;
    private List<Polyline> polylinePaths = new ArrayList<>();
    private Marker mCurrLocationMarker;
    private int position;
    private float closest = 1000000000;
    private LatLng closeLatLng;
    private int closestIndex;
    private double geoLat;
    private double geoLong;
    final String type[] = {"Please Select A Building", "Alliance House", "Burch Apartments", "Campion House", "Catherine/Monica Hall",
            "Chardin House", "Corkery Apartments", "Coughlin Hall", "Crimont Hall", "Cushing House", "DeSmet Hall", "Dillon Hall", "Dussault Suites",
            "Goller Hall", "Kennedy Apartments", "Lincoln House", "Madonna Hall", "Marian Hall", "River Inn Hall", "Roncalli House", "Sharp Apartments",
            "Twohy Hall", "Welch Hall", "Chardin House"};
    //variables for step counter
    private TextView count;
    private SensorManager manager;
    private Boolean zeroSteps;
    private float stepsDontCount;
    private float steps;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            //mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        spinner = (Spinner) findViewById(R.id.spinner);

        Button btnFindNearest = findViewById(R.id.btnFindNearest);
        btnFindNearest.setOnClickListener(this);
        Button btnFindPath = findViewById(R.id.btnFindPath);
        btnFindPath.setOnClickListener(this);
        setSpinner();
        getDeviceLocation();

        //sensor pedometer
        count = (TextView) findViewById(R.id.stepText);
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        zeroSteps = true;
        pedometer();
        //createFusedLocationServices();
        CreateGeofenceToComplete();
        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnFindNearest:
                destLatLng = closeLatLng;
                sendRequest();
                break;

            case R.id.btnFindPath:
                sendRequest();
                break;

            default:
                break;
        }


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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Log.d(TAG, "onMapReady: ");

        getLocationPermission();
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        getDeviceLocation();

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
        mMap.setMyLocationEnabled(true);
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            currLatLng = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            destinationPoint.set(0,currLatLng);
                            for(int i = 1; i < destinationPoint.size(); i++) { //start at 1 because 0 is current
                                // location and always closest
                                float[]results = new float[1];
                                Location.distanceBetween(mLastKnownLocation.getLatitude(),
                                        mLastKnownLocation.getLongitude(), destinationPoint.get(i).latitude,
                                        destinationPoint.get(i).longitude, results);
                                Log.d(TAG, "getDeviceLocation: Current location" + destinationPoint.get(0).toString());
                                Log.d(TAG, "getDeviceLocation: " + results[0]);
                                if(results[0] < closest){

                                    closest = results[0];
                                    closeLatLng = (destinationPoint.get(i));
                                    closestIndex = i;
                                    //destinationPoint.set(0,closeLatLng);
                                    Log.d(TAG, "getDeviceLocation: " + type[i]);
                                    Log.d(TAG, "getDeviceLocation: " + destinationPoint.get(i));
                                    Log.d(TAG, "getDeviceLocation: " + results[0]);
                                }
                            }
                            Log.d(TAG, "onComplete: Task successful" + currLatLng.toString());
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            currLatLng = mDefaultLocation;
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    public void setSpinner(){

        destinationPoint = new ArrayList<>();
        destinationPoint.add(new LatLng(mDefaultLocation.latitude,mDefaultLocation.longitude));
        destinationPoint.add(new LatLng(47.668670, -117.400111));//Alliance1
        destinationPoint.add(new LatLng(47.669174, -117.406664));//Burch2
        destinationPoint.add(new LatLng(47.668663, -117.401090));//Campion3
        destinationPoint.add(new LatLng(47., -117));//Catherine/Monica4
        destinationPoint.add(new LatLng(47.669824, -117.399450));//Chardin5
        destinationPoint.add(new LatLng(47.670131, -117.400162));//Corkery6
        destinationPoint.add(new LatLng(47.664840, -117.397315));//Coughlin7
        destinationPoint.add(new LatLng(47.670186, -117.401682));//Crimont8
        destinationPoint.add(new LatLng(47.669313, -117.398404));//Cushing9
        destinationPoint.add(new LatLng(47.667834, -117.401336));//DeSmet10
        destinationPoint.add(new LatLng(47.669266, -117.400999));//Dillon11
        destinationPoint.add(new LatLng(47.666730, -117.408119));//Dussault12
        destinationPoint.add(new LatLng(47.669284, -117.400215));//Goller13
        destinationPoint.add(new LatLng(47.668599, -117.408095));//Kennedy14
        destinationPoint.add(new LatLng(47.668634, -117.399486));//Lincoln15
        destinationPoint.add(new LatLng(47.666774, -117.397601));//Madonna16
        destinationPoint.add(new LatLng(47.668627, -117.394011));//Marian17
        destinationPoint.add(new LatLng(47., -117));//RiverInn18
        destinationPoint.add(new LatLng(47.668652, -117.399025));//Roncalli19
        destinationPoint.add(new LatLng(47.669293, -117.403457));//Sharp20
        destinationPoint.add(new LatLng(47.668694, -117.397763));//Twohy21
        destinationPoint.add(new LatLng(47.667747, -117.400001));//Welsh22
        destinationPoint.add(new LatLng(47.669768, -117.399430));//Chardin23

        final ArrayAdapter<CharSequence> spinnerArrayAdapter = ArrayAdapter.createFromResource(
                this, R.array.dorms, R.layout.spinner_layout);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(spinnerArrayAdapter);
        //creates the drop down menu for the category
       /* final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        spinner.setAdapter(spinnerArrayAdapter);*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
                destLatLng = destinationPoint.get(i);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destLatLng, 18));
                originMarkers.add(mMap.addMarker(new MarkerOptions().title(type[i]).position(destLatLng)));
                Log.d(TAG, "onItemSelected: type" + type[i] + " Coordinates: " + destinationPoint.get(i).toString());
                position = i;
                geoLat= destinationPoint.get(position).latitude;
                geoLong = destinationPoint.get(position).longitude;
            }

            /**
             * if nothing is selected
             * @param adapterView
             */
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void sendRequest() {
        // currLatLng = mDefaultLocation;
        double origLat = currLatLng.latitude;
        double origLong = currLatLng.longitude;
        double destLat = destLatLng.latitude;
        double destLong = destLatLng.longitude;
        String origin = origLat + "," + origLong;
        String destination = destLat + "," + destLong;
        Log.d(TAG, "sendRequest: origin LatLong- "  + " destination LaatLng- " + destLatLng);
        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));

            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }
    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {}

    @Override
    public void onLocationChanged(Location location)
    {
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        currLatLng = latLng;
        destinationPoint.set(0,latLng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

    }
    private void CreateGeofenceToComplete() {
        //create the geofence list
        geofenceList = new ArrayList<>();
        Toast.makeText(this, "from create geofence", Toast.LENGTH_SHORT).show();
        // create the client
        geofencingClient = LocationServices.getGeofencingClient(this);

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
                // currently set to crosby. replace first and second arg with geoLat and geoLong respectively
                .setCircularRegion(
                        47.667275,
                        -117.401374,
                        300)
                // how long the geo fence stays active
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                // how the geo fence will be triggered
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .setLoiteringDelay(10)
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
                        Toast.makeText(MapsActivity.this, "geo fence created", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.e(TAG, e.getMessage().toString());
                        Log.d(TAG, "failed to add geofence: " + geofence.getRequestId());
                        Toast.makeText(MapsActivity.this, "geo fence not created", Toast.LENGTH_SHORT).show();
                    }
                });
        Log.d(TAG, "CREATE_GEOFENCE_TO_COMPLETE DONE RUNNING");


        if (LocationServices.getFusedLocationProviderClient(this).getLastLocation() == null) {
            Log.d(TAG, "it was null!");
        }

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
        builder.addGeofences(geofenceList);
        assert (geofenceList != null);
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
        Log.d(TAG, intent.toString());
        pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d(TAG, "INSIDE PENDING INTENT");
        return pendingIntent;
    }
    /**
     * sensor is activated and steps from sensor assigned to the textview
     */
    public void pedometer(){
        manager.registerListener(new SensorEventListener() {

                                     @Override
                                     public void onSensorChanged(SensorEvent event) {
                                         //determines if the activity is just launched
                                         if(zeroSteps){
                                             stepsDontCount = event.values[0];//records steps since device reboot
                                             zeroSteps = false;//activity will no loner be considered new
                                         }
                                         steps = event.values[0] - stepsDontCount;//subtracts the values stored in the
                                         // phone so only the steps taken since the app show
                                         count.setText(steps + "");
                                     }
                                     @Override
                                     public void onAccuracyChanged(Sensor sensor, int accuracy) {

                                     }
                                 }, manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_UI);
    }
}
