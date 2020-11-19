package com.example.datn1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datn1.R;
import com.example.datn1.databinding.ActivityConfirmPasswordBinding;
import com.example.datn1.databinding.ActivityHomeBinding;

public class ConfirmPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityConfirmPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChangePass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChangePass:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}