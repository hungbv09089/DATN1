package com.example.datn1.ui.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.datn1.R;
import com.example.datn1.databinding.FragmentScheduleBinding;

import java.util.Calendar;

public class ScheduleFragment extends Fragment implements View.OnClickListener {
    private FragmentScheduleBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);

        binding.edtDate.setOnClickListener(this);
        binding.btnDate.setOnClickListener(this);

        return binding.getRoot();
    }

    private void showDialogDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String day, month;
                if (i2 < 10) {
                    day = "0" + i2;
                } else {
                    day = i2 + "";
                }

                if (i1 + 1 < 10) {
                    month = "0" + (i1 + 1);
                } else {
                    month = i1+1 + "";
                }

                binding.edtDate.setText(day + "/" + month + "/" + i);
            }
        }, y, m, d);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDate:
            case R.id.edtDate:
                showDialogDatePicker();
                break;
        }
    }
}
