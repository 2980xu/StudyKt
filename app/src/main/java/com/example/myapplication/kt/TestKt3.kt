package com.example.myapplication.kt

fun main() {
    try {
        var name1: String? = null

        checkException(name1)

        println(name1!!.length)
    } catch (e: IllegalArgumentException) {
        println("$e")
//        e.printStackTrace()
    }

    var name2 : String?=null
    checkNotNull(name2)
    requireNotNull(name2)

    var name3 : Boolean = false
    //检测name3是不是false，如果是false就抛出异常，如果是true就不抛出异常
    require(name3)

}

fun checkException(name1: String?) {
    name1 ?: throw CustomException()

}

class CustomException : IllegalArgumentException("写的什么玩意")