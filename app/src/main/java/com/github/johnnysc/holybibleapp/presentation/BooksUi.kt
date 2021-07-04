package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {
    data class Base(private val books: List<BookUi>) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
    }
}