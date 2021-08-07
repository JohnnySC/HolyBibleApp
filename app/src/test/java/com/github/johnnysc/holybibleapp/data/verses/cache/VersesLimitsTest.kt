package com.github.johnnysc.holybibleapp.data.verses.cache

import com.github.johnnysc.holybibleapp.core.Multiply
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 17.07.2021
 */
class VersesLimitsTest {

    @Test
    fun test() {
        val limits = VersesLimits(66, 150, Multiply(2), Multiply(1))
        val expected = Pair(66_150_000, 66_151_000)
        val actual = Pair(limits.min(), limits.max())
        assertEquals(expected, actual)
    }
}