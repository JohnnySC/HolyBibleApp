package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomain

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersData : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    data class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(chapters)
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper) = mapper.map(e)
    }
}