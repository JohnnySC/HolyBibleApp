package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersScrollPositionCache
import com.github.johnnysc.holybibleapp.presentation.main.ScrollPosition

/**
 * @author Asatryan on 12.07.2021
 **/
interface ChaptersInteractor : ScrollPosition {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper<ChaptersDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        private val scrollPosition: ChaptersScrollPositionCache
    ) : ChaptersInteractor {
        override suspend fun fetchChapters() = ChaptersAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer
        ).map(mapper)

        override fun scrollPosition() = scrollPosition.chaptersScrollPosition()
        override fun saveScrollPosition(position: Int) =
            scrollPosition.saveChaptersScrollPosition(position)
    }
}