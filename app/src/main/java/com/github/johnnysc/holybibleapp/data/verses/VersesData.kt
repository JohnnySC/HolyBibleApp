package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.BuildString

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesData : Abstract.DataObject {

    abstract fun <T> map(
        mapper: VersesDataToDomainMapper<T>, book: BuildString, chapterNumber: Int
    ): T

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>, book: BuildString, chapterNumber: Int
        ) = mapper.map(Triple(verses, book, chapterNumber))
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>, book: BuildString, chapterNumber: Int
        ) = mapper.map(e)
    }
}