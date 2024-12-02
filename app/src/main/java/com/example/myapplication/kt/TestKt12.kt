package com.example.myapplication.kt

//动态参数 vararg

class TestKt12Info<T>(vararg obj: T, var isRs: Boolean = true) {
    //out我们的T 只能被读取 不能被修改
    val objectArrays: Array<out T> = obj

    fun showArray(index: Int): T? = objectArrays[index].takeIf { isRs }

    fun <o> mapObj(index: Int, mapAction: (T?) -> o) =
        mapAction(objectArrays[index].takeIf { isRs })

    //运算符重载
    operator fun get(index: Int): T? = objectArrays[index].takeIf { isRs }
}

class PointClass(var x: Int, var y: Int) {
    // 重载 '+' 操作符，实现两个 Point 对象的相加
    operator fun plus(other: PointClass): PointClass {
        return PointClass(x + other.x, y + other.y)
    }

    // 重载 '-' 操作符，实现 Point 对象的相减
    operator fun minus(other: PointClass):PointClass{
        return PointClass(x - other.x, y - other.y)
    }

    // 重载 '-' 操作符，实现 Point 对象的取反
    operator fun unaryMinus(): PointClass {
        return PointClass(-x, -y)
    }

    override fun toString(): String {
        return "PointClass($x, $y)"
    }
}

fun <INPUT> showInput(vararg obj: INPUT, index: Int) {
    var objArray: Array<out INPUT> = obj
    objArray.forEach {
        println("obj: ${it} length: ${it.toString().length}")
    }
    println((objArray[index] as String?)?.length ?: "啥也不是🤬")
}

fun main() {
    val p1 = TestKt12Info("wangxiaoxun", 123, true, 'a', null, isRs = true)
    println(p1.showArray(0))
    println(p1.showArray(1))
    println(p1.showArray(2))
    println(p1.showArray(3))
    println(p1.showArray(4))

    val r = p1.mapObj(4) {
        it
        it.toString()
        it.toString().length
    }
    println("r: $r")
    println("====================================")

    showInput("wangxiaoxun😄😄", 123, null, index = 2)
    println("====================================")

    //[]运算符学习
    val p2 = TestKt12Info("123", 456, 789)
    println(p2[2])
    println("====================================")

    val p1class = PointClass(1,5)
    val p2class = PointClass(2,9)
    println("sum: ${p1class + p2class}")
    println("minus: ${p1class - p2class}")
    println("unaryMinus: ${-p1class}")
    println("====================================")

}