package com.fanyiran.demo_libjpeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private native String stringFromJNI();

    public void onHelloClick(View view) {
        ((TextView)findViewById(R.id.tv)).setText(stringFromJNI());
    }
}
