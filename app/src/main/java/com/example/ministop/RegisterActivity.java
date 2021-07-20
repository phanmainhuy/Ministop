package com.example.ministop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button btnSaveRegist, btnCancel;
    EditText txtphone, txtpassword, txtrepass, txtemail, txtname, txtaddress, txtbirthday, txtgender;
    RequestQueue requestQueue;

    String url = "http://" + DEPRESS.ip +"/wsministop/register.php";

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
        txtbirthday = findViewById(R.id.txt_ngaysinh);
        txtgender = findViewById(R.id.txt_gioitinh);
        requestQueue = Volley.newRequestQueue(this);



        btnSaveRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtphone.getText().toString().equals("") || txtpassword.getText().toString().equals("") || txtrepass.getText().toString().equals("") || txtemail.getText().toString().equals("") || txtname.getText().toString().equals("") || txtaddress.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
//                if(txtrepass.getText().toString().equals(txtpassword.getText().toString()))
//                {
//                    Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_LONG).show();
//                    return;
//                }

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error+"", Toast.LENGTH_SHORT);

                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError{
                        //lay mang
                        Map<String, String> params = new HashMap<String, String>();
                        String sdt = txtphone.getText().toString();
                        String matkhau = txtpassword.getText().toString();
                        String email = txtemail.getText().toString();
                        String hoten = txtname.getText().toString();
                        String diachi = txtaddress.getText().toString();
                        String ngaysinh = txtbirthday.getText().toString();
                        String gioitinh = txtgender.getText().toString();

                        //put len
                        params.put("sdt", sdt);
                        params.put("hoten", hoten);
                        params.put("matkhau", matkhau);
                        params.put("email", email);
                        params.put("ngaysinh", ngaysinh);
                        params.put("gioitinh", gioitinh);
                        params.put("diachi", diachi);

                        return params;
                    }
                };
                    requestQueue.add(stringRequest);
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
            }

        });






    }





//    public void HandleRegister(View view)
//    {
//        int id = view.getId();
//        switch (id)
//        {
//            case  R.id.btnSaveRegister:

//                else
//                {
//                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
//                    Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent1);
//                    break;
//                }
//
//            case  R.id.btnCancel:
//                Intent intent2 = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(intent2);
//                break;
//        }
//
//    }








}