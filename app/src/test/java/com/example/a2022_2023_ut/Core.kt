package com.example.a2022_2023_ut

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/*
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
*/

class CoreUnitTest {
    val core = CoreOp()

    // covers printing basics and user function call
    @Test
    fun syntax1() {
        println("Hello world!")
        core.argPrint(arrayOf("Hello", "\nHello", "my", "baby,", "hello", "my", "honey", "\nHello", "my", "ragtime", "gal"))
    }

    // covers math operations and user function calls
    @Test
    fun syntax2() {
        val a = core.math(CoreOp.MathOp.Sub, 5, 2)
        assertEquals(a,3)
        println("a==$a")
    }
}
