package com.github.johnnysc.holybibleapp.presentation.deeplink

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [DeeplinkData.Base]
 *
 * @author Asatryan on 05.08.2021
 */
class DeeplinkDataTest {

    @Test
    fun test_share_text() {
        val deeplinkData = DeeplinkData.Base("TestURL")
        val actual = deeplinkData.shareText("left", "right")
        val expected = "leftTestURLright"
        assertEquals(expected, actual)
    }

    @Test
    fun test_ids_correct() {
        val deeplinkData = DeeplinkData.Base("testURL")
        val actual = deeplinkData.ids("testURL1_2_3")
        val expected = listOf(1, 2, 3)
        assertEquals(expected, actual)
    }

    @Test
    fun test_ids_incorrect() {
        val deeplinkData = DeeplinkData.Base("testURL")
        val incorrectList = listOf(
            "",
            "_",
            "testURL",
            "testURL_",
            "testURL_!",
            "testURL!",
            "testURL!_e",
            "testURLx_x",
            "testURLx_x_$",
            "testURL__*___",
            "testURL_____",
        )
        val actualList = incorrectList.map {
            deeplinkData.ids(it)
        }
        val expected = emptyList<Int>()
        actualList.forEach { actual ->
            assertEquals(expected, actual)
        }
    }
}