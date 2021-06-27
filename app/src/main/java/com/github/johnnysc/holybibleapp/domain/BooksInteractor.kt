package com.github.johnnysc.holybibleapp.domain

import com.github.johnnysc.holybibleapp.data.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.BooksRepository

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksInteractor {

    suspend fun fetchBooks(): BookDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
    ) : BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchBooks().map(mapper)
    }
}