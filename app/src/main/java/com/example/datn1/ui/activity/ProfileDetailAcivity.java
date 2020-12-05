package com.example.datn1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.datn1.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDetailAcivity extends AppCompatActivity {
    CircleImageView detailAvatar;
    TextView detailFullname,detailEmail,detailBirhday,detailSex,detailHometown,detailAddress,detailPhonenumber,detailCMND;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail_acivity);
        detailAvatar=findViewById(R.id.detailAvatar);
        detailFullname=findViewById(R.id.detailFullname);
        detailEmail=findViewById(R.id.detailEmail);
        detailBirhday=findViewById(R.id.detailBirhday);
        detailSex=findViewById(R.id.detailSex);
        detailHometown=findViewById(R.id.detailHometown);
        detailAddress=findViewById(R.id.detailAddress);
        detailPhonenumber=findViewById(R.id.detailPhonenumber);
        detailCMND=findViewById(R.id.detailCMND);

        Intent intent= getIntent();
        String username = intent.getStringExtra("username");
        String pasword = intent.getStringExtra("username");
        String fullname = intent.getStringExtra("fullname");
        String avatar = intent.getStringExtra("avatar");
        String email = intent.getStringExtra("email");
        String birthday = intent.getStringExtra("birthday");
        String CMND = intent.getStringExtra("CMND");
        String hometown = intent.getStringExtra("hometown");
        String phonenumber = intent.getStringExtra("phonenumber");
        String address = intent.getStringExtra("address");
        Boolean male = intent.getBooleanExtra("male",true);
        detailFullname.setText(fullname);
        detailAddress.setText(address);
        detailBirhday.setText(birthday);
        detailCMND.setText(CMND);
        detailEmail.setText(email);
        detailHometown.setText(hometown);
        detailSex.setText(convertSex(male));
        detailPhonenumber.setText(phonenumber);
        Picasso.with(this).load(avatar).into(detailAvatar);
    }
    public String convertSex(Boolean male){
        if(male==true)return "Nam";
        else return"Nữ";
    }
}