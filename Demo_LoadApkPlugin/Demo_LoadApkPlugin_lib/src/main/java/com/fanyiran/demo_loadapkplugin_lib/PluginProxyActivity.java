package com.fanyiran.demo_loadapkplugin_lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by fanqiang on 2019/4/15.
 */
public class PluginProxyActivity extends Activity implements IPlugin{
    private Activity hostActivity;
    private int pluginFrom;

    @Override
    public void onAttach(Activity context) {
        this.hostActivity = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        pluginFrom = savedInstanceState.getInt(IPlugin.EXTRA_PLUGIN_FROM);
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.setContentView(layoutResID);
        } else {
            hostActivity.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            return super.findViewById(id);
        } else {
            return hostActivity.findViewById(id);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onResume() {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onResume();
        }
    }

    @Override
    public void onStart() {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onStart();
        }
    }

    @Override
    public void onPause() {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (pluginFrom == IPlugin.PLUGIN_FROM_INNER) {
            super.onDestroy();
        }
    }
}
