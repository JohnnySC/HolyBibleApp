package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.domain.BookDomain
import com.github.johnnysc.holybibleapp.domain.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.ErrorType

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>) = BooksUi.Success(books, bookMapper)
    override fun map(errorType: ErrorType) = BooksUi.Fail(errorType, resourceProvider)
}