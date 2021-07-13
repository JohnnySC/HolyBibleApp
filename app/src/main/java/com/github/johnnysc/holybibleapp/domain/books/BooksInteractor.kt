package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.data.books.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.books.BooksRepository

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
    ) : BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchBooks().map(mapper)
    }
}