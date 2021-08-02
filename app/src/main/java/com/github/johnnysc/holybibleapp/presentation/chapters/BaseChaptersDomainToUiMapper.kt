package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.core.TextMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomain
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChaptersDomainToUiMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersDomainToUiMapper(
    private val mapper: ChapterDomainToUiMapper<ChapterUi>,
    resourceProvider: ResourceProvider
) : ChaptersDomainToUiMapper<ChaptersUi>(resourceProvider) {

    override fun map(data: Pair<List<ChapterDomain>, Abstract.Object<Unit, TextMapper>>) =
        ChaptersUi.Base(
            ArrayList(
                data.first.map { chapterDomain -> chapterDomain.map(mapper) }), data.second
        )

    override fun map(errorType: ErrorType) = errorMessage(errorType).let { error ->
        ChaptersUi.Base(
            mutableListOf(ChapterUi.Fail(error)),
            object : Abstract.Object<Unit, TextMapper> {
                override fun map(mapper: TextMapper) = mapper.map(error)
            })
    }
}