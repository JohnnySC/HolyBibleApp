package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.data.cache.BookDb
import com.github.johnnysc.holybibleapp.data.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.cache.DbWrapper
import com.github.johnnysc.holybibleapp.data.net.BookCloud
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
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestToBookMapper()),
            BooksCacheMapper.Base(TestToBookMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(
            listOf(
                BookData(0, "name0", "ot"),
                BookData(1, "name1", "nt")
            )
        )

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                BookData(0, "name0 db", "ot db"),
                BookData(1, "name1 db", "nt db")
            )
        )

        assertEquals(expectedCache, actualCache)
    }

    private inner class TestBooksCloudDataSource : BooksCloudDataSource {

        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0", "ot"),
                BookCloud(1, "name1", "nt")
            )
        }
    }

    private inner class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = ArrayList<BookDb>()

        override fun fetchBooks() = list

        override fun saveBooks(books: List<BookData>) {
            books.map { book ->
                list.add(book.mapTo(object : BookDataToDbMapper {
                    override fun mapToDb(id: Int, name: String, testament:String, db: DbWrapper) = BookDb().apply {
                        this.id = id
                        this.name = "$name db"
                        this.testament = "$testament db"
                    }

                }, object : DbWrapper {
                    override fun createObject(id: Int) = BookDb().apply {
                        this.id = id
                    }
                }))
            }
        }
    }
}