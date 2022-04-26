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
    private fun unitTestSection(name: String) {
        println("---------- $name ----------")
    }

    private fun argPrint(vararg args: Any) {
        for(s in args) {
            print(s.toString())
            print(" ")
        }
        print("\n")
    }

    // covers syntax basics
    @Test
    fun syntax() {
        // s1 printing
        unitTestSection("PRINTING")
        println("Hello world!")
        argPrint("Hello", "\nHello", "my", "baby,", "hello", "my", "honey", "\nHello", "my", "ragtime", "gal")

        // s2 variables
        unitTestSection("VARIABLES")
        val s2a: Int = 3 // val == constant assignment (bot not const in C++ sense)
        assertEquals(s2a,3)
        var s2b: String = "hello" // var == mutable
        assertEquals(s2b,"hello")
        assertNotEquals(s2b,"world")
        s2b = "world" // reassignment
        assertEquals(s2b,"world")
        val s2c: Int // deferred assignment
        s2c = 44
        assertEquals(s2c,44)
        println("s2a[$s2a]")
        println("s2b[$s2b]")
        println("s2c[$s2c]")

        // s3 math & functions
        unitTestSection("MATH & FUNCTIONS")
        fun sum(a: Int, b: Int): Int {
            return a+b
        }

        fun sub(a: Int, b: Int): Int {
            return a-b
        }

        fun mult(a: Int, b: Int): Int {
            return a*b
        }

        fun div(a: Int, b: Int): Int {
            return a/b
        }

        fun rem(a: Int, b: Int): Int {
            return a%b
        }

        val s3a = sub(5, 2)
        val s3b = mult(s3a, 8)
        val s3c = sum(s3b, 4)
        val s3d = div(s3c, 2)

        assertEquals(s3a,3)
        assertEquals(s3b,24)
        assertEquals(s3c,28)
        assertEquals(s3d,14)

        println("s3a[$s3a]")
        println("s3b[$s3b]")
        println("s3c[$s3c]")
        println("s3d[$s3d]")

        // s4 classes
        unitTestSection("CLASSES")

        // cartesian plane
        class Point(val x: Int, val y: Int) {
            fun equal(other: Point): Boolean {
                return x == other.x && y == other.y
            }
        }

        open class Rectangle(val origin: Point, val width: Int, val height: Int) {
            fun collision(other: Rectangle): Boolean {
                return origin.x + width >= other.origin.x &&
                       origin.x <= other.origin.x + other.width &&
                       origin.y + height >= other.origin.y &&
                       origin.y <= other.origin.y + other.height
            }

            fun print(name: String) {
                val x = origin.x
                val y = origin.y
                println("$name[x[$x], y[$y], w[$width], h[$height]]")
            }
        }

        class Square(origin: Point, length: Int): Rectangle(origin, length, length) { }

        val s4a = Square(Point(0,0), 3)
        val s4b = Square(Point(4,4), 2)
        val s4c = Square(Point(2,2), 3)

        assertEquals(s4a.collision(s4b), false)
        assertEquals(s4b.collision(s4c), true)
        assertEquals(s4a.collision(s4c), true)

        s4a.print("s4a")
        s4b.print("s4b")
        s4c.print("s4c")
    }
}
