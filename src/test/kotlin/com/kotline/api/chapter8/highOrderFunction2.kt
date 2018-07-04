package com.kotline.api.chapter8

import org.junit.Test

class HighOrderfunction2 {

   fun <T>Collection<T>.joinToString(
       separator: String = ", ",
       prefix : String = "",
       postfix: String = "",
       transform: (T) -> String = {it.toString()}
   ) : String {

       val result = StringBuilder()

       for((index, element) in this.withIndex()) {

           if(index > 0) result.append(separator)
           result.append(transform(element))
       }

       result.append(prefix)
       return result.toString()
   }

    @Test
    fun transformString() {
        val letters = listOf("Alpha", "Beta")
        println(letters.joinToString())
    }

    /*
     * 함수를 반환하는 함수
     */
    enum class Delivery{ STANDARD, EXPEDITED }

    class Order(val itemCount: Int)

    fun getShippingCostCalculator(delivery: Delivery) : (Order) -> Double
    {

        if(delivery == Delivery.EXPEDITED) {
            return {order -> 6 + 2.1 * order.itemCount}
        }

        return {order-> 1.2 * order.itemCount}
    }


    @Test
    fun printShoppingCost() {
        val calculator = getShippingCostCalculator(Delivery.EXPEDITED)

        println("Shipping costs ${calculator(Order(3))}")
    }


    data class Person(val fistName: String,
                      val lastName: String,
                      val phoneNumber: String?)

    class ContactListFilters {
        var prefix: String = ""
        var onlyWithPhoneNumber: Boolean = false

        fun getPredicate(): (Person)->Boolean {

            var startwithPrefix = {p:Person -> p.fistName.startsWith(prefix) || p.lastName.startsWith(prefix)}

            if(!onlyWithPhoneNumber) {
                return startwithPrefix
            }

            return {startwithPrefix(it) && it.phoneNumber != null}
        }
    }

    @Test
    fun filterContactList() {
        val contacts = listOf(Person("Dmitry", "Jemerov", "123-4567")
                            ,Person("Svetlana", "Isakova", null))

        val contactListFilters = ContactListFilters()

        with(contactListFilters) {
            prefix = "Dm"
            onlyWithPhoneNumber = true
        }

        println(contacts.filter(contactListFilters.getPredicate()))
    }

    enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

    data class SiteVisit (val path: String,
                          val duration: Double,
                          val os: OS)

    val log = listOf(
            SiteVisit("/", 34.0, OS.WINDOWS),
            SiteVisit("/", 22.0, OS.MAC),
            SiteVisit("/login", 12.0, OS.WINDOWS),
            SiteVisit("/signup", 8.0, OS.IOS),
            SiteVisit("/", 16.3, OS.ANDROID))


    @Test
    fun test() {
        /*
         * OS가 windows인 요소만 뽑는 함수
         */
        val averageWidnowsDuration = log
                .filter { it.os == OS.WINDOWS }
                .map(SiteVisit::duration)
                .average()

        println(averageWidnowsDuration)
    }

    /*
     * filter할 os를 변수로 받는 확장함수 - os가 안드로이드이면서 ios인것은 구할수 없음~
     */
    fun List<SiteVisit>.averageDurationFor(os:OS) =
            filter { it.os == os }.map(SiteVisit::duration).average()

    @Test
    fun test1() {
        println(log.averageDurationFor(OS.WINDOWS))
        println(log.averageDurationFor(OS.MAC))
    }

    /*
     * 다중 조건을 filtering할수 있는 함수
     */
    val averageMobileDuration = log
            .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
            .map(SiteVisit::duration)
            .average()

    @Test
    fun test2() {
        println(averageMobileDuration)
    }

    /*
     * 지금까지의 함수는 다양한 조건을 수용할수 없다.
     * 좀더 매끄러운 함수가 필요해~
     * 아예 filter조건을 람다로 받는 함수를 만들자.
     */
    fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean)
            = filter(predicate).map(SiteVisit::duration).average()

    @Test
    fun test3() {
        //OS가 모바일인 로그만
        println(log.averageDurationFor{ it.os in setOf(OS.ANDROID, OS.IOS)})

        //IOS이면서 "/signup"에 접근한 로그만
        println(log.averageDurationFor { it.os == OS.IOS && it.path == "/signup" })
    }
}