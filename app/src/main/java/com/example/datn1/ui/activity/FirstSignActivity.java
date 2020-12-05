package com.example.datn1.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datn1.R;
import com.example.datn1.model.Account;
import com.example.datn1.model.UserProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FirstSignActivity extends AppCompatActivity  {
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;
    TextView edtFirstPhoneNumber, edtFirstHometown, edtFirstAddress,edtFirstCMND;
    FirebaseStorage storage;
    StorageReference storageReference;
    CircleImageView firstAvatar;
    String url = "https://dofolife.herokuapp.com/createFirstProfile";
    DatePicker datePicker;
    TextView edtBirhday;
    DatePickerDialog datePickerDialog;
    String birthday;
    RadioButton maleRadio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_sign);
        edtBirhday= findViewById(R.id.edtBirhday);
        maleRadio=findViewById(R.id.maleRadio);

        firstAvatar=findViewById(R.id.firstAvatar);
        edtFirstAddress=findViewById(R.id.edtFirstAddress);
        edtFirstCMND=findViewById(R.id.edtFirstCMND);
        edtFirstHometown= findViewById(R.id.edtFirstHometown);
        edtFirstPhoneNumber= findViewById(R.id.edtFirstPhoneNumber);
        edtBirhday=findViewById(R.id.edtBirhday);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        maleRadio.setChecked(true);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
         datePickerDialog = new DatePickerDialog(FirstSignActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edtBirhday.setText(dayOfMonth+"-"+monthOfYear+"-"+year);
                birthday=dayOfMonth+"-"+monthOfYear+"-"+year;
            }
        }, year, month, day);
    }
    public void onSubmit(View view){
        Log.e("TAG", "onSubmit: "+birthday );
    }
    public void onPickDate(View view){
        datePickerDialog.show();
    }
    public void SelectImage(View view)
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
                firstAvatar.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    public void onSubmitFirstView(View view)
    {
        Log.d("TAG", "onSubmitFirstView: ");
    }
    public void uploadImage(View view)
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
                                            String phonenumber= edtFirstPhoneNumber.getText().toString();
                                            String hometown= edtFirstHometown.getText().toString();
                                            String address = edtFirstAddress.getText().toString();
                                            String CMND = edtFirstCMND.getText().toString();
                                            String birthday = edtBirhday.getText().toString();
                                            Boolean male= maleRadio.isChecked();

                                            OkHttpClient client = new OkHttpClient();
                                            MediaType MEDIA_TYPE = MediaType.parse("application/json");
                                            JSONObject postdata = new JSONObject();
                                            try {
                                                postdata.put("username", username);
                                                postdata.put("password", password);
                                                postdata.put("avatar", imageurl);
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
//                                                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
//                                                            intent.putExtra("username",username);
//                                                            intent.putExtra("password",password);
//                                                            startActivity(intent);
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

            String phonenumber= edtFirstPhoneNumber.getText().toString();
            String hometown= edtFirstHometown.getText().toString();
            String address = edtFirstAddress.getText().toString();
            String CMND = edtFirstCMND.getText().toString();
            String birthday = edtBirhday.getText().toString();
            Boolean male= maleRadio.isChecked();

            OkHttpClient client = new OkHttpClient();
            MediaType MEDIA_TYPE = MediaType.parse("application/json");
            JSONObject postdata = new JSONObject();
            try {
                postdata.put("username", username);
                postdata.put("password", password);
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