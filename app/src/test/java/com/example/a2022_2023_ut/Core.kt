package com.example.a2022_2023_ut

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.delay
import org.junit.Test

import org.junit.Assert.*
import java.lang.NullPointerException
import java.lang.NumberFormatException
import java.util.function.UnaryOperator
import java.util.function.IntUnaryOperator
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

typealias Inteeger = Int
typealias Loooooong = Long
typealias Dooble = Double
typealias Strang = String
typealias MyHandler = (Any?) -> Any?
typealias Predicate<T> = (T) -> Boolean
typealias FuncRetInt_Int = (Int) -> Int
typealias FuncRetInt_Unit = () -> Int

class Outer {
    inner class Inner
}

typealias OuterAlias = Outer
typealias InnerAlias = Outer.Inner

open class TestUtilities {
    public fun unitTestSection(name: String) {
        println(">>>>>>>>>> $name >>>>>>>>>>")
    }

    public fun endUnitTestSection(name: String) {
        println("<<<<<<<<<< $name <<<<<<<<<<")
    }

    public fun argPrint(vararg args: Any) {
        for(s in args) {
            print(s.toString())
            print(" ")
        }
        print("\n")
    }

    public fun prefixCollectionPrint(prefix: String, args: Collection<Any>) {
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
}

class CoreUnitTest : TestUtilities() {
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
            assertEquals("a is 1", s1)

            a = 3
            val s2 = "${s1.replace("is", "was")}, but now is $a"
            assertEquals("a was 1, but now is 3", s2)

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

            assertEquals(4, a)
            assertEquals(4, b)
            assertEquals(3, c)
            assertEquals(3, d)

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

            assertEquals("String", eType)
            assertEquals("String", fType)
            assertEquals("Unknown", gType)
            assertEquals("Int", hType)

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
            assertEquals(15, b.hexidecimal)
            assertEquals(11, b.binary)

            assertType<Int>(b.oneMillion)
            assertType<Long>(b.creditCardNumber)

            assertEquals(0xFFECDE5E, b.underscoreHexBytes)
            assertEquals(4293713502, b.underscoreHexBytes)
            assertEquals(0b11010010011010011001010010010010, b.underscoreBytes)
            assertEquals(3530134674, b.underscoreBytes)

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
            assertEquals(b.string, s)
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

    class ConditionsAndLoops {
        @Test
        fun ifExpression() {
            CoreUnitTest().unitTestSection("IF EXPRESSION")
            val a = 4
            val b = 3

            // use "run" lambda as a scope
            run {
                var max = a

                // single `if` single statement
                if (a < b) assert(false)
            }

            run {
                var max: Int
                // `if/else` scoped statement
                if(a > b) {
                    assert(true)
                } else {
                    assert(false)
                }
            }

            run {
                // ternary operator style `if` showing how `if` returns a value
                val max = if (a > b) a else b

                if(max == a) assert(true)
            }
            CoreUnitTest().endUnitTestSection("IF EXPRESSION")
        }

        enum class Flurb {
            DONK, FLURBUS_UNUM, JINKIES
        }

        /*
        `when` statement is the equivalent of c `switch` statement, but more powerful because it can
        take any type and can check types instead of values. `when`, like `if` also returns a value
         */
        @Test
        fun whenExpression() {
            CoreUnitTest().unitTestSection("WHEN EXPRESSION")
            run {
                when (3) {
                    1 -> assert(false)
                    2 -> assert(false)
                    3 -> assert(true)
                    else -> assert(false)
                }
            }

            run {
                val s: String = "donk"
                when (s) {
                    "hello" -> assert(false)
                    "world" -> assert(false)
                    "donk" -> assert(true)
                    else -> assert(false)
                }
            }

            /*
            `else` case is required in `when` statement unless the compiler can prove there are no
            instances of any values *other* than the given cases, which is the case for enumerations
            where all cases are covered.
             */
            run {
                val flurbinimus = when (Flurb.DONK) {
                    Flurb.DONK -> "DONK"
                    Flurb.FLURBUS_UNUM -> "FLURBUS_UNUM"
                    Flurb.JINKIES -> "JINKIES"
                }
                assertEquals("DONK", flurbinimus)
            }


            /*
            Cases can be comma separated to handle common behavior
             */
            run {
                when (3) {
                    1, 2, 3 -> assert(true)
                    else -> assert(false)
                }
            }

            /*
            The `when` statement allows for runtime case assembly. IE, you can make a case the
            result of a function call that will execute at runtime
             */
            run {
                val s = "3"
                when (3) {
                    s.toInt() -> assert(true)
                    else -> assert(false)
                }
            }

            /*
            `when` can also check in or !in ranges
             */
            run {
                when (41) {
                    in 1..10 -> assert(false)
                    !in 1..10 -> assert(true)
                }
            }

            /*
            A `when` statement can check for types
             */
            run {
                val x: Int = 3
                val a: Any = x
                when (a) {
                    is String -> assert(false)
                    is Double -> assert(false)
                    is Long -> assert(false)
                    is Int -> assert(true)
                    else -> assert(false)
                }
            }

            /*
            `when` statement operator can be assigned inline
             */
            run {
                when(val x: Int = 14) {
                    in 1..13 -> assert(false)
                    else -> assert(true)
                }
            }

            CoreUnitTest().endUnitTestSection("WHEN EXPRESSION")
        }

        @Test
        fun forLoops() {
            CoreUnitTest().unitTestSection("FOR LOOPS")

            // make an array[1,2,3,4,5]
            val ar: Array<Int> = Array(5) { i -> i+1 }

            /*
            `for` can have a single statement
             */
            run {
                var sum: Int = 0
                for (element in ar) sum += element
                assertEquals(15, sum)
            }

            /*
            `for` can also be brace enclosed for multiple statements
             */
            run {
                var value: Int = 0
                for (element in ar) {
                    value += element
                    value -= 1
                }
                assertEquals(10, value)
            }

            /*
            `for` can use a range
             */
            run {
                var sum: Int = 0
                for (element in 1..5) sum += element
                assertEquals(15, sum)
            }

            /*
            `for` can use a variety of overly-clever iteration and stepping mechanisms
             */
            run {
                var sum: Int = 0
                for(element in 10 downTo 0 step 2) sum += element
                assertEquals(30, sum)
            }

            /*
            As a surprisingly useful convenience, kotlin provides a way to (somewhat awkwardly)
            access element's index in `for` loops via standard `withIndex()` container function
             */
            run {
                var indexSum: Int = 0
                var valueSum: Int = 0
                for ((index, value) in ar.withIndex()) {
                    indexSum += index
                    valueSum += value
                }
                assertEquals(10, indexSum)
                assertEquals(15, valueSum)
            }

            /*
            `for` functions with `return`, `break`, and `continue` similarly to C/C++
             */
            run {
                var sum: Int = 0
                for(i in 1..10) {
                    if(i > 7) break
                    sum += i
                }

                assertEquals( 28, sum)
            }

            run {
                var sum: Int = 0
                for(i in 10 downTo 1) { // reverse range
                    if(i > 7) continue
                    sum += i
                }

                assertEquals(28, sum)
            }

            run {
                var sum: Int = 0
                run {
                    for(i in 1..10) {
                        if(i > 7) return
                        sum += i
                    }
                    sum += 100 // won't run
                }

                assertEquals(28, sum)
            }

            CoreUnitTest().endUnitTestSection("FOR LOOPS")
        }

        /*
        While loops are straightforward in kotlin, they basically match c/c++
         */
        @Test
        fun whileLoops() {
            CoreUnitTest().unitTestSection("WHILE LOOPS")
            run {
                var x: Int = 10
                while(x > 0) {
                    x--
                }

                assertEquals(0, x)
            }

            run {
                var x: Int = 0
                do {
                    x++
                } while(x < 11)

                assertEquals(11, x)
            }
            CoreUnitTest().endUnitTestSection("WHILE LOOPS")
        }
    }

    /* ignoring returns and jumps as they function essentially identical to c */

    class Exceptions() {
        @Test
        fun exceptionClasses() {
            CoreUnitTest().unitTestSection("EXCEPTIONS CLASSES")

            class myException(override val message: String?) : Throwable(message) {}
            var b = false

            try {
                try {
                    throw myException("woah")
                } catch (e: myException) {
                    assertEquals("woah", e.message)
                    b = true
                    assert(true)
                } finally {
                    // optional block
                    if(!b) assert(false)
                }
            } catch (e : Throwable) {
                assert(false) // shouldn't run because it was caught lower
            }

            CoreUnitTest().endUnitTestSection("EXCEPTIONS CLASSES")
        }

        // try can have a return value
        @Test
        fun tryAsAnExpression() {
            CoreUnitTest().unitTestSection("TRY AS AN EXCEPTION")

            var s: String = "hello"
            var a: Int? = try { s.toInt() } catch ( e: NumberFormatException) { null }
            if(a == null) assert(true)
            else assert(false)
            s = "12"
            a = try { s.toInt() } catch ( e: NumberFormatException) { null }
            if(a == null) assert(false)
            else {
                assertEquals(12, a)
            }

            CoreUnitTest().endUnitTestSection("TRY AS AN EXCEPTION")
        }

