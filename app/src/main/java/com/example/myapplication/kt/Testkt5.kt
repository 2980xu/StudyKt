package com.example.myapplication.kt

import java.io.File

fun main() {
    println("=============集合配合解构语法===================")
    //集合配合解构语法
    val list1 = listOf("1", "2", "3", "4")
    val (v1, v2, v3) = list1
    println("v1: ${v1} v2: ${v2} v3: ${v3}")
    //筛选 _相当于拒收赋值的意思
    val (_, n2, n3) = list1
    println("n2: ${n2} n3: ${n3}")

    println("=============getOrElse用法===================")
    //getOrElse用法
    println(list1.getOrElse(1) {
        "数组越界了"
    })
    println(list1.getOrElse(4) {
        "数组越界了"
    })

    println("=============getOrNull用法===================")
    //getOrNull用法
    println(list1.getOrNull(3))
    println(list1.getOrNull(4) ?: "数组越界了😔")
    println("================================")

    println("=============toMutableList===================")
    val list2: MutableList<String> = list1.toMutableList()
    list2.add("5")
    list2.remove("3")
    println("list2: $list2")

    println("=============toList===================")
    val list3: List<String> = list2.toList()
    println("list3: $list3")

    println("=============setof集合不会出现重复元素===================")
    //setof集合不会出现重复元素
    val list4 = setOf("1", "2", "3", "1")
    println("list4: $list4")
    println(list4.elementAt(0)) //相当于list[0]
    println(list4.elementAt(1))
    println(list4.elementAt(2))
    println(list4.elementAtOrElse(3) {
        "越界了😔"
    })
    println(list4.elementAtOrNull(4) ?: "你越界了😕")

    println("=============mutableSetOf===================")
    val list45 = mutableSetOf("1", "2", "3", "4")
    list45 += "5"
    list45 += "6"
    list45 -= "3"
    list45.add("7")
    list45.remove("1")
    println("list45: $list45")

    println("=============mutableListOf===================")
    val list5 = mutableListOf("1", "2", "3", "4", "2", "2", "2", "2", "2", "2")
    println("list5: $list5")

    println("=============toSet===================")
    //list转set去重
    val list6 = list5.toSet()
    println("list6: $list6")

    println("=============distinct===================")
    println("list5: $list5")
    //快捷函数去重
    val list7 = list5.distinct()
    println("list7: $list7")

//    IntArray           intArrayOf
//    DoubleArray        doubleArrayOf
//    LongArray          longArrayOf
//    ShortArray         shortArrayOf
//    ByteArray          byteArrayOf
//    FloatArray         floatArrayOf
//    BooleanArray       booleanArrayOf
//    Array<对象类型>     arrayOf对象数组
    println("=============intArrayOf===================")
    val intArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
    println(intArray[0])

    println("=============elementAtOrElse===================")
    println(intArray.elementAtOrElse(10) {
        -1
    })

    println("=============elementAtOrNull===================")
    println(intArray.elementAtOrNull(100) ?: "越界了😄")

    println("=============toIntArray===================")
    //list集合转数组
    val array = listOf(1, 2, 3).toIntArray()
    println(array[0])

    println("=============arrayOf对象数组===================")
    //arrayOf对象数组
    val objArray: Array<File> = arrayOf(File("aaaa"), File("bbb"), File("ccc"))
    println(objArray[0])

    println("=============mapOf===================")
    //map
    val map1: Map<String, Int> = mapOf("wangjx" to (123), "wangjinxu" to 456)
    println(map1.get("wangjx"))
    println(map1["wangjinxu"])
    println(map1["wangjinxu123"])
    val map2 = mapOf(Pair("wangjx", 789), Pair("wangjinxu", 77777))
    println(map2.get("wangjinxu"))

    println("=============getOrDefault===================")
    println(map1.getOrDefault("wangjinxu456", 999999))

    println("=============getOrElse===================")
    println(map1.getOrElse("wangjinxu456") {
        "没有找到😭"
    })

    //map不要使用getValue,如果找不到会出现崩溃
    //println(map1.getValue("wangjinxu456"))

    //不可变map遍历
    val map3 = mapOf(Pair("a", 123), Pair("b", 456), "c" to 789)
    println("=============forEach第一种方式遍历===================")
    //第一种方式遍历：
    map3.forEach { (key, value) ->
        println("不可变第一种方式： key: $key value: $value")
    }

    println("=============forEach第二种方式遍历===================")
    //第二种方式遍历：
    map3.forEach {
        println("不可变第二种方式： key: ${it.key} value: ${it.value}")
    }

    println("=============forEach第三种方式遍历===================")
    //第三种方式遍历：
    map3.forEach {
        println("不可变第三种方式： $it")
    }

    println("=============forEach第四种方式遍历===================")
    //第四种方式遍历：
    for (item: Map.Entry<String, Int> in map3) {
        println("不可变第四种方式： $item")

    }

    println("=============mutableMapOf===================")
    //可变map遍历
    val map4 = mutableMapOf(Pair("a", 123), Pair("b", 456), "c" to 789)
    map4.put("d", 123)
    map4 += "e" to 478
    map4 -= "b"
    map4["f"] = 44444
    println(map4)

    println("=============getOrPut===================")
    //getOrPut用法  如果map内没有g则在map集合内添加"g" 6666
    val putMap = map4.getOrPut("g") {
        6666
    }
    println(putMap)

    println("=============getOrPut===================")
    //getOrPut用法  如果map内有a则在map中直接获取value
    val updateMap = map4.getOrPut("a") {
        7777
    }
    println(updateMap)
    println(map4)
}