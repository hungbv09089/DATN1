package com.example.datn1.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.datn1.R;
import com.example.datn1.adapter.BottomNewsAdapter;
import com.example.datn1.adapter.TopNewsAdapter;
import com.example.datn1.databinding.ActivityHomeBinding;
import com.example.datn1.databinding.HeaderNavBinding;
import com.example.datn1.model.TopNew;
import com.example.datn1.ui.fragment.MessFrag;
import com.example.datn1.ui.fragment.NewsHomeFragment;
import com.example.datn1.ui.fragment.ScheduleFragment;
import com.example.datn1.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {
    private ActivityHomeBinding binding;

    private HeaderNavBinding headerNavBinding;
    private List<TopNew> topNewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        headerNavBinding = HeaderNavBinding.bind(binding.navView.getHeaderView(0));

        headerNavBinding.setUser(new User("https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg", "Ngọc Trinh", "abcasckjkac@gmail.com"));

        binding.navView.setNavigationItemSelectedListener(this);

        fakeData();

        settingRecyclerView();
    }

    private void settingRecyclerView() {
        binding.recyclerViewBottom.setAdapter(new BottomNewsAdapter(topNewList));
        binding.recyclerViewBottom.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fakeData() {
        topNewList = new ArrayList<>();
        topNewList.add(new TopNew("HOT - Ngọc Trinh muối mặt khi bị 'người tình' Hoài Linh mỉa mai chuyện đào mỏ đại gia","https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg"));
        topNewList.add(new TopNew("HOT - Ngọc Trinh muối mặt khi bị 'người tình' Hoài Linh mỉa mai chuyện đào mỏ đại gia","https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg"));
        topNewList.add(new TopNew("HOT - Ngọc Trinh muối mặt khi bị 'người tình' Hoài Linh mỉa mai chuyện đào mỏ đại gia","https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg"));
        topNewList.add(new TopNew("HOT - Ngọc Trinh muối mặt khi bị 'người tình' Hoài Linh mỉa mai chuyện đào mỏ đại gia","https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg"));
        topNewList.add(new TopNew("HOT - Ngọc Trinh muối mặt khi bị 'người tình' Hoài Linh mỉa mai chuyện đào mỏ đại gia","https://media.tinmoi.vn/upload/camnhung/2020/11/17/130719-ngoc-trinh-bi-mia-mai-tm4.jpg"));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_info:
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}