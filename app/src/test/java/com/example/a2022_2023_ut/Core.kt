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

    class testStrings() {
        val boomer = listOf("Cool cat", "Groovy", "Square", "Hoosegow", "Flip a wig", "Threads")
        val genX = listOf("Dude", "Radical", "Diss", "Yuppie", "Headbanger", "Gnarly")
        val genY = listOf("Ok, boomer", "Salty", "Yeet", "Yolo", "Low-key", "Lit", "TBH")
        val genZ = listOf("Basic", "Sus", "Fam", "Stan", "Big Yikes", "Boujee", "High-key", "Cringe", "Drip")
        val all = boomer + genX + genY + genZ
    }

    // covers syntax basics
    @Test
    fun syntax1Printing() {
        unitTestSection("PRINTING")
        println("Hello world!")
        argPrint(
            "Hello",
            "\nHello",
            "my",
            "baby,",
            "hello",
            "my",
            "honey",
            "\nHello",
            "my",
            "ragtime",
            "gal"
        )
    }

    @Test
    fun syntax2Variables() {
        unitTestSection("VARIABLES")
        val s2a: Int = 3 // val == constant assignment (bot not const in C++ sense)
        assertEquals(s2a, 3)
        var s2b: String = "hello" // var == mutable
        assertEquals(s2b, "hello")
        assertNotEquals(s2b, "world")
        s2b = "world" // reassignment
        assertEquals(s2b, "world")
        val s2c: Int // deferred assignment
        s2c = 44
        assertEquals(s2c, 44)
        println("s2a[$s2a]")
        println("s2b[$s2b]")
        println("s2c[$s2c]")
    }

    @Test
    fun syntax3MathAndFunctions() {
        unitTestSection("MATH & FUNCTIONS")
        fun s3Sum(a: Int, b: Int): Int {
            return a + b
        }

        fun s3Sub(a: Int, b: Int): Int {
            return a - b
        }

        fun s3Mult(a: Int, b: Int): Int {
            return a * b
        }

        fun s3Div(a: Int, b: Int): Int {
            return a / b
        }

        fun s3Rem(a: Int, b: Int): Int {
            return a % b
        }

        val s3a = s3Sub(5, 2)
        val s3b = s3Mult(s3a, 8)
        val s3c = s3Sum(s3b, 4)
        val s3d = s3Div(s3c, 2)
        val s3e = s3Rem(14, 6)

        assertEquals(s3a, 3)
        assertEquals(s3b, 24)
        assertEquals(s3c, 28)
        assertEquals(s3d, 14)
        assertEquals(s3e, 2)

        println("s3a[$s3a]")
        println("s3b[$s3b]")
        println("s3c[$s3c]")
        println("s3d[$s3d]")
        println("s3d[$s3e]")
    }

    @Test
    fun syntax4Classes() {
        unitTestSection("CLASSES")

        // cartesian plane
        class S4Point(val x: Int, val y: Int) {
            fun equal(other: S4Point): Boolean {
                return x == other.x && y == other.y
            }
        }

        open class S4Rectangle(val origin: S4Point, val width: Int, val height: Int) {
            fun collision(other: S4Rectangle): Boolean {
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

        class S4Square(origin: S4Point, length: Int) : S4Rectangle(origin, length, length) {}

        val s4a = S4Square(S4Point(0, 0), 3)
        val s4b = S4Square(S4Point(4, 4), 2)
        val s4c = S4Square(S4Point(2, 2), 3)

        assertEquals(s4a.collision(s4b), false)
        assertEquals(s4b.collision(s4c), true)
        assertEquals(s4a.collision(s4c), true)

        s4a.print("s4a")
        s4b.print("s4b")
        s4c.print("s4c")
    }

    @Test
    fun syntax5StringTemplates() {
        unitTestSection("STRING TEMPLATES")

        var s5a = 1;
        val s5s1 = "s5a is $s5a"
        assertEquals(s5s1, "s5a is 1")

        s5a = 3
        val s5s2 = "${s5s1.replace("is", "was")}, but now is $s5a"
        assertEquals(s5s2, "s5a was 1, but now is 3")

        println("s5s1[$s5s1]")
        println("s5s2[$s5s2]")
    }

    @Test
    fun syntax6ConditionalExpressions() {
        unitTestSection("CONDITIONAL EXPRESSIONS")

        fun s6MinOf(a: Int, b: Int): Int {
            if(a < b) {
                return a
            } else {
                return b
            }
        }

        fun s6AlsoMinOf(a: Int, b: Int) = if (a < b) a else b

        val s6a = s6MinOf(5,4)
        val s6b = s6AlsoMinOf(5,4)
        val s6c = s6MinOf(3,27)
        val s6d = s6AlsoMinOf(3,27)

        assertEquals(s6a, 4)
        assertEquals(s6b, 4)
        assertEquals(s6c, 3)
        assertEquals(s6d, 3)

        println("s6a[$s6a]")
        println("s6b[$s6b]")
        println("s6c[$s6c]")
        println("s6d[$s6d]")

        class Borggle(val data: Any) {}
        class Boggler() {}

        fun getMyType(a: Any): String =
            when(a) {
                is Int -> "Int"
                is String -> "String"
                is Borggle -> getMyType(a.data)
                else -> "Unknown"
            }

        val s6e = "HELLO HELLO"
        val s6eType = getMyType(s6e)
        val s6f = Borggle(s6e)
        val s6fType = getMyType(s6f)
        val s6g = Boggler()
        val s6gType = getMyType(s6g)
        val s6h = 13
        val s6hType = getMyType(s6h)

        assertEquals(s6eType, "String")
        assertEquals(s6fType, "String")
        assertEquals(s6gType, "Unknown")
        assertEquals(s6hType, "Int")

        val s6fData = s6f.data as String

        println("s6e[$s6e]")
        println("s6f[$s6fData]")
        println("s6g[$s6g]")
        println("s6h[$s6h]")
    }

    @Test
    fun syntax7LoopingAndCollections() {
        unitTestSection("LOOPING AND COLLECTIONS")
        val strs = testStrings()
        var boomer = mutableListOf<String>()

        // for loop
        for (s in strs.all) {
            if(s in strs.boomer) {
                boomer.add(s)
            }
        }

        assertNotEquals(boomer.size, 0)
        assertEquals(boomer, strs.boomer)
        argPrint(boomer)

        var genX = mutableListOf<String>()
        var i = 0

        while(i<strs.all.size) {
            val s = strs.all.get(i)
            when {
                s in strs.genX -> genX.add(s)
            }
            i++
        }

        assertNotEquals(genX.size, 0)
        assertEquals(genX, strs.genX)
        argPrint(genX)
    }
}
