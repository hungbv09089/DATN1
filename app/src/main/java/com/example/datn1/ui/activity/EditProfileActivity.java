package com.example.datn1.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.datn1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditProfileActivity extends AppCompatActivity {
    CircleImageView editAvatar;
    private Uri filePath;
    FirebaseStorage storage;
    private final int PICK_IMAGE_REQUEST = 22;
    DatePickerDialog datePickerDialog;
    StorageReference storageReference;
    String birthdays;
    EditText edtEditFullname,edtEditEmail,edtEditPhoneNumber,edtEditHometown,edtEditAddress,edtEditCMND;
    TextView edtEditBirhday;
    RadioButton EditMaleRadio;
    String username,password,fullname,avatar,email,birthday,CMND,hometown,phonenumber,address,_id;
    Boolean male;
    String url = "https://dofolife.herokuapp.com/updateProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editAvatar=findViewById(R.id.EditAvatar);
        edtEditFullname= findViewById(R.id.edtEditFullname);
        edtEditEmail= findViewById(R.id.edtEditEmail);
        edtEditPhoneNumber= findViewById(R.id.edtEditPhoneNumber);
        edtEditHometown= findViewById(R.id.edtEditHometown);
        edtEditAddress= findViewById(R.id.edtEditAddress);
        edtEditCMND= findViewById(R.id.edtEditCMND);
        edtEditBirhday= findViewById(R.id.edtEditBirhday);
        EditMaleRadio= findViewById(R.id.EditMaleRadio);
        storage = FirebaseStorage.getInstance();
        EditMaleRadio.setChecked(true);
        Intent intent= getIntent();
        _id = intent.getStringExtra("_id");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        fullname = intent.getStringExtra("fullname");
        avatar = intent.getStringExtra("avatar");
        email = intent.getStringExtra("email");
        birthday = intent.getStringExtra("birthday");
        CMND = intent.getStringExtra("CMND");
        hometown = intent.getStringExtra("hometown");
        phonenumber = intent.getStringExtra("phonenumber");
        address = intent.getStringExtra("address");
        male = intent.getBooleanExtra("male",true);

        edtEditFullname.setText(fullname);
        edtEditEmail.setText(email);
        edtEditPhoneNumber.setText(phonenumber);
        edtEditHometown.setText(hometown);
        edtEditAddress.setText(address);
        edtEditCMND.setText(CMND);
        edtEditBirhday.setText(birthday);
        EditMaleRadio.setText(convertSex(male));
        Picasso.with(this).load(avatar).into(editAvatar);



        storageReference = storage.getReference();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtEditBirhday.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                birthdays=dayOfMonth+"-"+monthOfYear+"-"+year;
            }
        }, year, month, day);
    }
    public String convertSex(Boolean male){
        if(male==true)return "Nam";
        else return"Nữ";
    }
    public void onBack(View view){
        super.onBackPressed();
    }
    public void deleteProfile(View view){
        final ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE = MediaType.parse("application/json");
        JSONObject postdata = new JSONObject();
        try {
            postdata.put("_id", _id);
            postdata.put("username", username);
            postdata.put("password", password);
            Log.d("TAG", "deleteProfile: "+_id);
            Log.d("TAG", "deleteProfile: "+username);
            Log.d("TAG", "deleteProfile: "+password);
        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
        Request request = new Request.Builder()
                .url("https://dofolife.herokuapp.com/deleteProfile")
                .post(body)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Network Error");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Intent intent= new Intent(EditProfileActivity.this, ListProfileActivity.class);
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
                });
            }
        });
    }
    public void selectImage(View view)
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                editAvatar.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }
    public void onPickDate(View view){
        datePickerDialog.show();
    }
    public void finish(View view)
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Loading...");
            progressDialog.show();

            // Defining the child of storageReference
            final StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());
            Log.e("TAG123", "uploadImage: "+storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString()) );
            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String TAG="TEST";
                                            Intent intent=getIntent();
                                            final String username= intent.getStringExtra("username");
                                            final String password= intent.getStringExtra("password");
                                            Log.e(TAG, "onSuccess: "+username );
                                            Log.e(TAG, "onSuccess: "+password );

                                            final String imageurl= String.valueOf(uri);
                                            final String fullname= edtEditFullname.getText().toString();
                                            final String email= edtEditEmail.getText().toString();
                                            final String phonenumber= edtEditPhoneNumber.getText().toString();
                                            final String hometown= edtEditHometown.getText().toString();
                                            final String address = edtEditAddress.getText().toString();
                                            final String CMND = edtEditCMND.getText().toString();
                                            final  String birthday = edtEditBirhday.getText().toString();
                                            final Boolean male= EditMaleRadio.isChecked();

                                            OkHttpClient client = new OkHttpClient();
                                            MediaType MEDIA_TYPE = MediaType.parse("application/json");
                                            JSONObject postdata = new JSONObject();
                                            try {
                                                Log.d(TAG, "onSuccess: "+_id);
                                                postdata.put("_id", _id);
                                                postdata.put("username", username);
                                                postdata.put("password", password);
                                                postdata.put("avatar", imageurl);
                                                postdata.put("fullname", fullname);
                                                postdata.put("email", email);
                                                postdata.put("birthday", birthday);
                                                postdata.put("CMND", CMND);
                                                postdata.put("hometown", hometown);
                                                postdata.put("phonenumber", phonenumber);
                                                postdata.put("address", address);
                                                postdata.put("male", male);
                                            } catch(JSONException e){
                                                // TODO Auto-generated catch block
                                                e.printStackTrace();
                                            }
                                            RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
                                            Request request = new Request.Builder()
                                                    .url(url)
                                                    .post(body)
                                                    .header("Accept", "application/json")
                                                    .header("Content-Type", "application/json")
                                                    .build();
                                            client.newCall(request).enqueue(new Callback() {
                                                @Override
                                                public void onFailure(Call call, IOException e) {
                                                    Log.e("Error", "Network Error");
                                                }
                                                @Override
                                                public void onResponse(Call call, Response response) throws IOException {
                                                    final String json = response.body().string();
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            progressDialog.dismiss();
                                                                   Intent data = getIntent();
                                                    String username=data.getStringExtra("username");
                                                    String password=data.getStringExtra("password");
                                                    Intent intent= new Intent(EditProfileActivity.this, ProfileDetailAcivity.class);
                                                    Log.d("TAG", "onSuccess: "+_id);
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
                                                    });
                                                }
                                            });



                                        }
                                    });

