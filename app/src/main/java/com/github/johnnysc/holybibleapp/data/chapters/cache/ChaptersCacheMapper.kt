package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.isFavorite
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCacheMapper :
    Abstract.Mapper.Data<Pair<List<ChapterDb>, List<Int>>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>) : ChaptersCacheMapper {
        override fun map(data: Pair<List<ChapterDb>, List<Int>>) = data.first.map { chapterDb ->
            chapterDb.map(mapper, data.second.isFavorite(chapterDb))
        }
    }
}