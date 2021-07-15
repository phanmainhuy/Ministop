package com.example.ministop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter_Recycle extends RecyclerView.Adapter<ProductsAdapter_Recycle.KHUNGNHIN> implements Filterable {
    Context context;
    ArrayList<Products> dulieu;
    ArrayList<Products> dulieusearch;
    private OnClickListener listener;

    //Y:192.168.22.102  //Ru:192.168.1.7
    String ip = "192.168.22.102";
    String url = "http://" + ip + "/wsministop/sanpham/";

    public ProductsAdapter_Recycle(Context context, ArrayList<Products> dulieu, OnClickListener listener) {
        this.context = context;
        this.dulieu = dulieu;
        this.listener = listener;
        this.dulieusearch = dulieu;
    }
    @NonNull

    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_sanpham,null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter_Recycle.KHUNGNHIN holder, int position) {
        Products products = dulieu.get(position);

        Picasso.with(context)
                .load(url + products.hinh)
                .placeholder(R.drawable.no_image_found)
                .into(holder.hinh);

        holder.ten.setText(products.ten);
        //holder.mota.setText(products.mota);

        holder.gia.setText(products.gia + " đồng");


        holder.products = dulieu.get(position);

    }

    @Override
    public int getItemCount() {
        return dulieu.size();
    }


    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        Products products;
        ImageView hinh;
        TextView ten;
        TextView gia;
        //TextView mota;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);

            hinh = itemView.findViewById(R.id.img_product);
            ten = itemView.findViewById(R.id.tv_product1);
            //mota = itemView.findViewById(R.id.tv_product2);
            gia = itemView.findViewById(R.id.tv_product3);


            //Xu ly su kien click item cua recycle view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClick(products);
                }
            });
        }
    }

    //Search view cho san pham
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty())
                {
                    dulieu = dulieusearch;
                }
                else
                {
                    ArrayList<Products> list = new ArrayList<>();
                    for(Products products : dulieusearch)
                    {
                        if(products.getTen().toLowerCase().contains(strSearch.toLowerCase()))
                        {
                            list.add(products);
                        }
                    }
                    dulieu = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dulieu;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dulieu = (ArrayList<Products>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
