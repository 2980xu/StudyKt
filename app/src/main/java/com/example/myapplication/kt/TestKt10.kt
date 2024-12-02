package com.example.myapplication.kt

//Kt Any学习
//Any 相当于 java object
//Kt任何一个类都继承Any


interface RunnableKt{
    fun run()
}

//父类
open class TeskKt10 {
    open fun add() = println("add")
    open fun del() = println("del")
}

class TestKt101 {
    //伴生对象（Kotlin中是没有类似于java的static这种东西，所以伴生companion object相当于java中的static）
    //无论TestKt101()初始化多少次，伴生对象只有一次加载
    //无论TestKt101.info调用多少次，伴生对象只有一次加载
    //伴生对象只会初始化一次
    companion object {
        val info = "haha"

    }
}

fun main() {

    //匿名对象表达式方式
    val p = object : TeskKt10() {
        override fun add() {
//            super.add()
            println("main add ")
        }

        override fun del() {
            super.del()
            println("main del ")
        }
    }

    p.add()
    p.del()

    //java的接口 用对象表达式方式
    val p1 = object :Runnable {
        override fun run() {
            println("java方式一： run")
        }
    }
    p1.run()

    Runnable{
        println("java方式二： run")
    }.run()


    val p2 = object : RunnableKt {
        override fun run() {
            println("kt方式一： run")
        }
    }
    p2.run()
    //小结：java 接口有两种实现方式（1.object:对象表达式 2.简洁表达式）
    //kt只有一种实现方式（object:对象表达式）

    println("=============================")

    println(TestKt101.info)

}