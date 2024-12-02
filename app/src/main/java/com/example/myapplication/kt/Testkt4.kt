package com.example.myapplication.kt

fun main() {

    println("================split======================")
    var name: String = "name1,name2,name3"
    val list = name.split(",")
    println("split: $list")

    println("================c++解构形式输出======================")
    //c++解构形式输出
    val (v1, v2, v3) = list
    println("v1: $v1 v2: $v2 v3: $v3")

    println("================replace======================")
    var pwd = "ABCDEFG"
    val pwda = pwd.replace(Regex("[ADG]")) {
        when (it.value) {
            "A" -> "1"
            "D" -> "5"
            "G" -> "3"
            else -> it.value
        }
    }
    println("加密密码是：$pwda")
    val pwds = pwda.replace(Regex("[153]")) {
        when (it.value) {
            "1" -> "A"
            "5" -> "D"
            "3" -> "G"
            else -> it.value
        }
    }
    println("解密密码是：$pwds")


    //== 值内容的比较 相当与java中equlas
    //=== 引用的比较

    println("================toInt======================")
    // 类型转换
    var num1: Int = "888".toInt()
    println(num1)

    println("================toIntOrNull======================")
    val num2: Int? = "888.8".toIntOrNull()
    println(num2)

    println("================toUpperCase======================")
    val name1 = "wjx"
    println(name1.toUpperCase())

    println("================apply======================")
    //apple内置函数的使用
    //apple函数返回类型是name2本身
    //apple匿名函数里面持有的是this == name2本身
    val name2 = "wangjinxu"
    name2.apply {
        println(length)
    }.apply {
        println(this[length - 1])
    }.apply {
        println(toUpperCase())
    }

    println("================let======================")
    //let内置函数的使用
    //let返回类型是根据匿名函数最后一行的变化而变化
    //let匿名函数里面持有的是it == listTest(集合)本身
    val listTest = listOf(6, 4, 7, 2, 3)
    val num: Int = listTest.let {
        it.first() + it.first() //let匿名函数最后一行作为返回值，apply匿名函数最后一行返回的永远都是name2类型本身
    }
    println(num)

    println("================run======================")
    //run内置函数的使用
    //run返回类型是根据匿名函数最后一行的变化而变化
    //run匿名函数里面持有的是this == name3本身
    val name3 = "wangjinxu"
    var type = name3.run {
        true
        88.66
    }
    println(type)

    println("================run======================")
    val name4 = "wangjinxu1"
    name4.run(::isSameName).apply {
        println(this)
    }.run(::showName).run(::showResult).run(::println)

    //===============================>上面是具名显示，下面修改为匿名
    println("================run======================")
    val name5 = "wangjinxu2"
    name5.run {
        if (this == "wangjinxu") true else false
    }.apply {
        println(this)
    }.run {
        if (this) "wangjinxu" else "啥也不是"
    }.run {
        "[$this]"
    }.run {
        println(this)
    }


    println("================let======================")
    name5.let(::isSameName).let(::showName).let(::showResult).let(::println)

    println("================with======================")
    //with内置函数的使用
    //with返回类型是根据匿名函数最后一行的变化而变化
    //with匿名函数里面持有的是this == name6本身
    val name6 = "wangjinxu"
    //具名写法
    with(with(with(with(name6, ::isSameName), ::showName), ::showResult), ::println)
    //匿名写法
    with(with(with(with(name6) {
        if (name == "wangjinxu") true else false
    }) {
        if (this) "王金旭😊" else "啥也不是😶"
    }) {
        "[$this]"
    }) {
        println(this)
    }

    println("================also======================")
    //also内置函数的使用
    //also函数返回类型是name7本身
    //also匿名函数里面持有的是it == name7本身
    val name7 = "wangjinxu"
    name7.also {
        if (it == "wangjinxu")
            println("true")
        else
            println("false")
    }.also {
        if (it == "wangjinxu")
            println("wangjinxu😊")
        else
            println("啥也不是😶")
    }.also {
        println("$it")
    }

    println("================takeIf======================")
    //takeIf内置函数的使用
    //name8.takeIf 如果匿名函数里面是true则返回name8本身，如果是false则返回null
    //takeIf匿名函数里面持有的是it == name8本身
    //真正用途是takeIf + 空合并操作符 配合使用

    val name8 = "wangjinxu1111"
    val str8 =  name8.takeIf {
        if (it == "wangjinxu") true else false
    }
    println(str8)
    val str88 = name8.takeIf(::isSameName) ?: "啥也不是🤫"
    println(str88)

    println("================takeUnless======================")
    //takeUnless内置函数的使用
    //name8.takeUnless 如果匿名函数里面是true则返回null，如果是false则返回name9本身
    //takeUnless匿名函数里面持有的是it == name9本身
    //与takeIf相反
    //真正用途是takeUnless + isNullOrBlank + 空合并操作符 配合使用
    val name9 = ""
    val str9 = name9.takeUnless {
        if (it.isNullOrBlank()) true else false
    } ?: "啥也不是🤯"
    println(str9)


}

fun isSameName(name: String) = if (name == "wangjinxu") true else false
fun showName(isSameName: Boolean) = if (isSameName) "wangjinxu😊" else "啥也不是😶"
fun showResult(result: String) = "[$result]"


//结论：
//使用apply来修改对象的属性并返回对象本身。
//使用let来处理对象并返回处理结果，特别是在对象可能为null的情况下。
//使用run来执行代码块并返回最后的计算结果，可以用来处理非null对象的情况。
//如果你需要在代码块中处理对象并且返回结果，let适合；如果你需要利用对象的上下文并返回处理结果，run可能更适合。

//apply 与also是一样的
//let，run，with是一样的
//run和with只是调用方式不同


