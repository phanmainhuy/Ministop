package com.example.ministop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter_Recycle extends RecyclerView.Adapter<ProductsAdapter_Recycle.KHUNGNHIN> {
    Context context;

    public ProductsAdapter_Recycle(Context context, ArrayList<Products> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
    }

    ArrayList<Products> dulieu;

    //Y:192.168.22.102  //Ru:192.168.1.7
    String ip = "192.168.22.102";
    String url = "http://" + ip + "/wsministop/sanpham/";



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
        holder.gia.setText(products.gia + " đồng");
    }

    @Override
    public int getItemCount() {
        return dulieu.size();
    }

    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        ImageView hinh;
        TextView ten;
        TextView gia;

        public KHUNGNHIN(@NonNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.img_product);
            ten = itemView.findViewById(R.id.tv_product1);
            gia = itemView.findViewById(R.id.tv_product2);
        }
    }
}
