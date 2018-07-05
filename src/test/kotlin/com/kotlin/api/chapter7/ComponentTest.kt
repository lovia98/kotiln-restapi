package com.kotlin.api.chapter7

import org.junit.Test

/*
 * 구조 분해 선언
 */
data class MyPair(val x:Int, val y:Int)

class MyPair2(val x:Int, val y:Int) {
    operator fun component1() = x
    operator fun component2() = y
}


data class NameComponents(val name: String,
                          val extension: String)

fun splitFilenme(fullName: String) : NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}

class ComponentTest {

    @Test
    fun test() {
        val p = MyPair(10, 20)
        val (x, y) = p

        println(x)
        println(y)
    }

    @Test
    fun test1() {
        val (name, ext) = splitFilenme("exmaple.kt")
        println(name)
        println(ext)
    }

    /*
     * 구조 분해 선언을 사용해 맵 이터레이션 하기
     */
    fun printEntries(map: Map<String, String>) {
        for((key, value) in map) {
            println("$key -> $value")
        }
    }

    @Test
    fun test2() {
        val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
        printEntries(map)
    }
}