package com.example.myapplication.kt

//主构造函数：规范来说：_XXX，属于临时输入类型，不能直接使用，需要接收下来成为变量才可以使用
class TestKt8(_name: String, _age: Int, _num: Int) {
    var name = _name
        get() = field //get不可以私有化
        private set(value) {
            field = value
        }

    fun outPutField() {
        println(name)
    }

}

/*
*相当于：
* class TestKt88(_name: String, _age: Int, _num: Int) {
    var name = _name
    val age = _age
    var num = _num
    fun outPutField() {
        println(name)
        println(age)
        println(num)
    }

}
*
 */

class TestKt88(var name: String, val age: Int, var num: Int) {

    fun outPutField() {
        println(name)
        println(age)
        println(num)
    }

}

class TestKt888(name: String = "wangxiaoxun") {
    init {
        println("主构造函数被调用")
        //校验的作用
        require(name.isNotBlank()) {
            "name is null"
        }
    }

    val name = name

    //次构造必须调用主构造
    constructor(name: String, age: Int) : this(name) {
        println("二个构造函数 name: $name age: $age")
    }

    constructor(name: String, age: Int, num: Int) : this(name) {
        println("三个构造函数 name: $name age: $age num: $num")
    }

    constructor(name: String, age: Int, num: Int, info: String) : this(name) {
        println("四个构造函数 name: $name age: $age num: $num info: $info")
    }

    fun outPutField() {
        println(name)
    }

}

//第一步：执行val age初始化
class TestKt8888(_name: String, val age: Int) {

    //第二步：执行_name赋值
    val mName = _name

    //第三步：执行主构造函数init
    //注：第二步和第三步是同时执行，只不过类成员变量在init上面
    init {
        println("TestKt8888 init")
    }

    constructor(name: String) : this(name, 666) {
        //第五步：执行次构造函数
        println("name: $name")
    }

    //第四步：执行info赋值
    val info = "AAAA"
}

class TestKt88888() {
    //延时初始化
    lateinit var name: String

    fun setName() {
        name = "wangxiaoxun"
        println("name: $name")
    }

    fun showName() {
        //如果name采用lateinit，没有初始化上来就使用会出现报错
//        println("name: $name")
        if (::name.isInitialized) {
            println("name isInitialized true: $name")
        } else {
            name = "😊"
            println("name isInitialized false: $name")

        }
    }

}


class TestKt888887() {
    val name = requestName()

    val name1 by lazy { requestName() }

    fun requestName() : String {
        println("开始加载============")
        println("加载中💕==============")
        println("加载中💕==============")
        println("加载中💕==============")
        println("加载中💕==============")
        println("加载中💕==============")
        return "wangxiaoxun"
    }
}


fun main() {

    val sss = TestKt8("wangjinxu", 30, 666)
//    sss.name = "wangxiaoxun"
    sss.outPutField()
    println("===========================")

    TestKt88(name = "wangxiaoxun", num = 29, age = 777).outPutField()
    println("===========================")

    TestKt888("wjx0").outPutField()
    println("===========================")

    TestKt888("wjx1", 1).outPutField()
    println("===========================")

    TestKt888("wjx2", 2, 777).outPutField()
    println("===========================")

    TestKt888("wjx3", 3, 777, "hello kt").outPutField()
    println("===========================")

    TestKt888("1111")
    println("===========================")

    TestKt8888("wjx4")//调用构造代码块
    //转成java代码之后调用顺序
    //第一步：this.age = age;
    //第二步：this.mName = _name;
    //第三步：String var3 = "TestKt8888 init";
    //第四步：System.out.println(var3);
    //第五步：this.info = "AAAA";
    println("===========================")

    TestKt888() //先调用主构造函数，再调用次构造
    println("===========================")

    //采用懒初始化方式
    val p = TestKt88888()
    Thread.sleep(5000)
    p.setName()
    p.showName()
    println("===========================")

    //采用普通方式
//    val p1 = TestKt888887()
//    Thread.sleep(5000)
//    println("开始展示😄")
//    println(p1.name)
//    println("===========================")

    //采用惰性初始化方式:只有使用得时候才去初始化
    val p2 = TestKt888887()
    Thread.sleep(5000)
    println("开始展示😄")
    println(p2.name1)



}