        /* ignoring the `Nothing` type as too specific a usecase to cover */
    }

    /* Ignoring `Packages and Imports` documentation as they are awkward to 'unit test'. In short:
    - Packages: 1 more files can start with the `package your.package.name.here` invocation to
    declare those files as being part of that package name. This is relevant when importing the code
    for naming reasons and can affect visibility (code marked `internal` will only be visible in
    files in the same package/module).
    - Imports: `import` keyword is technologically distinct from c-style `include` and `using` but
    its functional purposes are similar to those two c keywords, import exists to allow to
    code/functionality to be exported and used by other code and allow use of code without
    specifying its whole namespace. In that second regard`import`s can specify different namespace
    depths to import more or less specific things from a package.
     */

    class Classes() {
        /*
        Ignoring the following topics for being too in-depth/advanced for common usage:
        Sealed classes
        Generics: in, out, where
        Inline classes
        Object expressions and declarations
        Delegation
        Delegated properties
         */

        @Test
        fun classDefinitionAndInstantiation() {
            CoreUnitTest().unitTestSection("CLASS DEFINITION AND INSTANTIATION")
            class Dog { } // class definition scope
            class Cat  // empty class with scope omitted

            run {
                var a: Any = Cat()
                when (a) {
                    is Cat -> assert(true)
                    else -> assert(false)
                }
            }
            CoreUnitTest().endUnitTestSection("CLASS DEFINITION AND INSTANTIATION")
        }

        @Test
        fun constructors() {
            CoreUnitTest().unitTestSection("CLASS CONSTRUCTORS")
            /*
            Member values can be define in the constructor of a class
             */
            run {
                class Flubinarg(val memberValue: Int) {}
                val f = Flubinarg(4)
                assertEquals(4, f.memberValue)
            }

            /*
            Because temporaryReference is not defined with `val` or `var` then temporaryReference
            will only be available within initialization context. IE, temporaryReference will be
            accessible within initial value assignment of `val`s & `var`s and `init` scopes, but
            will not be accessible as a member value when used as an object by outside code.

            `val`s/`var`s can be defined in a class body, like below with `memberValue`. In
            comparison to `temporaryReference`, `memberValue` will be accessible to outside code as
            a member value.
             */
            run {
                class Flobinarg(temporaryReference: Int) {
                    val memberValue: Int = temporaryReference

                    init {
                        assertEquals(3, temporaryReference)
                    }
                }

                val f = Flobinarg(3)
                assertEquals(3, f.memberValue)
            }

            /*
            Classes can have secondary constructors for alternative initialization. Secondary
            constructors can reference their class memory/members with `this` keyword to
            disambiguate the secondary constructor's arguments from the main constructor's
            arguments.
             */
            run {
                class Flibinarg() {
                    var value: Int = 2

                    constructor(value: Int) : this() {
                        this.value = value
                    }
                }

                class Flabinarg(val string: String) {
                    var flibinarg: Flibinarg = Flibinarg()

                    constructor(string: String, flibinarg: Flibinarg) : this(string) {
                        this.flibinarg = flibinarg
                    }
                }

                val flab = Flabinarg("woot")
                val flab2 = Flabinarg("wart", Flibinarg(4))

                assertEquals("woot", flab.string)
                assertEquals(2, flab.flibinarg.value)
                assertEquals("wart", flab2.string)
                assertEquals(4, flab2.flibinarg.value)
            }

            /*
            Secondary constructors can be treated like a primary constructor if no primary
            constructor is provided
             */
            run {
                class Flebinarg {
                    val value: String

                    constructor(value: String) {
                        this.value = value
                    }
                }

                val flebinarg = Flebinarg("wala wala bing bang")
                assertEquals("wala wala bing bang", flebinarg.value)
            }

            CoreUnitTest().endUnitTestSection("CLASS CONSTRUCTORS")
        }

        class classAllMemberTypes(val property1: Int) {
            val property2: Int

            init {
                property2 = 2
            }

            fun function1(int1: Int, int2: Int) : Boolean {
                return int1 == int1
            }

            class nestedClass(val property1: Int) { }

            inner class innerNestedClass(val property1: Int) {
                val property2: Int = this@classAllMemberTypes.property1
            }
        }

        @Test
        fun classMembers() {
            CoreUnitTest().unitTestSection("CLASS MEMBERS")
            val c = classAllMemberTypes(15)
            val nc = classAllMemberTypes.nestedClass(77)
            val inc = classAllMemberTypes(1).innerNestedClass(2)

            val anonymousClass = object {
                val goodbye = "Goodbye"
                val fam = "Fam"

                override fun toString() = "$goodbye $fam"
            }

            assertEquals(15, c.property1)
            assertEquals(2, c.property2)
            assert(c.function1(13, 13))
            assertEquals(77, nc.property1)
            assertEquals(2, inc.property1)
            assertEquals(1, inc.property2)
            assertEquals("Goodbye Fam", anonymousClass.toString())
            CoreUnitTest().endUnitTestSection("CLASS MEMBERS")
        }

        open class Base6 {
            open fun foo() : Int { return 13 }
        }

        interface Base6_2 {
            fun foo() : Int { return 14 }
        }

        class Derived6() : Base6(), Base6_2 {
            override fun foo() : Int {
                return super<Base6_2>.foo() + super<Base6>.foo()
            }
        }

        @Test
        fun classInheritance() {
            CoreUnitTest().unitTestSection("CLASS INHERITANCE")

            open class Base1
            class Derived1 : Base1()

            // show type inheritance
            run {
                val d = Derived1()

                if(d is Derived1) {
                    assert(true)

                    if(d is Base1) {
                        assert(true)
                    } else {
                        assert(false)
                    }
                } else {
                    assert(false)
                }
            }

            open class Base2(val value: Int)
            class Derived2(value: Int) : Base2(value)

            // show inherited members
            run {
                val d = Derived2(3)

                if(d is Derived2) {
                    assert(true)

                    if(d is Base2) {
                        assert(true)
                        assertEquals(3, d.value)
                    } else {
                        assert(false)
                    }
                } else {
                    assert(false)
                }
            }

            open class Base3 {
                open fun foo() : Int { return 1 }
                fun faa() : Int { return 2 }
                open val fii : Int = 3
                open val fuu : Int = 5
            }

            class Derived3(override val fuu : Int = 5) : Base3() {
                override fun foo() : Int { return 3 }
                override val fii : Int = 4
            }

            // show overriding methods and properties
            run {
                val d = Derived3()

                if(d is Derived3) {
                    assert(true)

                    if(d is Base3) {
                        assert(true)
                        assertEquals(3, d.foo())
                        assertEquals(4, d.fii)
                        assertEquals(5, d.fuu)
                    } else {
                        assert(false)
                    }
                } else {
                    assert(false)
                }
            }

            open class Base4 {
                init { println("Initializing Base4 class") }
            }

            class Derived4() : Base4() {
                init { println("Initializing Derived4 class") }
            }

            // show order of initialization (base is initialized before derived)
            run {
                val d = Derived4()

                if(d is Derived4) {
                    assert(true)

                    if(d is Base4) {
                        assert(true)
                    } else {
                        assert(false)
                    }
                } else {
                    assert(false)
                }
            }

            open class Base5 {
                open fun foo() : Int { return 1 }
            }

            class Derived5() : Base5() {
                override fun foo() : Int {
                    return super.foo()
                }

                inner class Inner5() {
                    fun foo() : Int {
                        return super@Derived5.foo()
                    }
                }
            }

            // show access of super class
            run {
                val d = Derived5()

                if(d is Derived5) {
                    assert(true)

                    if(d is Base5) {
                        assert(true)
                        assertEquals(1, d.foo())
                        assertEquals(1, d.Inner5().foo())
                    } else {
                        assert(false)
                    }
                } else {
                    assert(false)
                }
            }

            // show multiple inheritance rules where identical functions inherited from 2 parents
            // requires an override in the derived class
            run {
                val d = Derived6()

                if(d is Derived6) {
                    assert(true)

                    if(d is Base6) {
                        assert(true)

                        if(d is Base6_2) {
                            assert(true)
                            assertEquals(27, d.foo())
                        }
                    } else {
                        assert(false)
                    }
                } else {
                    assert(false)
                }
            }

            CoreUnitTest().endUnitTestSection("CLASS INHERITANCE")
        }

        // const classes apparently need to be an `object`
        object constClass1 {
            const val value = 3
            const val value2 = "wartwartwart"
        }

