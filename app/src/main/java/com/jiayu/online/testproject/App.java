package com.jiayu.online.testproject;

import android.app.Application;

import com.amap.api.maps.AMap;
import com.jiayu.commonbase.base.BaseApplication;
import com.jiayu.commonbase.manager.TaotutuManager;
import com.jiayu.online.taotutu_tts.TTSManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TaotutuManager.init(this);
        TaotutuManager.setAccessSecret("56cbaf232dbd48d9b6b7b88760b5784e");
        TaotutuManager.setAccessKey("7b51de35ba4f465a81c48af594df38e1");

    }
}
