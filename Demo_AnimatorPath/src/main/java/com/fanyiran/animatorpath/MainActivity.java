package com.fanyiran.animatorpath;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onTvClick(View view) {
        AnimatorPath path = new AnimatorPath();
        path.moveTo(100.0f,100.0f);
        path.lineTo(200.0f,200.0f);
        path.cubicTo(200.0f,-300.0f,300.0f,300.0f,600.0f,100.0f);
        path.startAnimator(view,10000);
    }
}
