package com.github.johnnysc.holybibleapp.data.verses.cloud

import com.github.johnnysc.holybibleapp.core.Multiply
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [VerseToWrapperMapper.Base]
 *
 * @author Asatryan on 30.07.2021
 */
class VerseToWrapperMapperTest {

    @Test
    fun test() {
        val mapper = VerseToWrapperMapper.Base(1, 2, 3, Multiply(2), Multiply())
        val actual = mapper.map("text")
        val expected = VerseRuWrapper(1_002_003, 3, "text")
        assertEquals(expected, actual)
    }
}