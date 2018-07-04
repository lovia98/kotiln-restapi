package com.kotline.api.chapter7

import org.junit.Test


class Email

fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf()
    }

/*
 * 지연 초기화 : backing field 사용
 */
class Person(val name: String) {
    private var _emails: List<Email>? = null

    val emails: List<Email>
        get() {
            if(_emails == null) { //_email이 null인 경우에만 한번 초기화
                _emails = loadEmails(this)
            }
            return _emails!!
        }
}

class DelegateTest {

    @Test
    fun test() {
        val p = Person("Alice")
        p.emails
        p.emails
    }
}


/*
 * by와 lazy함수를 통해 지연 초기화
 */
class SomeThing(val name: String) {
   val emails by lazy{ loadEmailsWtihSomThing(this)}
}

fun loadEmailsWtihSomThing(something: SomeThing): List<Email> {
    println("Load emails for ${something.name}")
    return listOf()
}
