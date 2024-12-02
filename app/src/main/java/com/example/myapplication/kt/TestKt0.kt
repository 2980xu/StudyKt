package com.example.myapplication.kt

//编译时常量
const val PI = 3.14

//java有两种数据类型：
//1种：基本数据类型：int，double等等
//2种：引用数据类型：String等
//Kotlin只有一种数据类型

//range表达式if(num in 1..99)
fun rangeFunc() {
    val num = 99
    if (num in 1..99) {
        println("num1111: $num")
    } else if (num in 1 until 99) {
        println("num2222: $num")
    }
}

//函数参数的默认参数
fun defaultFunc(name: String, age: Int = 123): Int {
    if (name == "wangxiaoxun") {
        return 111
    } else {
        return age
    }
}

//kotlin中Unit默认关键字有，相当于java中void
fun unitFunc(name: String, num: Int): Unit {
    println("name: $name num: $num")
}

//Kotlin中Nothing会执行一次但是会抛出异常
//格式：TODO("不是注释提示，会终止程序")

//反引号：``
fun `反引号用法`(name: String) {
    println("name: $name")
}

//匿名函数
fun lenFunc() {
    val str = "wangxiaoxun"
    val len = str?.count() {
        //count里面就是匿名函数
        //it = w a n g x i a o x u n
        it == 'x'
    }
    println("len: $len")
}

//函数参数
fun typeFunc() {
    //1.函数输入输出的声明
    val methodFunc: (num: Int) -> String
    //相当于
    //    fun methodFunc(num: Int): String {
    //        return ""
    //    }

    //2.对上面函数的实现
    methodFunc = { num ->
        "$num 😄"
        //匿名函数不要写return，最后一行就是返回值
    }

    println(methodFunc(999))
}

//匿名函数类型推导
fun backFunc1() {
    //匿名函数，类型推导为String
    //方法名 ： 必须指定参数类型和返回类型
    //方法名 =  类型推到返回类型
    val methodFunc2 = { num: Int ->
        "$num 😊"
    }
    //相当于
    //val methodFunc2: (num: Int) -> String
}

//不使用内联inline java会生成num对象INSTANCE
fun testNumInfo1(name: String, age: Int, num: (String, Int) -> String) {
    if (name == "wangxiaoxun" && age == 123) {
        println(num("😄😄😄", 101))
    } else {
        println(num("🤬🤬🤬", 111))
    }
}

//如果此函数有使用lambda作为参数，就需要声明成内联
//lambda参数就相当于java接口
//如果此函数不使用内联，在调用端会生成多个对象来完成lambda的调用（会造成性能损耗）
inline fun testNumInfo2(name: String, age: Int, num: (String, Int) -> String) {
    if (name == "wangxiaoxun" && age == 123) {
        println(num("😄😄😄", 101))
    } else {
        println(num("🤬🤬🤬", 111))
    }
}


//函数引用
fun numInfoFunc(name: String, code: Int): String {
    return "name: $name code: $code"
}

//函数类型作为返回类型
fun backFunc(): (String, Int) -> String {
    return { name: String, code: Int -> if (name == "wangxiaoxun" && code == 123) "😄" else "🤯" }
}

fun main() {
    val str = "只读类型常量"

    println("=============range表达式==================")
    rangeFunc()

    println("=============函数参数==================")
    println(defaultFunc("wangxiaoxun1"))

    println("=============反引号用法==================")
    反引号用法("🤬🤬🤬")

    println("=============匿名函数==================")
    lenFunc()

    println("=============函数参数==================")
    typeFunc()

    println("=============匿名写法1==================")
    //匿名写法1
    testNumInfo1("wangjinxu", 30) { name, code ->
        "name: $name code: $code"
    }

    println("=============匿名写法2==================")
    //匿名写法2
    testNumInfo1("wangjinxu", 30, { name, code -> "name: $name code: $code" })

    println("=============匿名写法3==================")
    //匿名写法3
    testNumInfo1("wangjinxu", 30, num = { name, code -> "name: $name code: $code" })

    println("=============具名写法4==================")
    //具名写法4
    testNumInfo2("wangxiaoxun", 123, ::numInfoFunc)
    println("===============================")

    println("=============backFunc==================")
    val back = backFunc()
    //back相当于匿名函数
    println(back("wangxiaoxun", 123))
}