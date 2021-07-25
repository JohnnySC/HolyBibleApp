package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersData : Abstract.DataObject {

    abstract fun <T> map(mapper: ChaptersDataToDomainMapper<T>, bookName: String): T

    data class Success(
        private val chapters: List<ChapterData>
    ) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>, bookName: String) =
            mapper.map(Pair(chapters, bookName))
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>, bookName: String) =
            mapper.map(e)
    }
}