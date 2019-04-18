package com.fanyiran.demo_baseadapter_lib;

/**
 * Created by fanqiang on 2019/4/16.
 */
public interface ItemType<T> {

    boolean openClick();

    int getType();

    int getLayout();

    void fillContent(RvViewHolder rvViewHolder, int position,T data);

    boolean isCurrentType(T data, int position);
}
