package com.fanyiran.demo_loadapkplugin;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fanyiran.demo_loadapkplugin_lib.IPlugin;
import com.fanyiran.demo_loadapkplugin_lib.PluginActivity;
import com.fanyiran.demo_loadapkplugin_lib.PluginApkInfo;
import com.fanyiran.demo_loadapkplugin_lib.PluginManager;
import com.fanyiran.demo_loadapkplugin_lib.PluginProxyActivity;
import com.fanyiran.utils.FileUtils;

public class MainActivity extends AppCompatActivity {
    private String pluginApkName = "plugin.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().init(this);
    }


    public void onLoadApkClick(View view) {
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+pluginApkName;
        FileUtils.assetCopyToSdCard(this,pluginApkName, targetPath);
        if (PluginManager.getInstance().loadApk(targetPath)) {
            Toast.makeText(this,"load success!",Toast.LENGTH_SHORT).show();
        }
    }

    public void onJumpActivityClick(View view) {
        PluginApkInfo pluginApkInfo = PluginManager.getInstance().getPluginApkInfo();
        if (pluginApkInfo == null) {
            Toast.makeText(this,"未加载apk",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, PluginActivity.class);
        intent.putExtra(IPlugin.EXTRA_PLUGIN_NAME,"com.fanyiran.demo_loadapkplugin_apkplugin.PluginDemoActivity");
        intent.putExtra(IPlugin.EXTRA_PLUGIN_FROM,IPlugin.PLUGIN_FROM_EXTERNAL);
        startActivity(intent);
    }
}
