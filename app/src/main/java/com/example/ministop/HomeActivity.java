package com.example.ministop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {
    //navigation handle
    private int mSelectedId;
    private static final String SELECTED_ITEM_ID = "selected"; //nguoi dung da select item
    //private static final String FRIST_TIME = "fist_time"; // nguoi dung select lan dau
    private boolean mUserSawDrawer = false; //neu nguoi dung mo thi sau do khong hien thi lai

    //Y:192.168.22.102    //Ru:192.168.1.7
    String ip = "192.168.1.7";

    RecyclerView recyclerView, recyclerView2;

    ArrayList<Options> dulieu = new ArrayList<>();
    OptionsAdapter_Recycle optionsAdapter_recycle;

    ArrayList<Products> data = new ArrayList<>();
    ProductsAdapter_Recycle productsAdapter_recycle;

    //Y: 192.168.22.102     //Ru: 192.168.1.7
    String url = "http://" + ip +"/wsministop/getdanhmuc.php";
    String url2 = "http://" + ip + "/wsministop/getsanpham.php";

    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;

    Toolbar toolbar;
    NavigationView navigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Ánh xạ
        recyclerView = findViewById(R.id.recycleView_option);
        recyclerView2 = findViewById(R.id.recycleView_product);
        viewFlipper = findViewById(R.id.viewflipper);

        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationLeft = findViewById(R.id.nagivationviewLeft);
        //Xu ly Navigation Left
        //tao su kien click navigationLeft
        navigationLeft.setNavigationItemSelectedListener(this);
        //Xu ly khong lap Activity khi chon item Navigation
        showDrawer();
//        if(!didUserSeeDrawer()) //first time
//        {
//            showDrawer();
//            markDrawerSeen();//khong duoc hien thi vi nguoi dung da chon
//        }
//        else
//        {
//            hideDrawer();
//        }
        //Luu su kien tu acvivity da chon
        mSelectedId = savedInstanceState == null ? R.id.nagivationviewLeft : savedInstanceState.getInt(SELECTED_ITEM_ID);
        navigation(mSelectedId);

        //Load du lieu danh muc sp
        optionsAdapter_recycle = new OptionsAdapter_Recycle(this, dulieu);
        recyclerView.setAdapter(optionsAdapter_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LayDanhMucSP();
        loadViewFlipper();
        //Load du lieu list san pham
        productsAdapter_recycle = new ProductsAdapter_Recycle(this, data,this);
        recyclerView2.setAdapter(productsAdapter_recycle);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        LaySanPham();

    }

    public void LaySanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        data.add(new Products(jsonObject.getString("idsanpham"), jsonObject.getString("tensanpham"), jsonObject.getString("gia"),jsonObject.getString("hinhanh"),jsonObject.getString("mota")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                productsAdapter_recycle.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url2, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }


    void loadViewFlipper() {
        // Y: 192.168.22.102    //Ru: 192.168.1.5
        String urlslide = "http://" + ip + "/wsministop/slide/";
        ArrayList<String> mangslide = new ArrayList<>();

        mangslide.add(urlslide + "1.jpg");
        mangslide.add(urlslide + "2.jpg");
        mangslide.add(urlslide + "3.jpg");
        mangslide.add(urlslide + "4.jpg");
        mangslide.add(urlslide + "5.jpg");
        mangslide.add(urlslide + "6.jpg");
        mangslide.add(urlslide + "7.jpg");
        mangslide.add(urlslide + "8.jpg");
        mangslide.add(urlslide + "9.jpg");
        mangslide.add(urlslide + "10.jpg");

        for (int i = 0; i < mangslide.size(); i++) {
            ImageView imageView = new ImageView(this);
            Picasso.with(this).load(mangslide.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
    }


    public void LayDanhMucSP() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        dulieu.add(new Options(jsonObject.getString("iddanhmucsp"), jsonObject.getString("tendanhmucsp"), jsonObject.getString("hinhanh")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                optionsAdapter_recycle.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }

    //dieu huong navigation
    private void navigation(int mSelectedId) {
        Intent intent = null;
        if (mSelectedId == R.id.mnu_user) {
            intent = new Intent(HomeActivity.this, UserActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (mSelectedId == R.id.mnu_cart) {
            intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        //Intent intent = null;
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        navigation(mSelectedId);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    //nut tro ve navigation
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private  void showDrawer()
    {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private  void hideDrawer()
    {
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    //---------------------------------------------------------------------------//
    //---------------------Xu ly su kien click item san pham--------------------//
    // -------------------------------------------------------------------------//

    @Override
    public void itemClick(Products products) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("key1",products);
        startActivity(intent);
    }

    //---------------------------------------------------------------------------//
    //--------------------------------------------------------------------------//
    // -------------------------------------------------------------------------//









//    private boolean didUserSeeDrawer()
//    {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mUserSawDrawer = sharedPreferences.getBoolean(FRIST_TIME, false);
//        return mUserSawDrawer;
//    }
//    private void markDrawerSeen()
//    {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mUserSawDrawer = true;
//        sharedPreferences.edit().putBoolean(FRIST_TIME, mUserSawDrawer).apply();
//    }

}