package com.pxy.task1220.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pxy.task1220.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    String stringList[];
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
        stringList = context.getResources().getStringArray(R.array.img);
    }

    @NonNull
    @Override
    public MyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.Holder holder, int position) {
        Glide.with(context).load(stringList[position]).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return stringList.length;
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
        }
    }
}
