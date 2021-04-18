package com.example.ministop;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OptionsAdapter_Recycle extends RecyclerView.Adapter<OptionsAdapter_Recycle.KHUNGNHIN>{
    Context context;
    ArrayList<Options> dulieu;


    public OptionsAdapter_Recycle(Context context, ArrayList<Options> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_options,null);
        return  new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
        Options options = dulieu.get(position);

        holder.hinh.setImageResource(options.hinh);
        holder.ten.setText(options.ten);
    }

    @Override
    public int getItemCount() {
        return dulieu.size();
    }

    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        ImageView hinh;
        TextView ten;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.img_option);
            ten = itemView.findViewById(R.id.tv_option);
        }
    }
}
