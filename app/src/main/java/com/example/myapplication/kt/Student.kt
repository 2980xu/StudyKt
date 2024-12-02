package com.example.myapplication.kt

class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age) ,Study{
    var snos = sno
    var grades = grade
    override fun readBooks() {
        println(name + " readBooks")
    }

    override fun doHomeWork() {
        println(name + " doHomeWork")
    }
}