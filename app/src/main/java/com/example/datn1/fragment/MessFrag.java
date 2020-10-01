package com.example.datn1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.datn1.R;
import com.example.datn1.adapter.MessageAdapter;
import com.example.datn1.databinding.FragmentMessBinding;
import com.example.datn1.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessFrag extends Fragment implements View.OnClickListener {

    private FragmentMessBinding binding;
    private MessageAdapter adapter;
    private List<Message> messageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMessBinding.inflate(inflater, container, false);

        messageList = new ArrayList<>();
        adapter = new MessageAdapter();

        binding.rvMessage.setAdapter(adapter);
        binding.rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.btnSend.setOnClickListener(this);

        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                messageList.add(new Message(binding.edtContent.getText().toString(), ""));
                adapter.submitList(messageList);
                binding.edtContent.setText("");
                break;
        }
    }
}