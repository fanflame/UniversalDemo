package com.fanyiran.demo_loadapkplugin_lib;

import android.content.res.Resources;

/**
 * Created by fanqiang on 2019/4/15.
 */
public class PluginApkInfo {
    private ClassLoader classLoader;
    private Resources resources;

    public PluginApkInfo(ClassLoader classLoader, Resources resources) {
        this.classLoader = classLoader;
        this.resources = resources;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public Resources getResources() {
        return resources;
    }
}
