package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper
import com.github.johnnysc.holybibleapp.data.core.FavoritesList

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudMapper :
    Abstract.Mapper.Data<Pair<List<ChapterCloud>, FavoritesList>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>, private val multiply: Multiply) :
        ChaptersCloudMapper {
        override fun map(data: Pair<List<ChapterCloud>, FavoritesList>) =
            data.let { (chapters, ids) ->
                chapters.map { chapterCloud ->
                    val isFavorite = ids.isFavorite(chapterCloud) { id -> multiply.rest(id) }
                    chapterCloud.map(mapper, isFavorite)
                }
            }
    }
}