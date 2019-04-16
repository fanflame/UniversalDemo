package com.fanyiran.demo_loadapkplugin_lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by fanqiang on 2019/4/15.
 */
public interface IPlugin {
    String EXTRA_PLUGIN_NAME="EXTRA_PLUGIN_NAME";
    String EXTRA_PLUGIN_FROM = "EXTRA_PLUGIN_FROM";
    // TODO: 2019/4/15 内部跳转会调用super的方法？
    int PLUGIN_FROM_INNER = 0;
    int PLUGIN_FROM_EXTERNAL = 1;

    void onAttach(Activity context);
    void onCreate(Bundle savedInstanceState);

    void setContentView(int resLayoutId);


    void onResume();


    void onStart();


    void onPause();


    void onStop();


    void onDestroy();


    void onActivityResult(int requestCode, int resultCode, Intent data);
}
