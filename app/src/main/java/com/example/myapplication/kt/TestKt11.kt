package com.example.myapplication.kt

class TestKt11(_info: String) {
    val mInfo = _info

    inner class InnerClass {
        fun runInnerClass() {
            //默认情况下内部的类不能访问外部的类，要增加修饰符（inner）才能成为内部类才可以访问外部类
            println("runInnerClass : $mInfo")
        }
    }

    inner class InnerClass1 {
        inner class InnerClass11 {
            fun runInnerClass11() {
                println("runInnerClass11 : $mInfo")
            }
        }

        inner class InnerClass12 {
            fun runInnerClass12() {
                println("runInnerClass12 : $mInfo")
            }
        }
    }
}

//嵌套类
//默认情况下：静态嵌套类
//特点：外部类可以访问内部的嵌套类成员
//     内部的嵌套类不能访问外部类成员
class OutClass(_info: String) {
    fun runOutClass() {
        println("runOutClass ")
    }

    class InnerClass {
        fun runInnerClass(info: String) {
            println("runInnerClass $info")

        }
    }
}

//普通类
class DataClass(var name: String, var age: Int, var info: String)
//解析之后包括：set,get,构造函数

//数据类
//使用条件：
//1.注册监听请求回调可以使用javabean
//2.数据类至少有一个参数的主构造函数
//3.参数必须有定义变量的关键字var val
//4.数据类不能使用abstract， open， sealed，inner等等修饰
//需求比较， copy， toString，解构这种复杂功能时也可以使用数据类

data class DataClass1(var name: String, var age: Int, var info: String)
//解析之后包括：set,get,构造函数，解构函数，copy，tostring，hashcode，equals


data class DataClass11(var name: String, var info: String) {
    var mName: String = ""

    init {
        println("主构造函数")
    }

    constructor(name: String) : this(name, "123") {
        mName = name
        println(name)
    }

    override fun toString(): String {
        return "name: $name info: $info mName: $mName"
    }
}

//普通类
class Student11(var name: String, var age: Int, var info: String) {

    //解构函数 顺序必须是component1 component2 component3 从1开始 与构造函数的入参一一对应
    operator fun component1() = name
    operator fun component2() = age
    operator fun component3() = info
}

//数据类
data class Student12(var name: String, var age: Int, var info: String) {

//    //解构函数 顺序必须是component1 component2 component3 从1开始 与构造函数的入参一一对应
//    operator fun component0() = name
//    operator fun component1() = name
//    operator fun component2() = age
//    operator fun component3() = info
}

//枚举类
enum class Week {
    星期一,
    星期二,
    星期三,
    星期四,
    星期五,
    星期六,
    星期日
}

class LimbsInfo(val name: String, val length: String) {
    fun show() {
        println("name: ${name} length: ${length}")
    }
}

//1.所有枚举值保持一致的效果
//2.枚举的主构造参数必须和枚举的参数保持一致
enum class Limbs(val limbsInfo: LimbsInfo) {
    LEFT_HAND(LimbsInfo("左手", "11")),
    RIGHT_HAND(LimbsInfo("右手", "12")),
    LEFT_FOOT(LimbsInfo("左脚", "11")),
    RIGHT_FOOT(LimbsInfo("右脚", "11"));

    fun show() = "枚举name： ${limbsInfo.name} 枚举length： ${limbsInfo.length}"
}

enum class Exams1 {
    Func1,
    Func2,
    Func3,
    Func4,
    Func5;
}

//密封类 成员必须有类型并且继承本类
sealed class Exams {
    object Func1 : Exams()
    object Func2 : Exams()
    object Func3 : Exams()
    object Func4 : Exams()
    class Func5(val names: String) : Exams()
    class Func6(val names: String) : Exams()
}

class Teacher(val exams: Exams) {
    fun show() = when (exams) {
        is Exams.Func1 -> "成绩不及格"
        is Exams.Func2 -> "成绩及格"
        is Exams.Func3 -> "成绩良好"
        is Exams.Func4 -> "成绩优秀"
        is Exams.Func5 -> "😄😄 name: ${(this.exams as Exams.Func5).names}"
        is Exams.Func6 -> "😄😄 name: ${(this.exams as Exams.Func6).names}"
    }
}


//接口
//1.接口里面所有成员和接口本身都是public open的，所以不需要open，这个是接口的特殊
//2.接口不能有主构造
//3.实现类不仅要重写接口的函数，也要重写接口的成员
//4.接口实现代码区域，全部都要增加override关键字来修饰
interface IUsbinterface {
    //接口成员变量是不可以动态赋值,可以使用get去赋值
    val name: String
        get() = "接口初始化"
    var type: String
    fun getInfo(): String
}

