package com.example.myapplication.kt

//重命名扩展
import com.example.myapplication.test.randomprintln as gg

class TestKt15(val name: String, val age: Int, val info: String)

//普通类上增加扩展函数
fun TestKt15.show() {
    println("show name: $name, age: $age, info: $info")
}

//String类上增加扩展函数
fun String.addExcetion(num: Int) = this + "@".repeat(num)

//超类上增加扩展函数
data class ResponseResult(val result: String, val code: Int)

fun Any.showInfo() = println("any is $this")

fun Any.showInfo2(): Any {
    println("any is $this")
    return this
}

//泛型增加扩展函数
fun <T> T.showT() = println("${if (this is String) "你是string长度是$length" else "this is $this"}")

fun testFunc() {}

//内置增加扩展函数
inline fun <I, O> I.mlet(lambda: (I) -> O) = lambda(this)

inline fun <I> I.mapply(lambda: I.() -> Unit): I {
    lambda(this)
    return this
}

//属性增加扩展
val String.strInfo: String
    get() = "11111"

//可空类型增加扩展
fun String?.outputType(default: String) {
    println(this ?: default)
}

//中缀表达式infix
infix fun <C1, C2> C1.gogogo(c2: C2) {
    println("C1: $this")
    println("C2: $c2")
}

//DSL领域专用语言
class Context() {
    val name = "wangxiaoxun"
    fun toast(str: String) {
        println(str)
    }
}

inline fun Context.apply2(lambda: Context.(String) -> Unit): Context {
    lambda("😄😄😄")
    return this
}


//变换函数 map
fun showMap() {
    val list = listOf("list1", "list2", "list3", "list4")
    val list1 = list.map {
        "$it"
        88
    }.map {
        "123"
    }.map {
        it.length
    }
    println(list1)
}

//变换函数 flatmap相当于List<List<String>>
fun showFlatMap() {
    val list = listOf("list1", "list2", "list3", "list4")
    val list1 = list.flatMap {
        listOf("hello1: $it", "hello2: $it", "hello3: $it")
    }
    println(list1)
}

//过滤函数filter
fun showFilter() {
    val numlist = listOf(
        listOf("1", "2", "3"),
        listOf("41", "5", "6"),
        listOf("7", "81", "9")
    )
    val newList = numlist.flatMap { it ->
        //进来了三次
        it.filter {
            //进来了九次
            println("过滤函数filter: $it")
            //true加入到新的集合
            //false过滤掉
            it.contains("1")
        }
    }.map {
        print("$it ")
    }
}

//合并函数zip
fun showZip() {
    val names = listOf("张三", "李四", "王五")
    val ages = listOf(21, 31, 41)
    val info = listOf("😄", "😭", "愤怒")
//    var p1 : List<Pair<Pair<String, Int>,String>> = names.zip(ages).zip(info)
    var p1: List<Pair<String, Int>> = names.zip(ages)
    println(p1)


    p1.forEach {
        println("name: ${it.first} age: ${it.second}")
    }

}


fun main() {
    val p1 = TestKt15("wangxiaoxun", 23, "😄")
    p1.show()
    println("==================================")

    println("wangxiaoxun".addExcetion(3))
    println("==================================")

    "wangxiaoxun".showInfo()
    println("==================================")

    ResponseResult("😄", 202).showInfo()
    println("==================================")

    "🤬🤬🤬".showInfo2().showInfo2().showInfo2()
    println("==================================")

    "wangxiaoxun".showT()
    123.showT()
    'a'.showT()
    456.6.showT()
    false.showT()
    testFunc().showT()
    println("==================================")

    "wangxiaoxun".mlet {
        println("it: $it")
        666
    }
    println("==================================")

    val str: String = "1223"
    println(str.strInfo)
    println("==================================")

    val str1: String? = null
    str1.outputType("😭😭😭")
    println("==================================")

    //下面是map自带的中缀表达式
    mapOf("一" to (1))
    mapOf("二" to 2)

    //下面是自定义的中缀表达式
    "三" gogogo 3
    println("==================================")

    //扩展文件
    val list1 = listOf("1", "3", "2", "6")
    val set1 = setOf("1", "3", "2", "6")

//    println(list1.randomprintln())
//    println(set1.randomprintln())

    //重命名扩展 修改引包
    println(list1.gg())
    println(set1.gg())
    println("==================================")

    "str".mapply {
        this
        println("str: $this")
    }
    println("==================================")

    Context().apply2 {
        //this 相当于Context
        // it 相当于传入的String参数
        toast(this.name)
        toast("wangjinxu")
        toast(it)
        toast(name)
    }
    println("==================================")

    showMap()
    println("==================================")

    showFlatMap()
    println("==================================")

    showFilter()
    println("==================================")

    showZip()

}