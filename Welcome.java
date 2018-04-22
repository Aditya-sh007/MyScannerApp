package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity implements View.OnClickListener{

    private Button SignUp;
    private Button SignIn;
    private TextView Guest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Guest=(TextView) findViewById(R.id.Guest);
        SignIn=(Button) findViewById(R.id.SignIn);
        SignUp=(Button) findViewById(R.id.SignUp);
        Guest.setOnClickListener(this);
        SignIn.setOnClickListener(this);
        SignUp.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v==Guest){

            startActivity(new Intent(this,OcrCaptureActivity.class));

        }
        if(v==SignIn){

            startActivity(new Intent(this,SigninActivity.class));

        }
        if(v==SignUp){
            startActivity(new Intent(this,MainActivity.class));

        }

    }




}
