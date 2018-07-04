package com.kotline.api.chapter7

import org.junit.Test

/*
 * 연산자 오버로딩
 *
 * 자바와 같이 코틀린에서도 어떤 언어 기능이 정해진 사용자 작성함수와 연결되는 경우가 몇 가지 있다.
 * 하지만 코틀린에서는 이런 언어 기능이 어떤타입(클래스)와 연관되기 보다는 특정 함수 이름과 연관된다.
 *
 * 예를 들어 plus라는 이름의 특별한 메소드를 정의하면 그 클래스의 인스턴스에 대해 + 연산자를 사용할 수 있다.
 *
 * 이런 식으로 어떤 언어 기능과 미리 정해진 이름의 함수를 연결해주는 기법을 코틀린에서는 관례(convention)라고 부른다.0
 */
data class Point(val x:Int, val y:Int){
    //연산자 오버로딩
    operator fun plus(other:Point) :Point {
        return Point(x+other.x, y+other.y)
    }
}

class ArithMeticTest {

    @Test
    fun test1() {
        oper1()
    }

    /*
     * 연산자 오버로딩을 통해 일반 객체를 + 기호로 더할 수 있다.
     */
    fun oper1() {
        val p1 = Point(10,20)
        val p2 = Point(30, 40)

        println(p1+p2)

    }

    @Test
    fun test2() {
        oper2()
    }

    /*
     * 연산자를 확장 함수로 정의하기 <-- 이 구현도 위 클래스 내부의 연산자 오버로딩 함수와 똑같은 기능
     */
     operator fun Point.plus(other: Point) : Point {
        return Point(x + other.x, y+other.y)
    }

    //그럼 다른 객체로 해보자..
    data class Name(val name: String)
    operator fun Name.plus(other: Name) : Name {
        return Name("$name ${other.name}")
    }

    fun oper2() {
        val firstName = Name("Han")
        val lastName = Name("juhee")

        println(firstName+lastName)
    }

    /*
     * 타입이 다른 두개의 객체의 연산자 정의하기
     */
    operator fun Point.times(scale: Double) : Point{
        return Point((x*scale).toInt(), (y*scale).toInt())
    }

    @Test
    fun test3() {
        val p = Point(10, 20)
        println(p*1.5)
    }

    /*
     * Point * Double 함수는 있지만 컴파일이 자동으로 둘을 바꿔서 연산해주진 않는다. (Double * Point)
     * Double* Point를 하고 싶다면 별도로 구현해야 한다.
     */
     operator fun Double.times(p:Point) : Point
            = Point((this * p.x).toInt(), (this * p.y).toInt())

    /*
     * 결과 타입이 피연산자 타입과 다른 연산자 정의하기
     */
     operator fun Char.times(count: Int) : String
            = toString().repeat(count)

    @Test
    fun test4() {
        println('a' * 3)
    }

    /*
     * 복합 대입 연산자 오버로딩
     *
     *  위에서와 같이 +를 오버로딩 하면 +뿐 아니라 +=와 같은 복합대입 연산자도 자동으로 지원해 준다.
     */
    @Test
    fun test5() {
        var point = Point(1,2)
        point += Point(3,4)

        println(point)
    }

    /*
        collection에 element를 추가 하는 용도로 쓰이는 plus
    */
    @Test
    fun test6() {
        val numbers = ArrayList<Int>()
        numbers += 42
        println(numbers[0])
    }
}