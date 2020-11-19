package com.example.datn1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.datn1.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btnQuenMatKhau;
    private Button btnLogin;
    private Button btnPass;
    private TextView tvSigin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        //set text gạch chân
        btnQuenMatKhau.setText(Html.fromHtml("<u>Quên mật khẩu?</u>"));

        tvSigin.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnPass.setOnClickListener(this);
    }

    private void initView() {
        btnQuenMatKhau = (Button) findViewById(R.id.btn_quen_mat_khau);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnPass = (Button) findViewById(R.id.btn_quen_mat_khau);
        tvSigin =  findViewById(R.id.tvSignIn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvSignIn:
                startActivity(new Intent(this,SigningActivity.class));
                finish();
                break;
            case R.id.btnLogin:
                startActivity(new Intent(this,HomeActivity.class));
                finish();
                break;
            case R.id.btn_quen_mat_khau:
                startActivity(new Intent(this,ConfirmPasswordActivity.class));
                finish();
                break;
        }
    }
}