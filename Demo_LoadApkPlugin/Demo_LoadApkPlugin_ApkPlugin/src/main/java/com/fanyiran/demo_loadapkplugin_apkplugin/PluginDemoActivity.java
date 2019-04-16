package com.fanyiran.demo_loadapkplugin_apkplugin;

import android.os.Bundle;

import com.fanyiran.demo_loadapkplugin_lib.PluginProxyActivity;

public class PluginDemoActivity extends PluginProxyActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plugin);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
