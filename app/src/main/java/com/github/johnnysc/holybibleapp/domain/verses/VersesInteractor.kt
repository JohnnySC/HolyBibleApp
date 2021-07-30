package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersScrollPositionCache
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesRepository
import com.github.johnnysc.holybibleapp.data.verses.VersesScrollPositionCache
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterCache
import com.github.johnnysc.holybibleapp.presentation.main.ScrollPosition

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesInteractor : ScrollPosition {

    suspend fun fetchVerses(): VersesDomain

    fun showNextChapter()

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>,
        private val scrollPositionCache: VersesScrollPositionCache,
        private val chaptersRepository: ChaptersRepository,
        private val chapterCache: ChapterCache, //todo move chapter cache and chapter number to repository
        private val chapterScrollPositionCache: ChaptersScrollPositionCache
    ) : VersesInteractor {

        override fun showNextChapter() {
            chapterCache.save(chapterCache.read() + 1)
            chapterScrollPositionCache.saveChaptersScrollPosition(
                chapterScrollPositionCache.chaptersScrollPosition() + 1
            )
        }

        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            chaptersRepository.fetchData(),
            bookIdContainer,
            chapterCache
        ).map(mapper)

        override fun scrollPosition() = scrollPositionCache.versesScrollPosition()
        override fun saveScrollPosition(position: Int) =
            scrollPositionCache.saveVersesScrollPosition(position)
    }
}