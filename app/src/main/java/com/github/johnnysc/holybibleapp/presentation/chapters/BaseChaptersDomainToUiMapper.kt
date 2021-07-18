package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomain
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomainToUiMapper
import com.github.johnnysc.holybibleapp.core.ResourceProvider

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper<ChapterUi>,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper<ChaptersUi>(resourceProvider) {

    override fun map(data: List<ChapterDomain>) = ChaptersUi.Base(data.map { chapterDomain ->
        chapterDomain.map(mapper)
    })

    override fun map(errorType: ErrorType) =
        ChaptersUi.Base(listOf(ChapterUi.Fail(errorMessage(errorType))))
}