package com.fanyiran.demo_hermes.bean;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class People implements IPeople {
    private String name;
    private String pwd;
    private static People people;
    private People(){}
    public static People getInstance() {
        if(people == null)
            people = new People();
        return people;
    }

    @Override
    public void setPeople(String name,String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public People getPeople() {
        return people;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }
}