        @Test
        fun classProperties() {
            CoreUnitTest().unitTestSection("CLASS PROPERTIES")

            // basic properties
            run {
                class ab {
                    var a: String = "a"
                    var b: String = "b"
                }

                val c = ab()
                assertEquals("a", c.a)
                assertEquals("b", c.b)
            }

            // getters & setters
            run {
                class ab {
                    var a: Int = 3
                        get() = field // field == a
                        set(value) { field = value }
                }
            }

            // compile time constants
            run {
                val a = constClass1
                assertEquals(3, a.value)
                assertEquals("wartwartwart", a.value2)
            }

            // late initialization of values
            run {
                class late {
                    lateinit var value: String
                    fun setup() {
                        value = "three"
                    }
                }

                val l = late()
                l.setup()

                assertEquals("three", l.value)
            }

            CoreUnitTest().endUnitTestSection("CLASS PROPERTIES")
        }

        interface Interface1 {
            fun wee() : Int
        }

        interface Interface2 : Interface1 {
            fun woo() : Int { return 0 }
            val waa : Int
        }

        interface Interface3 {
            fun foo() : Int { return 1 }
        }

        interface Interface4 {
            fun foo() : Int { return 2 }
        }

        @Test
        fun classInterfaces() {
            CoreUnitTest().unitTestSection("CLASS INTERFACES")

            // overrides of methods, properties, and interface inheritance
            run {
                class Implementor : Interface2 {
                    override fun wee() : Int { return 0 }
                    override fun woo() : Int { return 1 }
                    override val waa : Int = 2
                }

                val i = Implementor()
                assertEquals(0, i.wee())
                assertEquals(1, i.woo())
                assertEquals(2, i.waa)
            }

            // resolve interface conflicts with an override
            run {
                class Implementor : Interface3, Interface4 {
                    override fun foo() : Int {
                        return super<Interface4>.foo() + super<Interface3>.foo()
                    }
                }

                val i = Implementor()
                assertEquals(3, i.foo())
            }

            CoreUnitTest().endUnitTestSection("CLASS INTERFACES")
        }

        fun interface IntPredicate {
            fun accept(i: Int) : Boolean
        }

        fun interface IntQuotient {
            fun accept(quotient: Double) : Double
        }

        fun interface IntDividend {
            fun accept(div: Double) : IntQuotient
        }

        fun functionalDivide() : IntDividend {
            return object : IntDividend {
                override fun accept(dividend: Double) : IntQuotient {
                    return object : IntQuotient {
                        val dividend = dividend
                        override fun accept(divisor: Double) : Double {
                            return dividend as Double / divisor as Double
                        }
                    }
                }
            }
        }

        @Test
        fun samConversions() {
            CoreUnitTest().unitTestSection("SAM CONVERSIONS")

            // basic predicate usage
            run {
                val isOdd = object : IntPredicate { // can use lambda
                    override fun accept(i: Int) : Boolean {
                        return i % 2 == 1
                    }
                }
            }

            // can do a SAM conversion for a whole sequence of operations
            run {
                val quotient = functionalDivide().accept(6.0).accept(3.0)
                assertEquals(2.0, quotient, 0.0)
            }

            /*
            Ignoring "Migration from an interface with constructor function to a functional
            interface" as well ass "Functional interfaces vs. type aliases", conceptually it seems
            too complicated to be common practice.
             */

            CoreUnitTest().endUnitTestSection("SAM CONVERSIONS")
        }

        // anyone can see
        public class visibilityExample1 {
            fun foo() : Int { return 1 }
        }

        // only visible inside the enclosing class
        private class visibilityExample2 {
            fun foo() : Int { return 2 }
        }

        // only visible to code compiled in the same module
        internal class visibilityExample3 {
            fun foo() : Int { return 3 }
        }

        // not available in "top-level" declarations (ie, the top-level is where "CoreUnitTest" is
        // declared, so a theoretical "CoreUnitTest2" at the same level could not see this)
        protected class visibilityExample4 {
            fun foo() : Int { return 4 }
        }

        class visibilityMembersExample1 {
            public fun foo() : Int { return 1 }
            private fun faa() : Int { return 2 } // only available within this class only
            public fun testFaa() : Int { return faa() }
            internal fun fuu() : Int { return 3 } // only available within files within the same module (files compiled together)
            protected fun fii() : Int { return 4 } // only available within this class and within subclasses
            public fun testFii() : Int { return fii() }
        }

        // primary constructors can have a visibility modifier
        class visibilityConstructors1 public constructor(val i: Int) {
            fun foo() : Int { return 1 }
        }

        class visibilityConstructors2 private constructor(val i: Int) {
            fun foo() : Int { return 2 }
            class accessor {
                fun access(i : Int) : visibilityConstructors2 {
                    return visibilityConstructors2(i)
                }
            }
        }

        // available only within current compilation module
        class visibilityConstructors3 internal constructor(val i: Int) {
            fun foo() : Int { return 3 }
        }

        class visibilityConstructors4 protected constructor(val i: Int) {
            fun foo() : Int { return 4 }
            class accessor {
                fun access(i : Int) : visibilityConstructors4 {
                    return visibilityConstructors4(i)
                }
            }
        }

        @Test
        fun visibilityModifiers() {
            CoreUnitTest().unitTestSection("VISIBILITY MODIFIERS")

            // class/function visibility
            run {
                assertEquals(1,visibilityExample1().foo())
                assertEquals(2,visibilityExample2().foo())
                assertEquals(3,visibilityExample3().foo())
                assertEquals(4,visibilityExample4().foo())
            }

            // class member visibility
            run {
                val vme1 = visibilityMembersExample1()
                assertEquals(1,vme1.foo())
                assertEquals(2,vme1.testFaa())
                assertEquals(3,vme1.fuu())
                assertEquals(4,vme1.testFii())
            }

            // class primary constructor visibility
            run {
                val vc1 = visibilityConstructors1(5)
                val vc2 = visibilityConstructors2.accessor().access(6)
                val vc3 = visibilityConstructors3(7)
                val vc4 = visibilityConstructors4.accessor().access(8)
                assertEquals(1, vc1.foo())
                assertEquals(5, vc1.i)
                assertEquals(2, vc2.foo())
                assertEquals(6, vc2.i)
                assertEquals(3, vc3.foo())
                assertEquals(7, vc3.i)
                assertEquals(4, vc4.foo())
                assertEquals(8, vc4.i)
            }

            CoreUnitTest().endUnitTestSection("VISIBILITY MODIFIERS")
        }

        val <T> List<T>.lastIndex: Int
            get() = size - 1

        @Test
        fun extensionFunctions() {
            CoreUnitTest().unitTestSection("EXTENSION FUNCTIONS")

            // extension functions
            run {
                fun MutableList<Int>.incr(index: Int) {
                    this[index] += 1
                }

                val ml = mutableListOf(1, 2, 3,)
                ml.incr(1)
                assertEquals(1,ml[0])
                assertEquals(3,ml[1])
                assertEquals(3,ml[2])
            }

            // nullable receiver
            run {
                fun Any?.toInt() : Int {
                    return if(this == null) {
                        0
                    } else if (this is Int) {
                        this
                    } else {
                        0 as Int
                    }
                }

                var a: Any? = null
                var i: Int = a.toInt()

                assertEquals(0, i)

                a = "3"
                i = a.toInt()

                assertEquals(0, i)

                a = 3
                i = a.toInt()

                assertEquals(3, i)
            }

            // extension properties
            run {
                val l = listOf(1,2,3)
                assertEquals(2, l.lastIndex)
            }

            /*
            'Companion objects' and 'Declaring extensions as members' are ignored in these tests as
            too complicated/obscure to be regularly used in kotlin code.

            Scope of extensions is being ignored as it is too complicated to be worthwhile to show
            in these tests. Basically, you just have to import the extension's name when used
            outside of the package it was declared in.
             */

            CoreUnitTest().endUnitTestSection("EXTENSION FUNCTIONS")
        }

        enum class DogBreed {
            pitbull, boxer, everything_else
        }

        data class Dog(val breed: DogBreed, val age: Int)

        @Test
        fun dataClasses() {
            CoreUnitTest().unitTestSection("DATA CLASSES")

            val dogs = listOf(Dog(DogBreed.boxer, 3), Dog(DogBreed.everything_else, 2), Dog(DogBreed.pitbull, 5))

            assertEquals(Dog(DogBreed.pitbull, 5), dogs.elementAt(2))
            assertEquals(3, dogs.elementAt(0).component2())

            val dogs2 = listOf(dogs.elementAt(0).copy(age = 11), Dog(DogBreed.everything_else, 2), Dog(DogBreed.pitbull, 5))

            assertEquals(Dog(DogBreed.boxer, 11), dogs2.elementAt(0))
            assertEquals(Dog(DogBreed.pitbull, 5), dogs2.elementAt(2))
            assertEquals(2, dogs2.elementAt(1).component2())

            CoreUnitTest().endUnitTestSection("DATA CLASSES")
        }

        class Outer1 {
            fun foo(): Int { return 1 }
            class Nested {
                fun faa(): Int { return 2 }
            }
        }

