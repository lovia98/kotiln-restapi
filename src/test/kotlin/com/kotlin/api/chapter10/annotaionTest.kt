package com.kotlin.api.chapter10

import com.sun.tools.classfile.Dependency
import org.junit.Test
import org.mockito.InjectMocks
import kotlin.reflect.KClass

/*
 * 어노테이션 만들기 :
 */

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,
        AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Fancy

//클래스에 붙일 수 있고
@Fancy class Foo {
    //함수에 붙일 수 있고
    @Fancy fun baz(@Fancy foo: Int): Int { //파라미터에 붙일 수 있고
        return(@Fancy 1) //expression에 붙일 수 있다.
    }
}

/*
 * 기본생성자에 어노테이션 붙이기
 *   : 기본 생성자 선언에 constructor키워드를 추가하고 어노테이션을 추가 해야 한다.
 */
@Target(AnnotationTarget.CONSTRUCTOR,
        AnnotationTarget.PROPERTY_SETTER)
annotation class Inject

class Boo @Inject constructor(dependency: Dependency) {
}

//프라퍼티 accessors에 어노테이션 붙이기
class Voo {
    var x: Dependency? = null
        @Inject set
}

annotation class Special(val why: String)
@Special("example") class Roo {}

annotation class ReplaceWith(val expression: String)

annotation class Deprecated(val message: String,
                            val replaceWith: ReplaceWith = ReplaceWith(""))

@Deprecated("This function is deprecated, use == instead",
        ReplaceWith("hahahahahah"))
fun deprecated() {}

