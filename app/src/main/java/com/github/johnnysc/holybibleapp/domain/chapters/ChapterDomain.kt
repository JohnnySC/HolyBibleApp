package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.data.chapters.ChapterId

/**
 * @author Asatryan on 11.07.2021
 **/
interface ChapterDomain {
    fun <T> map(mapper: ChapterDomainToUiMapper<T>): T

    data class Base(
        private val chapterId: ChapterId,
        private val isFavorite: Boolean
    ) : ChapterDomain {
        override fun <T> map(mapper: ChapterDomainToUiMapper<T>) =
            mapper.map(Pair(chapterId, isFavorite))
    }
}