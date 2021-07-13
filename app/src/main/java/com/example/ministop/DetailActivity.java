package com.example.ministop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtTen, txtGia, txtMoTa;
    Button button;
    Products products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);

        imageView = findViewById(R.id.img_CTSP);
        txtTen = findViewById(R.id.tv_CTSP1);
        txtGia = findViewById(R.id.tv_CTSP2);
        txtMoTa = findViewById(R.id.tv_CTSP3);

        //Gui du lieu tu home qua
        Intent intent = getIntent();
        if(intent != null)
        {
            products = (Products) intent.getSerializableExtra("key1");
            //Bo sung load hinh sau

            txtTen.setText(products.getTen());
            txtGia.setText(products.getGia());
            txtMoTa.setText(products.getMota());

        }
    }
}