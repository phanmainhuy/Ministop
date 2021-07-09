package com.example.ministop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnDangky;
    CheckBox chkSave;
    EditText txtusername, txtpassword;
    SharedPreferences luutru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#fdcc32"));

        // Set BackgroundDrawable

        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle(""); //Thiết lập tiêu đề
        //actionBar.hide();
        String title = actionBar.getTitle().toString();

        //anh xa
        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
        chkSave = findViewById(R.id.chkSave);
        btnLogin = findViewById(R.id.btnLogin);
        btnDangky = findViewById(R.id.btnRegister);

        luutru = getSharedPreferences("data", MODE_PRIVATE);

        //nap du lieu
        if(luutru.getBoolean("saveinfo", false)== true)
        {
            //load du lieu len editText
            txtusername.setText(luutru.getString("username", ""));
            txtpassword.setText(luutru.getString("password", ""));
            chkSave.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtusername.getText().toString().equals("0948462040") && txtpassword.getText().toString().equals("123"))
                {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = luutru.edit();
                    if(chkSave.isChecked())
                    {
                        //luu lai thong tin
                        editor.putString("username", txtusername.getText().toString());
                        editor.putString("password", txtpassword.getText().toString());
                    }
                    editor.putBoolean("saveinfo", chkSave.isChecked());
                    editor.commit();
                    Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }

    public void HandleLogin(View view)
    {
        int id = view.getId();
        switch (id)
        {
           case R.id.btnRegister:
               Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
               break;
            case R.id.btnfb:
                FbClick();
                break;
            case R.id.btngg:
                GgClick();
                break;
            case R.id.btnLogin:
                Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent1);
                break;

        }


    }
    //Fuction
    private  void GgClick()
    {
        String text = "https://accounts.google.com/signin/v2/identifier?hl=vi&passive=true&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAAQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
        Uri uri = Uri.parse(text);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    private void FbClick()
    {
        String text = "https://www.facebook.com/";
        Uri uri = Uri.parse(text);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}