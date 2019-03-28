//package com.fanyiran.lib;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
///**
// * Created by fanqiang on 2019/3/27.
// */
//public class $Proxy0 extends Proxy implements People {
//    protected $Proxy0(InvocationHandler h) {
//        super(h);
//    }
//
//    @Override
//    public void saySomething() {
//        Method method;
//        try {
//            method = Class.forName("$Proxy0").getMethod("saySomething");
//            h.invoke(this, method, null);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//}
