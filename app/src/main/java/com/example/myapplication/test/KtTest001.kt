package com.example.myapplication.test

//扩展文件
fun <T> Iterable<T>.randomprintln() = this.shuffled().first()