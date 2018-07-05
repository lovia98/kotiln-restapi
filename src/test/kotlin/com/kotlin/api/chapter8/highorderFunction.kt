package com.kotlin.api.chapter8

import org.junit.Test

class HighOrderFunction {


    @Test
    fun one() {

        val sum = {x: Int, y: Int -> x+y}
        println(sum(3,4))

        val sum2:(x: Int, y:Int) -> Int = {x,y -> x+y}
        println(sum2(3,4))
    }


    fun performRequest(
            url: String,
            callback: (code: Int, content: String) -> Unit
    ) {

        callback(123, "ColcoGroup")
    }

    @Test
    fun test() {
        val url = "http://kotl.in"
        performRequest(url){code: Int, content: String ->println("$code - $content")}
    }

    fun twoAndThree(operation: (Int, Int) -> Int) {
        val result = operation(2,3)
        println("The result is $result")
    }

    @Test
    fun test1() {
        twoAndThree{a,b -> a+b}
        twoAndThree{a,b -> a*b}
    }

    /*
     * String에 대한 filter 함수 만들기
     */
    fun String.filter(predicate: (Char) -> Boolean) :String {

        val sb = StringBuilder()
        for(index in 0 until length) {
            val element = get(index)
            if(predicate(element)) sb.append(element)
        }

        return sb.toString()
    }

    @Test
    fun test2() {
        println("juhee".filter{it == 'e'})
        println("juhee".filter{it > 'e'})
    }

    fun processTheAnswer(f:(Int)->Int) {
        println(f(42))
    }
}