        class Outer2 {
            private val fii: Int = 3 // accessible by members and inner classes
            fun foo(): Int { return 1 }
            inner class Inner {
                fun faa(): Int { return 2 }
                fun fii(): Int { return fii }
            }
        }

        class Outer3 {
            private val fii: Int = 3 // accessible by members and inner classes
            fun foo(): Int { return 1 }

            interface IntInner {
                fun faa(): Int
                fun fii(): Int
            }

            // anonymous inner classes are created with object expressions
            val Inner = object : IntInner {
                override fun faa(): Int { return 2 }
                override fun fii(): Int { return fii }
            }
        }

        @Test
        fun nestedAndInnerClasses() {
            CoreUnitTest().unitTestSection("NESTED AND INNER CLASSES")

            // classes can be nested inside one another and accessed via dot notation
            assertEquals(1, Outer1().foo())
            assertEquals(2, Outer1.Nested().faa())

            // inner classes can access their parent object's members
            assertEquals(1, Outer2().foo())
            assertEquals(2, Outer2().Inner().faa())
            assertEquals(3, Outer2().Inner().fii())

            // anonymous inner classes are created with object expressions
            assertEquals(1, Outer3().foo())
            assertEquals(2, Outer3().Inner.faa())
            assertEquals(3, Outer3().Inner.fii())

            CoreUnitTest().endUnitTestSection("NESTED AND INNER CLASSES")
        }

        // type-safe enums function similarly to C
        enum class UnexpectedDirections {
            BACK, TO, THE, FUTURE
        }

        // enums can be initialized to specific values
        enum class ZeroOneTwoThree(val v: Int) {
            ZERO(0),
            ONE(1),
            TWO(2),
            THREE(3)
        }

        enum class AAAAAUGH {
            WAITING_AT_THE_DMV {
                override fun signal() = FREE_AT_LAST
            },
            FREE_AT_LAST {
                override fun signal() = WAITING_AT_THE_DMV
            };

            abstract fun signal(): AAAAAUGH
        }

        enum class IntOtherMaths : UnaryOperator<Int>, IntUnaryOperator {
            FILTHY_DIVIDE_BY_ZERO {
                override fun apply(i: Int): Int {
                    assert(false) // you bad, bad man
                    return 0
                }
            },
            SQUARE {
                override fun apply(i: Int) : Int {
                    return i * i
                }
            };

            override fun applyAsInt(i: Int) = apply(i)
        }

        @Test
        fun enumClasses() {
            CoreUnitTest().unitTestSection("ENUM CLASSES")

            // can be used in `when` statements like C style `switch`
            when(UnexpectedDirections.FUTURE) {
                UnexpectedDirections.BACK -> assert(false)
                UnexpectedDirections.TO -> assert(false)
                UnexpectedDirections.THE -> assert(false)
                UnexpectedDirections.FUTURE -> assert(true)
            }

            // Since enum class constants are really also classes, they can have constructors and
            // properties
            assertEquals(0, ZeroOneTwoThree.ZERO.v)
            assertEquals(1, ZeroOneTwoThree.ONE.v)
            assertEquals(2, ZeroOneTwoThree.TWO.v)
            assertEquals(3, ZeroOneTwoThree.THREE.v)

            // enum constants (which are really just more classes under the hood) automatically
            // inherit the enum class as an interface and can override said interface to have custom
            // method implementations. There are some interesting (?) applications where you can use
            // this feature to implement state transitions
            assertEquals(AAAAAUGH.FREE_AT_LAST, AAAAAUGH.WAITING_AT_THE_DMV.signal())
            assertEquals(AAAAAUGH.WAITING_AT_THE_DMV, AAAAAUGH.FREE_AT_LAST.signal())

            // implement interfaces on an enum
            assertEquals(4, IntOtherMaths.SQUARE.apply(2))

            val ev = enumValues<IntOtherMaths>()
            assertEquals("FILTHY_DIVIDE_BY_ZERO", ev[0].toString())
            assertEquals("SQUARE", ev[1].toString())

            CoreUnitTest().endUnitTestSection("ENUM CLASSES")
        }

        @Test
        fun typeAliases() {
            CoreUnitTest().unitTestSection("TYPE ALIASES")

            /*
            typealias Inteeger = Int
            typealias Loooooong = Long
            typealias Dooble = Double
            typealias Strang = String
            typealias MyHandler = (Any?) -> Any?
            typealias Predicate<T> = (T) -> Boolean

            class Outer {
                inner class Inner
            }

            typealias OuterAlias = Outer
            typealias InnerAlias = Outer.Inner
             */

            val i : Inteeger = 10
            val l : Loooooong = 1
            val d : Dooble = 4.4
            val s : Strang = "Hullo Vorld?"
            val mh : MyHandler = { a: Any? ->
                if(a != null && a is Inteeger) {
                    a*a
                } else {
                    a
                }
            }

            val pd : Predicate<Dooble> = { d -> d == 0.0 }

            assert(i is Inteeger)
            assert(i is Int)
            assert(l is Loooooong)
            assert(l is Long)
            assert(d is Dooble)
            assert(d is Double)
            assert(s is Strang)
            assert(s is String)
            assert(mh is MyHandler)
            assertEquals(null, mh(null))
            assertEquals(4, mh(2 as Int))
            assert(pd is Predicate<Dooble>)
            assert(pd(0.0))

            CoreUnitTest().endUnitTestSection("TYPE ALIASES")
        }
    }

    class Functions() {
        @Test
        fun functionUsage() {
            CoreUnitTest().unitTestSection("FUNCTION USAGE")

            // function declaration and usage
            // look mah, I can function
            run {
                fun square(x: Int): Int {
                    return x * x
                }
                assertEquals(4, square(2))
                assertEquals(9, square(3))
                assertEquals(16, square(4))
            }

            // function parameters
            run {
                fun mult(x: Int, y: Int): Int {
                    return x * y
                }
                assertEquals(2, mult(2, 1))
                assertEquals(4, mult(2, 2))
                assertEquals(6, mult(2, 3))
            }

            // default arguments
            run {
                fun multOrSquare(x: Int, y: Int = x): Int {
                    return x * y
                }
                assertEquals(4, multOrSquare(2))
                assertEquals(9, multOrSquare(3))
                assertEquals(16, multOrSquare(4))
                assertEquals(2, multOrSquare(2, 1))
                assertEquals(4, multOrSquare(2, 2))
                assertEquals(6, multOrSquare(2, 3))

                // overriding class members
                open class Foo {
                    open fun fii(): Int {
                        return 1
                    }
                }

                class Faa : Foo() {
                    override fun fii(): Int {
                        return 2
                    }
                }

                assertEquals(1, Foo().fii())
                assertEquals(2, Faa().fii())
            }

            // named arguments with default arguments
            run {
                fun foo(bar: Int = 0, baz: Int) : Int { return bar + baz }

                assertEquals(3, foo(2,1))

                // can specify arguments by name
                assertEquals(5, foo(bar = 3,baz = 2))

                // default arguments can be used in this way as well if left unspecified
                assertEquals(7, foo(baz = 7))
            }

            // unit-returning functions (c void-like return)
            run {
                class fakeRetVal(var value : Int) { }

                // Type `Unit` has only one value, Unit. It is used in place of `void` from C languages
                fun foo(frv : fakeRetVal) : Unit { frv.value = 1 }

                // `Unit` can be omitted in the definition
                fun faa(frv : fakeRetVal) { frv.value = 2 }

                val frv = fakeRetVal(0)
                foo(frv)
                assertEquals(1, frv.value)
                faa(frv)
                assertEquals(2, frv.value)
            }

            // single expression functions
            run {
                // can remove curly braces if function is a single expression
                fun double(x: Int) : Int = x * 2

                // also return type for single expression functions is optional if it can be
                // inferred by the compiler
                fun dooble(x: Int) = x * 2

                // return type is optional with block body functions only if the return type is Unit
                fun daable(x: Int) = { }

                assertEquals(4, double(2))
                assertEquals(4, dooble(2))
            }

            // variable number of function arguments (of the same type)
            run {
                fun <T> asList(vararg ts: T): List<T> {
                    val result = ArrayList<T>()
                    for (t in ts) { // ts is an Array
                        result.add(t)
                    }
                    return result
                }

                var al = asList(4,3,2,1)
                assertEquals(3, al.elementAt(1))
            }

            /* Omitting `Infix notation` as it is unlikely to be something I will write commonly.
            It is enough to say that infix notation is kind of like a fusion of standard function
            calls and operator overloading, allowing the user to use custom functions like they
            would for Kotlin keywords.
             */
            CoreUnitTest().endUnitTestSection("FUNCTION USAGE")
        }

        /*
        Omitting "function scope" as it would be a pain to end the enclosing classes to show of
        how you can write `fun foo()` anywhere. The fact that functions can be declared
        basically anywhere is visible throughout this file. To whit:
        - local functions: functions can be defined inside of other functions
        - member functions: functions can be defined as methods of a class
         */

