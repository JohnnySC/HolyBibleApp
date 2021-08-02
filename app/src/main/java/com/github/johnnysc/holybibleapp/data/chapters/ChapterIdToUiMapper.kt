package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.ResourceProvider
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterUi

/**
 * @author Asatryan on 13.07.2021
 **/
interface ChapterIdToUiMapper<T> {
    fun map(realId: Int, generatedId: Int, isFavorite: Boolean): T

    class Base(private val resourceProvider: ResourceProvider) : ChapterIdToUiMapper<ChapterUi> {
        override fun map(realId: Int, generatedId: Int, isFavorite: Boolean) =
            ChapterUi.Base(
                realId,
                generatedId,
                resourceProvider.string(R.string.chapter_number, realId),
                isFavorite
            )
    }
}