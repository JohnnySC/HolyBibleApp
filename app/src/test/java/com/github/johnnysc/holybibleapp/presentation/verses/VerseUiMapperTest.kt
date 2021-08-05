package com.github.johnnysc.holybibleapp.presentation.verses

import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [VerseUiMapper.Share]
 *
 * @author Asatryan on 04.08.2021
 */
class VerseUiMapperTest {

    @Test
    fun test() {
        val mapper = VerseUiMapper.Share(DeeplinkData.Base("link"), "Genesis Ch.1")
        val item = VerseUi.Base(2_003_004, "text", false)
        val actual = item.map(mapper)
        val expected = "Genesis Ch.1\ntext\nlink2_3_4"
        assertEquals(expected, actual)
    }
}