        /*
        Generic functions

        Functions can be made available for any type which can successfully be logically
        substituted in place of a symbolic type (commonly specified as `T`).
         */
        @Test
        fun genericFunctions() {
            fun <T> toAny(t: T) : Any {
                return t as Any
            }

            assert(toAny(3) is Any)
            assert(toAny(4.44) is Any)
            assert(toAny("hello") is Any)
        }

        /*
        tailRecursiveFunctions:

        Functions can tail recursively call themselves to implement the tail recursion
        functional paradigm. If `tailrec` keyword is used in the function definition, the
        compiler will optimize the recursion so the function does not run out of stack space.
         */
        @Test
        fun tailRecursiveFunctions() {
            tailrec fun iterate(x : Int, depth: Int) : Int {
                return if(x<depth) {
                    depth
                } else {
                    iterate(x+1, depth)
                }
            }

            assertEquals(10, iterate(1,10))
            assertEquals(15, iterate(2,15))
            assertEquals(999999999, iterate(3,999999999))
        }
    }

    class Lambdas() {
        @Test
        fun higherOrderFunctions() {
            CoreUnitTest().unitTestSection("HIGHER ORDER FUNCTIONS")
            val l = listOf(1,2,3)
            val r = l.fold(10, { a, e -> a + e})
            assertEquals(16, r)
            CoreUnitTest().endUnitTestSection("HIGHER ORDER FUNCTIONS")
        }

        @Test
        fun functionTypes(){
            CoreUnitTest().unitTestSection("FUNCTION TYPES")
            val l1 = { i:Int -> i+1 }
            val l2 = { 1 }

            assert(l1 is FuncRetInt_Int)
            assert(l2 is FuncRetInt_Unit)
            CoreUnitTest().endUnitTestSection("FUNCTION TYPES")
        }

        @Test
        fun invokingAFunctionTypeInstance() {
            CoreUnitTest().unitTestSection("INVOKING A FUNCTION TYPE INSTANCE")
            val l: (String, String) -> String = { s, s2 -> s + s2 }

            assertEquals("1 and 2", l.invoke("1 an", "d 2"))
            assertEquals("3 or 4", l("3 o", "r 4"))

            CoreUnitTest().endUnitTestSection("INVOKING A FUNCTION TYPE INSTANCE")
        }

        @Test
        fun lambdaExpressionsAndAnonymousFunctions() {
            fun min(strings: List<String>, lessThan: (String,String) -> Boolean): String {
                var r: String = ""
                for(s in strings) {
                    println("s:$s, r:$r")
                    if(r == "" || lessThan(s, r)) {
                        println("${s}.length < ${r}.length")
                        r = s
                    }
                }
                return r
            }

            val strings = listOf("helloworld", "the lalelulelo", "foo")
            if(min(strings, { a,b -> a.length < b.length }) == "foo") assert(true)
            else assert(false)
        }

        @Test
        fun lambdaExpressionSyntax() {
            val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
            assertEquals(5, sum(3,2))
            val sum2 = { x: Int, y: Int -> x + y }
            assertEquals(10, sum2(4,6))
        }

        private fun callDoubleArgLambda(x: Int, y: Int, op: (Int, Int) -> Int): Int {
            return op(x,y)
        }

        private fun callSingleArgLambda(i:Int, op: (i: Int) -> Int) : Int {
            return op(i)
        }

        @Test
        fun passingTrailingLambdas() {
            // if the last argument of a function is another function, it can be outside
            // the call parenthesis
            assertEquals(15, callDoubleArgLambda(5,3) { x:Int, y:Int -> x * y } )
        }

        @Test
        fun itImplicitNameOfASingleParameter() {
            // if a lambda has only 1 parameter, the name of that parameter can be omitted
            // and the compiler will give that parameter the name 'it'
            assertEquals(4, callSingleArgLambda(2) { it * it })
        }

        @Test
        fun returningAValueFromALambdaExpression() {
            // lambda returns can implicit or qualified
            assertEquals(9, callSingleArgLambda(3) { it * it })
            assertEquals(16, callSingleArgLambda(4) { return@callSingleArgLambda it * it })
        }

        // I can't get underscores to work for lambdas, leaving as a comment
        /*
        @Test
        fun underscoreForUnusedVariables() {
            assertEquals(3, callDoubleArgLambda(4, 3) { (_, x) -> x })
        }
         */

        @Test
        fun anonymousFunctions() {
            // standard functions can be used like lambdas by excluding the function name
            assertEquals(3, callDoubleArgLambda(8, 5, fun(x:Int ,y: Int) : Int { return x - y }))
            assertEquals(3, callDoubleArgLambda(2, 1, fun(x,y) = x + y))
        }

        @Test
        fun closures() {
            // lambdas and anonymous functions can access values created outside of themselves
            val other = 14
            assertEquals(22, callSingleArgLambda(8) { it + other })
        }

        /*
        Skipping function literals with a receiver, as their usage in common code seems unlikely
         */
    }

    /*
    Skipping operator overloading, that's more of a library writing skill
     */

    class NullSafety() {
        @Test
        fun nullSafety() {
            CoreUnitTest().unitTestSection("NULL SAFETY")

            // `if` null check
            run {
                var i : Int? = null
                if(i == null) assert(true)
                else assert(false)

                i = 3
                if(i != null) {
                    assertEquals(3,i)
                }
                else assert(false)
            }

            // value can be treated as a non-nullable within an `if` if a null check precedes later checks
            run {
                val i : Int? = 3
                if(i != null && i == 3) assert(true)
                else assert(false)
            }

            // safe call operator can magically cause null to be checked before an operation occurs
            run {
                class Foo {
                    var i : Int = 3
                }
                var f : Foo? = null
                if(f?.i == 3) {
                    assert(false)
                }
                f = Foo()
                if(f?.i == 3) {
                    assert(true)
                }
            }

            // can use this expressions (specifically `let`)
            run {
                class Foo {
                    var i : Int = 3
                }
                var f : Foo? = null
                f?.let { assertEquals(3, it.i) } // won't run
                f = Foo()
                f?.let { assertEquals(3, it.i) }
            }

            // can replace `if/else` with elvis operator `?:`
            run {
                class Foo {
                    var i : Int = 3
                }
                var f : Foo? = null
                assertEquals(0, f?.i ?: 0)
                f = Foo()
                assertEquals(3, f?.i ?: 0)
            }

            // the `!!` operator is used to forcibly attempt to cast to non-nullable type, which can
            // cause null exceptions to be thrown
            run {
                class Foo {
                    var i : Int = 3
                }
                var f : Foo? = null
                try {
                    assertEquals(3, f!!.i)
                    assert(false) // this code should not be hit
                } catch(e: NullPointerException) {
                    assert(true)
                }
            }

            // The `as?` operator will attempt to cast to the given type, else it will return null
            run {
                val s: String = "hello"
                val a: Any = s
                val i: Int? = a as? Int
                if(i == null) assert(true)
                else assert(false)

                val s2: String? = a as? String
                if(s2 != null) assert(true)
                else assert(false)
            }

            CoreUnitTest().endUnitTestSection("NULL SAFETY")
        }

    }

    class Equality() {
        @Test
        fun equality() {
            CoreUnitTest().unitTestSection("EQUALITY")

            class foo(val i: Int, val u: Int) {}

            // structure equality only
            run {
                val f = foo(3,2)
                val f2 = foo(3,2)

                if(f.i==f2.i) assert(true)
                else assert(false)

                if(f.u==f2.u) assert(true)
                else assert(false)

                if(f!==f2) assert(true) // point to different objects
                else assert(false)
            }

            // referential equality
            run {
                val f = foo(3,2)
                val f2 = f

                if(f.i==f2.i) assert(true)
                else assert(false)

                if(f.u==f2.u) assert(true)
                else assert(false)

                if(f===f2) assert(true) // point to the same object
                else assert(false)
            }

            CoreUnitTest().endUnitTestSection("EQUALITY")
        }
    }

    class ThisExpressions() {
        @Test
        fun thisExpressions() {
            CoreUnitTest().unitTestSection("THIS EXPRESSIONS")

            run {
                class foo {
                    fun getThis(): foo {
                        // this refers to the current object of the class
                        return this
                    }
                }

                val f = foo()
                assertEquals(f, f.getThis())
            }

            run {
                class foo {
                    inner class faa {
                        fun getThis(): faa {
                            // this refers to the current object of the class
                            return this
                        }

                        fun getFooThis(): foo {
                            // qualified this (with @) can get other this symbols
                            return this@foo
                        }
                    }
                }

                val f = foo()
                val f2 = f.faa()
                assertEquals(f, f2.getFooThis())
                assertEquals(f2, f2.getThis())
            }

            run {
                class foo {
                    fun faa() : Int {
                        return 3
                    }

                    fun fii() : Int {
                        return 1
                    }

                    fun fooTests() : Int {
                        // member calls to this do not require explicit usage of the `this` symbol
                        assertEquals(3, this.faa())
                        assertEquals(1, fii())
                        return 2
                    }
                }

                assertEquals(2, foo().fooTests())
            }

            CoreUnitTest().endUnitTestSection("THIS EXPRESSIONS")
        }
    }

