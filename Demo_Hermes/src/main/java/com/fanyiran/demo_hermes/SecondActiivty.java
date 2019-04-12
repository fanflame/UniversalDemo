package com.fanyiran.demo_hermes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.fanyiran.demo_hermes.bean.IPeopleManager;
import com.fanyiran.demo_hermes.bean.People;
import com.fanyiran.demo_hermes.bean.PeopleManager;
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

    @Override
    protected void onDestroy() {
        ProcessManager.getInstance().unConnect(this);
        super.onDestroy();
    }

    public void OnGetPeopleClick(View view) {
        People people = ProcessManager.getInstance().getInstance(IPeopleManager.class).getPeople();
        Toast.makeText(this,String.format("people from another process name:%s,pwd:%s",
                people.getName(),people.getPwd()), Toast.LENGTH_LONG).show();
    }

    public void OnSetPeopleClick(View view) {
        PeopleManager.getInstance().setPeople(new People("hahaha","hehehe"));
    }
}
