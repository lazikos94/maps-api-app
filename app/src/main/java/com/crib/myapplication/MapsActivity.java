package com.crib.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    RadioButton rb1, rb2,rb3,rb4;
    Button r3;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        //double latitude = getIntent().getDoubleExtra("lat", 0);
        //double longitude = getIntent().getDoubleExtra("lng", 0);
        double mylatitude = getIntent().getDoubleExtra("latitude", 0);
        double mylongtitude = getIntent().getDoubleExtra("longtitude", 0);
        String title=getIntent().getStringExtra("nme");
        String description=getIntent().getStringExtra("snip");
       // LatLng position = new LatLng(latitude, longitude);
        LatLng myposition = new LatLng(mylatitude, mylongtitude);

        Marker User = mMap.addMarker(new MarkerOptions().position(myposition).title(title).snippet(description));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myposition));
        User.showInfoWindow();
        /*mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });*/
       // Marker aUser = mMap.addMarker(new MarkerOptions().position(position).title(title).snippet(description));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        //aUser.showInfoWindow();







        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }

        LatLng ElectricalEngineers = new LatLng(41.139945, 24.914231);
        mMap.addMarker(new MarkerOptions().position(ElectricalEngineers).title("ElectricalEngineers"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ElectricalEngineers));

        LatLng CivilEngineers = new LatLng(41.143023, 24.912606);
        mMap.addMarker(new MarkerOptions().position(CivilEngineers).title("CivilEngineers"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CivilEngineers));

        LatLng ArchitectEngineers = new LatLng(41.140590, 24.916251);
        mMap.addMarker(new MarkerOptions().position(ArchitectEngineers).title("ArchitectEngineers"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ArchitectEngineers));

        LatLng Esties = new LatLng(41.146266, 24.915331);
        mMap.addMarker(new MarkerOptions().position(Esties).title("Esties"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Esties));


    }

    private static final String TAG = "MapsActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private Boolean mLocationPermissionsGranted = false;
    public GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    public LatLng position;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        r3 = (Button) findViewById(R.id.button3);

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapsActivity.this,InputActivity.class);
                startActivity(intent);
            }
        });

        getLocationPermission();



    }



   /* public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rb1:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    break;
            case R.id.rb2:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    break;
            case R.id.rb3:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.rb4:
                if (checked)
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }
    }*/


    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);

                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                }
            }catch(SecurityException e){
                Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
            }
        }
    private void moveCamera (LatLng latLng,float zoom){
            Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

   private void initMap () {
            Log.d(TAG, "initMap: initializing map");
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            mapFragment.getMapAsync(MapsActivity.this);
        }

   private void getLocationPermission () {
            Log.d(TAG, "getLocationPermission: getting location permissions");
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionsGranted = true;
                    initMap();
                } else {
                    ActivityCompat.requestPermissions(this,
                            permissions,
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }

    @Override
    public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
            Log.d(TAG, "onRequestPermissionsResult: called.");
            mLocationPermissionsGranted = false;

            switch (requestCode) {
                case LOCATION_PERMISSION_REQUEST_CODE: {
                    if (grantResults.length > 0) {
                        for (int i = 0; i < grantResults.length; i++) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                mLocationPermissionsGranted = false;
                                Log.d(TAG, "onRequestPermissionsResult: permission failed");
                                return;
                            }
                        }
                        Log.d(TAG, "onRequestPermissionsResult: permission granted");
                        mLocationPermissionsGranted = true;
                        //initialize our map
                        initMap();
                    }
                }
            }
        }



    }


