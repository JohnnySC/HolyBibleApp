package com.github.johnnysc.holybibleapp.core

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [Multiply]
 *
 * @author Asatryan on 01.08.2021
 */
class MultiplyTest {

    @Test
    fun test_one_time() {
        val actual = Multiply(1).map(5)
        val expected = 5000
        assertEquals(expected, actual)
    }

    @Test
    fun test_two_times() {
        val actual = Multiply(2).map(7)
        val expected = 7_000_000
        assertEquals(expected, actual)
    }

    @Test
    fun test_negative_times() {
        val actual = Multiply(-3).map(4)
        val expected = 4000
        assertEquals(expected, actual)
    }

    @Test
    fun test_int_max() {
        val actual = Multiply(8).map(3)
        val expected = 2_000_000_000
        assertEquals(expected, actual)
    }

    @Test
    fun test_zero() {
        val actual = Multiply(0).map(9)
        val expected = 9000
        assertEquals(expected, actual)
    }

    @Test
    fun test_zero_as_argument() {
        val actual = Multiply(3).map(0)
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun test_negative_argument() {
        val actual = Multiply(2).map(-7)
        val expected = -7_000_000
        assertEquals(expected, actual)
    }

    @Test
    fun test_rest() {
        val actual = Multiply().rest(5008)
        val expected = 8
        assertEquals(expected, actual)
    }

    @Test
    fun test_int_min() {
        val actual = Multiply(8).map(-9)
        val expected = -2_000_000_000
        assertEquals(expected, actual)
    }
}