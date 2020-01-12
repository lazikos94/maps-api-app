package com.crib.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LocationActivity extends AppCompatActivity{
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        Button button = (Button) findViewById(R.id.getLocation);
        Button button1 =(Button) findViewById(R.id.addLocation);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(LocationActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(LocationActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location!= null){
                            TextView textView = findViewById(R.id.location);
                            textView.setText( location.getLatitude() + "\n" +location.getLongitude());

                        }

                    }
                });

            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(LocationActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(LocationActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location!= null){
                            Double loclat=location.getLatitude();
                            Double loclong=location.getLongitude();
                            String name="my location";
                            Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                            intent.putExtra("lat", loclat);
                            intent.putExtra("lng", loclong);
                            intent.putExtra("nme",name);

                            startActivity(intent);

                        }

                    }
                });


            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}

