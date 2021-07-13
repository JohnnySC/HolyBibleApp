package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCacheMapper : Abstract.Mapper.Data<List<ChapterDb>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper) : ChaptersCacheMapper {
        override fun map(data: List<ChapterDb>) = data.map { chapterDb ->
            chapterDb.map(mapper)
        }
    }
}