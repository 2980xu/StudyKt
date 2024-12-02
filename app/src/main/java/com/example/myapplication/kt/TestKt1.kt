package com.example.myapplication.kt

import kotlin.math.max
import kotlin.math.min

fun sub(a: Int, b: Int): Int {
    return a + b
}

fun largeNum(a: Int, b: Int): Int {
    return max(a, b)
}

fun minNum(a: Int, b: Int) = min(a, b)

fun largeNum1(a: Int, b: Int) = if (a > b) {
    a
} else {
    b
}


fun getStore(name: String) = when (name) {
    "Tom1" -> 96
    "Tom2" -> 98
    "Tom3" -> 100
    "Tom4" -> 66
    "Tom5" -> 99
    else -> 0
}

fun checkNumType(number: Number) = when (number) {
    is Int -> {
        println("number is Int")
    }

    is Double -> {
        println("number is Double")
    }

    else -> {
        println("number not support")
    }
}

fun getStore1(name: String) = when {
    name.startsWith("Jerry") -> 66
    name == "Tom1" -> 77
    name == "Tom2" -> 24
    name == "Tom3" -> 69
    name == "Tom4" -> 78
    name == "Tom5" -> 96
    else -> 0
}

fun forMethod() {
    for (i in 0..3) {
        println(i)
    }
}

fun utilMethod() {
    val a = 0 until 5 step 2
    for (i in a) {
        println(i)
    }
}

fun downToMethod() {
    for (i in 5 downTo 1 step 2) {
        println(i)
    }
}

fun callPerson() {
    val p = Person("Jack", 66)
//    p.name = "Jack"
//    p.age = 66
    p.eat()
}

fun callStudent() {
    val p = Student("Jim", 88, "Jack", 66)
    p.eat()
    letTest(p)
}

fun listofTest() {
    var list = listOf("1", "2", "3", "4", "5")
    for (i in list) {
        println(i)
    }
}

fun mutableListOfTest() {
    var list = mutableListOf("1", "2", "3", "4", "5")
    list.add(1, "6")

    //第一种方式遍历
    for (i in list) {
        println("第一种方式: $i")
    }

    //第二种方式遍历
    list.forEach {
        println("第二种方式: $it")
    }

    //第三种方式遍历
    list.forEachIndexed { index, s ->
        println("第三种方式 index: $index s: $s")
    }
}

fun muatorTest() {
    //muator += -=操作
    var list = mutableListOf("1", "2", "3", "4", "5")
    list += "6"
    list += "7"
    list += "8"
    list -= "3"
    println(list)

    //removeIf
    list.removeIf { it.contains("4") }
    list.removeIf { true } //list元素进行一个一个删除
    println(list)
}

fun setofTest() {
    var set = setOf<String>("q", "w", "e", "r")
    for (i in set) {
        println(i)
    }
}

fun mutableSetOfTest() {
    var set = mutableSetOf<String>("q", "w", "e", "r")
    set.add("t")
    for (i in set) {
        println(i)
    }
}

fun mapofTest() {
//    val map = HashMap<String, Int>()
//    map["q"] = 1
//    map["w"] = 2
    val map = mapOf("Q" to 1, "W" to 2, "E" to 3, "R" to 4)
    for ((fruit, number) in map) {
        println("fruit: " + fruit + " number: " + number)
    }
}

fun mutableMapOfTest() {
//    val map = HashMap<String, Int>()
//    map["q"] = 1
//    map["w"] = 2
    val map = mutableMapOf("Q" to 1, "W" to 2, "E" to 3, "R" to 4)
    map["T"] = 5
    for ((fruit, number) in map) {
        println("fruit: " + fruit + " number: " + number)
    }
}

fun maxLengthTest() {
    val list = listOf("banana", "apple", "orange")
    val maxLength = list.maxBy { it.length }
    println(maxLength)
}

fun toUpperCaseTest() {
    val list = listOf("banana", "apple", "orange")
    val newList = list.map { it.toUpperCase() }
    for (i in newList) {
        println("fruit: " + i)
    }
}

fun filterTest() {
    val list = listOf("banana", "apple", "orange", "pear")
    val newList = list.filter { it.length < 5 }.map { it.toUpperCase() }
    for (i in newList) {
        println("fruit: " + i)
    }
}

fun anyAndAllTest() {
    val list = listOf("banana", "apple", "orange", "pear")
    val any = list.any { it.length < 5 }
    val all = list.all { it.length < 5 }
    println("any: " + any + " all: " + all)
}


fun letTest(study: Study?) {
    study?.let { stu ->
        stu.doHomeWork()
        stu.doHomeWork()
    }
}

//var study : Study ?= null
//
//fun ifTest() {
//    if (study != null) {
//        study.doHomeWork()
//        study.doHomeWork()
//    }
//}

fun strTest() {
    val student = Student("Jim", 88, "Jack", 66)
    println("name: ${student.snos}")
}


fun printParam(str: String = "hello", num: Int) {
    println("str: $str, num: $num")
}

fun main() {
    println("hello kotlin")
    println("================sub======================")
    println("a + b = " + sub(123, 456))

    println("================largeNum======================")
    println("largeNum = " + largeNum(123, 456))

    println("================minNum======================")
    println("minNum = " + minNum(123, 456))

    println("================largeNum1======================")
    println("largeNum1 = " + largeNum1(123, 456))

    println("================getStore======================")
    println("getStore = " + getStore("Tom4"))

    println("================checkNumType======================")
    println("checkNumType = " + checkNumType(10))

    println("================forMethod======================")
    forMethod()

    println("================utilMethod======================")
    utilMethod()

    println("================downToMethod======================")
    downToMethod()

    println("================callPerson======================")
    callPerson()

    println("================callStudent======================")
    callStudent()


    println("================CellPhone======================")
    val cellPhone1 = CellPhone("Jack", 123.66)
    val cellPhone2 = CellPhone("Jack", 123.66)
    println("cellPhone1 equals cellPhone2 = " + (cellPhone1 == cellPhone2))
    println("cellPhone1 equals cellPhone2 = " + (cellPhone1.equals(cellPhone2)))

    println("================listofTest======================")
    listofTest()

    println("================mutableListOfTest======================")
    mutableListOfTest()

    println("================muatorTest======================")
    muatorTest()

    println("================setofTest======================")
    setofTest()

    println("================mutableSetOfTest======================")
    mutableSetOfTest()

    println("================mapofTest======================")
    mapofTest()

    println("================mutableMapOfTest======================")
    mutableMapOfTest()

    println("================maxLengthTest======================")
    maxLengthTest()

    println("================toUpperCaseTest======================")
    toUpperCaseTest()

    println("================filterTest======================")
    filterTest()

    println("================anyAndAllTest======================")
    anyAndAllTest()

    println("================strTest======================")
    strTest()
    printParam(num = 66, str = "100")
    printParam(num = 88)

    println("================HashMap======================")
    val map: HashMap<Int, String> = HashMap<Int, String>(4)
    // 往HashMap中添加键值对
    map.put(1, "One")
    map.put(2, "Two")
    map.put(3, "Three")
    map.put(4, "Four")

    // 添加一个导致碰撞的键
    map.put(5, "Five") // 这会与键1发生碰撞

    // 显示HashMap中的所有键值对
    for (key in map.keys) {
        println("键: " + key + ", 值: " + map.get(key))
    }

}



