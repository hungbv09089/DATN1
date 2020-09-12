package com.example.datn1.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragment extends FragmentStatePagerAdapter {
    private final List<Fragment> mfraglist=new ArrayList<>();
    private final List<String> mfragtitlelist=new ArrayList<>();
    private final List<String> mfraglistIcon=new ArrayList<>();
    public MyFragment(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfraglist.get(position);
    }

    @Override
    public int getCount() {
        return mfraglist.size();
    }
    public void addFragment(Fragment fragment,String title){
        mfraglist.add(fragment);
        mfragtitlelist.add(title);
    }public CharSequence getPageTitle(int position){
        return mfragtitlelist.get(position);
    }
}
