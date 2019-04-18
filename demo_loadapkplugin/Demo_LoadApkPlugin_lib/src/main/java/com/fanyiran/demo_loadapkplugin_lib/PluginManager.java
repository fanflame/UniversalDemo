package com.fanyiran.demo_loadapkplugin_lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by fanqiang on 2019/4/15.
 */
public class PluginManager {
    private static final String OPTIMIZED_DIR = "plugin_dex";
    private static final PluginManager ourInstance = new PluginManager();

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    private PluginApkInfo pluginApkInfo;
    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    public boolean loadApk(String path) {
        if (context == null) {
            throw new IllegalStateException("未初始化");
        }
        if (path == null)
            return false;
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        DexClassLoader classLoader = getClassLoader(path);
        AssetManager assetManager = getAssetManager(path);
        Resources resources = getResources(assetManager,context);
        pluginApkInfo = new PluginApkInfo(classLoader, resources);
        return true;
    }

    private AssetManager getAssetManager(String path) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method methods = AssetManager.class.getMethod("addAssetPath",String.class);
            methods.invoke(assetManager,path);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Resources getResources(AssetManager assetManager,Context context) {
        return new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
    }

    private DexClassLoader getClassLoader(String path) {
        String optimizedDirectory = context.getDir(OPTIMIZED_DIR, Context.MODE_PRIVATE).getAbsolutePath();
        return new DexClassLoader(path, optimizedDirectory, null, getClass().getClassLoader());
    }

    public PluginApkInfo getPluginApkInfo() {
        return pluginApkInfo;
    }

    public Context getContext() {
        return context;
    }
}
