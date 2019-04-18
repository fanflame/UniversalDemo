package com.fanyiran.dynamicproxyimpldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanyiran.lib.LibEntry;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LibEntry.testProxy();
    }

}
