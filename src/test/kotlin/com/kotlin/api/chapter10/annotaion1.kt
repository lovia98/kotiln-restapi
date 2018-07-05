package com.kotlin.api.chapter10

annotation class Suspendable

class Fiber {
    fun sleep(int : Int) {}
}

val f = @Suspendable { Fiber().sleep(10) }

