package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterUi

/**
 * @author Asatryan on 13.07.2021
 **/
interface ChapterIdToUiMapper : Abstract.Mapper {
    fun map(generatedId: Int, realId: Int): ChapterUi

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper {
        override fun map(generatedId: Int, realId: Int) =
            ChapterUi.Base(generatedId, resourceProvider.getString(R.string.chapter_number, realId))
    }
}