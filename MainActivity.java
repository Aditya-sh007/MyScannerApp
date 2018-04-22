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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG = "nileshG";
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private FirebaseAuth mRef;
    private ProgressDialog progressdialog;
    private TextView textViewSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRef = FirebaseAuth.getInstance();
        if(mRef.getCurrentUser()!=null){
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),OcrCaptureActivity.class));
        }

        progressdialog=new ProgressDialog(this);
        buttonRegister=(Button) findViewById(R.id.buttonRegister);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        textViewSignin=(TextView) findViewById(R.id.textViewSignIn);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }
    private void registerUser(){
        String email =editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        if(password.length()<6){
            Toast.makeText(MainActivity.this, "Length of password should be greater than 6 character",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter email",Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressdialog.setMessage("Registering User...");
        progressdialog.show();
        mRef.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressdialog.dismiss();
                            Toast.makeText(MainActivity.this, "Registered Successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),OcrCaptureActivity.class));
                        }
                        else{
                            progressdialog.dismiss();
                            Toast.makeText(MainActivity.this, "Enter valid email Id",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }
        if (view == textViewSignin) {
            finish();
            startActivity(new Intent(this, SigninActivity.class));
        }

    }
}