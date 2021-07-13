package com.example.ministop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button btnSaveRegist, btnCancel;
    EditText txtphone, txtpassword, txtrepass, txtemail, txtname, txtaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("#fdcc32"));
//
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(""); //Thiết lập tiêu đề
//        String title = actionBar.getTitle().toString();

        //anh xa
        //button
        btnSaveRegist = findViewById(R.id.btnSaveRegister);
        btnCancel = findViewById(R.id.btnCancel);
        //edit text
        txtphone = findViewById(R.id.txt_phone);
        txtpassword = findViewById(R.id.txt_password);
        txtrepass = findViewById(R.id.txt_repass);
        txtemail = findViewById(R.id.txt_email);
        txtname = findViewById(R.id.txt_nameuser);
        txtaddress = findViewById(R.id.txt_address);



    }
    public void HandleRegister(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case  R.id.btnSaveRegister:
                if(txtphone.getText().toString().equals("") || txtpassword.getText().toString().equals("") || txtrepass.getText().toString().equals("") || txtemail.getText().toString().equals("") || txtname.getText().toString().equals("") || txtaddress.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtrepass != txtpassword)
                {
                    Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent1);
                    break;
                }

            case  R.id.btnCancel:
                Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent2);
                break;
        }

    }








}