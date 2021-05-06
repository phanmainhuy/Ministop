package com.example.ministop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfulRegistrationActivity extends AppCompatActivity {
    Button btnOKSucess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_registration);


        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#fdcc32"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(""); //Thiết lập tiêu đề
        String title = actionBar.getTitle().toString();
        actionBar.hide();


        //anh xa
        btnOKSucess = findViewById(R.id.btnOKSucessful);




    }

    public void HandleSuccessfulRegistration(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.btnOKSucessful:
                Intent intent1 = new Intent(SuccessfulRegistrationActivity.this, LoginActivity.class);
                startActivity(intent1);
                break;




        }

    }



}