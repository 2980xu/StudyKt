package com.example.myapplication.kt

//单例：饿汉式
object TestKt16 {
    fun showTestKt16() {
        println("showTestKt16")
    }
}

//单例：懒汉式
class TestKt16s() {
    companion object {
        private var instance: TestKt16s? = null
            get() {
                if (field == null) {
                    field = TestKt16s()
                }
                return field
            }

        @Synchronized
        fun getInstances() = instance!!
    }

    fun showTestKt16s() {
        println("TestKt16s")
    }
}

//单例：懒汉式(双重锁)
class TestKt16ss private constructor() {
    companion object {
        val instance: TestKt16ss by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TestKt16ss()
        }
    }

    fun showTestKt16ss() {
        println("TestKt16ss")
    }
}

fun main() {
    TestKt16.showTestKt16()
    println("===============================")

    TestKt16s.getInstances().showTestKt16s()
    println("===============================")

    TestKt16ss.instance.showTestKt16ss()
}