package com.fanyiran.invocationhandlerdemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class InvocationHandlerImpl implements InvocationHandler {
    private Object target;
    private HashMap<String,Method> map;

    public InvocationHandlerImpl(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method targetMethod;
        if((targetMethod = map.get(method.getName())) != null){
            targetMethod.invoke(target,args);
        }
        return null;
    }

    public void addMethod(String methodName,Method method){
        if (map == null) {
            map = new HashMap();
        }
        map.put(methodName,method);
    }
}
