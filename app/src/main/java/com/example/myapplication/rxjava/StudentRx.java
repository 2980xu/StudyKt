package com.example.myapplication.rxjava;

import java.util.ArrayList;
import java.util.List;

public class StudentRx {
    String name;
    int age;
    List<InfoRx> infoRxList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<InfoRx> getInfoRxList() {
        return infoRxList;
    }

    public void setInfoRxList(List<InfoRx> infoRxList) {
        this.infoRxList = infoRxList;
    }
}
