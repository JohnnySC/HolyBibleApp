package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersData
import com.github.johnnysc.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

/**
 * Test for [ChaptersAndBooksDomain]
 *
 * @author Asatryan on 02.08.2021
 */
class ChaptersAndBooksDomainTest {

    @Test
    fun test_success() {
        val chaptersAndBooksDomain = ChaptersAndBooksDomain(
            ChaptersData.Success(emptyList()),
            BooksData.Success(listOf(BookData.Base(5, "name5", "ot"))),
            TestIdContainer(5)
        )
        val expected = ChaptersDomain.Success(emptyList(), BookDomain.Base(5, "name5"))

        val actual = chaptersAndBooksDomain.map(BaseChaptersDataToDomainMapper(
            BaseChapterDataToDomainMapper(),
            BaseBookDataToDomainMapper()
        ))

        assertEquals(expected, actual)
    }

    @Test
    fun test_chapters_fail() {
        val chaptersAndBooksDomain = ChaptersAndBooksDomain(
            ChaptersData.Fail(UnknownHostException()),
            BooksData.Success(listOf(BookData.Base(5, "name5", "ot"))),
            TestIdContainer(5)
        )
        val expected = ChaptersDomain.Fail(ErrorType.NO_CONNECTION)

        val actual = chaptersAndBooksDomain.map(BaseChaptersDataToDomainMapper(
            BaseChapterDataToDomainMapper(),
            BaseBookDataToDomainMapper()
        ))

        assertEquals(expected, actual)
    }

    @Test
    fun test_books_fail() {
        val chaptersAndBooksDomain = ChaptersAndBooksDomain(
            ChaptersData.Success(emptyList()),
            BooksData.Fail(IllegalStateException("something wrong happened")),
            TestIdContainer(5)
        )
        val expected = ChaptersDomain.Fail(ErrorType.GENERIC_ERROR)

        val actual = chaptersAndBooksDomain.map(BaseChaptersDataToDomainMapper(
            BaseChapterDataToDomainMapper(),
            BaseBookDataToDomainMapper()
        ))

        assertEquals(expected, actual)
    }

    private inner class TestIdContainer(private val id: Int) : Read<Int> {
        override fun read() = id
    }
}