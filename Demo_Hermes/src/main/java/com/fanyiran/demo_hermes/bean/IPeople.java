package com.fanyiran.demo_hermes.bean;


import com.fanyiran.hermes_lib.ChildAnnotation;

/**
 * Created by fanqiang on 2019/4/11.
 */
@ChildAnnotation("com.fanyiran.demo_hermes.bean.People")
public interface IPeople {
    void setPeople(String name,String pwd);
    IPeople getPeople();
}
