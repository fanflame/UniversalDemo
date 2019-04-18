package com.fanyiran.hermes_lib;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.google.gson.Gson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class ProcessManager {
    private Gson gson;
    private IPersonInterface personInterface;
    private static final ProcessManager ourInstance = new ProcessManager();
    private ProcessServiceConnection processServiceConnection;

    public static ProcessManager getInstance() {
        return ourInstance;
    }

    private ProcessManager() {
    }

    //该方法在非主进程中调用
    public <T> T getInstance(Class<T> tClass) {
        if (gson == null) {
            gson = new Gson();
        }
        CommunicationBean bean = new CommunicationBean();
        bean.setType(CommunicationBean.TYPE_GETINSTANCE);
        ChildAnnotation annotation = tClass.getAnnotation(ChildAnnotation.class);
        if (annotation == null) {
            throw new IllegalStateException("接口未添加注解");
        }
        bean.setClassName(annotation.value());
        String content = gson.toJson(bean);
        try {
            personInterface.sendString(content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Object instance = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, new ProcessInvocationHandler(tClass));
        return (T) instance;
    }

    public void connect(Context context) {
        processServiceConnection = new ProcessServiceConnection();
        context.bindService(new Intent(context,ProcessService.class),processServiceConnection,Context.BIND_AUTO_CREATE);
    }

    public void unConnect(Context context) {
        context.unbindService(processServiceConnection);
    }

    public <T> void regist(Class<T> peopleClass) {
        CacheCenter.getInstance().regist(peopleClass);
    }

    class ProcessServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            personInterface = IPersonInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            personInterface = null;
        }
    }

    class ProcessInvocationHandler implements InvocationHandler {
        private Class tClass;
        ProcessInvocationHandler(Class tClass) {
            this.tClass = tClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args){
            CommunicationBean bean = new CommunicationBean();
            ChildAnnotation annotation = (ChildAnnotation) tClass.getAnnotation(ChildAnnotation.class);
            if (annotation == null) {
                throw new IllegalStateException("接口未添加注解");
            }
            bean.setClassName(annotation.value());
            bean.setMethodName(method.getName());
            bean.setType(CommunicationBean.TYPE_METHOD);
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes != null) {
                List<CommunicationBean.MethodParamsBean> methodParams = new ArrayList<>(args.length);
                CommunicationBean.MethodParamsBean temp;
                for (int i = 0; i < parameterTypes.length; i++) {
                    temp = new CommunicationBean.MethodParamsBean();
                    temp.setParamsClass(parameterTypes[i].getName());
                    temp.setParamObject(gson.toJson(args[i]));
                    methodParams.add(temp);
                }
                bean.setMethodParams(methodParams);
            }
            String content = gson.toJson(bean);
            try {
                String result = personInterface.sendString(content);
                return gson.fromJson(result,method.getReturnType());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
