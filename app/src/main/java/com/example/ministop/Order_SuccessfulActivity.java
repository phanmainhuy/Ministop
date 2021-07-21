package com.example.ministop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Order_SuccessfulActivity extends AppCompatActivity {

    ImageView imgLogo, imgshipping;
    Animation anilogo, aniship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_successful);
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
        imgLogo = findViewById(R.id.imgLogo);
        imgshipping = findViewById(R.id.imgshipping);

        //ap dung animation
        anilogo = AnimationUtils.loadAnimation(this, R.anim.combine_logo);
        imgLogo.startAnimation(anilogo);
        aniship = AnimationUtils.loadAnimation(this, R.anim.sequential_shipping);
        imgshipping.startAnimation(aniship);




    }










}