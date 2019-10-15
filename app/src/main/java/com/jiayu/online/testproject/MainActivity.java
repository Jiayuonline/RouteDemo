package com.jiayu.online.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiayu.commonbase.manager.TaotutuManager;
import com.jiayu.online.taotutu_route.activity.RouteListActivity;
import com.jiayu.online.taotutu_route.bean.RouteDetailBean;
import com.jiayu.online.taotutu_route.bean.RouteListBean;
import com.jiayu.online.taotutu_route.bean.TTSPointBean;
import com.jiayu.online.taotutu_route.manager.TaoRouteManager;
import com.jiayu.online.taotutu_route.presenter.RouteDetailPresenter;
import com.jiayu.online.taotutu_route.presenter.RouteListPresenter;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    private TextView tvResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnMain = findViewById(R.id.btn_main);
        tvResp = findViewById(R.id.tv_resp);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaoRouteManager.goRouteList(MainActivity.this);
            }
        });


        Button btnDetial = findViewById(R.id.btn_detail_activiity);
        btnDetial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaoRouteManager.goRouteDetail(MainActivity.this,"4635b9cfc91f4965af01aa643faab4ad");
            }
        });

        final TaoRouteManager routeManager = new TaoRouteManager();

        Button btnList = findViewById(R.id.btn_list);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routeManager.getRouteList(1, 10, new RouteListPresenter.OnRouteListCallback() {
                    @Override
                    public void onSuccess(List<RouteListBean.RouteTwoListBean> list) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (RouteListBean.RouteTwoListBean routeTwoListBean : list) {
                                stringBuilder.append(routeTwoListBean.getRouteId()+","+routeTwoListBean.getTitle()+"\r\n");
                        }

                        tvResp.setText(stringBuilder.toString());
                    }

                    @Override
                    public void onFailed(Throwable throwable) {

                    }
                });
            }
        });

        Button btnDetail = findViewById(R.id.btn_detail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routeManager.getRouteDetail("r201811002", new RouteDetailPresenter.OnDetailCallback() {
                    @Override
                    public void onSuccess(RouteDetailBean routeDetailBean) {


                        tvResp.setText(routeDetailBean.getTitle()+"\r\n"+routeDetailBean.getDeparture());
                    }

                    @Override
                    public void onFailed(Throwable throwable) {

                    }
                });
            }
        });
        Button btnTTS = findViewById(R.id.btn_tts);

        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routeManager.getTTSList("121.388467,31.176875", 1, 10, new RouteDetailPresenter.OnTTSCallback() {
                    @Override
                    public void onSuccess(List<TTSPointBean> list) {
                        StringBuilder sb = new StringBuilder();
                        for (TTSPointBean ttsPointBean : list) {
                            sb.append(ttsPointBean.getName()+","+ttsPointBean.getAddress()+"\r\n");
                        }

                        tvResp.setText(sb.toString());
                    }

                    @Override
                    public void onFailed(Throwable throwable) {

                    }
                });
            }
        });

    }
}
