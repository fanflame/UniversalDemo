package com.fanyiran.demo_hermes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fanyiran.demo_hermes.bean.IPeopleManager;
import com.fanyiran.demo_hermes.bean.People;
import com.fanyiran.demo_hermes.bean.PeopleManager;
import com.fanyiran.hermes_lib.ProcessManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProcessManager.getInstance().regist(IPeopleManager.class);
    }

    public void jumpToSecond(View view) {
        startActivity(new Intent(this,SecondActiivty.class));
    }

    public void OnSetPeopleClick(View view) {
        PeopleManager.getInstance().setPeople(new People("hahaha","hehehe"));
    }

    public void OnGetOtherProgressPeople(View view) {
        People people = PeopleManager.getInstance().getPeople();
        Toast.makeText(this,String.format("people from another process name:%s,pwd:%s",
                people.getName(),people.getPwd()),Toast.LENGTH_LONG).show();
    }
}
