package com.fanyiran.demo_hermes.bean;


import com.fanyiran.hermes_lib.ChildAnnotation;

/**
 * Created by fanqiang on 2019/4/11.
 */
@ChildAnnotation("com.fanyiran.demo_hermes.bean.PeopleManager")
public interface IPeopleManager {
    void setPeople(People people);
    People getPeople();
}
