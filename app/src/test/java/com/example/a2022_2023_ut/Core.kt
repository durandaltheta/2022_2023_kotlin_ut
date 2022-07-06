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
    fun unitTestSection(name: String) {
        println("<<<<<<<<<< $name <<<<<<<<<<")
    }

    fun endUnitTestSection(name: String) {
        println(">>>>>>>>>> $name >>>>>>>>>>")
    }

    private fun argPrint(vararg args: Any) {
        for(s in args) {
            print(s.toString())
            print(" ")
        }
        print("\n")
    }

    private fun prefixCollectionPrint(prefix: String, args: Collection<Any>) {
        print("$prefix: ")

        // clever lambda hack to avoid checking for first or last position in a list
        var printElementPrefix: () -> Unit
        printElementPrefix = { printElementPrefix = { print(", ")  } }

        for(s in args) {
            printElementPrefix()
            print(s.toString())
        }

        print("\n")
    }

    class Syntax {
        class testStrings() {
            val boomer = listOf("Cool cat", "Groovy", "Square", "Hoosegow", "Flip a wig", "Threads")
            val genX = listOf("Dude", "Radical", "Diss", "Yuppie", "Headbanger", "Gnarly")
            val genY = listOf("Ok, boomer", "Salty", "Yeet", "Yolo", "Low-key", "Lit", "TBH")
            val genYEveryOther = listOf("Ok, boomer", "Yeet", "Low-key", "TBH")
            val genZ = listOf("Basic", "Sus", "Fam", "Stan", "Big Yikes", "Boujee", "High-key", "Cringe", "Drip")

            // practice with type templates, variadics (vararg), spread operator, and functional list manipulation
            private fun <T> concat(vararg lists: List<T>): List<T> = mutableListOf(*lists).flatten().toList()

            val all = concat(boomer, genX, genY, genZ)

        }

        // covers syntax basics
        @Test
        fun printing() {
            CoreUnitTest().unitTestSection("PRINTING")
            println("Hello world!")
            CoreUnitTest().argPrint(
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
            CoreUnitTest().endUnitTestSection("PRINTING")
        }

        @Test
        fun variables() {
            CoreUnitTest().unitTestSection("VARIABLES")
            val a: Int = 3 // val == constant assignment (bot not const in C++ sense)
            assertEquals(a, 3)
            var b: String = "hello" // var == mutable
            assertEquals(b, "hello")
            assertNotEquals(b, "world")
            b = "world" // reassignment
            assertEquals(b, "world")
            val c: Int // deferred assignment
            c = 44
            assertEquals(c, 44)
            println("a[$a]")
            println("b[$b]")
            println("c[$c]")
            CoreUnitTest().endUnitTestSection("VARIABLES")
        }

        @Test
        fun mathAndFunctions() {
            CoreUnitTest().unitTestSection("MATH & FUNCTIONS")
            fun sum(a: Int, b: Int): Int {
                return a + b
            }

            fun sub(a: Int, b: Int): Int {
                return a - b
            }

            fun mult(a: Int, b: Int): Int {
                return a * b
            }

            fun div(a: Int, b: Int): Int {
                return a / b
            }

            fun rem(a: Int, b: Int): Int {
                return a % b
            }

            val a = sub(5, 2)
            val b = mult(a, 8)
            val c = sum(b, 4)
            val d = div(c, 2)
            val e = rem(14, 6)

            assertEquals(a, 3)
            assertEquals(b, 24)
            assertEquals(c, 28)
            assertEquals(d, 14)
            assertEquals(e, 2)

            println("a[$a]")
            println("b[$b]")
            println("c[$c]")
            println("d[$d]")
            println("d[$e]")
            CoreUnitTest().endUnitTestSection("MATH & FUNCTIONS")
        }

        @Test
        fun classes() {
            CoreUnitTest().unitTestSection("CLASSES")

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

            class Square(origin: Point, length: Int) : Rectangle(origin, length, length) {}

            val a = Square(Point(0, 0), 3)
            val b = Square(Point(4, 4), 2)
            val c = Square(Point(2, 2), 3)

            assertEquals(a.collision(b), false)
            assertEquals(b.collision(c), true)
            assertEquals(a.collision(c), true)

            a.print("a")
            b.print("b")
            c.print("c")
            CoreUnitTest().endUnitTestSection("CLASSES")
        }

        @Test
        fun stringTemplates() {
            CoreUnitTest().unitTestSection("STRING TEMPLATES")

            var a = 1;
            val s1 = "a is $a"
            assertEquals(s1, "a is 1")

            a = 3
            val s2 = "${s1.replace("is", "was")}, but now is $a"
            assertEquals(s2, "a was 1, but now is 3")

            println("s1[$s1]")
            println("s2[$s2]")
            CoreUnitTest().endUnitTestSection("STRING TEMPLATES")
        }

        @Test
        fun conditionalExpressions() {
            CoreUnitTest().unitTestSection("CONDITIONAL EXPRESSIONS")

            fun minOf(a: Int, b: Int): Int {
                if(a < b) {
                    return a
                } else {
                    return b
                }
            }

            fun alsoMinOf(a: Int, b: Int) = if (a < b) a else b

            val a = minOf(5,4)
            val b = alsoMinOf(5,4)
            val c = minOf(3,27)
            val d = alsoMinOf(3,27)

            assertEquals(a, 4)
            assertEquals(b, 4)
            assertEquals(c, 3)
            assertEquals(d, 3)

            println("a[$a]")
            println("b[$b]")
            println("c[$c]")
            println("d[$d]")

            class Borggle(val data: Any) {}
            class Boggler() {}

            fun getMyType(a: Any): String =
                when(a) {
                    is Int -> "Int"
                    is String -> "String"
                    is Borggle -> getMyType(a.data)
                    else -> "Unknown"
                }

            val e = "HELLO HELLO"
            val eType = getMyType(e)
            val f = Borggle(e)
            val fType = getMyType(f)
            val g = Boggler()
            val gType = getMyType(g)
            val h = 13
            val hType = getMyType(h)

            assertEquals(eType, "String")
            assertEquals(fType, "String")
            assertEquals(gType, "Unknown")
            assertEquals(hType, "Int")

            val fData = f.data as String

            println("e[$e]")
            println("f[$fData]")
            println("g[$g]")
            println("h[$h]")
            CoreUnitTest().endUnitTestSection("CONDITIONAL EXPRESSIONS")
        }

        @Test
        fun loopingAndCollections() {
            CoreUnitTest().unitTestSection("LOOPING AND COLLECTIONS")
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
            CoreUnitTest().prefixCollectionPrint("boomer",boomer)

            var genX = mutableListOf<String>()
            var genY = mutableListOf<String>()
            var genYEveryOther = mutableListOf<String>()
            var genZ = mutableListOf<String>()
            var i = 0

            // while loop
            while(i<strs.all.size) {
                val s = strs.all[i]

                // when filtering
                when {
                    s in strs.genX -> genX.add(s)
                    s in strs.genY -> genY.add(s)
                    s in strs.genZ -> genZ.add(s)
                }
                i++
            }

            // range
            for(index in 0..genY.size step 2) {
                genYEveryOther.add(genY.elementAt(index))
            }

            assertNotEquals(genX.size, 0)
            assertEquals(genX, strs.genX)
            assertEquals(genY, strs.genY)
            assertEquals(genYEveryOther, strs.genYEveryOther)
            assertEquals(genZ, strs.genZ)
            CoreUnitTest().prefixCollectionPrint("genX",genX)
            CoreUnitTest().prefixCollectionPrint("genY",genY)
            CoreUnitTest().prefixCollectionPrint("genYEveryOther",genYEveryOther)
            CoreUnitTest().prefixCollectionPrint("genZ",genZ)
            CoreUnitTest().endUnitTestSection("LOOPING AND COLLECTIONS")
        }

        @Test
        fun nullableValuesAndNullChecks() {
            CoreUnitTest().unitTestSection("NULLABLE VALUES AND NULL CHECKS")

            fun checkNull(i: Int?, expInt: Int, expNull: Boolean) {
                if(i != null) {
                    assertEquals(i, expInt)
                } else {
                    assert(expNull)
                }
            }

            var i: Int?
            i = 1
            checkNull(i, 1, false)
            i = null
            checkNull(i, 2, true)
            i = 2
            checkNull(i, 2, false)
            CoreUnitTest().endUnitTestSection("NULLABLE VALUES AND NULL CHECKS")
        }

        enum class TypeEnum {
            UNKNOWN, INT, STRING
        }

        @Test
        fun typeChecksAndAutomaticCasts() {
            CoreUnitTest().unitTestSection("TYPE CHECKS AND AUTOMATIC CASTS")
            var obj: Any
            obj = "hello"

            fun typeCheck(obj: Any): TypeEnum = when(obj) {
                is Int -> TypeEnum.INT
                is String -> TypeEnum.STRING
                else -> TypeEnum.UNKNOWN
            }

            class donk { }

            assertEquals(typeCheck(obj), TypeEnum.STRING)
            obj = 3
            assertEquals(typeCheck(obj), TypeEnum.INT)
            obj = donk()
            assertEquals(typeCheck(obj), TypeEnum.UNKNOWN)
            CoreUnitTest().endUnitTestSection("TYPE CHECKS AND AUTOMATIC CASTS")
        }

    }

    class Types {
        class basicTypes() {
            // integer types
            val one = 1 // Int
            val threeBillion = 3000000000 // Long
            val oneLong = 1L // Long
            val oneByte: Byte = 1

            // floating point
            val pi = 3.14 // Double
            val eFloat = 2.7182819f // Float

            // literal constants
            val decimal = 123
            val decimalL = 123L
            val hexidecimal = 0x0F
            val binary = 0b0001011

            // underscore seperators
            val oneMillion = 1_000_000
            val creditCardNumber = 1234_5678_9012_3456L
            val underscoreHexBytes = 0xFF_EC_DE_5E
            val underscoreBytes = 0b11010010_01101001_10010100_10010010

            // type conversions
            val byte: Byte = 1
            val byteAsShort: Short = byte.toShort()
            val byteAsInt: Int = byte.toInt()
            val byteAsLong: Long = byte.toLong()
            val byteAsFloat: Float = byte.toFloat()
            val byteAsDouble: Double = byte.toDouble()
            val byteAsChar: Char = byte.toChar()

            // unsigned
            val ubyte: UByte = 1u
            val ubyteAsUShort: UShort = ubyte.toUShort()
            val ubyteAsUInt: UInt = ubyte.toUInt()
            val ubyteAsULong: ULong = ubyte.toULong()

            // boolean
            val boolean: Boolean = true

            // char
            val char: Char = 'a'

            // string
            val string: String = "hello world"

            // array
            val array1: Array<Int> = arrayOf(1,2,3,4,5)
            val array2 = Array(5) { i -> i+2 }
        }

        inline fun <reified T> assertType(a: Any) {
            if(a is T) assert(true)
            else if(a !is T) assert(false) // this line exists just to prove knowledge of '!is'
            else assert(false)
        }

        /*
        Show type checking
         */
        @Test
        fun typeChecksAndCasts() {
            CoreUnitTest().unitTestSection("TYPE CHECKS AND CASTS")
            val b = basicTypes()
            assertType<Int>(b.one)
            assertType<Long>(b.threeBillion)
            assertType<Long>(b.oneLong)
            assertType<Byte>(b.oneByte)
            assertType<Double>(b.pi)
            assertType<Float>(b.eFloat)
            assertType<Int>(b.decimal)
            assertType<Long>(b.decimalL)
            assertType<Int>(b.hexidecimal)
            assertType<Int>(b.binary)
            assertEquals(b.hexidecimal, 15)
            assertEquals(b.binary, 11)

            assertType<Int>(b.oneMillion)
            assertType<Long>(b.creditCardNumber)

            assertEquals(b.underscoreHexBytes, 0xFFECDE5E)
            assertEquals(b.underscoreHexBytes, 4293713502)
            assertEquals(b.underscoreBytes, 0b11010010011010011001010010010010)
            assertEquals(b.underscoreBytes, 3530134674)

            assertType<Byte>(b.byte)
            assertType<Short>(b.byteAsShort)
            assertType<Int>(b.byteAsInt)
            assertType<Long>(b.byteAsLong)
            assertType<Float>(b.byteAsFloat)
            assertType<Double>(b.byteAsDouble)
            assertType<Char>(b.byteAsChar)

            assertType<UByte>(b.ubyte)
            assertType<UShort>(b.ubyteAsUShort)
            assertType<UInt>(b.ubyteAsUInt)
            assertType<ULong>(b.ubyteAsULong)

            assertType<Boolean>(b.boolean)

            assertType<Char>(b.char)

            assertType<String>(b.string)

            assertType<Array<Int>>(b.array1)

            var array1c: MutableList<Any> = mutableListOf<Any>()
            for(e in b.array1) {
                array1c.add(e)
            }
            CoreUnitTest().prefixCollectionPrint("array1",array1c)

            assertType<Array<Int>>(b.array2)
            var array2c: MutableList<Any> = mutableListOf<Any>()
            for(i in 0 until b.array2.size) {
                array2c.add(b.array2[i])
            }
            CoreUnitTest().prefixCollectionPrint("array2",array2c)
            CoreUnitTest().endUnitTestSection("TYPE CHECKS AND CASTS")
        }

        /*
        Show explicit type detection with is and !is
         */
        @Test
        fun isAndNotIs() {
            CoreUnitTest().unitTestSection("IS AND NOT IS")
            val b = basicTypes()

            if(b.one is Int) assert(true)
            if(b.one !is Int) assert(false)
            if(b.threeBillion is Long) assert(true)
            if(b.threeBillion !is Long) assert(false)
            if(b.oneLong is Long) assert(true)
            if(b.oneLong !is Long) assert(false)
            if(b.oneByte is Byte) assert(true)
            if(b.oneByte !is Byte) assert(false)
            if(b.pi is Double) assert(true)
            if(b.pi !is Double) assert(false)
            if(b.eFloat is Float) assert(true)
            if(b.eFloat !is Float) assert(false)
            if(b.decimal is Int) assert(true)
            if(b.decimal !is Int) assert(false)
            if(b.decimalL is Long) assert(true)
            if(b.decimalL !is Long) assert(false)
            if(b.hexidecimal is Int) assert(true)
            if(b.hexidecimal !is Int) assert(false)
            if(b.binary is Int) assert(true)
            if(b.binary !is Int) assert(false)
            CoreUnitTest().endUnitTestSection("IS AND NOT IS")
        }

        /*
        Show how intelligent use of 'is' in 'if' statements allows you to directly use a value as
        detected type without an additional explicit cast.
         */
        @Test
        fun smartCasts() {
            CoreUnitTest().unitTestSection("SMART CASTS")
            val b = basicTypes()

            var a: Any

            a = b.one
            if(a is Int) {
                assert(true)
                println("a:$a")
            }
            if(a !is Int) assert(false)

            a = b.threeBillion
            if(a !is Long) return
            assert(true)
            println("a:$a")

            a = b.pi
            if(a is Double && a > 3) {
                assert(true)
                println("a:$a")
            }
            else assert(false)

            a = b.boolean
            when(a) {
                is Int -> assert(false)
                is String -> assert(false)
                is Boolean -> assert(true)
                else -> assert(false)
            }

            CoreUnitTest().endUnitTestSection("SMART CASTS")
        }

        // example of unsafe casting
        @Test
        fun unsafeCastOperator() {
            CoreUnitTest().unitTestSection("UNSAFE CAST OPERATOR")
            val b = basicTypes()
            var a: Any = b.string
            val s: String = a as String // unsafe cast
            assertEquals(s, b.string)
            println("s:$s")
            CoreUnitTest().endUnitTestSection("UNSAFE CAST OPERATOR")
        }

        // example of "safe" casting which requires null checking
        @Test
        fun safeNullableCastOperator() {
            CoreUnitTest().unitTestSection("SAFE NULLABLE CAST OPERATOR")
            val b = basicTypes()
            var a: Any = b.pi
            var s: String? = a as? String
            if(s == null) assert(true)
            else assert(false)

            a = b.string
            s = a as? String
            if(s != null) assert(true)
            else assert(false)
            println("s:$s")
            CoreUnitTest().endUnitTestSection("SAFE NULLABLE CAST OPERATOR")
        }

        /*
        Ignoring these tests as they are very complicated feature concepts and will essentially
        never be useful in day to day code (they are more like library concepts).

        Test how you can check for non-Generic (non-template) types but cannot for generics at
        runtime. This seems to be part of an awkward limitation of how templates/generics are done
        in kotlin, apparently because type checking can be (and is commonly) done at runtime rather
        than just at compile time, there's cases where certain information will not be available
        during runtime if a generic's type has been type-erased. In comparison, C++ does all type
        checking at compile time (although a similar mechanism exists within `std::any` which allows
        the user functionality similar to kotlin's 'is' keyword with try/catch statements and
        `std::any_cast`s).
        @Test
        fun typeErasureAndGenericTypeChecks() {

        }

        Basically shows that as a fallback the user can always use 'as' keyword to do an unsafe cast
        (or restructure the code using various tricks).
        @Test
        fun uncheckedCasts {

        }
         */
    }
}
