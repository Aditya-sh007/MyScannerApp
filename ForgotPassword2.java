package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ForgotPassword2 extends AppCompatActivity implements View.OnClickListener {

    private Button resetPassword;
    private EditText emailAddress;
    private TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        emailAddress=(EditText) findViewById(R.id.emailAddress);
        resetPassword= (Button) findViewById(R.id.resetPassword);
        String em= emailAddress.getText().toString().trim();
        Firebase ref = new Firebase("https://ocr-final-e7230.firebaseio.com");
        ref.resetPassword(em, new Firebase.ResultHandler()
        {


            @override
            public void onSuccess () {
             //Toast.makeText(this, "A password reset email has been sent,Check it.", Toast.LENGTH_SHORT).show();

            //password changed
        }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText("W")

            }
        });

    }
     //email= (TextView) findViewById(R.id.email);

    @Override
    public void onClick(View v) {
    }
}
}
