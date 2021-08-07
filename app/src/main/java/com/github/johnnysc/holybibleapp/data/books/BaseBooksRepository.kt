package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.data.books.cache.BookDb
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BookCloud
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.core.AbstractRepository
import com.github.johnnysc.holybibleapp.data.core.Limits
import com.github.johnnysc.holybibleapp.domain.books.BooksRepository

/**
 * @author Asatryan on 26.06.2021
 **/
class BaseBooksRepository(
    private val cloudDataSource: BooksCloudDataSource,
    private val cacheDataSource: BooksCacheDataSource,
    booksCloudMapper: BooksCloudMapper,
    booksCacheMapper: BooksCacheMapper
) : AbstractRepository<BookDb, BookCloud, BookData, BooksData>(
    cacheDataSource,
    booksCloudMapper,
    booksCacheMapper
), BooksRepository {

    override suspend fun fetchCloudData() = cloudDataSource.fetchBooks()
    override fun cachedDataList() = cacheDataSource.read()
    override fun returnSuccess(list: List<BookData>) = BooksData.Success(list)
    override fun returnFail(e: Exception) = BooksData.Fail(e)
    override fun limits(): Limits = BooksLimits()
}