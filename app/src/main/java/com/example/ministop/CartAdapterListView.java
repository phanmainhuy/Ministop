package com.example.ministop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapterListView extends BaseAdapter {
    Context context;
    ArrayList<CART> carts;
    //Y:192.168.22.102  //Ru:192.168.1.7
    String ip = "192.168.22.102";
    String url = "http://" + ip + "/wsministop/sanpham/";

    public CartAdapterListView(Context context, ArrayList<CART> carts)
    {
        this.context = context;
        this.carts = carts;
    }

    @Override
    public int getCount() {
        return carts.size();
    }

    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolderListCart
    {
        public TextView tvTenspCart, tvGiaspCart, tvSLspCart;
        Button btnAdd, btnMin;
        ImageView imgspCart;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolderListCart viewHolderListCart = null;
        if(view == null)
        {
            viewHolderListCart = new ViewHolderListCart();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_1dong_giohang, null);
            // anh xa
            viewHolderListCart.tvTenspCart = (TextView)view.findViewById(R.id.tv_Cart_tensp);
            viewHolderListCart.tvGiaspCart = (TextView)view.findViewById(R.id.tv_Cart_giasp);
            viewHolderListCart.tvSLspCart = (TextView)view.findViewById(R.id.txt_Cart_soluong_1dong);
            viewHolderListCart.imgspCart = (ImageView)view.findViewById(R.id.img_Cart_hinhsp);
            viewHolderListCart.btnAdd = (Button)view.findViewById(R.id.btn_Cart_AddNumber);
            viewHolderListCart.btnMin = (Button)view.findViewById(R.id.btn_Cart_MinNumber);
            view.setTag(viewHolderListCart);
        }
        else
        {
            viewHolderListCart = (ViewHolderListCart) view.getTag();
        }
        CART cart = (CART)getItem(position);
        viewHolderListCart.tvTenspCart.setText(cart.getTensp());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        viewHolderListCart.tvGiaspCart.setText(decimalFormat.format(cart.getGiasp()));
        viewHolderListCart.tvGiaspCart.setText(cart.getGiasp());

        //load anh
        Picasso.with(context)
                .load(url + cart.getHinhsp())
                .placeholder(R.drawable.no_image_found)
                .into(viewHolderListCart.imgspCart);

        //Them "" de chuyen ve kieu String
        viewHolderListCart.tvSLspCart.setText(cart.getSoluong() + "");


        return view;
    }



}
