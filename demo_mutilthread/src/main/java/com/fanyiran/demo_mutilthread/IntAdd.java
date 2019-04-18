package com.fanyiran.demo_mutilthread;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class IntAdd implements Add {
    private int i = 0;
    @Override
    public void increase() {
        i++;
    }

    @Override
    public int getResult() {
        return i;
    }
}
