package com.kotlin.api.onlintry

import org.junit.Test
import java.util.*

class Introduction {

    fun toJSON(collection: Collection<Int>): String {

        val sb = StringBuilder()

        sb.append("[")

        for((index,ele) in collection.withIndex()) {

            sb.append(ele)
            if(index < collection.size-1) sb.append(", ")
        }

        sb.append("]")

        return sb.toString()
    }


    @Test
    fun test() {
        println(toJSON(listOf(1, 2, 3, 42, 555)))
    }

    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

    fun getPattern(): String = """\d{2} ${month} \d{4}"""

    @Test
    fun test1() {
        println(getPattern())
    }

    interface Expr
    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr

    fun getList(): List<Int> {
        val arrayList = arrayListOf(1, 5, 2)
        Collections.sort(arrayList, object : Comparator<Int> {
            override fun compare(o1: Int, o2: Int): Int {
                return o2-o1
            }
        })
        return arrayList
    }

    @Test
    fun test2() {
        println(getList())
    }

    @Test
    fun test3() {
        println(listDesendingSort())
    }


    fun listDesendingSort() :List<Int>{

        return arrayListOf(1, 5, 2).sortedDescending()
    }

}