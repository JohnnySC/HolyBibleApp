package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.domain.books.BookDomain
import com.github.johnnysc.holybibleapp.domain.books.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.ResourceProvider

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper<BookUi>,
    private val uiDataCache: UiDataCache
) : BooksDomainToUiMapper<BooksUi>(resourceProvider) {

    override fun map(data: List<BookDomain>) = uiDataCache.cache(data.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType) =
        BooksUi.Base(listOf(BookUi.Fail(errorMessage(errorType))))
}