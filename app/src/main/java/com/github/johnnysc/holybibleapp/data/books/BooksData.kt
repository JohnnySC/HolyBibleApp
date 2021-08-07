package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.books.BooksDataMapper

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksData : Abstract.DataObject {
    abstract fun <T> map(mapper: BooksDataMapper<T>): T

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun <T> map(mapper: BooksDataMapper<T>) = mapper.map(books)
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun <T> map(mapper: BooksDataMapper<T>) = mapper.map(e)
    }
}