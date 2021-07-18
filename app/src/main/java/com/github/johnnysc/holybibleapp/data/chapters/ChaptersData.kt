package com.github.johnnysc.holybibleapp.data.chapters

import com.github.johnnysc.holybibleapp.core.Abstract

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersData : Abstract.DataObject {

    abstract fun <T> map(mapper: ChaptersDataToDomainMapper<T>): T

    data class Success(private val chapters: List<ChapterData>) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>) = mapper.map(chapters)
    }

    data class Fail(private val e: Exception) : ChaptersData() {
        override fun <T> map(mapper: ChaptersDataToDomainMapper<T>) = mapper.map(e)
    }
}