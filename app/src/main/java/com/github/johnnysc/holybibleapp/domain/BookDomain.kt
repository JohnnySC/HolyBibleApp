package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * todo rename to BooksDomain by lead
 * @author Asatryan on 26.06.2021
 **/
sealed class BookDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {
    class Success(private val books: List<Book>) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }

    class Fail(private val e: Exception) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(
            when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
