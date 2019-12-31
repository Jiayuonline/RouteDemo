package com.jiayu.online.testproject;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.jiayu.commonbase.http.ApiException;
import com.jiayu.commonbase.manager.TaotutuManager;
import com.jiayu.online.grouplib.bean.SocketBean;
import com.jiayu.online.grouplib.manager.GroupManager;
import com.jiayu.online.grouplib.model.GroupListModel;
import com.jiayu.online.grouplib.presenter.GroupPresenter;
import com.jiayu.online.taotutu_route.bean.RouteBookDetailBean;
import com.jiayu.online.taotutu_route.bean.RouteBookListBean;
import com.jiayu.online.taotutu_route.bean.RouteDetailBean;
import com.jiayu.online.taotutu_route.bean.RouteListBean;
import com.jiayu.online.taotutu_route.bean.TTSPointBean;
import com.jiayu.online.taotutu_route.helper.AMapHelper;
import com.jiayu.online.taotutu_route.manager.TaoRouteManager;
import com.jiayu.online.taotutu_route.presenter.RouteDetailPresenter;
import com.jiayu.online.taotutu_route.presenter.RouteListPresenter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    private TextView tvResp;
    int pageNo =  1;
    String routeId = "r201920260";
    private TaoRouteManager routeManager;
    private EditText et_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResp = findViewById(R.id.tv_resp);
        et_id = findViewById(R.id.et_id);
        routeManager = new TaoRouteManager();

        /**
         * 需要添加麦克风权限
         */
        if (Build.VERSION.SDK_INT >= 23) {
            AndPermission.with(this)
                    .runtime()
                    .permission(Permission.RECORD_AUDIO
                    )
                    .onGranted(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {

                        }
                    })
                    .onDenied(new Action<List<String>>() {
                        @Override
                        public void onAction(List<String> data) {

                        }
                    })
                    .start();
        }
        GroupManager.getInstance().initQNY(this, new GroupPresenter.OnRoomCallback() {
            @Override
            public void onConnected() {
                tvResp.setText("加入房间");
            }

            @Override
            public void onConnecting() {
                tvResp.setText("正在加入房间....");

            }

            @Override
            public void onLeaveRoom() {
                tvResp.setText("离开房间");
            }

            @Override
            public void onRemoteUserJoined() {
                tvResp.setText("队友加入房间");

            }

            @Override
            public void onRemoteUserLeft() {
                tvResp.setText("队友离开房间");

            }

            @Override
            public void onError(int i, String s) {
                tvResp.setText("语音出错: "+i+" , "+s);
            }
        });


        Button btnList = findViewById(R.id.btn_list);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageNo = 1;
                getRouteList();
            }
        });

        Button btnDetail = findViewById(R.id.btn_detail);
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRouteDetail();
            }
        });
        Button btnTTS = findViewById(R.id.btn_tts);

        btnTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTTS();
            }
        });
    }

    private void getTTS() {
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
                tvResp.setText(throwable.getMessage()  );
            }
        });
    }

    private void getRouteDetail() {

        String id = et_id.getText().toString();
        if ( id.isEmpty()){
            id = routeId;
        }


        routeManager.getRouteDetail(id, new RouteDetailPresenter.OnDetailCallback() {
            @Override
            public void onSuccess(RouteBookDetailBean routeBookDetailBean) {
                String json = new Gson().toJson(routeBookDetailBean);

                tvResp.setText(json  );
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }

    private void getRouteList() {



        routeManager.getRouteList(pageNo, 10,"121.394979,31.182782", new RouteListPresenter.OnRouteListCallback() {
            @Override
            public void onSuccess(List<RouteBookListBean> list) {
                StringBuilder stringBuilder = new StringBuilder();

                for (RouteBookListBean routeTwoListBean : list) {
                    stringBuilder.append(routeTwoListBean.getRouteId() + "," + routeTwoListBean.getTitle() + "\r\n");
                }
                tvResp.setText(stringBuilder.toString());
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }

    public void nextPage(View view) {
        pageNo++;
        getRouteList();
    }

    public void groupList(View view) {
        GroupManager.getGroupList(new GroupPresenter.OnGroupListCallback() {
            @Override
            public void onGroupList(List<GroupListModel> list) {
                Log.d(TAG, "onGroupList: ");
            }
        });
    }

    public void connectGroup(View view) {
        GroupManager.getInstance().startConnect("74be9a8e66864129bb09b3268f12905d", new GroupPresenter.OnScoketCallback() {
            @Override
            public void onConnected(String s) {
                Log.e(TAG, "onConnected: "  );
            }

            @Override
            public void onConnectFailed(Throwable throwable) {
                Log.e(TAG, "onConnectFailed: "  );
            }

            @Override
            public void onDisconnect() {
                Log.e(TAG, "onDisconnect: "  );
            }

            @Override
            public void onSendDataError(Throwable throwable) {
                Log.e(TAG, "onSendDataError: " );
            }

            @Override
            public void onMessage(String s) {
                Log.e(TAG, "onMessage: " );
            }

            @Override
            public void onMessage(SocketBean socketBean) {
                Log.e(TAG, "onMessage: "  );
            }
        });
    }

    public void connectRoom(View view) {
        GroupManager.getInstance().joinRoom("p2eDZ5_r8qtin0ayiyu1WslVSTG1lQ6HvzrvTmvj:QGjUROYWEuIklKLrwBSNckwIT7M=:eyJhcHBJZCI6ImU0NTFjajBoeiIsInJvb21OYW1lIjoiNzRiZTlhOGU2Njg2NDEyOWJiMDliMzI2OGYxMjkwNWQiLCJ1c2VySWQiOiI4ODMwOTA0OCIsImV4cGlyZUF0Ijo0MTAyNDE1OTk5LCJwZXJtaXNzaW9uIjoidXNlciJ9");
    }
}
