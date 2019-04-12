package com.fanyiran.demo_hermes.bean;

/**
 * Created by fanqiang on 2019/4/12.
 */
public class People {
    private String name;
    private String pwd;

    public People(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
