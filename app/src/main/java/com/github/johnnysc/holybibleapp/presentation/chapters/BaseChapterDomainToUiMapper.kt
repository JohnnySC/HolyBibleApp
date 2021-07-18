package com.github.johnnysc.holybibleapp.presentation.chapters

import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomainToUiMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId
import com.github.johnnysc.holybibleapp.data.chapters.ChapterIdToUiMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChapterDomainToUiMapper(private val mapper: ChapterIdToUiMapper<ChapterUi>) :
    ChapterDomainToUiMapper<ChapterUi> {

    override fun map(data: ChapterId) = data.map(mapper)
}