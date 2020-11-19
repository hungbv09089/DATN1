package com.example.datn1.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.R;
import com.example.datn1.databinding.AdapterProfileBinding;
import com.example.datn1.model.Profile;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    private List<Profile> list;

    public ProfileAdapter(List<Profile> list) {
        this.list = list;
    }
 
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterProfileBinding binding = AdapterProfileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position), (position + 1) + "");
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn muốn?");
                builder.setNegativeButton(Html.fromHtml("<font color='#673AB7'>Đặt lịch</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton(Html.fromHtml("<font color='#673AB7'>Xem chi tiết</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


                builder.create() ; builder.show();
            }
        });
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

        public void bind(Profile profile, String pos) {
            binding.setProfile(profile);
            binding.setPos(pos);
            binding.executePendingBindings();
        }
    }
}
