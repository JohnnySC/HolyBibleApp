package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Repository
import com.github.johnnysc.holybibleapp.data.books.cache.BookDb
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BookCloud
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper

/**
 * @author Asatryan on 26.06.2021
 **/
interface BooksRepository : Repository<BooksData> {

    class Base(
        private val cloudDataSource: BooksCloudDataSource,
        private val cacheDataSource: BooksCacheDataSource,
        booksCloudMapper: BooksCloudMapper,
        booksCacheMapper: BooksCacheMapper
    ) : Repository.Base<BookDb, BookCloud, BookData, BooksData>(
        cacheDataSource,
        booksCloudMapper,
        booksCacheMapper
    ), BooksRepository {

        override suspend fun fetchCloudData() = cloudDataSource.fetchBooks()
        override fun getCachedDataList() = cacheDataSource.read()
        override fun returnSuccess(list: List<BookData>) = BooksData.Success(list)
        override fun returnFail(e: Exception) = BooksData.Fail(e)
    }

    class Mock(
        private val cloudDataSource: BooksCloudDataSource,
        private val booksCloudMapper: BooksCloudMapper,
    ) : BooksRepository {
        override suspend fun fetchData(): BooksData {
            val books = cloudDataSource.fetchBooks()
            return BooksData.Success(booksCloudMapper.map(books))
        }
    }
}