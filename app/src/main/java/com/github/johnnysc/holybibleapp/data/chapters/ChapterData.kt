package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.DbWrapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDataToDbMapper
import com.github.johnnysc.holybibleapp.data.chapters.cache.ChapterDb
import com.github.johnnysc.holybibleapp.domain.chapters.ChapterDomain

/**
 * @author Asatryan on 11.07.2021
 **/
data class ChapterData(private val chapterId: ChapterId) :
    Abstract.Object.ToDb<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun mapBy(mapper: ChapterDataToDbMapper, db: DbWrapper<ChapterDb>) =
        mapper.mapToDb(chapterId, db)

    override fun map(mapper: ChapterDataToDomainMapper) = mapper.map(chapterId)
}