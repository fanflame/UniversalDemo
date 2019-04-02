package com.fanyiran.hotfixdemo;

import android.app.Application;

/**
 * Created by fanqiang on 2019/4/1.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // NOTE: 2019/4/1 用在项目中时需要打开以下注释
//        FixedUtils.fix(this);
    }
}
