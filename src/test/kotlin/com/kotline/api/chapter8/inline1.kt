package com.kotline.api.chapter8

import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader

class InLineTest1 {


    /*
     * use 함수를 자원 관리에 활용하기
     */
    fun readFirstLineFromFile(path: String) : String {

        BufferedReader(FileReader(path)).use {
            /*
             * 여기서 return은 람다 함수내에서 실행 되는 것이 아니라
             * readFirstLineFromFile 함수를 호출 할때 실행 된다.
             */
            br-> return br.readLine()
        }
    }

    data class Person(val name: String, val gae: Int)

    val people = listOf(Person("Alice", 29), Person("Bob", 31))


    /*
     * 넌로컬(non-local) return:
     *  자신을 둘러싸고 있는 블록보다 더 바깥에 있는 다른 블록을
     *  반환하게 만드는 return문을 넌로컬 return 이라고 함.
     */
    fun lookForAlice(people: List<Person>) {

        for(person in people) {
            if(person.name == "Alice") {
                println("Found!")
                return // "Alice"를 만나면 lookForAlice함수를 빠져나가는 return문
            }
        }

        println("Alice is not found!")
    }

    @Test
    fun test() {
        lookForAlice(people)
    }

    /*
     * 넌로컬(non-local) return
     */
    fun lookForAliceWithForeach(people: List<Person>) {

        people.forEach {
            if(it.name == "Alice") {
                /*
                 * 역시 "Alice"를 만나면 lookForAlice함수를 빠져나가는 return문
                 * : forEach가 인라인 함수이기 때문이다.
                 */
                println("Found!")
                return
            }
        }

        println("Alice is not found!")
    }

    @Test
    fun test1() {
        lookForAliceWithForeach(people)
    }

    /*
     * 인라이닝 되지 않은 함수에서는 넌로컬 return이 안된다.
     */
    fun List<Person>.nonInline(f: (Person)->Unit) {
        for (element in this) f(element)
    }

    fun lookForAliceWithNonInline(people: List<Person>) {
        people.nonInline {
            if(it.name == "Alice") {

                println("Found!")
                //syntax error : 레이블을 쓰라는 오류 메세지가 뜸
                //return
            }
        }

        println("Alice is not found!")
    }

    /*
     * 레이블을 이용해서 inline 함수밖으로만 빠져나가는 return을 만들수 있다.
     * : 로컬 return (local return) 만들기
     */
    fun lookForAliceWithLable(people: List<Person>) {

        people.forEach label@{
            if(it.name == "Alice") return@label
        }

        println("Alice might be somewhere")
    }


    @Test
    fun test2() {
        lookForAliceWithLable(people)
    }

    /*
     * 함수 이름(foreach)을 return 레이블로 사용하기
     */
    fun lookForAliceWithLable2(people: List<Person>) {

        people.forEach {
            if(it.name == "Alice") return@forEach
        }

        println("Alice might be somewhere")
    }

    @Test
    fun test3() {
        lookForAliceWithLable2(people)
    }

    /*
     * 레이블이 붙은 this식
     */
    @Test
    fun test4() {
        println(StringBuilder().apply sb@ {
            listOf(1,2,3).apply {
                this@sb.append(this.toString())
            }
        })
    }
}