class UsbInfo : IUsbinterface {
    //    override var name: String = ""
//        get() = field
//        set(value) {
//            field = value
//        }
    override val name: String
        get() = super.name

    override var type: String = ""
        get() = field
        set(value) {
            field = value
        }

    override fun getInfo(): String {
        return "name: $name type: $type"
    }

}

abstract class BaseMusicInfo {
    fun onCreate() {
        println("BaseMusicInfo name: ${name()}")
        println("BaseMusicInfo age: ${age()}")
    }

    abstract fun name(): String
    abstract fun age(): String
}

class MusicInfo(var name: String, var age: String) : BaseMusicInfo() {
    override fun name(): String {
        println("MusicInfo name: $name")
        return this.name
    }

    override fun age(): String {
        println("MusicInfo age: $age")
        return this.age
    }

    fun show() {
        super.onCreate()
    }

}

class KtInfoBase<T>(val obj: T) {
    fun show() {
        println("万能输出器： $obj")
    }
}

class StudentInfo(var name: String, var age: String) {
    override fun toString(): String {
        return "name: $name age: $age"
    }

    fun showStudentInfo(): String {
        return "name: $name 😄"
    }
}

data class TeacherInfo(var name: String, var age: String)


class UniversalInfo<T>(val obj: T, val isR: Boolean = true) {
    fun getInfo() = obj.takeIf { isR }
}

fun <T> showInfo(vb: T) {
    vb.also {
        println("vb:$it")
    } ?: println("啥也不是🤬")
}

fun main() {
    val p = TestKt11("😄")
    p.InnerClass().runInnerClass()
    p.InnerClass1().InnerClass11().runInnerClass11()
    p.InnerClass1().InnerClass12().runInnerClass12()

    OutClass.InnerClass().runInnerClass("121")
//    静态嵌套类：可以直接通过 OutClass.InnerClass() 创建实例。
//    非静态内部类：需要先创建 OutClass 的实例，然后通过该实例创建 InnerClass 的实例。

    println("====================================")

    println(DataClass("wangxiaoxun", 123, "😄"))
    println(DataClass1("wangxiaoxun", 123, "😄"))

    println("====================================")

    var p11 = DataClass11("wangxiaoxun")
    println(p11)
    println("====================================")

    //toString,hashcode,copy，equals等等 只管主构造函数不管次构造函数
    val p12 = p11.copy("wangxiaoxun")
    println(p12)
    println("====================================")

    var (name, age, info) = Student11("wangxiaoxun", 123, "😄")
    println("Student11 name: $name age: $age info: $info")
    println("====================================")

    var (name12, age12, info12) = Student12("wangxiaoxun", 123, "😄")
    println("name12: $name12 age12: $age12 info12: $info12")
    println("====================================")

    var (_, age11, _) = Student11("wangxiaoxun", 234, "😄")
    println("age11: $age11")
    println("====================================")

    println(Week.星期一)
    println(Limbs.LEFT_FOOT.show())
    println("====================================")

    println(Teacher(Exams.Func4).show())
    println(Teacher(Exams.Func5("wangxiaoxun")).show())
    println(Teacher(Exams.Func6("wangxiaoxun1111111")).show())
    println("====================================")

    println(Exams.Func1 == Exams.Func1) //返回true 因为他们是object单例
    println(Exams.Func5("123") == Exams.Func5("123")) //返回false 因为他们是class
    println("====================================")

    val ps = UsbInfo();
//    ps.name = "USB2222"
    ps.type = "audio"
    println(ps.getInfo())
    println("====================================")

    val p123 = MusicInfo("wangxiaoxun", "123")
    p123.show()
    println("====================================")

    val p456 = p123 as BaseMusicInfo
    p456.onCreate()
    println("====================================")

    val p21 = StudentInfo("wangxiaoxun1", "11")
    val p22 = StudentInfo("wangxiaoxun2", "12")

    val p31 = TeacherInfo("wangjinxu1", "31")
    val p32 = TeacherInfo("wangjinxu2", "32")

    KtInfoBase(p21).show()
    KtInfoBase(p22).show()
    KtInfoBase(p31).show()
    KtInfoBase(p32).show()
    println("====================================")

    println(UniversalInfo(p21).getInfo() ?: "啥也不是🤬")
    println("====================================")

    UniversalInfo(p22, false).getInfo()?.let {
        println(it.showStudentInfo())
    } ?: println("啥也不是🤬🤬")
    println("====================================")

    showInfo("wangxiaoxun")
    showInfo(123)
    showInfo(null)

}