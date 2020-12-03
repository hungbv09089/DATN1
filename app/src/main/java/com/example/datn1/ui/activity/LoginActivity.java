package com.example.datn1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class LoginActivity extends BaseActivity {
    private Button btnQuenMatKhau;
    private Button btnLogin;
    private Button btnPass;
    private TextView tvSigin;
    TextView txtAlert;
    private FirebaseAuth mAuth;
    private TextInputEditText edtTxtEmail;
    private TextInputEditText edtTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);

        initView();

        //set text gạch chân
        btnQuenMatKhau.setText(Html.fromHtml("<u>Quên mật khẩu?</u>"));
    }

    private void initView() {
        btnQuenMatKhau = (Button) findViewById(R.id.btn_quen_mat_khau);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnPass = (Button) findViewById(R.id.btn_quen_mat_khau);
        txtAlert= findViewById(R.id.txtAlert);
        tvSigin =  findViewById(R.id.tvSignIn);
        edtTxtEmail=findViewById(R.id.edtTxtEmail);
        edtTxtPassword=findViewById(R.id.edtTxtPassword);
    }
    public void updateUI(FirebaseUser user){
        startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
    }
    public void onClickSignIn(View view){
        mAuth.signInWithEmailAndPassword(edtTxtEmail.getText().toString(), edtTxtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOGIN:", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()==false){
                                txtAlert.setText("Vui lòng xác nhận Email trước khi đăng nhập!");
                            }
                            else {
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGINL:", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void GoToSignUp(View view){
        startActivity(new Intent(this,SigningActivity.class));
    }

}