//                                    Log.e("TAG", "onSuccess: "+taskSnapshot.getStorage().getDownloadUrl());
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            progressDialog.dismiss();

                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
        else{
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Loading...");
            progressDialog.show();
            String TAG="TEST";
            Intent intent=getIntent();
            final String username= intent.getStringExtra("username");
            final String password= intent.getStringExtra("password");
            Log.e(TAG, "onSuccess: "+username );
            Log.e(TAG, "onSuccess: "+password );
            final String fullname= edtEditFullname.getText().toString();
            final String email= edtEditEmail.getText().toString();
            final String phonenumber= edtEditPhoneNumber.getText().toString();
            final String hometown= edtEditHometown.getText().toString();
            final String address = edtEditAddress.getText().toString();
            final String CMND = edtEditCMND.getText().toString();
            final  String birthday = edtEditBirhday.getText().toString();
            final Boolean male= EditMaleRadio.isChecked();


            OkHttpClient client = new OkHttpClient();
            MediaType MEDIA_TYPE = MediaType.parse("application/json");
            JSONObject postdata = new JSONObject();
            try {
                postdata.put("_id", _id);
                postdata.put("username", username);
                postdata.put("password", password);
                postdata.put("fullname", fullname);
                postdata.put("email", email);
                postdata.put("birthday", birthday);
                postdata.put("CMND", CMND);
                postdata.put("hometown", hometown);
                postdata.put("phonenumber", phonenumber);
                postdata.put("address", address);
                postdata.put("male", male);


            } catch(JSONException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RequestBody body = RequestBody.create(MEDIA_TYPE, postdata.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("Error", "Network Error");
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String json = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                                                    Intent intent= new Intent(EditProfileActivity.this, ProfileDetailAcivity.class);
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
                    });
                }
            });

        }
    }
}