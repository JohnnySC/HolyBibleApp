package com.github.johnnysc.holybibleapp.sl.books

import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksService
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.core.RepositoryContainer

/**
 * @author Asatryan on 30.07.2021
 **/
class BooksRepositoryContainer(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
) : RepositoryContainer<BooksRepository> {

    override fun repository(): BooksRepository {
        val toBookMapper = ToBookMapper.Base()
        return BooksRepository.Base(
            if (useMocks)
                mockBooksCloudDataSource()
            else
                booksCloudDataSource(),
            BooksCacheDataSource.Base(coreModule.realmProvider, BookDataToDbMapper.Base()),
            BooksCloudMapper.Base(toBookMapper),
            BooksCacheMapper.Base(toBookMapper)
        )
    }

    private fun booksCloudDataSource() = BooksCloudDataSource.Base(
        coreModule.language,
        BooksCloudDataSource.English(booksService(), coreModule.gson),
        BooksCloudDataSource.Russian(coreModule.resourceProvider, coreModule.gson)
    )

    private fun mockBooksCloudDataSource() = if (coreModule.language.isChosenRussian())
        russianBookCloudDataSource()
    else
        BooksCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)

    private fun russianBookCloudDataSource() =
        BooksCloudDataSource.Russian(coreModule.resourceProvider, coreModule.gson)

    private fun booksService() = coreModule.makeService(BooksService::class.java)
}