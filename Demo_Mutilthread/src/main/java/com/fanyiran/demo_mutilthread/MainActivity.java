package com.fanyiran.demo_mutilthread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runThread();
    }

    private void runThread(){
        // NOTE: 线程不安全
//        final Add add = new IntAdd();
        // NOTE: Atomi
        final Add add = new AtomicAdd();
        // NOTE: 自定义锁实现Lock 与 unLock
//        final Add add = new CustomLockAdd();
        for (int i = 0; i < 20000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    add.increase();
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
            Log.v("sasasa,",add.getResult()+"");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
