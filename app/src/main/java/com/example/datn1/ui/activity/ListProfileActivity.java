package com.example.datn1.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.datn1.R;
import com.example.datn1.adapter.ProfileAdapter;
import com.example.datn1.databinding.ActivityListProfileBinding;
import com.example.datn1.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ListProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityListProfileBinding binding;
    private List<Profile> profileList;
    private ProfileAdapter adapter;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Khởi tạo dữ liệu
        createData();

        //Fake dữ liệu
        fakeData();

        //Setting Recyler View
        settingRecylerView();

        //Onclick
        settingClick();

    }

    private void settingClick() {
        binding.btnCreateProfile.setOnClickListener(this);
    }

    private void settingRecylerView() {
        adapter = new ProfileAdapter(profileList);
        binding.rcv.setAdapter(adapter);
        binding.rcv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fakeData() {
        profileList.add(new Profile("Hoàng Bá Long", "0362564440", "Lam Điền", "longhbpg07396@fpt.edu.vn", "13/02/2000", "Nam"));
        profileList.add(new Profile("Hoàng Bá Long", "0362564440", "Lam Điền", "longhbpg07396@fpt.edu.vn", "13/02/2000", "Nam"));
        profileList.add(new Profile("Hoàng Bá Long", "0362564440", "Lam Điền", "longhbpg07396@fpt.edu.vn", "13/02/2000", "Nam"));
        profileList.add(new Profile("Hoàng Bá Long", "0362564440", "Lam Điền", "longhbpg07396@fpt.edu.vn", "13/02/2000", "Nam"));
        profileList.add(new Profile("Hoàng Bá Long", "0362564440", "Lam Điền", "longhbpg07396@fpt.edu.vn", "13/02/2000", "Nam"));
    }

    private void createData() {
        profileList = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateProfile:
                showDialogCreateProfile();
                break;
        }
    }

    private void showDialogCreateProfile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_create_profile, null);

        Button btnCancle = view.findViewById(R.id.btnCancle);
        Button btnCreate = view.findViewById(R.id.btnCreate);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        builder.setView(view);

        builder.create();
        alertDialog = builder.show();
    }
}