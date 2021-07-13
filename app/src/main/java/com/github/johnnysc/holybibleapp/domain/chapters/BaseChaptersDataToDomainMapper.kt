package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersDataToDomainMapper(
    private val mapper: ChapterDataToDomainMapper
) : ChaptersDataToDomainMapper() {

    override fun map(data: List<ChapterData>) = ChaptersDomain.Success(data.map { chapterData ->
        chapterData.map(mapper)
    })

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}