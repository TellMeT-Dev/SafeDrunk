package com.example.safedrunk.model

enum class Operator (val symbol: String){
    PLUS("+"),
    MINUS("-"),
    TIMES("x")
}

data class MathChallenge(val a: Int,
    val b: Int,
    val operator: Operator){
    val answer: Int
        get() = when(operator) {
            Operator.PLUS -> a + b
            Operator.MINUS -> a - b
            Operator.TIMES -> a * b
        }
    val text: String get() = "$a ${operator.symbol} $b"
    fun isCorrect(input: String): Boolean = input.toIntOrNull() == answer

    companion object {
        const val MAX_ANSWER_DIGITS = 3
    }
}