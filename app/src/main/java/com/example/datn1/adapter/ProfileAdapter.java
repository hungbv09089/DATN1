package com.example.datn1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.R;
import com.example.datn1.databinding.AdapterProfileBinding;
import com.example.datn1.model.Profile;
import com.example.datn1.model.UserProfile;
import com.example.datn1.ui.activity.CreateProfileActivity;
import com.example.datn1.ui.activity.ListProfileActivity;
import com.example.datn1.ui.activity.ProfileDetailAcivity;
import com.example.datn1.ui.activity.ScheduleActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private List<UserProfile> list;
    CircleImageView circleImageView;
    public ProfileAdapter(List<UserProfile> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterProfileBinding binding = AdapterProfileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(list.get(position), (position + 1) + "");
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View  view) {
                String fullname = list.get(position).getFullname();
                String avatar = list.get(position).getAvatar();
                String email = list.get(position).getEmail();
                String birthday = list.get(position).getBirthday();
                String CMND = list.get(position).getCMND();
                String hometown = list.get(position).getHometown();
                String phonenumber = list.get(position).getPhonenumber();
                String address = list.get(position).getAddress();
                Boolean male = list.get(position).getMale();

                Intent data = ((Activity) view.getContext()).getIntent();
                String username=data.getStringExtra("username");
                String password=data.getStringExtra("password");
                Intent intent= new Intent(view.getContext(), ProfileDetailAcivity.class);
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
                view.getContext().startActivity(intent);

//                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                builder.setTitle("Thông báo");
//                builder.setMessage("Bạn muốn?");
//                builder.setNegativeButton(Html.fromHtml("<font color='#673AB7'>Đặt lịch</font>"), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        view.getContext().startActivity(new Intent(view.getContext(), ScheduleActivity.class));
//                    }
//                });
//                builder.setPositiveButton(Html.fromHtml("<font color='#673AB7'>Xem chi tiết</font>"), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        view.getContext().startActivity(new Intent(view.getContext(), ScheduleActivity.class));
//                    }
//                });
//
//
//                builder.create() ; builder.show();
            }
        });
//
//        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                list.remove(position);
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterProfileBinding binding;

        public ViewHolder(@NonNull AdapterProfileBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void bind(UserProfile profile, String pos) {
            Log.d("TAG", "bind: "+pos);
            binding.setProfile(profile);
            binding.setPos(pos);
            binding.executePendingBindings();
            circleImageView=binding.imageView10;
            Picasso.with(itemView.getContext()).load(profile.getAvatar()).into(circleImageView);
        }
    }
}
