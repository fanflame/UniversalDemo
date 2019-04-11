package com.fanyiran.demo_hermes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fanyiran.demo_hermes.bean.IPeople;
import com.fanyiran.demo_hermes.bean.People;
import com.fanyiran.hermes_lib.ProcessManager;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class SecondActiivty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ProcessManager.getInstance().connect(this);

    }

    public void tvOnClick(View view) {
        ProcessManager.getInstance().getInstance(IPeople.class).setPeople("hahaha","hehehe");
    }

    @Override
    protected void onDestroy() {
        ProcessManager.getInstance().unConnect(this);
        super.onDestroy();
    }
}
