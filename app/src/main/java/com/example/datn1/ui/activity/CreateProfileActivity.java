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

public class CreateProfileActivity extends AppCompatActivity {
    CircleImageView newAvatar;
    private Uri filePath;
    FirebaseStorage storage;
    private final int PICK_IMAGE_REQUEST = 22;
    DatePickerDialog datePickerDialog;
    StorageReference storageReference;
    String birthday;
    EditText edtNewFullname,edtNewEmail,edtNewPhoneNumber,edtNewHometown,edtNewAddress,edtNewCMND;
    TextView edtNewBirhday;
    RadioButton NewMaleRadio;

    String url = "https://dofolife.herokuapp.com/creatNewProfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        newAvatar=findViewById(R.id.newAvatar);
        edtNewFullname= findViewById(R.id.edtNewFullname);
        edtNewEmail= findViewById(R.id.edtNewEmail);
        edtNewPhoneNumber= findViewById(R.id.edtNewPhoneNumber);
        edtNewHometown= findViewById(R.id.edtNewHometown);
        edtNewAddress= findViewById(R.id.edtNewAddress);
        edtNewCMND= findViewById(R.id.edtNewCMND);
        edtNewBirhday= findViewById(R.id.edtNewBirhday);
        NewMaleRadio= findViewById(R.id.NewMaleRadio);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(CreateProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtNewBirhday.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                birthday=dayOfMonth+"-"+monthOfYear+"-"+year;
            }
        }, year, month, day);
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
                newAvatar.setImageBitmap(bitmap);
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

                                            String imageurl= String.valueOf(uri);
                                            String fullname= edtNewFullname.getText().toString();
                                            String email= edtNewEmail.getText().toString();
                                            String phonenumber= edtNewPhoneNumber.getText().toString();
                                            String hometown= edtNewHometown.getText().toString();
                                            String address = edtNewAddress.getText().toString();
                                            String CMND = edtNewCMND.getText().toString();
                                            String birthday = edtNewBirhday.getText().toString();
                                            Boolean male= NewMaleRadio.isChecked();

                                            OkHttpClient client = new OkHttpClient();
                                            MediaType MEDIA_TYPE = MediaType.parse("application/json");
                                            JSONObject postdata = new JSONObject();
                                            try {
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
                                                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                                            intent.putExtra("username",username);
                                                            intent.putExtra("password",password);
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
            String fullname= edtNewFullname.getText().toString();
            String email= edtNewEmail.getText().toString();
            String phonenumber= edtNewPhoneNumber.getText().toString();
            String hometown= edtNewHometown.getText().toString();
            String address = edtNewAddress.getText().toString();
            String CMND = edtNewCMND.getText().toString();
            String birthday = edtNewBirhday.getText().toString();
            Boolean male= NewMaleRadio.isChecked();

            OkHttpClient client = new OkHttpClient();
            MediaType MEDIA_TYPE = MediaType.parse("application/json");
            JSONObject postdata = new JSONObject();
            try {
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
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            intent.putExtra("username",username);
                            intent.putExtra("password",password);
                            startActivity(intent);
                        }
                    });
                }
            });

        }
    }
}