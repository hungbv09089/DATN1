package com.example.datn1.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.datn1.R;

import java.util.Calendar;

public class SigningActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvBirthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing);

        initView();


        tvBirthday.setOnClickListener(this);
    }

    private void initView() {
        tvBirthday = findViewById(R.id.tv_birthday);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_birthday:
                showDialogDatePicker();
                break;
        }
    }

    private void showDialogDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(SigningActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String day,month;
                if (i2<10){
                    day="0"+i2;
                }else {
                    day=i2+"";
                }

                if (i1+1<10){
                    month="0"+(i1+1);
                }else {
                    month=i1+"";
                }

                tvBirthday.setText(day+"/"+month+"/"+i);
            }
        }, y, m, d);
        dialog.show();
    }

}