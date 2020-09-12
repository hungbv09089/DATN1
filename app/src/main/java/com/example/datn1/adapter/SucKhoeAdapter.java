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
import com.example.datn1.model.Suckhoe;

import java.util.ArrayList;

public class SucKhoeAdapter extends RecyclerView.Adapter<SucKhoeAdapter.RecyclerViewHolder> {
    private ArrayList<Suckhoe> arrayListSK;
    Context context;

    public SucKhoeAdapter(ArrayList<Suckhoe> arrayListSK, Context context) {
        this.arrayListSK = arrayListSK;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suckhoe,parent,false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        Picasso.with(context).load(arrayListSK.get(position).getImgsuckhoe()).into(holder.imgsuckhoe);
        holder.tvTitleSK.setText(arrayListSK.get(position).getTitlesuckhoe());
    }

    @Override
    public int getItemCount() {
        return arrayListSK.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgsuckhoe;
        TextView tvTitleSK;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgsuckhoe=itemView.findViewById(R.id.imgsuckhoe);
            tvTitleSK=itemView.findViewById(R.id.titlesk);
        }
    }
}
