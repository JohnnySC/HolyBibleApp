package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Multiply
import com.github.johnnysc.holybibleapp.core.isFavorite
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudMapper :
    Abstract.Mapper.Data<Pair<List<ChapterCloud>, List<Int>>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>) : ChaptersCloudMapper {
        override fun map(data: Pair<List<ChapterCloud>, List<Int>>) = Multiply().let { multiply ->
            data.first.map { chapterCloud ->
                val isFavorite = data.second.map { id ->
                    multiply.rest(id)
                }.isFavorite(chapterCloud)
                chapterCloud.map(mapper, isFavorite)
            }
        }
    }
}