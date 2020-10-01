package com.example.datn1.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.databinding.ItemMessageBinding;
import com.example.datn1.databinding.ItemMessageFriendBinding;
import com.example.datn1.model.Message;


public class MessageAdapter extends ListAdapter<Message, MessageAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.getContent() == newItem.getContent();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }
    };

    public MessageAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType % 2 == 0) {
            return new ViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        } else {
            return new ViewHolder(ItemMessageFriendBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.bind(getItem(position));
        } else {
            holder.bindF(getItem(position));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMessageBinding binding;
        ItemMessageFriendBinding bindingF;

        public ViewHolder(ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewHolder(ItemMessageFriendBinding inflate) {
            super(inflate.getRoot());
            bindingF = inflate;
        }


        public void bind(Message item) {
            binding.setMessage(item);
            binding.executePendingBindings();
        }

        public void bindF(Message item) {
            bindingF.setMessage(item);
            bindingF.executePendingBindings();
        }
    }
}
