package com.example.datn1.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.datn1.R;
import com.example.datn1.fragment.MessFrag;
import com.example.datn1.fragment.MyFragment;
import com.example.datn1.fragment.NewsPaperFrag;
import com.example.datn1.fragment.ScheduleFrag;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager5);
        tabLayout=findViewById(R.id.tabLayout);
//        toolbar=findViewById(R.id.toolbar);
        HomeTabLayout();
    }
    private void HomeTabLayout(){
        MyFragment myFragment=new MyFragment(getSupportFragmentManager());
        myFragment.addFragment(new NewsPaperFrag(),"");
        myFragment.addFragment(new ScheduleFrag(),"");
        myFragment.addFragment(new MessFrag(),"");
        viewPager.setAdapter(myFragment);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Bảng tin");
        tabLayout.getTabAt(1).setText("Đặt lịch");
        tabLayout.getTabAt(2).setText("Tư Vấn");
    }
}