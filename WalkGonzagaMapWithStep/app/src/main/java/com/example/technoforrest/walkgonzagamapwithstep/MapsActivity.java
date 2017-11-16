package com.example.technoforrest.walkgonzagamapwithstep;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.technoforrest.walkgonzagamapwithstep.Modules.DirectionFinder;
import com.example.technoforrest.walkgonzagamapwithstep.Modules.DirectionFinderListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import android.location.Location;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Polyline;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity
            implements OnMapReadyCallback,
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener,
            LocationListener,SensorEventListener, StepListener {

        GoogleMap mGoogleMap;
        SupportMapFragment mapFrag;
        LocationRequest mLocationRequest;
        GoogleApiClient mGoogleApiClient;
        Location mLastLocation;
        Marker mCurrLocationMarker;
    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private Spinner spinner;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private String destinationPoint;


    //variables for step counter
        private int numSteps;
        private TextView TvSteps;
        private static final String TEXT_NUM_STEPS = "Steps: ";
        private StepDetector simpleStepDetector;
        private SensorManager sensorManager;
        private Sensor accel;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            simpleStepDetector = new StepDetector();
            simpleStepDetector.registerListener(this);
            TvSteps = (TextView) findViewById(R.id.stepText);

            getSupportActionBar().setTitle("Map Location Activity");

            mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);

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

                //creates the drop down menu for the category
                final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
                spinner.setAdapter(spinnerArrayAdapter);
                //destinationPoint = intent.getStringExtra("Category");
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        for(int j = 1; j < 24; j++) {
                            if (type[j].equals(destinationPoint)) {
                                spinner.setSelection(j);
                                i = j;

                                if(type[i].equals(type[1]))
                                {

                                } else if(type[i].equals(type[2])) {

                                } else if(type[i].equals(type[3])) {

                                } else if(type[i].equals(type[4])) {

                                } else if(type[i].equals(type[5])) {

                                } else if(type[i].equals(type[6])) {

                                } else if(type[i].equals(type[7])) {

                                } else if(type[i].equals(type[8])) {

                                } else if(type[i].equals(type[9])) {

                                } else if(type[i].equals(type[10])) {

                                } else if(type[i].equals(type[11])) {
                                    LatLng DeSemet = new LatLng(47.667834, -117.401336);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DeSemet, 18));
                                    originMarkers.add(mMap.addMarker(new MarkerOptions().title("DeSmet Hall").position(DeSemet)));
                                } else if(type[i].equals(type[12])) {

                                } else if(type[i].equals(type[13])) {

                                } else if(type[i].equals(type[14])) {

                                } else if(type[i].equals(type[15])) {

                                } else if(type[i].equals(type[16])) {

                                } else if(type[i].equals(type[17])) {

                                } else if(type[i].equals(type[18])) {

                                } else if(type[i].equals(type[19])) {

                                } else if(type[i].equals(type[20])) {

                                } else if(type[i].equals(type[21])) {

                                } else if(type[i].equals(type[22])) {

                                } else if(type[i].equals(type[23])) {

                                } else if(type[i].equals(type[24])) {

                                } else if(type[i].equals(type[25])) {

                                }
                                destinationPoint = adapterView.getItemAtPosition(i).toString();
                                break;
                            }
                        }
                        // originPoint = adapterView.getItemAtPosition(i).toString();
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
        //destinationPoint = spinner.getSelectedItem().toString();


        /*try {
            new DirectionFinder(this, mLastLocation.toString(), destinationPoint).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }

        @Override
        public void onPause() {
            super.onPause();

            //stop location updates when Activity is no longer active
            if (mGoogleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            }
        }

        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mGoogleMap=googleMap;
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            //Initialize Google Play Services
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //Location Permission already granted
                    buildGoogleApiClient();
                    mGoogleMap.setMyLocationEnabled(true);
                } else {
                    //Request Location Permission
                    checkLocationPermission();
                }
            }
            else {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }
        }

        protected synchronized void buildGoogleApiClient() {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

        @Override
        public void onConnected(Bundle bundle) {
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }

        @Override
        public void onConnectionSuspended(int i) {}

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {}

        @Override
        public void onLocationChanged(Location location)
        {
            mLastLocation = location;
            if (mCurrLocationMarker != null) {
                mCurrLocationMarker.remove();
            }

            //Place current location marker
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

            //move map camera
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));

        }

        public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
        private void checkLocationPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

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
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            MY_PERMISSIONS_REQUEST_LOCATION );
                                }
                            })
                            .create()
                            .show();


                } else {
                    // No explanation needed, we can request the permission.
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION );
                }
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // location-related task you need to do.
                        if (ContextCompat.checkSelfPermission(this,
                                Manifest.permission.ACCESS_FINE_LOCATION)
                                == PackageManager.PERMISSION_GRANTED) {

                            if (mGoogleApiClient == null) {
                                buildGoogleApiClient();
                            }
                            mGoogleMap.setMyLocationEnabled(true);
                        }

                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                        Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                simpleStepDetector.updateAccel(
                        event.timestamp, event.values[0], event.values[1], event.values[2]);
            }
        }

        @Override
        public void step(long timeNs) {
            numSteps++;
            TvSteps.setText(TEXT_NUM_STEPS + numSteps);
        }

    }

