package com.github.johnnysc.holybibleapp.presentation.books

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksUi {

    abstract fun map(mapper: BooksCommunication)

    data class Base(private val books: List<BookUi>) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
    }
}