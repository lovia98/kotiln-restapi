package com.kotlin.api.chapter8

import org.junit.Test
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock


class Lock : Lock {

    override fun lock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tryLock(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun tryLock(time: Long, unit: TimeUnit?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unlock() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun lockInterruptibly() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newCondition(): Condition {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/*
 * 인라인 함수 정의하기
 */
inline fun<T> synchronized(lock: Lock, action: () -> T) : T {

    lock.lock()

    try {
        return action()
    } finally {
        lock.unlock()
    }
}

class InlineTest {

    @Test
    fun test() {
        val l = Lock()

        synchronized(l) {
            //여기서 외부 변수인 l을 캡쳐링

            /* -> 람다 본문 내에서 매범 외부 변수를 접근을 위한 참조 변수를 생성하기 때문에
             *    메모리 낭비가 될 수 있지만, syncronized는 인라인이라 그런 낭비가 없다.
             */
        }
    }

    @Test
    fun test1() {

        fun foo(l: Lock) {
            println("Before sync")

            synchronized(l) {
                println("Action")
            }

            println("After sync")
        }
    }


    data class Person(val name: String, val age: Int)


    /*
     * filter 함수 : 람다식이라 성능이 오래 걸릴것 같지만, inline이라
     *              아래와 같은 for문과 성능차이가 없다.
     */
    @Test
    fun test2() {

        val people = listOf(
                Person("Alice", 20),
                Person("Bob", 31)
        )

        println(people.filter { it.age < 30 })
    }


    @Test
    fun test3() {

        val people = listOf(
                Person("Alice", 20),
                Person("Bob", 31)
        )

        val result = mutableListOf<Person>()

        for(person in people) {
            if(person.age < 30) result.add(person)
        }

        println(result)
    }
}