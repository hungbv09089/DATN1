package com.example.datn1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.databinding.AdapterBottomNewsBinding;
import com.example.datn1.model.TopNew;

import java.util.List;

public class BottomNewsAdapter extends RecyclerView.Adapter<BottomNewsAdapter.ViewHolder> {
    private List<TopNew> list;

    public BottomNewsAdapter(List<TopNew> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AdapterBottomNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setNews(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterBottomNewsBinding binding;

        public ViewHolder(AdapterBottomNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(TopNew topNew) {
            binding.setNews(topNew);
            binding.executePendingBindings();
        }
    }
}
