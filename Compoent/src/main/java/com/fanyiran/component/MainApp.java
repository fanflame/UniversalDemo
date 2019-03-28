package com.fanyiran.component;

import android.app.Application;

import com.fanyiran.componentlib.iinterface.IInit;


/**
 * Created by fanqiang on 2019/2/27.
 */
public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        String[] appArray = Config.appArrays;
        if(appArray == null)
            return;
        for (String s : appArray) {
            try {
                Class<?> aClass = Class.forName(s);
                Object init = aClass.newInstance();
                if (init instanceof IInit) {
                    ((IInit)init).initApp();
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
