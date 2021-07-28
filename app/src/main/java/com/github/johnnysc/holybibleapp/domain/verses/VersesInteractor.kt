package com.github.johnnysc.holybibleapp.domain.verses

import com.github.johnnysc.holybibleapp.core.Read
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.data.books.BooksRepository
import com.github.johnnysc.holybibleapp.data.verses.VersesDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.verses.VersesRepository

/**
 * @author Asatryan on 17.07.2021
 **/
interface VersesInteractor {

    suspend fun fetchVerses(): VersesDomain

    class Base(
        private val repository: VersesRepository,
        private val mapper: VersesDataToDomainMapper<VersesDomain>,
        private val chapterNumber: Read<Int>,
        private val booksRepository: BooksRepository,
        private val bookIdContainer: Read<Int>
    ) : VersesInteractor {

        override suspend fun fetchVerses() = VersesAndBooksDomain(
            repository.fetchData(),
            booksRepository.fetchData(),
            bookIdContainer,
            chapterNumber
        ).map(mapper)
    }
}