package kia.jkid

import kotlin.reflect.full.memberProperties

class Person(val name: String, val age: Int)

interface User {
	val username: String
}

fun foo(x: Int) = println(x)

fun sum(x: Int, y: Int) = x + y

var counter = 0

fun main(args: Array<String>) {
	val person = Person("Alice", 29)
	val kClass = person::class

	println(kClass.simpleName)
	println("Member properties:")
	kClass.memberProperties.forEach { println(it) }

	val anonymous = object : User {
		override val username: String
			get() = "anonymous"
	}

	println(anonymous::class.simpleName) // null - since it is an anonymous object

	val kFunction = ::foo
	kFunction.call(42) // 42 - does not prevent to call with incorrect number of parameters
	kFunction(43) // 43

	val kFunction2 = ::sum
	println(kFunction2.invoke(1, 2) + kFunction2.invoke(3, 4)) // 10
	// kFunction2(1) // error - no value passed for parameter p2

	val kProperty = ::counter
	kProperty.setter.call(21)
	println(kProperty.get()) // 21

	val memberProperty = Person::age
	println(memberProperty.get(person)) // 29
}
