package com.github.johnnysc.holybibleapp.data.chapters.cache

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.github.johnnysc.holybibleapp.data.core.FavoritesList

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCacheMapper :
    Abstract.Mapper.Data<Pair<List<ChapterDb>, FavoritesList>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>) : ChaptersCacheMapper {
        override fun map(data: Pair<List<ChapterDb>, FavoritesList>) = data.let { (chapters, ids) ->
            chapters.map { chapterDb -> chapterDb.map(mapper, ids.isFavorite(chapterDb)) }
        }
    }
}