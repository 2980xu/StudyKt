//注解JvmName（就是在编译器环节，修改类名）
@file:JvmName("Stu")

package com.example.myapplication.kt

fun getStuInfo(str: String) {
    println(str)
}

val names1 = listOf("张三1", "李四1", "王五1")

//JvmField（剔除成员私有化）
@JvmField
val names2 = listOf("张三2", "李四2", "王五2")

//JvmOverloads可以使用kt中入参的默认值
@JvmOverloads
fun showInfo(name: String, age: Int = 18, info: String = "😄") {
    println("name: $name age: $age info: $info")
}

class TestKt17 {
    companion object {
        val goHme = "回家"

        //JvmStatic 类名直接调用方法
        @JvmStatic
        @JvmOverloads
        fun showGoHome(name: String = "王小训", age: Int) = println("$name $age $goHme 😄😄😄")
    }
}

fun main() {
    showInfo("王小训")
}
