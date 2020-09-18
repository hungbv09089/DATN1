package com.example.datn1.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

import com.example.datn1.R;

public class LoginActivity extends BaseActivity {
    private Button btnQuenMatKhau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        //set text gạch chân
        btnQuenMatKhau.setText(Html.fromHtml("<u>Quên mật khẩu?</u>"));
    }

    private void initView() {
        btnQuenMatKhau = (Button) findViewById(R.id.btn_quen_mat_khau);
    }
}