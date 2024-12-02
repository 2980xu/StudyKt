package com.example.myapplication.kt

fun main() {
    //第一种情况是不能为null
    println("================name1======================")
    var name1: String = "wangjinxu"
    println(name1)

    println("================name2======================")
    //第二种情况是可以为null
    var name2: String? = "wangjinxu"
//    name2 = null
    //判断name2是否为“”
    if (name2?.isBlank() == true) {
        println(name2)
    }


    println("================name3======================")
    var name3: String? = "wangjinxu"
    println(name3)
    //判断name3是否为空，name3如果真为null，则？后面的capitalize不会执行，并返回null
    name3?.capitalize()
    println(name3)

    println("================let用法======================")
    //let用法
    var name4: String? = "wangjinxu"
    name4?.let {
        println(it.length)
    }

    println("================断言!!======================")
    //断言!! 确信这里的name5肯定不为null，如果为null则抛出异常NullPointerException
    var name5: String? = "wangjinxu"
    println(name5!!.capitalize())

    var name6: String? = "wangjinxu"
    name6 = null
    //空合并操作符：如果name6等于null，就会执行？：后面的区域
    println(name6 ?: "name6 is null")

    var name7:String ?= "wangjinxu"
//    name7 = null
    println(name7?.let { "[$it]" } ?: "name7 is null")

}

fun printStr(name: String) {
    println(name)
}

//在 Kotlin 中，? 是一个非常重要的语法特性，用于处理可能为 null 的情况。具体来说，? 在 Kotlin 中有以下几种主要用途：
//
//声明可空类型：
//? 用于声明一个变量可以是某种类型或者是 null。例如：
//
//var name: String? = null
//name = "Alice"
//在这个例子中，name 的类型是 String?，这意味着它可以是一个 String 对象，也可以是 null。
//
//安全调用操作符（Safe Call Operator）：
//当你需要调用一个可能为 null 的对象的方法或访问其属性时，可以使用 ?. 来安全地进行调用。如果对象为 null，表达式的结果
//也会是 null， 而不会引发空指针异常。例如：
//
//val length: Int? = name?.length
//这里，name?.length 的意思是：如果 name 不为 null，则返回 name.length，否则返回 null。
//
//** Elvis 操作符（Elvis Operator）**：
//?: 是 Elvis 操作符，用于提供一个默认值，尤其在处理可能为 null 的情况下。例如：
//
//val length: Int = name?.length ?: 0 (?:空合并操作符)
//在这个例子中，如果 name 为 null，length 将被赋值为 0，否则将使用 name.length 的值。
//
//非空断言操作符（Not-null Assertion Operator）：
//!! 用于强制将一个值视为非空类型。如果值为 null，将抛出 NullPointerException。这个操作符通常用于你确定某个值不会为 null 的情况下，但你必须确保自己不会引发异常。例如：
//
//val length: Int = name!!.length
//使用 !! 可以避免编译器对 null 的警告，但不建议在实际代码中频繁使用，因为它可能导致运行时错误。