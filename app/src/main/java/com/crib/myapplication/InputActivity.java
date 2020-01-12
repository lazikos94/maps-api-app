package com.crib.myapplication;

import android.app.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class InputActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button info = (Button)findViewById(R.id.send);
        client = LocationServices.getFusedLocationProviderClient(this);

        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {




                final Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                if (ActivityCompat.checkSelfPermission(InputActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(InputActivity.this, new OnSuccessListener<Location>() {
                    public void onSuccess(Location location){
                        if (location!=null){

                            //double lat=0;
                            //double lngt=0;
                            String name;

                            String snippet;
                           // EditText ltd = (EditText)findViewById(R.id.editText4);
                            //EditText lng= (EditText)findViewById(R.id.editText5);
                            EditText nme = (EditText)findViewById(R.id.editText3);
                            EditText snip = (EditText)findViewById(R.id.editText2);
                            if(snip.getText().toString().isEmpty()){
                                Toast.makeText(getBaseContext(), "Please Enter Description", Toast.LENGTH_SHORT).show();

                                snip.requestFocus();

                                return;
                            }else{

                                snippet = snip.getText().toString();
                            }

                            /*if(ltd.getText().toString().isEmpty()){
                                Toast.makeText(getBaseContext(), "Please Enter Latitude", Toast.LENGTH_SHORT).show();

                                ltd.requestFocus();

                                return;
                            }else{

                                lat = Double.parseDouble(ltd.getText().toString());
                            }

                            if(lng.getText().toString().isEmpty()){
                                Toast.makeText(getBaseContext(), "Please Enter Longitude", Toast.LENGTH_SHORT).show();

                                lng.requestFocus();

                                return;
                            }else{

                                lngt = Double.parseDouble(lng.getText().toString());
                            }*/

                            if(nme.getText().toString().isEmpty()){
                                Toast.makeText(getBaseContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();

                                nme.requestFocus();

                                return;
                            }else{

                                name = nme.getText().toString();
                            }
                            Double loclat=location.getLatitude();
                            Double loclong=location.getLongitude();

                            intent.putExtra("latitude",loclat);
                            intent.putExtra("longtitude",loclong);
                            //intent.putExtra("lat", lat);
                            //intent.putExtra("lng", lngt);
                            intent.putExtra("nme",name);
                            intent.putExtra("snip",snippet);
                            startActivity(intent);




                        }
                    }
                });



            }
        });

    }

}
