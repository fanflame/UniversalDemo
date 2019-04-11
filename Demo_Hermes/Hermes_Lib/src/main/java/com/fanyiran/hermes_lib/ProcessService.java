package com.fanyiran.hermes_lib;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class ProcessService extends Service {
    private Gson gson;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IPersonInterface.Stub(){
            @Override
            public String sendString(String content) {
                if (gson == null) {
                    gson = new Gson();
                }
                CommunicationBean bean = gson.fromJson(content, CommunicationBean.class);
                switch (bean.getType()) {
                    case CommunicationBean.TYPE_GETINSTANCE:
                        Object instance = CacheCenter.getInstance().getInstance(bean.getClassName());
                        if(instance == null){
                            try {
                                Class<?> aClass = Class.forName(bean.getClassName());
                                Method getInstance = aClass.getMethod("getInstance");
                                Object invoke = getInstance.invoke(null);
                                CacheCenter.getInstance().putObject(bean.getClassName(),invoke);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case CommunicationBean.TYPE_METHOD:
                        instance = CacheCenter.getInstance().getInstance(bean.getClassName());
                        if (instance == null) {
                            throw new IllegalStateException("实例未找到");
                        }
                        Method method = CacheCenter.getInstance().getMethod(bean.getClassName(),bean.getMethodName());
                        if (method == null) {
                            return null;
//                            throw new IllegalStateException("方法未找到");
                        }
                        try {
                            Object invoke = method.invoke(instance, getParams(bean));
                            return gson.toJson(invoke);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }

                return null;
            }
        };
    }
    private Object[] getParams(CommunicationBean bean) {
        if(bean == null)
            return null;
        return null;
    }
}
