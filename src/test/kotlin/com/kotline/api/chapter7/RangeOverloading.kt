package com.kotline.api.chapter7

import org.junit.Test


/*
 * 범위에 대해 쓸수 있는 관례
 */

data class MutablePoint(var x: Int, var y: Int)

data class Rectagle(val uperLeft: Point, val lowerRight: Point)

class RangeOverloading {

    /*
     * get 관례 구현하기
     */
    operator fun Point.get(index: Int) : Int {
        return when(index) {
            0 -> x
            1 -> y
            else ->
                throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }

    @Test
    fun test1() {

        val p = Point(10,20)
        println(p[1])
    }

    /*
     * set 관례 구현하기
     */
    operator fun MutablePoint.set(index: Int, value: Int) {
        when(index) {
            0-> x = value
            1-> y = value
            else ->
                throw IndexOutOfBoundsException("Invalid coordinate $index")
        }
    }

    @Test
    fun test2() {
        val p = MutablePoint(10, 20)
        p[1] = 42

        println(p)
    }

    /*
     * in 관례
     */
    operator fun Rectagle.contains(p: Point) : Boolean{
        return p.x in uperLeft.x until lowerRight.x &&
                p.y in uperLeft.y until lowerRight.y
    }

    @Test
    fun test3() {
        val rect = Rectagle(Point(10, 20), Point(50, 50))

        println(Point(20, 30) in rect)
        println(Point(5, 5) in rect)
    }


}