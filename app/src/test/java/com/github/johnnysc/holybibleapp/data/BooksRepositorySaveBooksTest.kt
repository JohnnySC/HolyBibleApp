package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.books.BaseBooksRepository
import com.github.johnnysc.holybibleapp.data.books.BookData
import com.github.johnnysc.holybibleapp.data.books.BooksData
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BookDb
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BookCloud
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.core.Limits
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author Asatryan on 29.06.2021
 **/
class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BaseBooksRepository(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actualCloud = repository.fetchData()
        val expectedCloud = BooksData.Success(
            listOf(
                BookData.Base(0, "name0", "ot"),
                BookData.Base(1, "name1", "nt")
            )
        )

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchData()
        val expectedCache = BooksData.Success(
            listOf(
                BookData.Base(0, "name0 db", "ot db"),
                BookData.Base(1, "name1 db", "nt db")
            )
        )

        assertEquals(expectedCache, actualCache)
    }

    private inner class TestBooksCloudDataSource : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud.Base> {
            return listOf(
                BookCloud.Base(0, "name0", "ot"),
                BookCloud.Base(1, "name1", "nt")
            )
        }
    }

    private inner class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override fun read() = list

        override fun save(data: List<BookData>) {
            data.map { book ->
                list.add(book.map(object : BookDataToDbMapper<BookDb> {

                    override fun mapToDb(
                        id: Int,
                        name: String,
                        testament: String,
                        db: DbWrapper<BookDb>
                    ) = BookDb().apply {
                        this.id = id
                        this.name = "$name db"
                        this.testament = "$testament db"
                    }

                }, object : DbWrapper<BookDb> {
                    override fun createObject(id: Int) = BookDb().apply {
                        this.id = id
                    }
                }))
            }
        }

        override fun favorites(limits: Limits) = ArrayList<Int>()
        override fun changeFavorite(id: Int) = Unit
    }
}