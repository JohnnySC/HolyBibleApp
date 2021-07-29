package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.ListMapper

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksUi {

    abstract fun map(mapper: ListMapper<BookUi>)

    data class Base(private val books: List<BookUi>) : BooksUi() {
        override fun map(mapper: ListMapper<BookUi>) = mapper.map(books)
    }
}