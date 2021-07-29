package com.github.johnnysc.holybibleapp.domain.books

import com.github.johnnysc.holybibleapp.data.books.BooksDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.books.BooksScrollPositionCache
import com.github.johnnysc.holybibleapp.presentation.main.ScrollPosition

/**
 * @author Asatryan on 27.06.2021
 **/
interface BooksInteractor : ScrollPosition {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper<BooksDomain>,
        private val scrollPosition: BooksScrollPositionCache
    ) : BooksInteractor {
        override suspend fun fetchBooks() = booksRepository.fetchData().map(mapper)
        override fun saveScrollPosition(position: Int) =
            scrollPosition.saveBooksScrollPosition(position)
        override fun scrollPosition() = scrollPosition.booksScrollPosition()
    }
}