package com.example.datn1.adapter;

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
        AdapterProfileBinding binding = AdapterProfileBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position), (position + 1) + "");
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
