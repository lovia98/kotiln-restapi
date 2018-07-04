package com.kotline.api.chapter9

import org.junit.Test

class genericTest {

    val <T> List<T>.penultimate: T
        get() = this[size-2]

    @Test
    fun test() {

        println(listOf(1,2,3,4).penultimate)
    }


    interface MyList<T> {
        operator fun get(index: Int) : T
    }

    class StringList: MyList<String> {
        override fun get(index: Int) : String { return "" }
    }

    class MyArrayList<T>: MyList<T> {
        override fun get(index: Int): T {
            return get(index)
        }
    }

    /*
     * 타입 파라미터 제약
     */
    fun <T: Number> List<T>.get0() : T = get(0)

    @Test
    fun test1() {
        println(listOf(1, 2, 3).get0())

        println(listOf(0.1, 2.3, 3.4).get0())

        //println(listOf("1", "2").get0())
    }

    fun <T: Comparable<T>> max(first: T, second: T) : T {
        return if(first > second) first else second
    }

    @Test
    fun test2() {
        println(max("kotlin", "java"))
    }

    fun <T> ensureTrailingPeriod(seq: T)
        where T : CharSequence, T : Appendable {

        if(! seq.endsWith('.')) {
            seq.append('.')
        }
    }

    @Test
    fun test3() {

        val helloWorld = StringBuilder("Hello World")
        ensureTrailingPeriod(helloWorld)
        println(helloWorld)
    }

    /*
     *
     */
    inline fun <reified T> isA(value: Any) = value is T
}