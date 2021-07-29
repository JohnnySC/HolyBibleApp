package com.github.johnnysc.holybibleapp.data.books

/**
 * @author Asatryan on 28.07.2021
 **/
interface BooksScrollPositionCache {

    fun saveBooksScrollPosition(position: Int)
    fun booksScrollPosition(): Int
}