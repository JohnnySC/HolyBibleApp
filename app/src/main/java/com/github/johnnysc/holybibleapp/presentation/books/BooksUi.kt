package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.ChangeFavorite
import com.github.johnnysc.holybibleapp.core.ListMapper

/**
 * @author Asatryan on 26.06.2021
 **/
sealed class BooksUi : ChangeFavorite<Int> {

    abstract fun map(mapper: ListMapper<BookUi>)

    data class Base(private val books: MutableList<BookUi>) : BooksUi() {

        override fun map(mapper: ListMapper<BookUi>) = mapper.map(books)

        override fun changeFavorite(id: Int) {
            val itemToChange = books.find {
                it.matches(id)
            } ?: BookUi.Empty
            val newItem = itemToChange.changeState()
            books[books.indexOf(itemToChange)] = newItem
        }
    }
}