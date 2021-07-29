package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.TextMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChaptersDataToDomainMapper

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChaptersDataToDomainMapper(
    private val mapper: ChapterDataToDomainMapper<ChapterDomain>
) : ChaptersDataToDomainMapper<ChaptersDomain>() {

    override fun map(data: Pair<List<ChapterData>, Abstract.Object<Unit, TextMapper>>) = ChaptersDomain.Success(
        data.first.map { chapterData -> chapterData.map(mapper) }, data.second
    )

    override fun map(e: Exception) = ChaptersDomain.Fail(errorType(e))
}