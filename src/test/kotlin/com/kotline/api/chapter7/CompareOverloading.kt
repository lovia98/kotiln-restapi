package com.kotline.api.chapter7

import org.junit.Test

/*
    비교연산자 오버로딩
 */
class CompareOverloading {

    class Person(val fisrtName: String, val lastName: String)

    @Test
    fun test() {

        val p1 = Person("han","juhee")
        val p2 = Person("kang", "hyun")

        /*
            class를 data로 설정하지 않아도 기본으로
            equals 제공 : 'Any' 객체로 부터 내려옴
         */
        p1.equals(p2)
    }

    //compareTo 메소드 구현하기
    class Beverage(val kind: String, val title : String) : Comparable<Beverage> {

        override fun compareTo(other: Beverage): Int {
            return compareValuesBy(this, other, Beverage::kind, Beverage::title)
        }

        override fun toString(): String {
            return "[kind=$kind, title=$title]"
        }
    }

    @Test
    fun test1() {

        val p1 = Beverage("coffee", "cafeMoca")
        val p2 = Beverage("tea", "english breakfirst")

        val list = listOf(p2, p1)

        println(list)
        println(list.sorted())
    }


}