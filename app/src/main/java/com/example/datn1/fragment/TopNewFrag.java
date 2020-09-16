package com.example.datn1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.R;
import com.example.datn1.adapter.TopNewAdapter;
import com.example.datn1.model.TopNew;

import java.util.ArrayList;

public class TopNewFrag extends Fragment {
    View view;
    TextView tvseeall;
    private RecyclerView rcvtopnew;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_topnew,container,false);
        tvseeall=view.findViewById(R.id.tvseeallnews);
        rcvtopnew=view.findViewById(R.id.rcvtopnew);
        tvseeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ArrayList<TopNew> topNewsArray=new ArrayList<>();
        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1000",""));
        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1001",""));
        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1002",""));
        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1003",""));
        topNewsArray.add(new TopNew("HOT - Phát hiện bệnh nhân nhiễm Covid thứ 1004",""));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        TopNewAdapter topNewAdapter=new TopNewAdapter(getContext(),topNewsArray);
        rcvtopnew.setLayoutManager(linearLayoutManager);
        rcvtopnew.setAdapter(topNewAdapter);
        return view;
    }
}
