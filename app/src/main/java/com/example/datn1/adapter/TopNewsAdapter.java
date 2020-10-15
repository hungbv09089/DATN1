package com.example.datn1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.databinding.AdpaterTopNewsBinding;
import com.example.datn1.model.TopNew;

import java.util.List;

public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.Viewholder> {
    private List<TopNew> list;

    public TopNewsAdapter(List<TopNew> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(AdpaterTopNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private AdpaterTopNewsBinding binding;

        public Viewholder(AdpaterTopNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(TopNew topNew) {
            binding.setTopNews(topNew);
            binding.executePendingBindings();
        }
    }
}
