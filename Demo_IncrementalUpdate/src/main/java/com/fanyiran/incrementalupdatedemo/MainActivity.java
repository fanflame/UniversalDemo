package com.fanyiran.incrementalupdatedemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 需要申请写权限
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        ((TextView) findViewById(R.id.tv)).setText(version);

        // Example of a call to a native method
        Button tv = (Button) findViewById(R.id.sample_text);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.execute();
            }
        });
    }

    private class Task extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            String oldApk = getApplicationContext().getPackageResourcePath();
            File file = new File(Environment.getExternalStorageDirectory(),"mergedApk.apk");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String path = new File(Environment.getExternalStorageDirectory(),"patch").getAbsolutePath();
            mergePatch(oldApk,path,file.getAbsolutePath());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(MainActivity.this,"完毕",Toast.LENGTH_LONG).show();
        }
    }

    public native void mergePatch(String oldApk,String patch,String newApkPath);
}
