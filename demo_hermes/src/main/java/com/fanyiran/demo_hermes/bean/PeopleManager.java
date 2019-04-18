package com.fanyiran.demo_hermes.bean;

/**
 * Created by fanqiang on 2019/4/11.
 */
public class PeopleManager implements IPeopleManager {
    private People people;
    private static PeopleManager peopleManager;
    private PeopleManager(){}
    public static PeopleManager getInstance() {
        if(peopleManager == null)
            peopleManager = new PeopleManager();
        return peopleManager;
    }

    @Override
    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public People getPeople() {
        return people;
    }
}
