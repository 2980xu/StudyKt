package com.example.myapplication.kt

//KT所有的类，默认是final修饰，不能被继承
open class Persons(val name: String) {
    //KT所有的函数，默认是final修饰，不能被重写
    open fun outPutName() = println("父类name: ${name}")
}

class Students(val studentName: String) : Persons(studentName) {
    override fun outPutName() {
//        super.outPutName()
        println("子类name😄: ${studentName}")
    }
}

//如果此函数有使用lambda作为参数，就需要声明成内联
//lambda参数就相当于java接口
//如果此函数不使用内联，在调用端会生成多个对象来完成lambda的调用（会造成性能损耗）
inline fun inlineFunc(name: String, age: Int, info: (String, Int) -> Unit) {
    if (name == "wangxiaoxun" && age == 123) {
        info("特别棒😊", 100)
    } else {
        info("哎😔", 101)
    }
}

//函数引用
fun infoFunc(message: String, code: Int) {
    println("message: $message, code: $code")
}

fun main() {
    val p: Persons = Students("wangxiaoxun")
    println(p.outPutName())
    println(p is Persons)
    println(p is Students)

    if (p is Students) {
        (p as Students).outPutName()
    }

    if (p is Persons) {
        (p as Persons).outPutName() //子类重写了父类
    }

    println("====================================")
    inlineFunc("wangxiaoxun", 12) { message, code ->
        println("message: $message code: $code")
    }
    //函数引用
    println("====================================")
    inlineFunc("wangxiaoxun", 12, ::infoFunc)


}