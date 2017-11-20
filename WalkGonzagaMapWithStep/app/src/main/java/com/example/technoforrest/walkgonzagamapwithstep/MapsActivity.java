package com.example.technoforrest.walkgonzagamapwithstep;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
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
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, DirectionFinderListener,SensorEventListener, StepListener {


     private static final String TAG = MapsActivity.class.getSimpleName();
     private GoogleMap mMap;
     private CameraPosition mCameraPosition;

     // The entry points to the Places API.
     private GeoDataClient mGeoDataClient;
     private PlaceDetectionClient mPlaceDetectionClient;

     // The entry point to the Fused Location Provider.
     private FusedLocationProviderClient mFusedLocationProviderClient;

     // A default location (Sydney, Australia) and default zoom to use when location permission is
     // not granted.
     private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
     private static final int DEFAULT_ZOOM = 15;
     private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
     private boolean mLocationPermissionGranted;

     // The geographical location where the device is currently located. That is, the last-known
     // location retrieved by the Fused Location Provider.
     private Location mLastKnownLocation;

     // Keys for storing activity state.
     private static final String KEY_CAMERA_POSITION = "camera_position";
     private static final String KEY_LOCATION = "location";

     // Used for selecting the current place.
    private FusedLocationProviderClient mFusedLocationClient;

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
    private String title;
    private int position;
    //variables for step counter
    private int numSteps;
    private TextView TvSteps;
    private static final String TEXT_NUM_STEPS = "Steps: ";
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);
        TvSteps = (TextView) findViewById(R.id.stepText);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //getSupportActionBar().setTitle("Map Location Activity");

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        spinner = (Spinner) findViewById(R.id.spinner);


        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequest();
            }
        });
        Intent intent = getIntent();
        final String type[] = {"Please Select A Building", "Alliance House", "Burch Apartments", "Campion House", "Catherine/Monica Hall",
                "Chardin House", "Corkery Apartments", "Coughlin Hall", "Crimont Hall", "Cushing House", "DeSmet Hall", "Dillon Hall", "Dussault Suites",
                "Goller Hall", "Kennedy Apartments", "Lincoln House", "Madonna Hall", "Marian Hall", "River Inn Hall", "Roncalli House", "Sharp Apartments",
                "Twohy Hall", "Welch Hall", "Chardin House"};
        destinationPoint = new ArrayList<LatLng>();
        destinationPoint.add(new LatLng(47.0, -117.0));//0
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

        //creates the drop down menu for the category
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        spinner.setAdapter(spinnerArrayAdapter);
        //destinationPoint = intent.getStringExtra("Category");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        spinner.setSelection(i);
                        destLatLng = destinationPoint.get(i);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(destLatLng, 18));
                        originMarkers.add(mMap.addMarker(new MarkerOptions().title(type[i].toString()).position(destLatLng)));
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
        currLatLng = new LatLng(48, -117);
        Log.d(TAG, "sendRequest: origin LatLong- " + currLatLng + " destination LaatLng- " + destLatLng);
        try {
            new DirectionFinder((DirectionFinderListener) this, currLatLng, destLatLng).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        Log.d(TAG, "onMapReady: ");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d(TAG, "onSuccess: ");
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            currLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                            //mMap.addMarker(new MarkerOptions().position(currLatLng).title("Marker in current location"));
                            originMarkers.add(mMap.addMarker(new MarkerOptions().title("Origin").position(currLatLng)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currLatLng, 18));
                        } else {
                            Log.d(TAG, "onSuccess: currLocation == null! ");
                        }
                    }
                });
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
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
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MapsActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION );
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION );
            }
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

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
    }

}

