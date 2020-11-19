package com.example.datn1.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datn1.R;

public class OTPConfirmActivity extends AppCompatActivity {
    private EditText edtOtp1;
    private EditText edtOtp2;
    private EditText edtOtp3;
    private EditText edtOtp4;
    private EditText edtOtp5;
    private EditText edtOtp6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_confim);

        initView();

        //Xét sự kiện tự động chuyển focus các edt
        setFocusEdtOTP();
    }

    private void initView() {
        edtOtp1 = findViewById(R.id.edt_otp1);
        edtOtp2 = findViewById(R.id.edt_otp2);
        edtOtp3 = findViewById(R.id.edt_otp3);
        edtOtp4 = findViewById(R.id.edt_otp4);
        edtOtp5 = findViewById(R.id.edt_otp5);
        edtOtp6 = findViewById(R.id.edt_otp6);

    }

    private void setFocusEdtOTP() {
        setFocusNext(edtOtp1,edtOtp4);
        setFocusNext(edtOtp4,edtOtp5);
        setFocusNext(edtOtp5,edtOtp6);
        setFocusNext(edtOtp6,edtOtp2);
        setFocusNext(edtOtp2,edtOtp3);
        setFocusNext(edtOtp3,edtOtp3);
    }

    private void setFocusNext(final EditText edtOtp1, final EditText edtOtp2) {
        edtOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!checkEmty(edtOtp1.getText().toString())){
                    edtOtp2.requestFocus();
                }
            }
        });
    }

    private boolean checkEmty(String s) {
        return s.isEmpty();
    }
}