    /*
    Realized late that the main documentation omits how to do threading, callbacks, futures and
    promises. Instead, it discusses the problems with these approaches and instead discusses how
    it solves the problems with coroutines (which it has its own section on).

    Therefore I am omitting code for this section.
     */

    /*
    Coroutines are covered in the official library section for them
     */

    /*
    Annotations are waaaay too complicated to investigate here. They are a very fancy feature which
    basically allows all sorts of code introspection, application of special rules for the compiler
    (or other tools), and other code injection vagaries. None of which are particularly easy to
    show in a test, thus they are omitted from the testing.

    In summary annotations are applied to classes, functions, and variables with the `@` symbol.
    `@Test` is an example of an annotation used in this file, which specifies the function it is
    applied to should be run as a unit test.
     */

    class DestructuringDeclarations {
        data class IntPair(val first: Int, val second: Int)
        data class IntTuple(val first: Int, val second: Int, val third: Int)

        // Using `val (name1, name2, ..., nameN)` syntax, can magically assign properties of a
        // certain types of objects into above named values.
        @Test
        fun returningMultipleValues() {
            CoreUnitTest().unitTestSection("RETURNING MULTIPLE VALUES")

            fun retPair() : IntPair {
                return IntPair(1,2)
            }

            val (first, second) = retPair()
            assertEquals(1,first)
            assertEquals(2,second)

            fun retTuple() : IntTuple {
                return IntTuple(3,4,5)
            }

            val (third, fourth, fifth) = retTuple()
            assertEquals(3,third)
            assertEquals(4,fourth)
            assertEquals(5,fifth)

            CoreUnitTest().endUnitTestSection("RETURNING MULTIPLE VALUES")
        }

        @Test
        fun destructuringMaps() {
            CoreUnitTest().unitTestSection("DESTRUCTURING MAPS")
            val map = mapOf("hello" to "world", "foo" to "faa", "what a" to "wonderful world")
            var cnt: Int = 0

            // can iterate through a map and automatically assign key and value to named `val`s
            for((key,value) in map) {
                when(key) {
                    "what a" -> {
                        cnt+=1
                        assertEquals("wonderful world",value)
                    }
                    "foo" -> {
                        cnt+=1
                        assertEquals("faa",value)
                    }
                    "hello" -> {
                        cnt+=1
                        assertEquals("world",value)
                    }
                    else -> assert(false)
                }
            }

            assertEquals(3,cnt)
            CoreUnitTest().endUnitTestSection("DESTRUCTURING MAPS")
        }

        // can ignore individual destructured values
        @Test
        fun destructuringUnusedVariables() {
            CoreUnitTest().unitTestSection("DESTRUCTURING UNUSED VARIABLES")
            fun retPair() : IntPair {
                return IntPair(44,52)
            }

            val (_, second) = retPair()
            assertEquals(52,second)


            val (first, _) = retPair()
            assertEquals(44,first)
            CoreUnitTest().endUnitTestSection("DESTRUCTURING UNUSED VARIABLES")
        }

        // lambda arguments can also use destructuring
        @Test
        fun destructuringInLambdas() {
            CoreUnitTest().unitTestSection("DESTRUCTURING IN LAMBDAS")

            data class RefInt(var i: Int)
            data class RefIntPair(var i: RefInt, var u: RefInt)

            // simple argument lambda
            run {
                val ri = RefInt(14)
                val l = { ri: RefInt -> ri.i = 3 }
                l(ri)
                assertEquals(3, ri.i)
            }

            // dual argument lambda
            run {
                val ri = RefInt(140)
                val ri2 = RefInt(11)
                val l = { ri: RefInt, ri2: RefInt -> ri.i = 1; ri2.i = 2 }
                l(ri, ri2)

                assertEquals(1, ri.i)
                assertEquals(2, ri2.i)
            }

            // dual arguments destructured from a single argument
            run {
                val rip = RefIntPair(RefInt(7),RefInt(8))
                val l = { (ri, ri2): RefIntPair -> ri.i += 1; ri2.i += 2 }
                l(rip)

                assertEquals(8, rip.i.i)
                assertEquals(10, rip.u.i)
            }

            // dual arguments destructured from a single argument and an additional argument
            run {
                val rip = RefIntPair(RefInt(9),RefInt(10))
                val ri = RefInt(11)
                val l = { (ri, ri2): RefIntPair, ri3: RefInt -> ri.i += 1; ri2.i += 2;  ri3.i += 3}
                l(rip, ri)

                assertEquals(10, rip.i.i)
                assertEquals(12, rip.u.i)
                assertEquals(14, ri.i)
            }

            CoreUnitTest().endUnitTestSection("DESTRUCTURING IN LAMBDAS")
        }
    }

    /*
    Reflection is too specialized to be widely used in my estimation and is not even included by
    default (it must be listed as a dependency explicitly in android gradle scripts). Basically,
    reflection gives the user more access to things like class, function and variable addresses (as
    references) to do things like pass function pointers around.

    Therefore I am omitting this feature from the unit tests.
     */
}

class StandardLibraryUnitTest : TestUtilities() {
    class Collections : TestUtilities() {
        @Test
        fun lists() {
            Collections().unitTestSection("COLLECTIONS.LISTS")

            val numbers = listOf("one", "two", "three", "four")
            assertEquals("one", numbers[0])
            assertEquals("two", numbers[1])
            assertEquals("three", numbers[2])
            assertEquals("four", numbers[3])

            var cnt = 0
            for(number in numbers) { // iterate values
                cnt+=1
            }

            assertEquals(4,cnt)

            cnt = 0
            val numbersIterator = numbers.iterator()
            while(numbersIterator.hasNext()) {
                assertEquals(numbers[cnt], numbersIterator.next())
                cnt+=1
            }

            assertEquals(4,cnt)

            while(cnt in 0..3) {
                when (cnt) {
                    0 -> assertEquals("one",numbers[cnt])
                    1 -> assertEquals("two",numbers[cnt])
                    2 -> assertEquals("three",numbers[cnt])
                    3 -> assertEquals("four",numbers[cnt])
                }
            }

            assertEquals(4,cnt)

            Collections().endUnitTestSection("COLLECTIONS.LISTS")
        }

        @Test
        fun mutableLists() {
            Collections().unitTestSection("COLLECTIONS.MUTABLE_LISTS")

            val numbers = mutableListOf("one", "two")
            numbers.add("three")
            numbers.add("four")
            assertEquals("one", numbers[0])
            assertEquals("two", numbers[1])
            assertEquals("three", numbers[2])
            assertEquals("four", numbers[3])

            var cnt = 0
            for(number in numbers) { // iterate values
                cnt+=1
            }

            assertEquals(4,cnt)

            while(cnt in 0..3) {
                when (cnt) {
                    0 -> assertEquals("one",numbers[cnt])
                    1 -> assertEquals("two",numbers[cnt])
                    2 -> assertEquals("three",numbers[cnt])
                    3 -> assertEquals("four",numbers[cnt])
                }
            }

            assertEquals(4,cnt)

            cnt = 0
            val numbersIterator = numbers.iterator()
            while(numbersIterator.hasNext()) {
                numbersIterator.next()
                numbersIterator.remove()
                cnt+=1
            }

            assertEquals(4,cnt)
            assertEquals(0, numbers.size)

            Collections().endUnitTestSection("COLLECTIONS.MUTABLE_LISTS")
        }

        @Test
        fun sets() {
            Collections().unitTestSection("COLLECTIONS.SETS")

            val numbers = setOf(1,1,1,2,3,2)
            val numbers2 = setOf(3,2,1)
            assert(numbers.contains(1))
            assert(numbers.contains(2))
            assert(numbers.contains(3))
            assertEquals(3, numbers.size)
            assertEquals(3, numbers2.size)
            assertEquals(numbers, numbers2)

            var cnt = 0
            for(number in numbers) { // iterate values
                cnt+=1
            }

            assertEquals(3,cnt)

            cnt = 0
            val numbersIterator = numbers.iterator()
            while(numbersIterator.hasNext()) {
                numbersIterator.next()
                cnt+=1
            }

            assertEquals(3,cnt)

            Collections().endUnitTestSection("COLLECTIONS.SETS")
        }

        @Test
        fun mutableSets() {
            Collections().unitTestSection("COLLECTIONS.MUTABLE_SETS")

            val numbers = mutableSetOf(1,1,1,2,)
            numbers.add(3)
            numbers.add(2)
            val numbers2 = mutableSetOf(3,2,1)
            assert(numbers.contains(1))
            assert(numbers.contains(2))
            assert(numbers.contains(3))
            assertEquals(3, numbers.size)
            assertEquals(3, numbers2.size)
            assertEquals(numbers, numbers2)

            var cnt = 0
            for(number in numbers) { // iterate values
                cnt+=1
            }

            assertEquals(3,cnt)

            Collections().endUnitTestSection("COLLECTIONS.MUTABLE_SETS")
        }

