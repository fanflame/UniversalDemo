package com.fanyiran.hermes_lib;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class CacheCenter {
    private HashMap<String,Object> objectMap;
    private HashMap<String,HashMap<String,Method>> methodMap;

    private static final CacheCenter ourInstance = new CacheCenter();

    public static CacheCenter getInstance() {
        return ourInstance;
    }

    private CacheCenter() {
    }

    public <T> void regist(Class<T> peopleClass) {
        if (methodMap == null || methodMap.get(peopleClass.getName()) != null) {
            return;
        }
        if (methodMap == null) {
            methodMap = new HashMap<>();
        }
        Method[] methods = peopleClass.getMethods();
        HashMap<String,Method> mapTemp = new HashMap<>();
        for (Method method : methods) {
            mapTemp.put(method.getName(),method);
        }
        methodMap.put(peopleClass.getName(),mapTemp);
    }
    public Object getInstance(String className) {
        if(objectMap == null)
            return null;
        return objectMap.get(className);
    }


    public void putObject(String className,Object invoke) {
        if (objectMap == null) {
            objectMap = new HashMap<>();
        }
        objectMap.put(className,invoke);
    }

    public Method getMethod(String className,String method) {
        if (methodMap == null) {
            return null;
        }
        HashMap<String, Method> stringMethodHashMap = methodMap.get(className);
        if (stringMethodHashMap == null) {
            return null;
        }
        return stringMethodHashMap.get(method);
    }
}
