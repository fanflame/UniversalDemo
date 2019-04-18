package com.fanyiran.demo_baseadapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fanyiran.demo_baseadapter_lib.RvBaseAdapter;
import com.fanyiran.demo_baseadapter_lib.RvListener;
import com.fanyiran.demo_baseadapter_lib.actiivty.RvBaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends RvBaseActivity {
    private List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public RecyclerView getRecycleView() {
        return findViewById(R.id.recycleView);
    }

    @Override
    public RvBaseAdapter getAdapter() {
        List<Data> baseDataList = getDataList();
        AdapterRv<Data> adapter = new AdapterRv<>(baseDataList);
        adapter.setRvListener(new RvListener<Data>() {
            @Override
            public void onClick(Data data, int position) {
                Toast.makeText(MainActivity.this,String.format("click:%s",position),Toast.LENGTH_SHORT).show();
            }
        });
        return adapter;
    }

    private List<Data> getDataList() {
        int size = 10000;
        list = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            Data data = new Data(random.nextInt(4), String.format("content:%s", i));
            list.add(data);
        }
        return list;
    }
}
