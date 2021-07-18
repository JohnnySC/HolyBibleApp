package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.ErrorType

/**
 * @author Asatryan on 27.06.2021
 **/
sealed class BooksDomain {

    abstract fun <T> map(mapper: BooksDomainToUiMapper<T>): T

    data class Success(private val books: List<BookDomain>) : BooksDomain() {
        override fun <T> map(mapper: BooksDomainToUiMapper<T>) = mapper.map(books)
    }

    data class Fail(private val errorType: ErrorType) : BooksDomain() {
        override fun <T> map(mapper: BooksDomainToUiMapper<T>) = mapper.map(errorType)
    }
}