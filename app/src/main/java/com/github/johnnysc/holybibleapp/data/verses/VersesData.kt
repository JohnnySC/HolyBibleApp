package com.github.johnnysc.holybibleapp.data.verses

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.data.books.BookData

/**
 * @author Asatryan on 17.07.2021
 **/
sealed class VersesData : Abstract.DataObject {

    abstract fun <T> map(
        mapper: VersesDataToDomainMapper<T>,
        book: BookData,
        chapterNumber: Int,
        lastChapter: Boolean,
    ): T

    data class Success(private val verses: List<VerseData>) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>,
            book: BookData,
            chapterNumber: Int,
            lastChapter: Boolean,
        ) = mapper.map(Triple(verses, book, Pair(chapterNumber, lastChapter)))
    }

    data class Fail(private val e: Exception) : VersesData() {
        override fun <T> map(
            mapper: VersesDataToDomainMapper<T>,
            book: BookData,
            chapterNumber: Int,
            lastChapter: Boolean,
        ) = mapper.map(e)
    }
}