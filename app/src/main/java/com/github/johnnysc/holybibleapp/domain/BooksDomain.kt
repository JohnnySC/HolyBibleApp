package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.BookData
import com.github.johnnysc.holybibleapp.data.BookDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.TestamentTemp
import com.github.johnnysc.holybibleapp.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Asatryan on 27.06.2021
 **/
sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {
    data class Success(private val books: List<BookDomain>) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(books)
    }
    data class Fail(private val errorType: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper) = mapper.map(errorType)
    }
}