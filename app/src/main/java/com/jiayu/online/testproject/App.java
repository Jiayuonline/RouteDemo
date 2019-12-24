package com.jiayu.online.testproject;

import android.app.Application;

import com.jiayu.commonbase.base.BaseApplication;
import com.jiayu.commonbase.manager.TaotutuManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TaotutuManager.getInstance().setAccessSecret(" ");
        TaotutuManager.getInstance().setAccessKey(" ");
        TaotutuManager.getInstance().setChannel("taotutu");
        TaotutuManager.getInstance().setPlatform("andorid");
        TaotutuManager.getInstance().setUniqueCode("123");
        TaotutuManager.getInstance().init(this);
    }
}
