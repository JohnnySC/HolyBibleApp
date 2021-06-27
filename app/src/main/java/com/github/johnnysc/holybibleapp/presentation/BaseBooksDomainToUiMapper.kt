package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.domain.BooksDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.ErrorType

/**
 * @author Asatryan on 27.06.2021
 **/
class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUiMapper {
    override fun map(books: List<Book>) = BooksUi.Success(communication, books)
    override fun map(errorType: ErrorType) = BooksUi.Fail(communication, errorType, resourceProvider)
}