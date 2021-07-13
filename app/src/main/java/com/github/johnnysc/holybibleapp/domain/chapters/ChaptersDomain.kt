package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.presentation.chapters.ChaptersUi

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersDomain : Abstract.Object<ChaptersUi, ChaptersDomainToUiMapper> {

    data class Success(
        private val chapters: List<ChapterDomain>
    ) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper) = mapper.map(chapters)
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun map(mapper: ChaptersDomainToUiMapper) = mapper.map(errorType)
    }
}