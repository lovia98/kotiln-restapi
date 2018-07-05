package com.kotlin.api.chapter8

import org.junit.Test

class AnonymousFunction {

    data class Person(val name: String, val age: Int)

    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    /*
     * forEach문에 익명 함수를 던져서 로컬 return 만들기
     */
    fun lookForAlice(people: List<Person>) {

        people.forEach(fun (person){
            if(person.name == "Alice") {
                println("${person.name} is not Alice")
            }
        })
    }

    @Test
    fun test() {
        lookForAlice(people)
    }

    /*
     * filter에 익명함수 넘기기
     */
    @Test
    fun test1() {
        people.filter(fun(person) : Boolean {
            return person.age < 30
        })
    }

    /*
     * 식이 본문인 익명 함수 사용하기 (return 생략)
     */
    @Test
    fun test2() {
        people.filter(fun(person) = person.age < 30)
    }
}