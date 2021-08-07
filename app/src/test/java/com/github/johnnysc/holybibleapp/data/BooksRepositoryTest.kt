package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.books.BaseBooksRepository
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.data.books.cache.BookDb
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BookCloud
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.core.Limits
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException

/**
 * @author Asatryan on 27.06.2021
 */
class BooksRepositoryTest : BaseBooksRepositoryTest() {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BaseBooksRepository(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchData()
        val expected = BooksData.Fail(unknownHostException)

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BaseBooksRepository(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchData()
        val expected = BooksData.Success(
            listOf(
                BookData.Base(0, "name0", "ot"),
                BookData.Base(1, "name1", "ot"),
                BookData.Base(2, "name2", "ot"),
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BaseBooksRepository(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchData()
        val expected = BooksData.Success(
            listOf(
                BookData.Base(10, "name10", "nt"),
                BookData.Base(11, "name11", "nt"),
                BookData.Base(12, "name12", "nt"),
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BaseBooksRepository(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actual = repository.fetchData()
        val expected = BooksData.Success(
            listOf(
                BookData.Base(10, "name10", "nt"),
                BookData.Base(11, "name11", "nt"),
                BookData.Base(12, "name12", "nt"),
            )
        )
        assertEquals(expected, actual)
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean
    ) : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud.Base> {
            if (returnSuccess) {
                return listOf(
                    BookCloud.Base(0, "name0", "ot"),
                    BookCloud.Base(1, "name1", "ot"),
                    BookCloud.Base(2, "name2", "ot"),
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestBooksCacheDataSource(
        private val returnSuccess: Boolean
    ) : BooksCacheDataSource {

        override fun read(): List<BookDb> {
            return if (returnSuccess)
                listOf(
                    BookDb().apply {
                        id = 10
                        name = "name10"
                        testament = "nt"
                    },
                    BookDb().apply {
                        id = 11
                        name = "name11"
                        testament = "nt"
                    },
                    BookDb().apply {
                        id = 12
                        name = "name12"
                        testament = "nt"
                    }
                ) else {
                emptyList()
            }
        }

        override fun save(data: List<BookData>) {
            // not used here
        }

        override fun favorites(limits: Limits) = ArrayList<Int>()
        override fun changeFavorite(id: Int) = Unit
    }
}