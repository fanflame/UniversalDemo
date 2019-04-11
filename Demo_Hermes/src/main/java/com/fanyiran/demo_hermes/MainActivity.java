package com.fanyiran.demo_hermes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fanyiran.demo_hermes.bean.IPeople;
import com.fanyiran.demo_hermes.bean.People;
import com.fanyiran.hermes_lib.ProcessManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProcessManager.getInstance().regist(IPeople.class);
    }

    public void tvOnClick(View view) {
        People people = (People) ProcessManager.getInstance().getInstance(IPeople.class).getPeople();
        Toast.makeText(this,String.format("people from another process name:%s,pwd:%s",
                people.getName(),people.getPwd()),Toast.LENGTH_LONG).show();
    }

    public void jumpToSecond(View view) {
        startActivity(new Intent(this,SecondActiivty.class));
    }
}
