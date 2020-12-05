package com.example.datn1.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.datn1.R;
import com.example.datn1.adapter.BottomNewsAdapter;
import com.example.datn1.adapter.ProfileAdapter;
import com.example.datn1.databinding.ActivityListProfileBinding;
import com.example.datn1.model.Account;
import com.example.datn1.model.Posts;
import com.example.datn1.model.Profile;
import com.example.datn1.model.TopNew;
import com.example.datn1.model.UserProfile;
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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityListProfileBinding binding;
    private List<Profile> profileList;
    private ProfileAdapter adapter;
    private AlertDialog alertDialog;
    String username,password;
    String url = "https://dofolife.herokuapp.com/getProfileOfAccount";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        profileList= new ArrayList<>();
        Intent data = getIntent();
        String username=data.getStringExtra("username");
        String password=data.getStringExtra("password");
        //Khởi tạo dữ liệu
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type UserProfileType = Types.newParameterizedType(List.class, UserProfile.class);
        final JsonAdapter<List<UserProfile>> jsonAdapter = moshi.adapter(UserProfileType);
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("username", username);
            postdata.put("password", password);
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
                final List<UserProfile> userProfiles = jsonAdapter.fromJson(json);
                runOnUiThread(new Runnable() {
                    @Override
                        public void run() {
                        adapter= new ProfileAdapter(userProfiles);
                        binding.rcv.setAdapter(adapter);
                        binding.rcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });
            }
        });
        settingClick();

    }

    private void settingClick() {
        binding.btnCreateProfile.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateProfile:
                showDialogCreateProfile();
                break;
        }
    }
    public String convertSex(Boolean male){
        if (male==true)
        return "Name";
        else return "Nữ";
    }

    private void showDialogCreateProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_create_profile, null);

        Button btnCancle = view.findViewById(R.id.btnCancle);
        Button btnCreate = view.findViewById(R.id.btnCreate);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                startActivity(new Intent(ListProfileActivity.this,CreateProfileActivity.class));
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data= getIntent();
                String username = data.getStringExtra("username");
                String password = data.getStringExtra("password");
                Log.d("TAG", password+"onClick: "+username);
                Intent intent= new Intent(ListProfileActivity.this,CreateProfileActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                startActivity(intent);
            }
        });

        builder.setView(view);

        builder.create();
        alertDialog = builder.show();
    }
}