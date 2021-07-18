package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChapterDataToDomainMapper
import com.github.johnnysc.holybibleapp.data.chapters.ChapterId

/**
 * @author Asatryan on 11.07.2021
 **/
class BaseChapterDataToDomainMapper : ChapterDataToDomainMapper<ChapterDomain> {
    override fun map(data: ChapterId) = ChapterDomain.Base(data)
}