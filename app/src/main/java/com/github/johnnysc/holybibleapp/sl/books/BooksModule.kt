package com.github.johnnysc.holybibleapp.sl.books

import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.books.ToBookMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BookDataToDbMapper
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheDataSource
import com.github.johnnysc.holybibleapp.data.books.cache.BooksCacheMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudDataSource
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksCloudMapper
import com.github.johnnysc.holybibleapp.data.books.cloud.BooksService
import com.github.johnnysc.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksInteractor
import com.github.johnnysc.holybibleapp.presentation.books.*
import com.github.johnnysc.holybibleapp.sl.core.BaseModule

/**
 * @author Asatryan on 15.07.2021
 **/
class BooksModule(
    private val coreModule: CoreModule,
    private val useMocks: Boolean
) : BaseModule<BooksViewModel> {

    override fun getViewModel(): BooksViewModel {
        val uiDataCache = getBooksUiDataCache()
        return BooksViewModel(
            getBooksInteractor(),
            getBooksMapper(uiDataCache),
            getBooksCommunication(),
            uiDataCache,
            coreModule.bookCache,
            coreModule.navigator,
            coreModule.navigationCommunication
        )
    }

    private fun getBooksInteractor() = BooksInteractor.Base(
        getBooksRepository(),
        BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
    )

    private fun getBooksRepository(): BooksRepository {
        val toBookMapper = ToBookMapper.Base()
        return if (useMocks)
            BooksRepository.Mock(
                getMockBooksCloudDataSource(),
                BooksCloudMapper.Base(toBookMapper)
            )
        else
          return  BooksRepository.Base(
                getBooksCloudDataSource(),
                BooksCacheDataSource.Base(coreModule.realmProvider, BookDataToDbMapper.Base()),
                BooksCloudMapper.Base(toBookMapper),
                BooksCacheMapper.Base(toBookMapper)
            )
    }

    private fun getBooksCloudDataSource() =
        BooksCloudDataSource.Base(getBooksService(), coreModule.gson)

    private fun getMockBooksCloudDataSource() =
        BooksCloudDataSource.Mock(coreModule.resourceProvider, coreModule.gson)

    private fun getBooksService() = coreModule.makeService(BooksService::class.java)

    private fun getBooksMapper(uiDataCache: UiDataCache) = BaseBooksDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        uiDataCache
    )

    private fun getBooksUiDataCache() = UiDataCache.Base(
        if (useMocks)
            CollapsedIdsCache.Mock(coreModule.resourceProvider)
        else
            CollapsedIdsCache.Base(coreModule.resourceProvider)
    )

    private fun getBooksCommunication() = BooksCommunication.Base()

}