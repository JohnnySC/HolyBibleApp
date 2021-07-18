package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudMapper : Abstract.Mapper.Data<List<ChapterCloud>, List<ChapterData>> {

    class Base(private val mapper: ToChapterMapper<ChapterData>) : ChaptersCloudMapper {
        override fun map(data: List<ChapterCloud>) = data.map { chapterCloud ->
            chapterCloud.map(mapper)
        }
    }
}