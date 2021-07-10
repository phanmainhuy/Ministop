package com.example.ministop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toolbar;

public class CartActivity extends AppCompatActivity {

    Button btnPay, btnContinue;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //anh xa
        btnPay = findViewById(R.id.btn_Cart_Pay);
        btnContinue = findViewById(R.id.btn_Cart_Continue);





    }
}