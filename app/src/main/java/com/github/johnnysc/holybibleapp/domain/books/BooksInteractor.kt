package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.domain.core.Interactor
import com.github.johnnysc.holybibleapp.domain.core.ScrollPosition

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksInteractor : Interactor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataMapper<BooksDomain>,
        scrollPosition: ScrollPosition
    ) : Interactor.Abstract(booksRepository, scrollPosition), BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchData().map(mapper)
    }
}