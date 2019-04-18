package com.fanyiran.invocationhandlerdemo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjectManager {
    private static final InjectManager ourInstance = new InjectManager();

//    private HashMap<Object,List<>>

    static InjectManager getInstance() {
        return ourInstance;
    }

    private InjectManager() {
    }

    public void regist(Object object) {
        Class<?> aClass = object.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                //获取注解的注解
                Invocation invocation = annotation.annotationType().getAnnotation(Invocation.class);
                if(invocation == null){//判断注解的注解是否包含invocation
                    continue;
                }
                int[] viewIds;
                try {
                    //注解的value值也可以通过反射获取
                    Method value = annotation.getClass().getMethod("value");
                    viewIds = (int[]) value.invoke(annotation);
                    if (viewIds == null) {
                        continue;
                    }
                    Class arg = invocation.arg();
                    String setOnListenerMethod = invocation.listener();
                    String method = invocation.method();

                    for (Object v : viewIds) {
                        Method findViewByIdMethod = aClass.getMethod("findViewById", int.class);
                        Object view = findViewByIdMethod.invoke(object, v);
                        Method setOnClickMethod = view.getClass().getMethod(setOnListenerMethod,arg);
                        InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(object);
                        invocationHandler.addMethod(method,declaredMethod);
                        Object proxy = Proxy.newProxyInstance(object.getClass().getClassLoader(), new Class[]{arg}, invocationHandler);
                        setOnClickMethod.invoke(view,proxy);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

//    public void unregist(Object object) {
//
//    }
}
