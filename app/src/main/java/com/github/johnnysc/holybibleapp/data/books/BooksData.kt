package com.github.johnnysc.holybibleapp.data.books

import com.github.johnnysc.holybibleapp.core.Abstract
import kotlin.Exception

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksData : Abstract.DataObject {
    abstract fun <T> map(mapper: BooksDataToDomainMapper<T>): T

    data class Success(private val books: List<BookData>) : BooksData() {
        override fun <T> map(mapper: BooksDataToDomainMapper<T>) = mapper.map(books)

        fun getById(id: Int) = books.find { it.find(id) } ?: BookData.Empty()
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun <T> map(mapper: BooksDataToDomainMapper<T>) = mapper.map(e)
    }
}