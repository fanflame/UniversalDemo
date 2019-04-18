package com.fanyiran.demo_baseadapter.itemtype;

import android.widget.TextView;

import com.fanyiran.demo_baseadapter.Data;
import com.fanyiran.demo_baseadapter.R;
import com.fanyiran.demo_baseadapter_lib.ItemType;
import com.fanyiran.demo_baseadapter_lib.RvViewHolder;

/**
 * Created by fanqiang on 2019/4/18.
 */
public class ItemType_3<T> implements ItemType<T> {
    public static final int TYPE_3 = 2;
    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public int getType() {
        return TYPE_3;
    }

    @Override
    public int getLayout() {
        return R.layout.item_type3;
    }

    @Override
    public void fillContent(RvViewHolder rvViewHolder, int position, T data) {
        ((TextView) rvViewHolder.getView(R.id.type_3)).setText(((Data)data).getContent());
    }

    @Override
    public boolean isCurrentType(T data, int position) {
        return ((Data)data).getItemType() == getType();
    }
}
