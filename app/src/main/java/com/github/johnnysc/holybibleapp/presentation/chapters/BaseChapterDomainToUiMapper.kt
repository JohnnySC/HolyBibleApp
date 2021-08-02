package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import com.github.johnnysc.holybibleapp.data.chapters.ChapterIdToUiMapper
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomainToUiMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper<ChapterUi>) :
    ChapterDomainToUiMapper<ChapterUi> {

    override fun map(data: Pair<ChapterId, Boolean>) = data.first.map(mapper, data.second)
}