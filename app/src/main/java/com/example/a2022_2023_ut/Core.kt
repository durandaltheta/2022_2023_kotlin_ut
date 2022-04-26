package com.example.a2022_2023_ut

class CoreOp {
    fun argPrint(args: Array<String>) {
        for(s in args) {
            print(s)
            print(" ")
        }
    }

    enum class MathOp {
        Add,
        Sub,
        Mult,
        Div,
        Rem
    }

    fun math(op: MathOp, a: Int, b: Int): Int {
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

        when(op) {
            MathOp.Add -> return sum(a,b)
            MathOp.Sub -> return sub(a,b)
            MathOp.Mult -> return mult(a,b)
            MathOp.Div -> return div(a,b)
            MathOp.Rem -> return rem(a,b)
        }
    }
}