package com.example.myapplication.kt

data class ObjectClass1(val name: String, val age: Int, val info: String)
data class ObjectClass2(val name: String, val age: Int, val info: String)
data class ObjectClass3(val name: String, val age: Int, val info: String)

class TypeClass {
    inline fun <reified T> randomOrDefault(defaultLambdaAction: () -> T): T {
        val listObject: List<Any> = listOf(
            ObjectClass1("wangxiaoxun1", 110, "😄"),
            ObjectClass2("wangxiaoxun2", 111, "😮"),
            ObjectClass3("wangxiaoxun3", 112, "🤬")
        )
        val randomObj: Any? = listObject.shuffled().first()
        println("随机产生的对象是: $randomObj")

        return randomObj.takeIf { it is T } as T? ?: defaultLambdaAction()
    }
}

fun main() {
    val finalInfo = TypeClass().randomOrDefault<ObjectClass2> {
//        ObjectClass3("wangxiaoxun3", 112, "🤬")
        ObjectClass2("wangxiaoxun2", 111, "😮")
    }
    println("finalInfo: $finalInfo")

}