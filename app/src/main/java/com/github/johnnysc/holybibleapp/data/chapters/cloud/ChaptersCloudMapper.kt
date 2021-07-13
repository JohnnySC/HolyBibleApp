package com.github.johnnysc.holybibleapp.data.chapters.cloud

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.chapters.ChapterData
import com.github.johnnysc.holybibleapp.data.chapters.ToChapterMapper

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChaptersCloudMapper : Abstract.Mapper {

    fun map(chapters: List<ChapterCloud>, bookId: Int): List<ChapterData>

    class Base(private val mapper: ToChapterMapper) : ChaptersCloudMapper {
        override fun map(chapters: List<ChapterCloud>, bookId: Int) = chapters.map { chapterCloud ->
            chapterCloud.map(mapper)
        }
    }
}