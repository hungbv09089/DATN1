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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.datn1.R;
import com.example.datn1.adapter.BottomNewsAdapter;
import com.example.datn1.model.Account;
import com.example.datn1.model.Posts;
import com.example.datn1.model.TopNew;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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



            String url = "https://dofolife.herokuapp.com/getAccountFromEmail";

            OkHttpClient client = new OkHttpClient();
            Moshi moshi = new Moshi.Builder().build();
            Type AccountType = Types.newParameterizedType(List.class, Account.class);
            final JsonAdapter<List<Account>> jsonAdapter = moshi.adapter(AccountType);
            MediaType MEDIA_TYPE = MediaType.parse("application/json");
            JSONObject postdata = new JSONObject();
            try {
                postdata.put("username", currentUser.getEmail());
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
                    String json = response.body().string();
                    final List<Account> account = jsonAdapter.fromJson(json);
                    if(account.size()!=0){
                        if(account.get(0).getFirstSignIn()==true){
                            Intent intent=new Intent(getApplicationContext(),FirstSignActivity.class);
                            intent.putExtra("username",account.get(0).getUsername());
                            intent.putExtra("password",account.get(0).getPassword());
                            startActivity(intent);
                        }
                        else{
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            intent.putExtra("username",account.get(0).getUsername());
                            intent.putExtra("password",account.get(0).getPassword());
                            startActivity(intent);
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            });
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
                                checkAccount();
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
    public void checkAccount(){
        Log.d("TAG", "checkAccount: ");
        String url = "https://dofolife.herokuapp.com/getAccount";
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type AccountType = Types.newParameterizedType(List.class, Account.class);
        final JsonAdapter<List<Account>> jsonAdapter = moshi.adapter(AccountType);
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", edtTxtEmail.getText().toString().toLowerCase());
            postdata.put("password", edtTxtPassword.getText().toString());
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
                Log.d("TAG", "checkAccount321 ");
                String json = response.body().string();

                final List<Account> account = jsonAdapter.fromJson(json);
                if(account.size()!=0){
                    if(account.get(0).getFirstSignIn()==true){
                        Log.d("TAG", "123");
                        Intent intent=new Intent(getApplicationContext(),FirstSignActivity.class);
                        intent.putExtra("username",account.get(0).getUsername());
                        intent.putExtra("password",account.get(0).getPassword());
                        startActivity(intent);
                    }
                    else{
                        Log.d("TAG", "321" +
                                "");
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        intent.putExtra("username",account.get(0).getUsername());
                        intent.putExtra("password",account.get(0).getPassword());
                        startActivity(intent);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });
    }
}
