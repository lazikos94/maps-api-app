package com.crib.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends Activity{
    Button b1,b2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }

}
