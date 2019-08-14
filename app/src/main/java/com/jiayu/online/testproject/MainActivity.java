package com.jiayu.online.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.model.LatLng;
import com.jiayu.commonbase.manager.TaotutuManager;
import com.jiayu.online.taotutu_route.activity.NavActivity;
import com.jiayu.online.taotutu_route.activity.RouteListActivity;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_main = findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i    = new Intent(MainActivity.this, RouteListActivity.class);
                startActivity( i);

            }
        });



    }
}
