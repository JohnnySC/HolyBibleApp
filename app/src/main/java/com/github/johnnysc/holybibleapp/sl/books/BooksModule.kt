package com.github.johnnysc.holybibleapp.sl.books

import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.domain.books.BaseBookDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BaseBooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksInteractor
import com.github.johnnysc.holybibleapp.presentation.books.*
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule

/**
 * @author Asatryan on 15.07.2021
 **/
class BooksModule(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
    private val repository: BooksRepository,
    private val clear: () -> Unit
) : BaseModule<BooksViewModel> {

    override fun viewModel(): BooksViewModel {
        val uiDataCache = booksUiDataCache()
        return BooksViewModel(
            interactor(),
            mapper(uiDataCache),
            communication(),
            uiDataCache,
            coreModule.bookCache,
            coreModule.navigator,
            coreModule.navigationCommunication,
            coreModule.resourceProvider,
            clear
        )
    }

    private fun interactor() = BooksInteractor.Base(
        repository,
        BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper()),
        coreModule.scrollPositionCache
    )

    private fun mapper(uiDataCache: UiDataCache) = BaseBooksDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        uiDataCache
    )

    private fun booksUiDataCache() = UiDataCache.Base(
        if (useMocks)
            CollapsedIdsCache.Mock(coreModule.resourceProvider)
        else
            CollapsedIdsCache.Base(coreModule.resourceProvider)
    )

    private fun communication() = BooksCommunication.Base()
}