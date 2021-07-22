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

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    Button btndathang, btngiamgia;
    NGUOIDUNG user;
    ListView lvsporder;
    OrderAdapter_lst OrderAdapter_lst;
    TextView lblten, lblsdt, lbldiachi, lbltiengiam, lblthanhtien, lbltongcong;
    String url = "http://" + DEPRESS.ip + "/wsministop/getsanpham.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#003894"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Đặt hàng"); //Thiết lập tiêu đề

        //anh xa
        btndathang = findViewById(R.id.btn_Order_Dathang);
        btngiamgia = findViewById(R.id.btn_Order_ApdungGiamGia);
        lbldiachi = findViewById(R.id.lbl_order_diachi);
        lblsdt = findViewById(R.id.lbl_order_sdt);
        lblten = findViewById(R.id.lbl_order_ten);
        lbltiengiam = findViewById(R.id.lbl_order_tiengiam);
        lbltongcong = findViewById(R.id.lbl_order_tongcong);
        lblthanhtien = findViewById(R.id.lbl_order_thanhtien);
        lvsporder = findViewById(R.id.lst_Order);
        OrderAdapter_lst = new OrderAdapter_lst(OrderActivity.this, DEPRESS.carts);
        lvsporder.setAdapter(OrderAdapter_lst);


        //Truyen du lieu user
        if(DEPRESS.USER != null)
        {
            user = DEPRESS.USER;
            lblten.setText(user.getHoten());
            lblsdt.setText(user.getSdt());
            lbldiachi.setText(user.getDiachi());
        }
        //truyen du lieu tien giam
        lbltiengiam.setText("- "+0 +" VND");


        //truyen du lieu tong cong
        lbltongcong.setText(DEPRESS.ThanhToan + " VND");
        lblthanhtien.setText(DEPRESS.ThanhToan + " VND");




        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DEPRESS.carts = new ArrayList<>();
                Intent intent = new Intent(OrderActivity.this, Order_SuccessfulActivity.class);
                startActivity(intent);
            }
        });

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