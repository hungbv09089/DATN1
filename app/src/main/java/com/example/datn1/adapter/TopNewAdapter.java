package com.example.datn1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.datn1.R;
import com.example.datn1.model.TopNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TopNewAdapter extends PagerAdapter {
    Context context;
    ArrayList<TopNew> topNewList;

    public TopNewAdapter(Context context, ArrayList<TopNew> topNewList) {
        this.context = context;
        this.topNewList = topNewList;
    }


    @Override
    public int getCount() {
        return topNewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_topnew,null);
        ImageView imgtopnew=view.findViewById(R.id.imgtopnew);
        TextView tvtitle=view.findViewById(R.id.titletopnew);

        Picasso.with(context).load(topNewList.get(position).getImage()).into(imgtopnew);
        tvtitle.setText(topNewList.get(position).getTitle());
        container.addView(view);
        return view;
    }
    public void destroyItem(ViewGroup viewGroup,int position,Object object){
        viewGroup.removeView((View) object);
    }
}