        @Test
        fun maps() {
            Collections().unitTestSection("COLLECTIONS.MAPS")

            val numbers = mapOf("three" to 3, "two" to 2, "one" to 1)

            if(1 in numbers.values) {
                assert(true)
            } else {
                assert(false)
            }

            assertEquals(3, numbers["three"])
            assertEquals(2, numbers["two"])
            assertEquals(1, numbers["one"])

            var cnt = 0
            for(number in numbers) { // iterate values
                cnt+=1
            }

            assertEquals(3,cnt)

            cnt = 0
            val numbersIterator = numbers.iterator()
            while(numbersIterator.hasNext()) {
                numbersIterator.next()
                cnt+=1
            }

            assertEquals(3,cnt)

            while(cnt in 0..2) {
                when (cnt) {
                    0 -> assertEquals(1,numbers["one"])
                    1 -> assertEquals(2,numbers["two"])
                    2 -> assertEquals(3,numbers["three"])
                }
            }

            assertEquals(3,cnt)

            Collections().endUnitTestSection("COLLECTIONS.MAPS")
        }

        @Test
        fun mutableMaps() {
            Collections().unitTestSection("COLLECTIONS.MUTABLE_MAPS")

            val numbers = mutableMapOf("three" to 3, "one" to 1)
            numbers["two"] = 2

            if(1 in numbers.values) {
                assert(true)
            } else {
                assert(false)
            }

            assertEquals(3, numbers["three"])
            assertEquals(2, numbers["two"])
            assertEquals(1, numbers["one"])

            var cnt = 0
            for(number in numbers) { // iterate values
                cnt+=1
            }

            assertEquals(3,cnt)

            cnt = 0
            val numbersIterator = numbers.iterator()
            while(numbersIterator.hasNext()) {
                numbersIterator.next()
                cnt+=1
            }

            assertEquals(3,cnt)

            while(cnt in 0..2) {
                when (cnt) {
                    0 -> assertEquals(1,numbers["one"])
                    1 -> assertEquals(2,numbers["two"])
                    2 -> assertEquals(3,numbers["three"])
                }
            }

            assertEquals(3,cnt)

            Collections().endUnitTestSection("COLLECTIONS.MUTABLE_MAPS")
        }

        @Test
        fun sequences() {
            Collections().unitTestSection("COLLECTIONS.SEQUENCES")
            // lists (and I assume, any Iterable) can be converted to sequences
            val wordsSequence = "The quick brown fox jumps over the lazy dog".split(" ").asSequence()

            // From the human reader's perspective, Sequences act essentially the same as normal
            // Iterables. However, they differ internally, in that Iterables are processed eagerly
            // (all elements are processed immediately when iterated through), while Sequences are
            // processed lazily (elements are only evaluated when they the results are required).
            // Additionally, Sequences process each element through the entire chain of operations
            // before pulling the next element. IE, in the example below, the string "The" will be
            // checked for length, converted to length, and placed in the final result container
            // before "quick" is processed.
            val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length <= 4 }
                .map { println("length: ${it.length}"); it.length }
                .take(3)

            assertEquals(3,lengthsSequence.elementAt(0))
            assertEquals(3,lengthsSequence.elementAt(1))
            assertEquals(4,lengthsSequence.elementAt(2))
            Collections().endUnitTestSection("COLLECTIONS.SEQUENCES")
        }

        @Test
        fun transformations() {
            Collections().unitTestSection("COLLECTIONS.TRANSFORMATIONS")
            // transformations create a collection of the results of a function on the elements of another collection
            val numbers = listOf(1, 2, 3)
            val mult3 = numbers.map { it * 3 }
            assertEquals(3, mult3[0])
            assertEquals(6, mult3[1])
            assertEquals(9, mult3[2])
            Collections().endUnitTestSection("COLLECTIONS.TRANSFORMATIONS")
        }

        @Test
        fun filtering() {
            Collections().unitTestSection("COLLECTIONS.FILTERING")
            // filtering in Kotlin is producing a subset collection from a parent collection based
            // on a predicate. A predicate is a function which can return either true or false,
            // if the predicate returns true the element passed as an argument to the predicate will
            // be appended to the subset collection
            val numbers = listOf(1,2,3,4,5,6)
            val evens = numbers.filter { it % 2 == 0}
            assertEquals(2, evens[0])
            assertEquals(4, evens[1])
            assertEquals(6, evens[2])
            Collections().endUnitTestSection("COLLECTIONS.FILTERING")
        }

        @Test
        fun grouping() {
            Collections().unitTestSection("COLLECTIONS.GROUPING")
            // grouping is the action of applying a predicate onto a Collection and returning a map
            // where each key in the map is a value returned from the predicate and each value is
            // the list of elements for which the predicate returned said key
            val numbers = listOf("one", "two", "three", "four", "five", "six")

            val group = numbers.groupBy { it.first().uppercase() }
            var it = group.iterator()
            val group1 = it.next()
            val group2 = it.next()
            val group3 = it.next()
            val group4 = it.next()

            assertEquals("O", group1.key)
            assertEquals("one", group1.value[0])

            assertEquals("T", group2.key)
            assertEquals("two", group1.value[0])
            assertEquals("three", group1.value[1])

            assertEquals("F", group2.key)
            assertEquals("four", group1.value[0])
            assertEquals("five", group1.value[1])

            assertEquals("S", group1.key)
            assertEquals("six", group1.value[0])
            Collections().endUnitTestSection("COLLECTIONS.GROUPING")
        }

        @Test
        fun retrieveCollectionParts() {
            Collections().unitTestSection("COLLECTIONS.RETRIEVE_COLLECTION_PARTS")
            val numbers = listOf("one", "two", "three", "four", "five", "six")

            // slice
            run {
                val slice1 = numbers.slice(1..3)
                val slice2 = numbers.slice(0..4 step 2)
                val slice3 = numbers.slice(setOf(3,5,0))

                assertEquals("two", slice1[0])
                assertEquals("three", slice1[1])
                assertEquals("four", slice1[2])
                assertEquals("one", slice2[0])
                assertEquals("three", slice2[1])
                assertEquals("five", slice2[2])
                assertEquals("four", slice3[0])
                assertEquals("six", slice3[1])
                assertEquals("one", slice3[2])
            }

            // take and drop
            run {
                val took1 = numbers.take(3)
                val took2 = numbers.takeLast(3)
                val took3 = numbers.drop(1)
                val took4 = numbers.dropLast(5)

                assertEquals("one", took1[0])
                assertEquals("two", took1[1])
                assertEquals("three", took1[2])
                assertEquals("four", took2[0])
                assertEquals("five", took2[1])
                assertEquals("six", took2[2])
            }

            // chunked
            run {
                val numbers = (0..7).toList()
                val chunks = numbers.chunked(3)

                assertEquals(listOf(0,1,2), chunks[0])
                assertEquals(listOf(3,4,5), chunks[1])
                assertEquals(listOf(6,7), chunks[2])
            }

            // windowed
            run {
                val numbers = listOf("one", "two", "three", "four", "five")
                val windows = numbers.windowed(3)

                assertEquals(listOf("one","two","three"), windows[0])
                assertEquals(listOf("two","three","four"), windows[1])
                assertEquals(listOf("three","four","five"), windows[2])
            }

            Collections().endUnitTestSection("COLLECTIONS.RETRIEVE_COLLECTION_PARTS")
        }

        @Test
        fun retrieveSingleElements() {
            Collections().unitTestSection("COLLECTIONS.RETRIEVE_SINGLE_ELEMENTS")
            val numbers = listOf("one", "two", "three", "four", "five")

            // retrieve by position: elementAt, first, last, elementAtOrNull, elementAtOrElse
            assertEquals("three", numbers.elementAt(2))
            assertEquals("one", numbers.first())
            assertEquals("five", numbers.last())
            assertEquals("five",numbers.elementAtOrNull(4))
            assertEquals(null,numbers.elementAtOrNull(5))
            assertEquals("five",numbers.elementAtOrElse(4) { null })
            assertEquals(null,numbers.elementAtOrElse(5) { null })

            // retrieve by condition: first, last, firstOrNull, lastOrNull
            assertEquals("four", numbers.first { it.length == 4 })
            assertEquals("three", numbers.last { it.startsWith("t") })
            assertEquals("three", numbers.firstOrNull { it.length == 5 })
            assertEquals(null, numbers.firstOrNull { it.length == 6 })
            assertEquals("two", numbers.lastOrNull { it.length == 3 })
            assertEquals(null, numbers.lastOrNull { it.length == 1 })

            // firstNotNullOf, firstNotNullOfOrNull
            run {
                val checkNull: (Any?) -> Any? = {
                    if(it != null && it != "MAYBENULL")  {
                        it
                    } else {
                        null
                    }
                }
                val assortment = listOf(null, "MAYBENULL", null, "NOTNULL", null)
                assertEquals("NOTNULL", assortment.firstNotNullOf(checkNull))
                assertEquals("NOTNULL", assortment.firstNotNullOfOrNull(checkNull))
                assertEquals(null, assortment.firstNotNullOfOrNull { null })
            }

            // random ommitted due to low importance

            // contains, containsAll, isEmpty, isNotEmpty
            assertTrue(numbers.contains("two"))
            assertTrue(numbers.containsAll(listOf("four","one","three")))
            assertFalse(numbers.isEmpty())
            assertTrue(numbers.isNotEmpty())

            Collections().endUnitTestSection("COLLECTIONS.RETRIEVE_SINGLE_ELEMENTS")
        }

