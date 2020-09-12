package com.example.datn1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.datn1.R;

public class TopNewFrag extends Fragment {
    View view;
    TextView tvseeall;
    ViewPager2 viewPager2;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_topnew,container,false);
        tvseeall=view.findViewById(R.id.tvseeallnews);
        viewPager2=view.findViewById(R.id.vptopnew);
        tvseeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
//    public void GetDataPN(){
//        ArrayList<TopNew> topNewsArray=new ArrayList<>();
//        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1000",""));
//        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1001",""));
//        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1002",""));
//        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1003",""));
//        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1004",""));
//        TopNewAdapter topNewAdapter=new TopNewAdapter(getContext(),topNewsArray);
//        viewPager2.setAdapter(topNewAdapter);
//    }
}
