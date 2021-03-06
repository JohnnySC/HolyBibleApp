package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.domain.core.Interactor
import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.domain.books.BooksRepository
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.domain.core.ScrollPosition
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterCache
import com.github.johnnysc.holybibleapp.sl.core.Feature

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesInteractor : Interactor {

    suspend fun fetchVerses(): VersesDomain

    fun showNextChapter()

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        scrollPositionCache: ScrollPosition,
        private val chaptersRepository: ChaptersRepository,
        private val chapterCache: ChapterCache,
    ) : Interactor.Abstract(repository, scrollPositionCache), VersesInteractor {

        override fun showNextChapter() {
            chapterCache.save(chapterCache.read() + 1)
            saveScrollPosition(Feature.CHAPTERS, scrollPosition(Feature.CHAPTERS) + 1)
        }

        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            chaptersRepository.fetchData(),
            bookIdContainer,
            chapterCache
        ).map(mapper)
    }
}