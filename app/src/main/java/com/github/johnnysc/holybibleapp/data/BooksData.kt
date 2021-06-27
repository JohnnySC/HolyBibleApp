package com.github.johnnysc.holybibleapp.data

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Book
import com.github.johnnysc.holybibleapp.domain.BooksDomain
import kotlin.Exception

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper>() {
    class Success(private val books: List<Book>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(books)
    }
    class Fail(private val e: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper) = mapper.map(e)
    }
}