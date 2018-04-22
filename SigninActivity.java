package com.google.android.gms.samples.vision.ocrreader;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "nileshG";
    private Button buttonSignIn;
    private EditText ediTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView textViewSignUp;
    private Button forgotPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),OcrCaptureActivity.class));
        }
        ediTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn= (Button) findViewById(R.id.buttonSignIn);
        textViewSignUp=(TextView) findViewById(R.id.textViewSignUp);
        forgotPassword=(Button)findViewById(R.id.forgotPassword);
        progressDialog = new ProgressDialog(this);
        forgotPassword.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }
    private void userLogin(){
        String email = ediTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing in ");
        progressDialog.show();
        Firebase.setAndroidContext(getApplicationContext());
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            startActivity(new Intent(getApplicationContext(),OcrCaptureActivity.class));
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(SigninActivity.this, "Wrong Credentials or check your Internet connection",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        if(v == buttonSignIn){
            userLogin();
        }
        if(v == textViewSignUp){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        if(v==forgotPassword){

            finish();
            startActivity(new Intent(this, ForgotPassword2.class));

        }
    }
}
