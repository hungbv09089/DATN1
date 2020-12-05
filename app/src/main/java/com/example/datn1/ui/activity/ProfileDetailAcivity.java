package com.example.datn1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.datn1.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileDetailAcivity extends AppCompatActivity {
    CircleImageView detailAvatar;
    TextView detailFullname,detailEmail,detailBirhday,detailSex,detailHometown,detailAddress,detailPhonenumber,detailCMND;
    String username,pasword,fullname,avatar,email,birthday,CMND,hometown,phonenumber,address,_id;
    Boolean male;
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
        _id = intent.getStringExtra("_id");
         username = intent.getStringExtra("username");
         pasword = intent.getStringExtra("username");
         fullname = intent.getStringExtra("fullname");
         avatar = intent.getStringExtra("avatar");
         email = intent.getStringExtra("email");
         birthday = intent.getStringExtra("birthday");
         CMND = intent.getStringExtra("CMND");
         hometown = intent.getStringExtra("hometown");
         phonenumber = intent.getStringExtra("phonenumber");
         address = intent.getStringExtra("address");
         male = intent.getBooleanExtra("male",true);
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
    public void onBack(View view){
        super.onBackPressed();
    }
    public void EditProfile(View view){
        Intent data = getIntent();
        String username=data.getStringExtra("username");
        String password=data.getStringExtra("password");
        Intent intent= new Intent(view.getContext(), EditProfileActivity.class);
        intent.putExtra("_id",_id);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        intent.putExtra("fullname",fullname);
        intent.putExtra("avatar",avatar);
        intent.putExtra("email",email);
        intent.putExtra("birthday",birthday);
        intent.putExtra("CMND",CMND);
        intent.putExtra("phonenumber",phonenumber);
        intent.putExtra("hometown",hometown);
        intent.putExtra("address",address);
        intent.putExtra("male",male);
        startActivity(intent);
    }
}