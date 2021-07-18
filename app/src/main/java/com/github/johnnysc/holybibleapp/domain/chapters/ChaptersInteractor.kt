package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersRepository

/**
 * @author Asatryan on 12.07.2021
 **/
interface ChaptersInteractor {

    suspend fun fetchChapters(): ChaptersDomain

    class Base(
        private val repository: ChaptersRepository,
        private val mapper: ChaptersDataToDomainMapper<ChaptersDomain>
    ) : ChaptersInteractor {
        override suspend fun fetchChapters() = repository.fetchData().map(mapper)
    }
}