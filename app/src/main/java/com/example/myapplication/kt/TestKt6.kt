package com.example.myapplication.kt

import java.io.File

fun main() {
//    IntArray           intArrayOf
//    DoubleArray        doubleArrayOf
//    LongArray          longArrayOf
//    ShortArray         shortArrayOf
//    ByteArray          byteArrayOf
//    FloatArray         floatArrayOf
//    BooleanArray       booleanArrayOf
//    Array<对象类型>     arrayOf对象数组
    println("==============intArrayOf================")
    val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
    println(intArray[0])

    println("==============elementAtOrElse================")
    println(intArray.elementAtOrElse(10) {
        -1
    })

    println("==============elementAtOrNull================")
    println(intArray.elementAtOrNull(100) ?: "越界了😄")

    //list集合转数组
    val array = listOf(1, 2, 3).toIntArray()
    println(array[0])

    //arrayOf对象数组
    val objArray: Array<File> = arrayOf(File("aaaa"), File("bbb"), File("ccc"))
    println(objArray[0])

}