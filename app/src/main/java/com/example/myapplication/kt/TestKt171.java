package com.example.myapplication.kt;

public class TestKt171 {
    public static void main(String[] args) {
        Stu.getStuInfo("111111");
        System.out.println("==============================");

        for (String str : Stu.getNames1()) {
            System.out.println(str);
        }
        System.out.println("==============================");

        for (String str : Stu.names2) {
            System.out.println(str);
        }
        System.out.println("==============================");

        Stu.showInfo("王小训");
        System.out.println("==============================");

        TestKt17.showGoHome(123);
        System.out.println("==============================");
    }
}
