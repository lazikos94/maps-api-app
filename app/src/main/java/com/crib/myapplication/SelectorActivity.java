package com.crib.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectorActivity extends AppCompatActivity{
    Button selection1;
    //Button selection2;
    Button selection3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        selection1 = (Button) findViewById(R.id.selection1);
        //selection2 =(Button) findViewById(R.id.selection2);
        selection3=(Button)findViewById(R.id.selection3);

        selection1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SelectorActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        /*selection2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SelectorActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });*/
        selection3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SelectorActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

    }



}
