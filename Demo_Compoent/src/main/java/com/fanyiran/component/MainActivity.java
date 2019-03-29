package com.fanyiran.component;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fanyiran.componentlib.CompoentService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnOpenLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompoentService.getInstance().getLaunchLogin().launchLoginActivity(MainActivity.this);
            }
        });

        findViewById(R.id.btnOpenChat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompoentService.getInstance().getLaunchChat().launchChatActivity(MainActivity.this);
            }
        });
    }
}
