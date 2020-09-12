package com.example.datn1.fragment;

import android.content.Intent;
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
import com.example.datn1.activity.SucKhoeActivity;
import com.example.datn1.adapter.SucKhoeAdapter;
import com.example.datn1.model.Suckhoe;

import java.util.ArrayList;

public class SucKhoeFrag extends Fragment {

     View view;
     ArrayList<Suckhoe> suckhoeList;
    TextView tvseeall;
    RecyclerView rcvsuckhoe;
    public View onCreateView(@NonNull LayoutInflater    inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_baivietsuckhoe,container,false);
        tvseeall=view.findViewById(R.id.seeallsk);
        rcvsuckhoe=view.findViewById(R.id.rcvsuckhoe);
        suckhoeList=new ArrayList<>();
        suckhoeList.add(new Suckhoe("","suc khỏe 01"));
        suckhoeList.add(new Suckhoe("","suc khỏe 02"));
        suckhoeList.add(new Suckhoe("","suc khỏe 03"));
        suckhoeList.add(new Suckhoe("","suc khỏe 04"));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        SucKhoeAdapter sucKhoeAdapter=new SucKhoeAdapter(suckhoeList,getContext());
        rcvsuckhoe.setLayoutManager(linearLayoutManager);
        rcvsuckhoe.setAdapter(sucKhoeAdapter);
        tvseeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SucKhoeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
