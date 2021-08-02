package com.github.johnnysc.holybibleapp.core

/**
 * Just multiply integers, use 1 - 3 times, not more, because of [Int.MAX_VALUE] and [Int.MIN_VALUE]
 *
 * @author Asatryan on 01.08.2021
 **/
class Multiply(times: Int = 1) : Abstract.Mapper.Data<Int, Int> {
    private val repeatCount: Int = if (times < 1) 1 else if (times > 3) 3 else times

    override fun map(data: Int): Int {
        if (data == 0) return 0
        val number = if (repeatCount == 3 && data > 2) 2 else data
        var result = number
        for (i in 0 until repeatCount) {
            result *= MULTIPLY
        }
        return result
    }

    fun rest(number: Int) = number % MULTIPLY

    private companion object {
        const val MULTIPLY = 1000
    }
}