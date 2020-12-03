package com.example.datn1.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.datn1.R;
import com.example.datn1.adapter.BottomNewsAdapter;

import com.example.datn1.databinding.ActivityHomeBinding;
import com.example.datn1.databinding.HeaderNavBinding;
import com.example.datn1.model.Posts;
import com.example.datn1.model.TestUser;
import com.example.datn1.model.TopNew;

import com.example.datn1.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private ActivityHomeBinding binding;

    private HeaderNavBinding headerNavBinding;
    RecyclerView recyclerView;
    DrawerLayout mDrawer;
    String url = "http://192.168.92.100:3000/getAllPost";
    private List<TopNew> topNewList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_home);
        recyclerView=findViewById(R.id.recyclerViewBottom);
        mDrawer=(DrawerLayout)findViewById(R.id.mDrawer);

        headerNavBinding = HeaderNavBinding.bind(binding.navView.getHeaderView(0));
        headerNavBinding.setUser(new User("https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg", "Ngọc Trinh", "abcasckjkac@gmail.com"));
        binding.navView.setNavigationItemSelectedListener(this);

        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type postType = Types.newParameterizedType(List.class, Posts.class);
        final JsonAdapter<List<Posts>> jsonAdapter = moshi.adapter(postType);
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                // Lấy thông tin JSON trả về. Bạn có thể log lại biến json này để xem nó như thế nào.
                String json = response.body().string();
                final List<Posts> posts = jsonAdapter.fromJson(json);

                // Cho hiển thị lên RecyclerView.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        topNewList = new ArrayList<>();
                        for (int x=0;x<posts.size();x++){
                            Log.e("000",posts.get(x).getTitle());
                            topNewList.add(new TopNew(posts.get(x).getTitle(),posts.get(x).getImage(),posts.get(x).getSubTitle(),posts.get(x).getUrl()));
                        }
                        recyclerView.setAdapter(new BottomNewsAdapter(topNewList));
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                });
            }
        });
    }
    public void onClickMenu(View view){
        mDrawer.openDrawer(Gravity.LEFT);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_user:
                startActivity(new Intent(HomeActivity.this,ListProfileActivity.class));
                break;
            case R.id.item_info:
                startActivity(new Intent(HomeActivity.this,AboutActivity.class));
                break;
            case R.id.item_logout:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                View view= LayoutInflater.from(this).inflate(R.layout.dialog_logout,null,false);
                builder.setView(view);
                builder.create();
                builder.show();
                break;
        }
        return true;
    }
}