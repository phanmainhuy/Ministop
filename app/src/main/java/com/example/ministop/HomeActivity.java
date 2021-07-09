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
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Options> dulieu = new ArrayList<>();
    OptionsAdapter_Recycle optionsAdapter_recycle;

    //192.168.22.102
    String url = "http://192.168.22.102/wsministop/getdanhmuc.php";

    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu);

        //Ánh xạ
        recyclerView = findViewById(R.id.recycleView_option);
        viewFlipper = findViewById(R.id.viewflipper);
        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nagivationview);
        bottomNavigationView = findViewById(R.id.navigation_bottom);
        //

        //Load du lieu
        optionsAdapter_recycle = new OptionsAdapter_Recycle(this, dulieu);
        recyclerView.setAdapter(optionsAdapter_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LayDanhMucSP();

        loadViewFlipper();

        //Cai dat cu click vao moi item cua menu bottom
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.mn_home:
                        Intent intent1 = new Intent(HomeActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        break;
                    /*case R.id.mn_notifications:
                        Intent intent2 = new Intent(HomeActivity.this, NoctificationsActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.mn_user:
                        Intent intent3 = new Intent(HomeActivity.this, UserActivity.class);
                        startActivity(intent3);
                        break;
                        case R.id.mnu_cart:
                        Intent intent4 = new Intent(HomeActivity.this, CartActivity.class);
                        startActivity(intent4);
                        break;*/
                }
                return false;
            }
        });
    }

    void loadViewFlipper() {
        // y 192.168.22.102
        String urlslide = "http://192.168.22.102/wsministop/slide/";
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

}