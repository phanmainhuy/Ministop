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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnDangky;
    CheckBox chkSave;
    EditText txtusername, txtpassword;
    SharedPreferences luutru;
    ArrayList<NGUOIDUNG> user = new ArrayList<>();

    //Y:192.168.22.102    //Ru:192.168.1.7
    String ip = "192.168.22.102";
    String urlnguoidung = "http://" + ip + "/wsministop/getnguoidung.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        ColorDrawable colorDrawable
//                = new ColorDrawable(Color.parseColor("#fdcc32"));
//        // Set BackgroundDrawable
//        actionBar.setBackgroundDrawable(colorDrawable);
//        actionBar.setTitle(""); //Thiết lập tiêu đề
//        //actionBar.hide();
//        String title = actionBar.getTitle().toString();

        //anh xa
        txtusername = findViewById(R.id.txtusername);
        txtpassword = findViewById(R.id.txtpassword);
        chkSave = findViewById(R.id.chkSave);
        btnLogin = findViewById(R.id.btnLogin);
        btnDangky = findViewById(R.id.btnRegister);

        LaydulieuDangNhap();
        luutru = getSharedPreferences("data", MODE_PRIVATE);
        //nap du lieu
        if (luutru.getBoolean("saveinfo", false) == true) {
            //load du lieu len editText
            txtusername.setText(luutru.getString("username", ""));
            txtpassword.setText(luutru.getString("password", ""));
            chkSave.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtusername.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtpassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    {
                    //Duyet mang
                    for (NGUOIDUNG i : user) {
                        if (txtusername.getText().toString().equals(i.sdt) && txtpassword.getText().toString().equals(i.matkhau)) {
                            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = luutru.edit();
                            if (chkSave.isChecked()) {
                                //luu lai thong tin
                                editor.putString("username", txtusername.getText().toString());
                                editor.putString("password", txtpassword.getText().toString());
                            }
                            editor.putBoolean("saveinfo", chkSave.isChecked());
                            editor.commit();
                            Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent1);
                        }
                        if(!txtusername.getText().toString().equals(i.sdt) || !txtpassword.getText().toString().equals(i.matkhau))
                        {
                            Toast.makeText(getApplicationContext(), "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }




                }


//                if(txtusername.getText().toString().equals("0948462040") && txtpassword.getText().toString().equals("123"))
//                {
//                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(), "Số điện thoại hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
//                    return;
//                }
            }
        });
    }

    public void LaydulieuDangNhap() {
        //Lay mang user
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        user.add(new NGUOIDUNG(jsonObject.getString("idnguoidung"), jsonObject.getString("sdt"), jsonObject.getString("hoten"), jsonObject.getString("matkhau"), jsonObject.getString("email"), jsonObject.getString("ngaysinh"), jsonObject.getString("gioitinh"), jsonObject.getString("diachi"), jsonObject.getString("hinhanh")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlnguoidung, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }


    public void HandleLogin(View view) {
        int id = view.getId();
        switch (id) {
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
//            case R.id.btnLogin:
//                Intent intent1 = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(intent1);
//                break;

        }
    }


    //Fuction
    private void GgClick() {
        String text = "https://accounts.google.com/signin/v2/identifier?hl=vi&passive=true&continue=https%3A%2F%2Fwww.google.com%2F&ec=GAZAAQ&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
        Uri uri = Uri.parse(text);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void FbClick() {
        String text = "https://www.facebook.com/";
        Uri uri = Uri.parse(text);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}