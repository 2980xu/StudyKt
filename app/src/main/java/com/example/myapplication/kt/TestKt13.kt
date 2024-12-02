package com.example.myapplication.kt

//生产者out 协变
//P只能被读取不能被修改
interface Producer<out P> {
    //不能被修改
//    fun showProduct(info : P)
    //只能被读取
    fun getInfo(): P
}

//消费者in 逆变
//C只能被修改不能被读取
interface Consumer<in C> {
    //只能被修改
    fun showProduct(info: C)
    //不能被读取
//    fun getInfo() : C
}

//不变
//T即能被修改又能被读取
interface AllInfo<T> {
    //能被修改
    fun showAllInfo(info: T)

    //能被读取
    fun getAllInfo(): T

}

open class Animal
open class Human : Animal()
open class Man : Human()
open class Woman : Human()

//===============生产者======================
class ProducerClass1 : Producer<Animal> {
    override fun getInfo(): Animal {
        println("getInfo(): Animal")
        return Animal()
    }
}

class ProducerClass2 : Producer<Human> {
    override fun getInfo(): Human {
        println("getInfo(): Human")
        return Human()
    }
}

class ProducerClass3 : Producer<Man> {
    override fun getInfo(): Man {
        println("getInfo(): Man")
        return Man()
    }
}

class ProducerClass4 : Producer<Woman> {
    override fun getInfo(): Woman {
        println("getInfo(): Woman")
        return Woman()
    }
}


//===============消费者======================
class ConsumerClass1 : Consumer<Animal> {
    override fun showProduct(info: Animal) {
        println("showProduct() Animal: $info")
    }
}

class ConsumerClass2 : Consumer<Human> {
    override fun showProduct(info: Human) {
        println("showProduct() Human: $info")
    }
}

class ConsumerClass3 : Consumer<Man> {
    override fun showProduct(info: Man) {
        println("showProduct() Man: $info")
    }
}

class ConsumerClass4 : Consumer<Woman> {
    override fun showProduct(info: Woman) {
        println("showProduct() Woman: $info")
    }
}


class AllInfoClass1 : AllInfo<Animal> {
    override fun showAllInfo(info: Animal) {
        println("showAllInfo() Animal: $info")
    }

    override fun getAllInfo(): Animal {
        println("getAllInfo(): Animal")
        return Animal()
    }
}

class AllInfoClass2 : AllInfo<Human> {
    override fun showAllInfo(info: Human) {
        println("showAllInfo() Human: $info")
    }

    override fun getAllInfo(): Human {
        println("getAllInfo(): Human")
        return Human()
    }
}

class AllInfoClass3 : AllInfo<Man> {
    override fun showAllInfo(info: Man) {
        println("showAllInfo() Man: $info")
    }

    override fun getAllInfo(): Man {
        println("getAllInfo(): Man")
        return Man()
    }
}

class AllInfoClass4 : AllInfo<Woman> {
    override fun showAllInfo(info: Woman) {
        println("showAllInfo() Woman: $info")
    }

    override fun getAllInfo(): Woman {
        println("getAllInfo(): Woman")
        return Woman()
    }
}

//SeteInClass类里面所有成员 跟泛型相关的： 只能被修改不能被读取
class SetInClass<in T> {
    fun setFun1(type: T) {
        println("setFun1: $type")
    }

    fun setFun2(type: T) {
        println("setFun2: $type")
    }

//    fun getTypeFun():T? {
//        return null
//    }
}

//GetOutClass类里面所有成员 跟泛型相关的： 只能被读取不能被修改
class GetOutClass<out T>(val item: T) {
    fun getFun1(): T {
        println("getFun1:")
        return item
    }

    fun getFun2(): T {
        println("getFun2:")
        return item
    }

//    fun setTypeFun(type: T) {
//      println("setTypeFun: $type")
//    }
}

fun main() {
    //泛型默认情况下是不可以，将泛型的子类对象 赋值给 泛型的父类对象
    //out: 泛型的子类对象 可以赋值给 泛型的父类对象
    val p1: Producer<Animal> = ProducerClass1()
    p1.getInfo()
    val p2: Producer<Animal> = ProducerClass2()
    p2.getInfo()
    val p3: Producer<Animal> = ProducerClass3()
    p3.getInfo()
    val p4: Producer<Animal> = ProducerClass4()
    p4.getInfo()

    //java代码示例
    //? extends就相当于Kt中的out
    //List<? extends CharSequence> list = new ArrayList<String>();
    //上面Producer去掉out就会报错
    println("====================================================")

    //泛型默认情况下是不可以，将泛型的父类对象 赋值给 泛型的子类对象
    //out: 泛型的父类对象 可以赋值给 泛型的子类对象
    val c1: Consumer<Man> = ConsumerClass1()
    c1.showProduct(Man())
    val c2: Consumer<Man> = ConsumerClass2()
    c2.showProduct(Man())
    val c3: Consumer<Man> = ConsumerClass3()
    c3.showProduct(Man())
    val c4: Consumer<Woman> = ConsumerClass4()
    c4.showProduct(Woman())

    //java代码示例
    //? super就相当于Kt中的in
    //List<? super String> list = new ArrayList<CharSequence>();
    //上面Consumer去掉in就会报错
    println("====================================================")

    val a1: AllInfo<Animal> = AllInfoClass1()
    a1.showAllInfo(Animal())
    a1.getAllInfo()
    val a2: AllInfo<Human> = AllInfoClass2()
    a2.showAllInfo(Human())
    a2.getAllInfo()
    val a3: AllInfo<Man> = AllInfoClass3()
    a3.showAllInfo(Man())
    a3.getAllInfo()
    val a4: AllInfo<Woman> = AllInfoClass4()
    a4.showAllInfo(Woman())
    a4.getAllInfo()

    println("====================================================")
    //协变 out 父类 = 子类
    //逆变 in 子类 = 父类
    println("====================================================")

    val p11 = SetInClass<String>()
    p11.setFun1("1111")
    p11.setFun2("2222")

    val p22 = GetOutClass<String>("3333")
    println(p22.getFun1())
    println(p22.getFun2())


}