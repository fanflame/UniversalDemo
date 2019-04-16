package com.fanyiran.demo_loadapkplugin_lib;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fanqiang on 2019/4/15.
 */
public class PluginActivity extends Activity {
    private PluginProxyActivity pluginProxyActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadActivity(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    private void loadActivity(Bundle savedInstanceState) {
        String name = getIntent().getStringExtra(IPlugin.EXTRA_PLUGIN_NAME);
        if (name != null) {
            try {
                Class<?> aClass = getClassLoader().loadClass(name);
                Object activity = aClass.newInstance();
                if (activity instanceof IPlugin) {
                    pluginProxyActivity = (PluginProxyActivity) activity;
                    pluginProxyActivity.onAttach(this);
                    if (savedInstanceState == null) {
                        savedInstanceState = new Bundle();
                    }
                    savedInstanceState.putInt(IPlugin.EXTRA_PLUGIN_FROM, getIntent().getIntExtra(IPlugin.EXTRA_PLUGIN_FROM, IPlugin.PLUGIN_FROM_INNER));
                    pluginProxyActivity.onCreate(savedInstanceState);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        if(pluginProxyActivity != null)
            pluginProxyActivity.onResume();
        super.onResume();
    }

    @Override
    protected void onStart() {
        if(pluginProxyActivity != null)
            pluginProxyActivity.onStart();
        super.onStart();
    }

    @Override
    protected void onPause() {
        if(pluginProxyActivity != null)
            pluginProxyActivity.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        if(pluginProxyActivity != null)
            pluginProxyActivity.onStart();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if(pluginProxyActivity != null)
            pluginProxyActivity.onDestroy();
        super.onDestroy();
    }

    /**
     * @return  如果不重写要加载的class无法找到
     */
    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getPluginApkInfo().getClassLoader();
    }

    /**
     * @return 如果不重写，要加载的布局文件失败
     */
    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getPluginApkInfo().getResources();
    }
}
