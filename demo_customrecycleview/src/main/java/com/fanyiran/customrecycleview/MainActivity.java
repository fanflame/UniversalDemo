package com.fanyiran.customrecycleview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RecycleView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycle = findViewById(R.id.recycle);
        recycle.setAdapter(adapter);
    }

    private RecycleView.Adapter adapter = new RecycleView.Adapter() {
        @Override
        public int getHeight(int position) {
            return 100;
        }

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public int getType(int position) {
            return 1;
        }

        @Override
        public View createView(int position) {
            return View.inflate(MainActivity.this,R.layout.item,null);
        }

        @Override
        public void bindView(View view, int position) {
            ((TextView) view.findViewById(R.id.tv)).setText("当前位置："+position);
//            if (position % 2 == 0) {
//                view.setBackgroundColor(Color.BLUE);
//            } else {
//                view.setBackgroundColor(Color.RED);
//            }
        }
    };
}
