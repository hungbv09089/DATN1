package com.example.datn1.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datn1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class SigningActivity extends BaseActivity{
    TextInputEditText txtFullname;
    private FirebaseAuth mAuth;
    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    TextInputEditText txtCfPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signing);
        txtCfPassword=findViewById(R.id.txtCfPassword);
        txtPassword=findViewById(R.id.txtPassword);
        txtEmail=findViewById(R.id.txtEmail);
        txtFullname=findViewById(R.id.txtFullname);
    }
    public void onBack(View view){
        super.onBackPressed();
    }
    public void onSignUp(View view){
        mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("LOGIN:", "signUpWithEmail:success");
                    FirebaseUser user= mAuth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(),VerifiedActivity.class));
                                finish();
                            }
                            else {

                            }
                        }
                    });
                } else {
                    Log.w("LOGINL:", "signUpWithEmail:failure", task.getException());
                }
            }
        });
    }
}