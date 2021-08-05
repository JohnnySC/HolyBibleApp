package com.github.johnnysc.holybibleapp.data.verse

import com.github.johnnysc.holybibleapp.core.Save
import com.github.johnnysc.holybibleapp.presentation.deeplink.DeeplinkData
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [DeeplinkIds]
 *
 * @author Asatryan on 05.08.2021
 */
class DeeplinkIdsTest {

    @Test
    fun test_correct() {
        val saveBookId = TestSaveId()
        val saveChapterId = TestSaveId()
        val saveVerseId = TestSaveId()
        val ids = DeeplinkIds(saveBookId, saveChapterId, saveVerseId, DeeplinkData.Base("testURL"))
        ids.save("testURL1_2_3")
        assertEquals(1, saveBookId.id)
        assertEquals(2, saveChapterId.id)
        assertEquals(3, saveVerseId.id)
    }

    @Test
    fun test_incorrect() {
        val saveBookId = TestSaveId()
        val saveChapterId = TestSaveId()
        val saveVerseId = TestSaveId()
        val ids = DeeplinkIds(saveBookId, saveChapterId, saveVerseId, DeeplinkData.Base("testURL"))
        ids.save("testURL1_2_a")
        assertEquals(0, saveBookId.id)
        assertEquals(0, saveChapterId.id)
        assertEquals(0, saveVerseId.id)
    }

    private inner class TestSaveId : Save<Int> {
        var id: Int = 0
        override fun save(data: Int) {
            id = data
        }
    }
}