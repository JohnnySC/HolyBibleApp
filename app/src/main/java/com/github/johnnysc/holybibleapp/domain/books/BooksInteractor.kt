package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.core.Interactor
import com.github.johnnysc.holybibleapp.core.ScrollPositionCache
import com.github.johnnysc.holybibleapp.data.books.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksInteractor : Interactor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper<BooksDomain>,
        scrollPosition: ScrollPositionCache
    ) : Interactor.Abstract(booksRepository, scrollPosition, Feature.BOOKS), BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchData().map(mapper)
    }
}