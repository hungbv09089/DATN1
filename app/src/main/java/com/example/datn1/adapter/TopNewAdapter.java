package com.example.datn1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn1.R;
import com.example.datn1.model.TopNew;

import java.util.ArrayList;

public class TopNewAdapter extends RecyclerView.Adapter<TopNewAdapter.RecyclerViewHolder> {
    Context context;
    ArrayList<TopNew> topNewList;

    public TopNewAdapter(Context context, ArrayList<TopNew> topNewList) {
        this.context = context;
        this.topNewList = topNewList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topnew,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.tvtitletopnew.setText(topNewList.get(position).getTitletopview());
//        holder.imgtopnew.setImageDrawable(topNewList.get(position).getImagetopview());
    }

    @Override
    public int getItemCount() {
        return topNewList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgtopnew;
        TextView tvtitletopnew;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtopnew=itemView.findViewById(R.id.imgtopnew);
            tvtitletopnew=itemView.findViewById(R.id.titletopnew);
        }
    }
}
