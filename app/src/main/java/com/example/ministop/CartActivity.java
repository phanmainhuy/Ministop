package com.example.ministop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    Button btnPay, btnContinue, btnAdd, btnMin;
    ListView lvCart;

    TextView tvThanhtien, tvNull, tvSL;
    CartAdapterListView cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#003894"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Giỏ hàng"); //Thiết lập tiêu đề


        //anh xa
        btnPay = findViewById(R.id.btn_Cart_Pay);
        btnContinue = findViewById(R.id.btn_Cart_Continue);
//        btnAdd = findViewById(R.id.btn_Cart_AddNumber);
//        btnMin = findViewById(R.id.btn_Cart_MinNumber);
        tvNull = findViewById(R.id.lbl_Cart_notificationcart);
        lvCart = findViewById(R.id.lst_Cart);
        tvSL = findViewById(R.id.txt_Cart_soluong_1dong);
        cartAdapter = new CartAdapterListView(CartActivity.this, DEPRESS.carts);
        lvCart.setAdapter(cartAdapter);

        checkData();
        

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent1);
                //startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });





    }

    private void checkData() {
        if(DEPRESS.carts.size() <=0)
        {
            cartAdapter.notifyDataSetChanged();
            tvNull.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);
        }
        else
        {
            cartAdapter.notifyDataSetChanged();
            tvNull.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);
        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}