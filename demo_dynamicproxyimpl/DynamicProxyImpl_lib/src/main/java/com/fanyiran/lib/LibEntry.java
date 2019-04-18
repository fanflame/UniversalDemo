package com.fanyiran.lib;

/**
 * Created by fanqiang on 2019/3/27.
 */
public class LibEntry {
    static public void testProxy() {
        People people = new Man();
        ManInvocationHandler handler = new ManInvocationHandler(people);
        People peopleProxy = (People) MyProxy.newProxyInstance(new MyClassLoader(), new Class[]{People.class}, handler);
        peopleProxy.saySomething();
    }
}
