package com.example.myapplication.kt

class Kt7 {
    var name = "wangjinxu"
        get() = field
        set(value) {
            field = value
        }

    /*
        背后做的事情：
        @NotNull
        private String name = "wangjinxu";
        public void setName(String name) {
            this.name = name
        }
         @NotNull
        public String getName(){
            return name;
        }

    */
    val age = 32

    /*
    背后做的事情：
    private int age = 32;
    public String getAge(){
        return this.age;
    }
    */
    val num1: Int
        get() = (1..100).shuffled().first()
}

fun main() {
    /*
        相当于：
        new Kt7().setName("wangxiaoxun")
    */
    Kt7().name = "wangxiaoxun"

    println("=============")
    /*
        相当于：
        new Kt7().getName()
    */
    var name2 = Kt7().name
    println(name2)
    println("=============")

    /*
        相当于：
        new Kt7().getAge() //val只有get
    */
    println(Kt7().age)
    println("=============")

    println(Kt7().num1)


}