package com.github.johnnysc.holybibleapp.sl.books

import com.github.johnnysc.holybibleapp.data.books.TestamentTemp
import com.github.johnnysc.holybibleapp.domain.books.*
import com.github.johnnysc.holybibleapp.presentation.books.*
import com.github.johnnysc.holybibleapp.presentation.core.FeatureNavigation
import com.github.johnnysc.holybibleapp.sl.core.BaseModule
import com.github.johnnysc.holybibleapp.sl.core.CoreModule
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 15.07.2021
 **/
class BooksModule(
    private val coreModule: CoreModule,
    private val useMocks: Boolean,
    private val repository: BooksRepository,
    private val clear: () -> Unit,
) : BaseModule<BooksViewModel> {

    override fun viewModel(): BooksViewModel {
        val uiDataCache = booksUiDataCache()
        return BooksViewModel(
            interactor(),
            mapper(uiDataCache),
            communication(),
            uiDataCache,
            coreModule.bookCache,
            FeatureNavigation.Base(
                coreModule.navigator, coreModule.navigationCommunication, Feature.BOOKS
            ),
            coreModule.resourceProvider,
            clear
        )
    }

    private fun interactor(): BooksInteractor.Base {
        val temp = TestamentTemp.Base()
        return BooksInteractor.Base(
            repository,
            BaseBooksDataToDomainMapper(
                BaseBookDataToDomainMapper(),
                temp,
                BookDataMapper.CompareTestament(temp),
                BookDataMapper.SaveTestament(temp)
            ),
            coreModule.scrollPositionCache
        )
    }

    private fun mapper(uiDataCache: UiDataCache) = BaseBooksDomainToUiMapper(
        coreModule.resourceProvider,
        BaseBookDomainToUiMapper(coreModule.resourceProvider),
        uiDataCache
    )

    private fun booksUiDataCache() = UiDataCache.Base(if (useMocks)
        CollapsedIdsCache.Mock(coreModule.resourceProvider)
    else
        CollapsedIdsCache.Base(coreModule.resourceProvider)
    )

    private fun communication() = BooksCommunication.Base()
}