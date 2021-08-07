package com.github.johnnysc.holybibleapp.sl.books

import com.github.johnnysc.holybibleapp.domain.books.BooksRepository

/**
 * @author Asatryan on 30.07.2021
 **/
interface BooksRepositoryProvider {

    fun booksRepository(): BooksRepository
}