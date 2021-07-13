package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.books.BooksDomain
import kotlin.Exception

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper> {
    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)
    }
    data class Fail(private val e: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(e)
    }
}