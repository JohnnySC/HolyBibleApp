package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.R
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
    override fun map(books: List<BookDomain>) = BooksUi.Base(books.map {
        it.map(bookMapper)
    })

    override fun map(errorType: ErrorType): BooksUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Base(listOf(BookUi.Fail(message)))
    }
}