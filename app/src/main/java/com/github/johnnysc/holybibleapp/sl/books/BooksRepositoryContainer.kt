package com.github.johnnysc.holybibleapp.sl.books

import com.github.johnnysc.holybibleapp.data.books.BaseBooksRepository
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.*
import com.github.johnnysc.holybibleapp.domain.books.BooksRepository
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.core.RepositoryContainer

/**
 * @author Asatryan on 30.07.2021
 **/
class BooksRepositoryContainer(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
    private val booksRu: () -> List<BookRu>,
) : RepositoryContainer<BooksRepository> {

    override fun repository(): BooksRepository {
        val toBookMapper = ToBookMapper.Base()
        return BaseBooksRepository(if (useMocks)
            mockBooksCloudDataSource()
        else
            booksCloudDataSource(),
            BooksCacheDataSource.Base(coreModule.realmProvider, BookDataToDbMapper.Base()),
            BooksCloudMapper.Base(toBookMapper),
            BooksCacheMapper.Base(toBookMapper)
        )
    }

    private fun booksCloudDataSource() =
        BooksCloudDataSource.Base(coreModule.language, english(), russian())

    private fun english() =
        BooksCloudDataSource.English(booksService(), coreModule.gson, booksTypeToken())

    private fun mockBooksCloudDataSource() = if (coreModule.language.isChosenRussian())
        russian()
    else
        BooksCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson, booksTypeToken())

    private fun booksTypeToken() = BooksTypeToken()

    private fun russian() = BooksCloudDataSource.Russian(booksRu)

    private fun booksService() = coreModule.makeService(BooksService::class.java)
}