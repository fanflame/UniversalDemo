package com.fanyiran.lib;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by fanqiang on 2019/3/27.
 */
public class ManInvocationHandler implements InvocationHandler {
    private Object object;

    public ManInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (object != null) {
            before();
            method.invoke(object);
        }
        return null;
    }

    private void before() {
        System.out.print("invocation before");
    }
}
