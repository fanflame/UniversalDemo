package com.fanyiran.demo_mutilthread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class AtomicAdd implements Add {
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void increase() {
        int request;
        do {
            request = atomicInteger.get();
        }while(!atomicInteger.compareAndSet(request,request + 1));
    }

    @Override
    public int getResult() {
        return atomicInteger.get();
    }
}
