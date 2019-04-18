package com.fanyiran.demo_baseadapter;

import com.fanyiran.demo_baseadapter.itemtype.ItemType_1;
import com.fanyiran.demo_baseadapter.itemtype.ItemType_2;
import com.fanyiran.demo_baseadapter.itemtype.ItemType_3;
import com.fanyiran.demo_baseadapter.itemtype.ItemType_4;
import com.fanyiran.demo_baseadapter.itemtype.ItemType_5;
import com.fanyiran.demo_baseadapter_lib.RvBaseAdapter;

import java.util.List;

/**
 * Created by fanqiang on 2019/4/18.
 */
public class AdapterRv<T> extends RvBaseAdapter<T> {
    public AdapterRv(List baseDataList) {
        super(baseDataList);
        addItemType(new ItemType_1<Data>());
        addItemType(new ItemType_2<Data>());
        addItemType(new ItemType_3<Data>());
        addItemType(new ItemType_4<Data>());
        addItemType(new ItemType_5<Data>());
    }
}
