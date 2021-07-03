package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.BookDomain
import com.github.johnnysc.holybibleapp.domain.BookDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.ErrorType

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    class Success(
        private val books: List<BookDomain>,
        private val bookMapper: BookDomainToUiMapper
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val booksUi = books.map {
                it.map(bookMapper)
            }
            mapper.map(booksUi)
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(BookUi.Fail(message)))
        }
    }
}