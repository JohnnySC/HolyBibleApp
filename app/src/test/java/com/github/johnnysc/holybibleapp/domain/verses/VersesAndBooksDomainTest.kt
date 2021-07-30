package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersData
import com.github.johnnysc.holybibleapp.data.verses.VerseData
import com.github.johnnysc.holybibleapp.data.verses.VersesData
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

/**
 * Test for [VersesAndBooksDomain]
 *
 * @author Asatryan on 30.07.2021
 */
class VersesAndBooksDomainTest {

    @Test
    fun test_success() {
        val title = BookData.Base(10, "name", "OT")
        val versesDomain = listOf(
            VerseDomain.Base(1001, "verse")
        )

        val domain = VersesAndBooksDomain(
            VersesData.Success(listOf(VerseData.Base(1, 1001, "verse"))),
            BooksData.Success(
                listOf(BookData.Base(10, "name", "OT"))
            ),
            ChaptersData.Success(listOf(ChapterData.Base(ChapterId.Base(1, 1)))),
            object : Read<Int> {
                override fun read() = 10
            },
            object : Read<Int> {
                override fun read() = 1
            }
        )

        val actual = domain.map(BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()))
        val expected = VersesDomain.Success(versesDomain, title, 1)
        assertEquals(expected, actual)
    }

    @Test
    fun test_verses_fail() {
        val domain = VersesAndBooksDomain(
            VersesData.Fail(UnknownHostException()),
            BooksData.Success(listOf(BookData.Base(10, "name", "OT"))),
            ChaptersData.Success(listOf(ChapterData.Base(ChapterId.Base(1, 1)))),
            object : Read<Int> {
                override fun read() = 10
            },
            object : Read<Int> {
                override fun read() = 1
            }
        )

        val actual = domain.map(BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()))
        val expected = VersesDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
    }

    @Test
    fun test_chapters_fail() {
        val domain = VersesAndBooksDomain(
            VersesData.Success(listOf(VerseData.Base(1, 1001, "verse"))),
            BooksData.Success(listOf(BookData.Base(10, "name", "OT"))),
            ChaptersData.Fail(UnknownHostException()),
            object : Read<Int> {
                override fun read() = 10
            },
            object : Read<Int> {
                override fun read() = 1
            }
        )

        val actual = domain.map(BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()))
        val expected = VersesDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)
    }

    @Test
    fun test_books_fail() {
        val domain = VersesAndBooksDomain(
            VersesData.Success(listOf(VerseData.Base(1, 1001, "verse"))),
            BooksData.Fail(HttpException(Response.success(200, null as Int?))),
            ChaptersData.Success(listOf(ChapterData.Base(ChapterId.Base(1, 1)))),
            object : Read<Int> {
                override fun read() = 10
            },
            object : Read<Int> {
                override fun read() = 1
            }
        )

        val actual = domain.map(BaseVersesDataToDomainMapper(BaseVerseDataToDomainMapper()))
        val expected = VersesDomain.Fail(ErrorType.SERVICE_UNAVAILABLE)
        assertEquals(expected, actual)
    }
}