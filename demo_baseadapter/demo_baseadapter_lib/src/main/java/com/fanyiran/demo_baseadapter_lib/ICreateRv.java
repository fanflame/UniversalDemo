package com.fanyiran.demo_baseadapter_lib;

import android.support.v7.widget.RecyclerView;

/**
 * Created by fanqiang on 2019/4/17.
 */
public interface ICreateRv {
    RecyclerView getRecycleView();

    RvBaseAdapter getAdapter();

    RecyclerView.LayoutManager getLayoutManager();

    RecyclerView.ItemDecoration getItemDecoration();
}