        @Test
        fun ordering() {
            Collections().unitTestSection("COLLECTIONS.ORDERING")

            // natural order
            run {
                val numbers = listOf("one", "two", "three", "four")
                val sortedNumbers = numbers.sorted()
                assertTrue("four" == sortedNumbers.elementAt(0))
                assertTrue("one" == sortedNumbers.elementAt(1))
                assertTrue("three" == sortedNumbers.elementAt(2))
                assertTrue("two" == sortedNumbers.elementAt(3))
            }

            // custom order
            run {
                class Version(val major: Int, val minor: Int): Comparable<Version> {
                    override fun compareTo(other: Version): Int = when {
                        this.major != other.major -> this.major.compareTo(other.major) // compareTo() in the infix form
                        this.minor != other.minor -> this.minor.compareTo(other.minor)
                        else -> 0
                    }
                }

                val versions = listOf(Version(1,5), Version(2,3), Version(2,4), Version(2,1), Version(1,1))
                assertTrue(0 == Version(1,5).compareTo(versions.elementAt(0)))
                assertTrue(Version(1,5) < versions.elementAt(1))
                assertTrue(Version(3,0) > versions.elementAt(1))

                val versionComparator = Comparator { v1: Version, v2: Version -> v1.compareTo(v2) }
                val sortedVersions = versions.sortedWith(versionComparator)
                assertTrue(versions.elementAt(0) == sortedVersions.elementAt(1))
                assertTrue(versions.elementAt(1) == sortedVersions.elementAt(3))
                assertTrue(versions.elementAt(2) == sortedVersions.elementAt(4))
                assertTrue(versions.elementAt(3) == sortedVersions.elementAt(2))
                assertTrue(versions.elementAt(4) == sortedVersions.elementAt(0))
            }

            // reverse order
            run {
                val numbers = listOf("one", "two", "three", "four")
                val reversedNumbers = numbers.reversed()
                assertEquals("four", reversedNumbers.elementAt(0))
                assertEquals("three", reversedNumbers.elementAt(1))
                assertEquals("two", reversedNumbers.elementAt(2))
                assertEquals("one", reversedNumbers.elementAt(3))
            }

            // skipping random order as unimportant

            Collections().endUnitTestSection("COLLECTIONS.ORDERING")
        }

        @Test
        fun aggregateOperations() {
            Collections().unitTestSection("COLLECTIONS.AGGREGATE_OPERATIONS")

            val numbers = listOf(1,2,3,4)

            // standard operations
            run {
                assertEquals(1, numbers.minOrNull())
                assertEquals(4, numbers.maxOrNull())
                assertEquals(10, numbers.sum())
                assertEquals(4, numbers.count())
            }

            // fold
            run {
                val foldSum = numbers.fold(0) { sum, element -> sum + element }
                assertEquals(10, foldSum)
            }

            // reduce is just a slightly simplified fold where the initial value is elementAt(0)
            run {
                val reduceSum = numbers.reduce { sum, element -> sum + element }
                assertEquals(10, reduceSum)
            }

            Collections().endUnitTestSection("COLLECTIONS.AGGREGATE_OPERATIONS")
        }

        @Test
        fun write() {
            Collections().unitTestSection("COLLECTIONS.WRITE")

            // add
            val numbers = mutableListOf(1,2,3)
            assertEquals(3, numbers.size)
            numbers.add(4)
            numbers.add(5)
            assertEquals(5, numbers.size)
            assertEquals(1, numbers.elementAt(0))
            assertEquals(2, numbers.elementAt(1))
            assertEquals(3, numbers.elementAt(2))
            assertEquals(4, numbers.elementAt(3))
            assertEquals(5, numbers.elementAt(4))


            // remove
            assertEquals(5, numbers.size)
            numbers.remove(3)
            assertEquals(4, numbers.size)
            assertEquals(1, numbers.elementAt(0))
            assertEquals(2, numbers.elementAt(1))
            assertEquals(4, numbers.elementAt(2))
            assertEquals(5, numbers.elementAt(3))

            // update
            numbers.set(1,6)
            assertEquals(4, numbers.size)
            assertEquals(1, numbers.elementAt(0))
            assertEquals(6, numbers.elementAt(1))
            assertEquals(4, numbers.elementAt(2))
            assertEquals(5, numbers.elementAt(3))

            Collections().endUnitTestSection("COLLECTIONS.WRITE")
        }

        @Test
        fun scopeFunctions() {
            Collections().unitTestSection("COLLECTIONS.SCOPE_FUNCTIONS")

            val numbers = mutableListOf("one","two","three","four","five")

            // let | arg: it, return: lambda result, extension function
            assertEquals("six", numbers.let { it.add("six"); "six" })
            assertEquals(6,numbers.size)
            assertEquals("two", numbers.elementAt(1))
            assertEquals("six", numbers.elementAt(5))

            // with | arg: this, return: lambda result, takes context object as an argument
            assertEquals("seven", with(numbers) {
                this.add("seven")
                "seven"
            })
            assertEquals(7,numbers.size)
            assertEquals("six", numbers.elementAt(5))
            assertEquals("seven", numbers.elementAt(6))

            // run | arg: this, return: lambda result, extension function
            assertEquals("eight", numbers.run {
                this.add("eight")
                "eight"
            })
            assertEquals(8,numbers.size)
            assertEquals("seven", numbers.elementAt(6))
            assertEquals("eight", numbers.elementAt(7))

            // apply | arg: this, return: context object, extension function
            assertEquals(numbers, numbers.apply { this.add("nine") })
            assertEquals(9,numbers.size)
            assertEquals("eight", numbers.elementAt(7))
            assertEquals("nine", numbers.elementAt(8))

            // also | arg: it, return: context object, extension function
            assertEquals(numbers, numbers.also { it.add("ten") })
            assertEquals(10,numbers.size)
            assertEquals("nine", numbers.elementAt(8))
            assertEquals("ten", numbers.elementAt(9))

            // takeIf | arg: it, return: object if matches predicate else null, extension function
            assertEquals(numbers, numbers.takeIf { it == numbers })
            assertEquals(null, numbers.takeIf { it != numbers })

            // takeUnless | arg: it, return: object if does not match predicate else null, extension function
            assertEquals(null, numbers.takeUnless { it == numbers })
            assertEquals(numbers, numbers.takeUnless { it != numbers })

            Collections().endUnitTestSection("COLLECTIONS.SCOPE_FUNCTIONS")
        }
    }

    class Coroutines : TestUtilities() {
        // coroutines are a *very* broad topic, so I have chosen to create a small subset of tests
        // which show basic usage

        suspend fun doWorld() {
            delay(1000L)
            println("World!")
        }

        @Test
        fun basicCoroutine() {
            runBlocking {
                launch { doWorld() }
                println("Hello")
            }
        }

        @Test
        fun coroutineChannel() {
            var recv_vals: MutableList<String> = mutableListOf()

            runBlocking {
                val ch = Channel<String>()

                launch {
                    ch.send("one ")
                    ch.send("more ")
                    ch.send("time")
                    ch.close()
                }

                launch {
                    for (s in ch) {
                        recv_vals.add(s)
                    }
                }
            }

            assertEquals("one ", recv_vals[0])
            assertEquals("more ", recv_vals[1])
            assertEquals("time", recv_vals[2])
        }

        @Test
        fun coroutineChannelExceptionCatching() {
            var recv_vals: MutableList<String> = mutableListOf()
            var caught = false

            runBlocking {
                val ch = Channel<String>()

                launch {
                    ch.send("one ")
                    ch.send("more ")
                    ch.send("time")
                    ch.close()
                }

                launch {
                    try {
                        while(true) {
                            recv_vals.add(ch.receive())
                        }
                    } catch (ce : ClosedReceiveChannelException) {
                        caught = true
                    }
                }
            }

            assertTrue(caught)
            assertEquals("one ", recv_vals[0])
            assertEquals("more ", recv_vals[1])
            assertEquals("time", recv_vals[2])
        }
    }
}
