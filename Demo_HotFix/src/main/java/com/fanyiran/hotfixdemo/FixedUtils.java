package com.fanyiran.hotfixdemo;

import android.content.Context;

import dalvik.system.BaseDexClassLoader;

/**
 * Created by fanqiang on 2019/3/7.
 */
public class FixedUtils {
    public static void fix(Context context) {
        BaseDexClassLoader baseDexClassLoader = new BaseDexClassLoader();
        BaseDexClassLoader contextClassLoader = (BaseDexClassLoader) context.getClassLoader();
    }

    private static void getElements(){

    }

}
