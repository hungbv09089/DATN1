package com.example.datn1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn1.R;
import com.example.datn1.databinding.ActivityHomeBinding;
import com.example.datn1.fragment.MessFrag;
import com.example.datn1.fragment.NewsHomeFragment;
import com.example.datn1.fragment.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.navHome.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, new NewsHomeFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = new NewsHomeFragment();
        switch (item.getItemId()) {
            case R.id.item_nav_news:
                fragment = new NewsHomeFragment();
                break;
            case R.id.item_nav_calender:
                fragment=new ScheduleFragment();
                break;
            case R.id.item_nav_chat:
                fragment=new MessFrag();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
        return true;
    }
}