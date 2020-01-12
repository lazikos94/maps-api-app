package com.crib.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
public class SignupActivity extends Activity{
    EditText ed1,ed2,ed3,ed4,ed5;
    Button b1,b2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        b1 = (Button)findViewById(R.id.signup);
        b2 = (Button)findViewById(R.id.back);
        ed1 = (EditText)findViewById(R.id.edText);
        ed2 = (EditText)findViewById(R.id.edText1);
        ed3 = (EditText)findViewById(R.id.edText2);
        ed4 = (EditText)findViewById(R.id.edText3);
        ed5 = (EditText)findViewById(R.id.edText4);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });



    }

}
