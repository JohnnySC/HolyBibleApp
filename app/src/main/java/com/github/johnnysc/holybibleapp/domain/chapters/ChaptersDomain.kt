package com.github.johnnysc.holybibleapp.domain.chapters

import com.github.johnnysc.holybibleapp.core.ErrorType
import com.github.johnnysc.holybibleapp.domain.books.BookDomain

/**
 * @author Asatryan on 11.07.2021
 **/
sealed class ChaptersDomain {

    abstract fun <T> map(mapper: ChaptersDomainToUiMapper<T>): T

    data class Success(
        private val chapters: List<ChapterDomain>, private val bookName: BookDomain,
    ) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) =
            mapper.map(Pair(chapters, bookName))
    }

    data class Fail(private val errorType: ErrorType) : ChaptersDomain() {
        override fun <T> map(mapper: ChaptersDomainToUiMapper<T>) = mapper.map(errorType)
    }
}