package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.domain.ErrorType

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    class Success(
        private val communication: BooksCommunication,
        private val books: List<Book>
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) = communication.show(books)
    }

    class Fail(
        private val communication: BooksCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider //ErrorTypeHandler
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when (errorType) { //todo move to other class
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getString(messageId))
        }

    }
}