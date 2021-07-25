package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository

/**
 * @author Asatryan on 12.07.2021
 **/
interface ChaptersInteractor {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper<ChaptersDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>
    ) : ChaptersInteractor {
        override suspend fun fetchChapters() = ChaptersAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer
        ).map(mapper)
    }
}