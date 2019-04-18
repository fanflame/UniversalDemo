package com.fanyiran.hotfixdemo;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fanyiran.utils.FileUtils;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 727;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    ,REQUEST_CODE);
        }

        findViewById(R.id.btnCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = Utils.calulate();
                Toast.makeText(MainActivity.this, ""+result, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnFix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //模拟下载过程
                String name = "patch.dex";
                String sdCardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File dexDemo = MainActivity.this.getDir(Constants.PATCH_DEX_PATH, Context.MODE_PRIVATE);
                try {
                    FileUtils.fileCopyToSdCard(sdCardPath,dexDemo.getAbsolutePath(),name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FixedUtils.fix(MainActivity.this);
            }
        });
    }
}
