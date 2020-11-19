package com.example.datn1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datn1.R;
import com.example.datn1.databinding.ActivityNumberPhoneConfimBinding;

public class NumberPhoneConfimActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityNumberPhoneConfimBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumberPhoneConfimBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.btnSigin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSigin:

                startActivity(new Intent(this, OTPConfirmActivity.class));
                finish();
                break;
        }
    }
}