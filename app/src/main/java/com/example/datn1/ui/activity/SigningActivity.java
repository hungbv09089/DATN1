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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    public void postCreateAccount(){
        String url = "https://dofolife.herokuapp.com/createAcount";
        OkHttpClient client = new OkHttpClient();

        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", txtEmail.getText().toString().toLowerCase());
            postdata.put("fullname", txtFullname.getText().toString());
            postdata.put("password", txtPassword.getText().toString());
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String data= response.body().string();
                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                // Cho hiển thị lên RecyclerView.
                Log.e("TAG", "onResponse: "+data );
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(),VerifiedActivity.class));
                    }
                });
            }
        });
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
                                postCreateAccount();
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