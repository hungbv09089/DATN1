package com.example.datn1.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.adapter.BottomNewsAdapter;
import com.example.datn1.adapter.TopNewsAdapter;
import com.example.datn1.databinding.FragmentNewsBinding;
import com.example.datn1.model.TopNew;

import java.util.ArrayList;
import java.util.List;

public class NewsHomeFragment extends Fragment {
    private FragmentNewsBinding binding;

    private List<TopNew> topNewList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);

        //fake data
        topNewList = new ArrayList<>();
        binding.recyclerViewTop.setAdapter(new TopNewsAdapter(topNewList));
        binding.recyclerViewTop.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        binding.recyclerViewBottom.setAdapter(new BottomNewsAdapter(topNewList));
        binding.recyclerViewBottom.setLayoutManager(new LinearLayoutManager(getContext()));
        return binding.getRoot();
    }
}
