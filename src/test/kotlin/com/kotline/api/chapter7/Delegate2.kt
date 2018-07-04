package com.kotline.api.chapter7

import org.junit.Test
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChageLister(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Delegate2 {

    class Person(val name: String, age: Int, salary: Int)
        :PropertyChangeAware() {
        var age:Int = age
            set(newValue){
                val oldValue = field
                field = newValue
                changeSupport.firePropertyChange("age", oldValue, newValue)
            }

        var salary: Int = salary
            set(newValue) {
                val oldValue = field
                field = newValue
                changeSupport.firePropertyChange("salary", oldValue, newValue)
            }
    }


    @Test
    fun test() {
        val p = Person("Dmitry", 34, 2000)
        p.addPropertyChageLister(
                PropertyChangeListener { event ->
                    println("Property ${event.propertyName} changed "+
                        "from ${event.oldValue} to ${event.newValue}")
                }
        )

        p.age = 5
        p.salary = 2100
    }

    @Test
    fun test1() {
        class ObservableProperty(
                val propName: String, var propValue: Int,
                val changeSupport: PropertyChangeSupport
        ) {
            fun getValue(): Int = propValue
            fun setValue(newValue: Int) {
                val oldValue = propValue
                propValue = newValue
                changeSupport.firePropertyChange(
                        propName, oldValue, newValue
                )
            }
        }
    }
}