package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.presentation.books.BookUi
import com.github.johnnysc.holybibleapp.presentation.books.CollapsedIdsCache
import com.github.johnnysc.holybibleapp.presentation.books.UiDataCache
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [UiDataCache.Base] method saveState
 * @author Asatryan on 12.07.2021
 **/
class UiDataCacheSaveDataTest {

    @Test
    fun test_no_collapsed() {
        val cache = TestCollapsedIdsCache()
        val uiDataCache = UiDataCache.Base(cache)

        uiDataCache.cache(
            listOf(
                BookUi.Testament(0, "0"),
                BookUi.Base(1, "1"),
                BookUi.Testament(2, "2"),
                BookUi.Base(3, "3"),
            )
        )
        uiDataCache.saveState()
        val expected = emptySet<Int>()
        val actual = cache.read()
        assertEquals(expected, actual)
    }

    @Test
    fun test_one_collapsed() {
        val cache = TestCollapsedIdsCache()
        val uiDataCache = UiDataCache.Base(cache)

        uiDataCache.cache(
            listOf(
                BookUi.Testament(0, "0", true),
                BookUi.Testament(2, "2"),
                BookUi.Base(3, "3"),
            )
        )
        uiDataCache.saveState()
        val expected = setOf(0)
        val actual = cache.read()
        assertEquals(expected, actual)
    }

    @Test
    fun test_two_collapsed() {
        val cache = TestCollapsedIdsCache()
        val uiDataCache = UiDataCache.Base(cache)

        uiDataCache.cache(
            listOf(
                BookUi.Testament(0, "0", true),
                BookUi.Testament(2, "2", true)
            )
        )
        uiDataCache.saveState()
        val expected = setOf(0, 2)
        val actual = cache.read()
        assertEquals(expected, actual)
    }


    private inner class TestCollapsedIdsCache : CollapsedIdsCache {

        private val list = ArrayList<Int>()

        override fun start() {
            list.clear()
        }

        override fun finish() = Unit

        override fun save(data: Int) {
            list.add(data)
        }

        override fun read() = list.toSet()
    }
}
