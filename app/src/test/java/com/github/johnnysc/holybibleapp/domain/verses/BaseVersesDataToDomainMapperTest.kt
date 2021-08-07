package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import com.github.johnnysc.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Test for [BaseVersesDataToDomainMapper]
 *
 * @author Asatryan on 30.07.2021
 */
class BaseVersesDataToDomainMapperTest {

    @Test
    fun test_last_item() {
        val mapper = BaseVersesDataToDomainMapper(
            BaseVerseDataToDomainMapper(),
            BaseBookDataToDomainMapper()
        )
        val book = BookDomain.Base(4, "4")
        val list = listOf(VerseDomain.Base(1, 1001, "verse1"))
        val expected = VersesDomain.Success(list, book, 0)
        val actual = mapper.map(
            Triple(
                listOf(VerseData.Base(1, 1001, "verse1")),
                BookData.Base(4, "4", "ot"),
                Pair(0, true)
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_not_last_item() {
        val mapper = BaseVersesDataToDomainMapper(
            BaseVerseDataToDomainMapper(),
            BaseBookDataToDomainMapper()
        )

        val list = listOf(
            VerseDomain.Base(1, 1001, "verse1"),
            VerseDomain.Next
        )
        val expected = VersesDomain.Success(list, BookDomain.Base(4, "4"), 0)
        val actual = mapper.map(
            Triple(
                listOf(VerseData.Base(1, 1001, "verse1")),
                BookData.Base(4, "4", "ot"),
                Pair(0, false)
            )
        )
        assertEquals(expected, actual)
    }
}